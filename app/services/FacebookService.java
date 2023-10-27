package services;

import beans.facebook.UserBean;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class FacebookService {

    public static UserBean getMe(String tokenUser) {
        FacebookClient facebookClient = new DefaultFacebookClient(tokenUser, Version.LATEST);
        User user = facebookClient.fetchObject("me", User.class);
        UserBean userBean = new UserBean();
        userBean.id = user.getId();
        userBean.name = user.getName();
        return userBean;
    }

    /**
     * https://developers.facebook.com/docs/facebook-login/guides/access-tokens#pagetokens
     * */
    public static JsonNode getPagesAccessTokens(String accessToken, String userId) throws IOException {
        URL url = new URL("https://graph.facebook.com/"+userId+"/accounts?access_token="+accessToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(con.getInputStream());
    }
    /**
     * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
     * */
    public static JsonNode setWeebhookFor(String pageId, String pageToken) throws IOException {
        URL url = new URL("https://graph.facebook.com/"+pageId+"/subscribed_apps?subscribed_fields=leadgen&access_token="+pageToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(con.getInputStream());
        }catch (Exception e){
            return mapper.readTree(con.getErrorStream());
        }
    }
    public static JsonNode getPageInstaledApps(String pageId, String pageToken) throws IOException {
        URL url = new URL("https://graph.facebook.com/"+pageId+"/subscribed_apps?access_token="+pageToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(con.getInputStream());
        }catch (Exception e){
            return mapper.readTree(con.getErrorStream());
        }
    }
}
