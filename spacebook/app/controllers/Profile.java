package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import play.db.jpa.Blob;

public class Profile extends Controller {

    public static void index() {
    	if(session.get("logged_in_userid") != null)
    	{	
    		String userId = session.get("logged_in_userid");
    		User user = User.findById(Long.parseLong(userId));
    		//String name = user.firstName;
    		if(user.firstName == null)
    		  {
    			  user.firstName = "Unknown";
    		  }
    		if(user.lastName == null)
    		  {
    			  user.lastName = "Unknown";
    		  }
    		if(user.age == null)
    		  {
    			  user.age = "Unknown";
    		  }
    		if(user.nationality == null)
    		  {
    			  user.nationality = "Unknown";
    		  }
    		render(user);
    	}
    	else
    	{
    		Accounts.index();
    	}
    }

    public static void changeStatus(String profileText)
    {
    	String userId = session.get("logged_in_userid");
    	User user = User.findById(Long.parseLong(userId));
    	user.statusText = profileText;
    	user.save();
    	Logger.info("Status changed to " + profileText);
    	index();
    }
    
    public static void getPicture(Long id)
    {
  	  	User user = User.findById(id);
  	  	Blob picture = user.profilePicture;
  	  	if (picture.exists())
  	  	{
  	  		response.setContentTypeIfNotSet(picture.type());
  	  		renderBinary(picture.get());
  	  	}
    }
    
    public static void uploadPicture(Long id, Blob picture)
    {
    	User user = User.findById(id);
    	user.profilePicture = picture;
        user.save();
        index();
    }
    
    public static void editProfile()
    {
    	if (session.get("logged_in_userid") != null)
    	{
    		render();
    	}
    	else
    	{
    		Accounts.index();
    	}
    }
    
    public static void edit(String firstName, String lastName, String age, String password1, String password2)
    {
    	if(password1.equals(password2))
    	{
    		String userId = session.get("logged_in_userid");
        	User user = User.findById(Long.parseLong(userId));
        	user.editUser(firstName, lastName, age, password1);
        	user.save();
        	Logger.info("New name:" + user.firstName + " " + user.lastName + ", new age:" + age + ", new password: " + user.password);
        	index();
    	}
    	else
    	{
    		String detail = "Passwords do not match";
    		Errors.index(detail, "/profile/editprofile");
    	}
    }
}