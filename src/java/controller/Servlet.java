/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.action.Action;
import controller.action.getactions.personal.user.Basket;
import controller.action.postactions.Login;
import controller.action.getactions.HomePage;
import controller.action.getactions.ChangeLanguage;
import controller.action.getactions.Contacts;
import controller.action.getactions.Info;
import controller.action.postactions.CreateAccount;
import controller.action.getactions.personal.LogOut;
import controller.action.getactions.LoginRequest;
import controller.action.getactions.MainMenu;
import controller.action.getactions.personal.Order;
import controller.action.getactions.personal.user.Orders;
import controller.action.getactions.personal.Profile;
import controller.action.getactions.personal.Settings;
import controller.action.getactions.SignUp;
import controller.action.getactions.personal.admin.Administration;
import controller.action.getactions.personal.admin.GetAllOrders;
import controller.action.getactions.personal.admin.GetUsers;
import controller.action.getactions.personal.user.UserAccount;
import controller.action.postactions.personal.user.AddToBasket;
import controller.action.postactions.personal.user.BasketConfirmation;
import controller.action.postactions.personal.user.ClearBasket;
import controller.action.postactions.personal.admin.AdminChangePassword;
import controller.action.postactions.personal.admin.AdminSaveChanges;
import controller.action.postactions.personal.user.UserChangePassword;
import controller.action.postactions.personal.user.UserSaveChanges;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sasha
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    
    private final Map<String, Action> getActions = new HashMap<>();
    
    private final Map<String, Action> postActions = new HashMap<>();
    
    @Override
    public synchronized void init(ServletConfig config) throws ServletException {
        getActions.put("home", new HomePage());
        getActions.put("logout", new LogOut());
        getActions.put("changeLanguage", new ChangeLanguage());
        getActions.put("loginRequest", new LoginRequest());
        getActions.put("signUp", new SignUp());
        getActions.put("mainMenu", new MainMenu());
        getActions.put("profile", new Profile());
        getActions.put("orders", new Orders());
        getActions.put("getOrder", new Order());
        getActions.put("account", new UserAccount());
        getActions.put("settings", new Settings());
        getActions.put("basket", new Basket());
        getActions.put("info", new Info());
        getActions.put("contacts", new Contacts());
        getActions.put("administration", new Administration());
        getActions.put("getUsers", new GetUsers());
        getActions.put("getAllOrders", new GetAllOrders());
        postActions.put("login", new Login());
        postActions.put("createAccount", new CreateAccount());
        postActions.put("userSaveChanges", new UserSaveChanges());
        postActions.put("adminSaveChanges", new AdminSaveChanges());
        postActions.put("userChangePassword", new UserChangePassword());
        postActions.put("adminChangePassword", new AdminChangePassword());
        postActions.put("addToBasket", new AddToBasket());
        postActions.put("basketConfirm", new BasketConfirmation());
        postActions.put("clearBasket", new ClearBasket());
    }
    
    @Override
    protected synchronized void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType ("text/html;charset=utf-8");
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        String actionKey = request.getParameter("getAction");
        if (actionKey == null) {
            actionKey = "home";
        }
        saveActionForRedirect(actionKey, request);
        Action action = getActions.get(actionKey);
        action.execute(request, response);
    }
    
    @Override
    protected synchronized void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("Cp1251");
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        String actionKey = request.getParameter("postAction");
        Action action = postActions.get(actionKey);
        action.execute(request, response);
    }
    
    private void saveActionForRedirect(String actionKey, HttpServletRequest request) {
        if (!actionKey.equals("loginRequest") && !actionKey.equals("signUp") 
                && !actionKey.equals("changeLanguage")) {
            request.getSession().setAttribute("lastAction", actionKey);
        }
    }
    
}
