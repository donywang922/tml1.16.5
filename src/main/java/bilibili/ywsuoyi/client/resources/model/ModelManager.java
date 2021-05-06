package bilibili.ywsuoyi.client.resources.model;

import bilibili.ywsuoyi.client.resources.animation.InnerAnimation;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ModelManager {
    public static ResourceManager resourceManager = MinecraftClient.getInstance().getResourceManager();
    public static final DefaultedList<ModelList> model_pack_list = DefaultedList.of();
    public static final Map<Identifier, JsonModel> maid_model_list = Maps.newHashMap();
    public static final Map<Identifier, Identifier> maid_texture_list = Maps.newHashMap();
    public static final Map<Identifier, List<Object>> maid_animations_list = Maps.newHashMap();
    public static final Map<Identifier, Object> CUSTOM_ANIMATION_MAP = Maps.newHashMap();
    public static final Logger logger = LogManager.getLogger();
    public static final Marker MARKER = MarkerManager.getMarker("ResourcesLoader");
    public static final Gson GSON = (new GsonBuilder()).registerTypeAdapter(Identifier.class, new Identifier.Serializer()).create();
    public static final ScriptEngine scriptEngine = getScriptEngine();

    private static ScriptEngine getScriptEngine() {
        Matcher matcher = Pattern.compile("^\\d\\.\\d\\.\\d_(\\d+)$").matcher(System.getProperty("java.version"));
        if (matcher.find()) {
            int version = Integer.parseInt(matcher.group(1));
            if (version < 60) {
                return new ScriptEngineManager().getEngineByName("nashorn");
            }
        }
        return new ScriptEngineManager(null).getEngineByName("nashorn");
    }

    public static void init() throws IOException {
        InnerAnimation.init();

        model_pack_list.clear();
        maid_model_list.clear();
        maid_texture_list.clear();
        maid_animations_list.clear();

        System.out.println(resourceManager.getAllNamespaces());
        for (String s : resourceManager.getAllNamespaces()) {
            if (resourceManager.containsResource(new Identifier(s, "maid_model.json"))) {
                model_pack_list.add(
                        GSON.fromJson(
                                JsonHelper.deserialize(
                                        GSON, new BufferedReader(
                                                new InputStreamReader(
                                                        resourceManager.getResource(
                                                                new Identifier(s, "maid_model.json")
                                                        ).getInputStream()
                                                        , StandardCharsets.UTF_8)
                                        ),
                                        JsonElement.class
                                ),
                                ModelList.class
                        ).decorate()
                );
            }
        }
        for (ModelList modelList : model_pack_list) {
            for (ModelInfo modelInfo : modelList.getModelList()) {
                JsonModel jsonModel = GSON.fromJson(
                        new InputStreamReader(
                                resourceManager.getResource(modelInfo.getModel())
                                        .getInputStream()
                                , StandardCharsets.UTF_8
                        ), JsonModel.class
                );
                if (!jsonModel.getFormatVersion().equals("1.10.0"))// 判断是不是 1.10.0 版本基岩版模型文件
                    logger.warn(MARKER, "{} model version is not 1.10.0", modelInfo.getModel());
                else if (jsonModel.getGeometryModel() == null)// 如果 model 字段为空
                    logger.warn(MARKER, "{} model file don't have model field", modelInfo.getModel());// 日志给出提示
                else {
                    maid_model_list.put(modelInfo.getModelId(), jsonModel);
                    maid_texture_list.put(modelInfo.getModelId(), modelInfo.getTexture());
                    maid_animations_list.put(modelInfo.getModelId(), loadAnimation(modelInfo.getAnimation()));
                }
            }
        }
    }

    public static List<Object> loadAnimation(List<Identifier> list) {
        List<Object> animations = Lists.newArrayList();
        if (!list.isEmpty()) {
            for (Identifier id : list) {
                if (id != null) {
                    if (CUSTOM_ANIMATION_MAP.containsKey(id)) {
                        animations.add(CUSTOM_ANIMATION_MAP.get(id));
                    } else if (InnerAnimation.getMaidEntityInnerAnimation().containsKey(id)) {
                        animations.add(InnerAnimation.getMaidEntityInnerAnimation().get(id));
                    } else if (InnerAnimation.getLivingEntityInnerAnimation().containsKey(id)) {
                        animations.add(InnerAnimation.getLivingEntityInnerAnimation().get(id));
                    } else {
                        try {
                            Bindings bindings = scriptEngine.createBindings();
                            InputStream stream = resourceManager.getResource(id).getInputStream();
                            Object scriptObject = scriptEngine.eval(IOUtils.toString(stream, StandardCharsets.UTF_8), bindings);
                            CUSTOM_ANIMATION_MAP.put(id, scriptObject);
                            animations.add(scriptObject);
                        } catch (IOException | ScriptException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return animations;
        }
        return null;
    }
}
