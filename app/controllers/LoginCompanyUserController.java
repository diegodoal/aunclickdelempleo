package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Victor on 29/01/2015.
 */
public class LoginCompanyUserController extends Controller {

    public static Result blank(){
        return ok(views.html.login_company_user.login.render());
    }

}
