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

    private static final String META_GRAPH_API_HOST = "https://graph.facebook.com/";
    private static final String SUBSCRIBED_APPS_URL = "/subscribed_apps";

    /**
     * Retorna informacoes do usuario logado
     * https://developers.facebook.com/tools/explorer/?method=GET&path=me%3Ffields%3Did%2Cname%2Cbirthday&version=v18.0
     */
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
        URL url = new URL(META_GRAPH_API_HOST +userId+"/accounts?access_token="+accessToken);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(con.getInputStream());
    }
    /**
     * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
     * POST: ira iniciar o listener de webhook
     * */
    public static JsonNode postSubscribe(String pageId, String pageToken) throws IOException {
        String link =  META_GRAPH_API_HOST + pageId + SUBSCRIBED_APPS_URL + "?subscribed_fields=leadgen,feed,messages&access_token=" + pageToken;
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(con.getInputStream());
        }catch (Exception e){
            return mapper.readTree(con.getErrorStream());
        }
    }
    /**
     * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
     * GET: ira retornar informacoes de listeners ativos
     * */
    public static JsonNode getSubscribe(String pageId, String pageToken) throws IOException {
        String link =  META_GRAPH_API_HOST + pageId + SUBSCRIBED_APPS_URL + "?access_token=" + pageToken;
        URL url = new URL(link);
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
