package controllers;

import play.mvc.Controller;
import play.mvc.Security;
import security.AppSecurity;

@Security.Authenticated(AppSecurity.class)
public class Usuarios extends Controller {
}
