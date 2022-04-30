package home.bluewhale.json_producer_consumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import home.bluewhale.json_producer_consumer.enums.UrlType;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "cameraAggregation")
public class CameraAggregation {

    @JsonApiId
    private long id;

    @JsonProperty("urlType")
    private UrlType urlType;

    @JsonProperty("videoUrl")
    private String videoUrl;

    @JsonProperty("value")
    private String value;

    @JsonProperty("ttl")
    private int ttl;

    @JsonCreator
    public CameraAggregation(@JsonProperty("id") long id,
                             @JsonProperty("sourceDataUrl") SourceData sourceDataUrl,
                             @JsonProperty("tokenDataUrl") TokenData tokenDataUrl) {
        this.id = id;

        if (sourceDataUrl != null) {
            urlType = sourceDataUrl.getUrlType();
            videoUrl = sourceDataUrl.getVideoUrl();
        }

        if (tokenDataUrl != null) {
            value = tokenDataUrl.getValue();
            ttl = tokenDataUrl.getTtl();
        }
    }
}
