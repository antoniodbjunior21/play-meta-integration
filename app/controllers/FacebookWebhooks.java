package controllers;

import beans.facebook.FBWebhookDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.FacebookConfiguracao;
import models.FacebookWebhook;
import models.Usuario;
import play.data.DynamicForm;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;
import services.FacebookService;
import utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FacebookWebhooks extends Controller {

    @Transactional
    public Result user() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String token = dynamicForm.get("token");
            return ok(Json.toJson(FacebookService.getMe(token)));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result validacaoWebhook() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String verifyToken = dynamicForm.get("hub.verify_token");
            String challenge = dynamicForm.get("hub.challenge");
            System.out.println(verifyToken);
            System.out.println(challenge);
            return ok(challenge);
        } catch (Exception e) {
            return badRequest();
        }
    }

    /**
     * https://developers.facebook.com/docs/graph-api/webhooks/getting-started/webhooks-for-leadgen
     */
    @Transactional
    public Result webhook() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = request().body().asJson();
            FacebookWebhook webhook = new FacebookWebhook();
            webhook.setContent(jsonNode.toString());
            webhook.alterar();
            System.out.println("received");
            return ok();
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result recebidos() {
        try {
            List<FacebookWebhook> webhooks = FacebookWebhook.buscar();
            ObjectMapper mapper = new ObjectMapper();
            List<FBWebhookDTO> dtos = new ArrayList<>();
            for (FacebookWebhook webhook : webhooks) {
                try {
                    FBWebhookDTO dto = Json.mapper().readValue(webhook.getContent(), FBWebhookDTO.class);
                    dto.id = webhook.getId();
                    dtos.add(dto);
                } catch (Exception ignored) {
                }
            }
            System.out.println("received new "+ DateUtil.formataTimestamp(new Date()));
            return ok(Json.toJson(dtos));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result excluirTodos() {
        try {
            List<FacebookWebhook> webhooks = FacebookWebhook.buscar();
            for (FacebookWebhook webhook : webhooks) {
                webhook.excluir();
            }
            return redirect(routes.FacebookWebhooks.recebidos());
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result excluir() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            Long id = Long.valueOf(dynamicForm.get("id"));
            FacebookWebhook facebookWebhook = (FacebookWebhook) FacebookWebhook.buscarPorId(FacebookWebhook.class, id);
            facebookWebhook.excluir();
            return redirect(routes.FacebookWebhooks.recebidos());
        } catch (Exception e) {
            return badRequest();
        }
    }


    @Transactional
    public Result pagesTokens() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String accessToken = dynamicForm.get("accessToken");
            String userId = dynamicForm.get("userId");
            return ok(FacebookService.getPagesAccessTokens(accessToken, userId));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result setWebhook() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String pageId = dynamicForm.get("pageId");
            String pageToken = dynamicForm.get("pageToken");
            return ok(FacebookService.setWeebhookFor(pageId, pageToken));
        } catch (Exception e) {
            return badRequest();
        }
    }

    @Transactional
    public Result instaledApps() {
        try {
            DynamicForm dynamicForm = DynamicForm.form().bindFromRequest();
            String pageId = dynamicForm.get("pageId");
            String pageToken = dynamicForm.get("pageToken");
            return ok(FacebookService.getPageInstaledApps(pageId, pageToken));
        } catch (Exception e) {
            return badRequest();
        }
    }
}
