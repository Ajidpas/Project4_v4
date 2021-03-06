/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.getactions;

import controller.action.Action;
import controller.action.LanguageBlock;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Login request
 * @author Sasha
 */
public class LoginRequest extends Action {

    /**
     * Show login page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    public void doExecute() throws ServletException, IOException {
        saveURIForRedirect();
        request.setAttribute("title", "login.text.title");
        new LanguageBlock().execute(request, response);
        request.getRequestDispatcher("/view/login.jsp").include(request, response);
    }
    
    /**
     * Save URI before go to login page for coming back after authorization
     */
    private void saveURIForRedirect() {
        String from = request.getParameter("from");
        session.setAttribute("from", from);
    }
    
}
