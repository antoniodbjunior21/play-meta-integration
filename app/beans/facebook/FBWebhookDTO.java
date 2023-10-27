package beans.facebook;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FBWebhookDTO {
   public Long id;
   public List<FBWebhookEntry> entry;
   public String object;
}
