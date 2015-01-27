package controllers;

import play.*;

import play.mvc.*;

import java.util.*;

import models.*;

public class Members extends Controller {

    public static void index() {
        if(session.get("logged_in_userid") != null)
    	{
        	List<User> users = User.findAll();
        	String userId = session.get("logged_in_userid");
        	User user = User.findById(Long.parseLong(userId));
        	users.remove(user);
        	render(users);
        }
        else
    	{
        	Accounts.index();
    	}
    }
    
    public static void follow(long id)
    {
    	User friend = User.findById(id);    	
    	String userId = session.get("logged_in_userid");
    	User me = User.findById(Long.parseLong(userId));
    	me.beFriend(friend);
    	Home.index();
    }
}