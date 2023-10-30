package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import security.AppSecurity;

@Security.Authenticated(AppSecurity.class)
public class Dashboard extends Controller {

    @Transactional
    public Result abrirDashboard() {
        try {
            return ok(views.html.dashboard.render());
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }
    }

}
