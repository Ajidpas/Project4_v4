/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.action.getactions.personal;

import controller.action.ConcreteLink;
import controller.action.getactions.GetAction;
import controller.action.getactions.HomePage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import model.entity.Admin;
import model.entity.User;

/**
 * Personal profile
 * @author Sasha
 */
public class Profile extends GetAction {

    /**
     * Output user profile page
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doExecute() throws ServletException, IOException {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            goToPage("profile.text.title", "/view/person/user/profile.jsp");
            return;
        }
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin != null) {
            goToPage("profile.text.title", "/view/person/admin/profile.jsp");
            return;
        }
        sendRedirect(null, "login.errormessage.loginplease", "home");
    }

    /**
     * Get array list of link chain direct to current page (in fact this method 
     * gets link chain of its' previous page, add its' own link and return 
     * created array list)
     * 
     * @return array list of links
     */
    @Override
    public List<ConcreteLink> getLink() {
        List<ConcreteLink> links = new ArrayList<>();
        links.addAll(new HomePage().getLink());
        String linkValue = "/servlet?getAction=profile";
        String linkName = "profile.text.title";
        ConcreteLink concreteLink = new ConcreteLink(linkValue, linkName);
        links.add(concreteLink);
        return links;
    }
    
}
