package home.bluewhale.json_producer_consumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import home.bluewhale.json_producer_consumer.enums.UrlType;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "sourceData")
public class SourceData {

    @JsonProperty("urlType")
    private UrlType urlType;
    @JsonProperty("videoUrl")
    private String videoUrl;

    @JsonCreator
    public SourceData(@JsonProperty("urlType") UrlType urlType,
                      @JsonProperty("videoUtl") String videoUrl) {
        this.urlType = urlType;
        this.videoUrl = videoUrl;
    }
}
