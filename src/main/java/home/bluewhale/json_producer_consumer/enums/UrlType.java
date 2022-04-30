package home.bluewhale.json_producer_consumer.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum UrlType {
    @JsonProperty("LIVE") LIVE,
    @JsonProperty("ARCHIVE") ARCHIVE;
}
