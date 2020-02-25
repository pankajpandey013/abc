/**
 * 
 */
package client.abc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import client.abc.util.UserInfo;

/**
 * @author pankaj
 *
 */
@Controller
@RequestMapping("/")
public class AbcController {
	
	@Autowired 
	HttpSession session;

	private String appMode = "DEV";
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	

    @RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public String loginError(Model model) {
    	
        model.addAttribute("errorMessage", "Login attempt failed");
        return "login";
    }
    @RequestMapping(value = "/loginNoAccess", method = RequestMethod.GET)
    public String loginNoAccess(Model model) {
    	
        model.addAttribute("errorMessage", "You do not have access to this app. Please contact admin. ");
        SecurityContextHolder.getContext().setAuthentication(null);
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return "redirect:/login";
    }


    @RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session) {
        // read principal out of security context and set it to session
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        validatePrinciple(authentication.getPrincipal());
        UserInfo loggedInUser = ((UserInfo) authentication.getPrincipal());
        if (!loggedInUser.getUserName().contains("ABC")) {
        	return "redirect:/loginNoAccess";
        } 
        //appData.setAssignedUser(loggedInUser.getUserId());
        model.addAttribute("currentUser", loggedInUser.getUserName());
        session.setAttribute("userId", loggedInUser.getUserId());
        return "redirect:index";
    }
    
    private void validatePrinciple(Object principal) {
        if (!(principal instanceof UserInfo)) {
            throw new  IllegalArgumentException("Principal can not be null!");
        }
    }



}
