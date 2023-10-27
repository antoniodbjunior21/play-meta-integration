package beans.facebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBWebhookEntry {
    public String time;
    
    @JsonProperty("changed_fields")
    public List<String> changedFields;
    public String id;
    public String uid;
}
