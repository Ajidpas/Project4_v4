/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.action.Action;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 * Servlet class
 * @author Sasha
 */
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    
    /** log4j logger */
    static final Logger LOGGER = Logger.getLogger(Servlet.class);
    
    /**
     * Do get servlet method
     * @param request http servlet request
     * @param response http servlet response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected synchronized void doGet(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        String actionKey = request.getParameter("getAction");
        if (actionKey == null) {
            actionKey = "home";
        }
        LOGGER.info("Get action was called: " + actionKey);
        saveActionForRedirect(actionKey, request);
        Action action = new ActionFactory().getGetAction(actionKey);
        action.execute(request, response);
        LOGGER.info("Get action was performed: " + actionKey);
    }
    
    /**
     * Do post servlet method
     * @param request http servlet request
     * @param response http servlet response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected synchronized void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("windows-1251");
        response.setCharacterEncoding("windows-1251");
        response.setContentType("text/html");
        String actionKey = request.getParameter("postAction");
        if (actionKey == null) {
            doGet(request, response);
            return;
        }
        LOGGER.info("Post action was called: " + actionKey);
        Action action = new ActionFactory().getPostAction(actionKey);
        action.execute(request, response);
        LOGGER.info("Post action was performed: " + actionKey);
    }
    
    /**
     * Save last action to back to the last page
     * @param actionKey action name
     * @param request http servlet request
     */
    private void saveActionForRedirect(String actionKey, HttpServletRequest request) {
        if (!actionKey.equals("loginRequest") && !actionKey.equals("signUp") 
                && !actionKey.equals("changeLanguage")) {
            request.getSession().setAttribute("lastAction", actionKey);
        }
    }
    
}
