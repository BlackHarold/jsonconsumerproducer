package home.bluewhale.json_producer_consumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

import java.net.URL;

@Data
@JsonApiResource(type = "Camera")
public class Camera {

    @JsonApiId
    private long id;

    @JsonProperty("sourceDataUrl")
    private URL sourceDataUrl;

    @JsonProperty("tokenDataUrl")
    private URL tokenDataUrl;

    @JsonCreator
    public Camera(@JsonProperty("id") long id,
                  @JsonProperty("sourceDataUrl") URL sourceDataUrl,
                  @JsonProperty("tokenDataUrl") URL tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }
}
