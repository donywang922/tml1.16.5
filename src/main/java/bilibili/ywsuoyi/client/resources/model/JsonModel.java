package bilibili.ywsuoyi.client.resources.model;

import com.google.gson.annotations.SerializedName;

public class JsonModel {
    @SerializedName("format_version")
    private String formatVersion;

    @SerializedName("geometry.model")
    private JsonModelPart geometryModel;

    public String getFormatVersion() {
        return formatVersion;
    }

    public JsonModelPart getGeometryModel() {
        return geometryModel;
    }

    @Override
    public String toString() {
        return
                "CustomModelPOJO{" +
                        "format_version = '" + formatVersion + '\'' +
                        ",geometry.model = '" + geometryModel + '\'' +
                        "}";
    }
}
