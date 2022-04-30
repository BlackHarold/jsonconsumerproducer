package home.bluewhale.json_producer_consumer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.katharsis.resource.annotations.JsonApiResource;
import lombok.Data;

@Data
@JsonApiResource(type = "tokenData")
public class TokenData {

    @JsonProperty("value")
    private String value;
    @JsonProperty("ttl")
    private int ttl;

    @JsonCreator
    public TokenData(@JsonProperty("value") String value,
                     @JsonProperty("ttl") int ttl) {
        this.value = value;
        this.ttl = ttl;
    }
}
