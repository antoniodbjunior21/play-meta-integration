package beans.facebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBWebhookValue {
    public String verb;

    @JsonProperty("object_id")
    public String object_id;
}
