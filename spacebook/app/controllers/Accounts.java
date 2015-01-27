package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import java.util.ArrayList;

public class Accounts extends Controller
{
	public static void signup()
	{
		if (session.get("logged_in_userid") == null)
    	{
			render();
    	}
		else
		{
			Errors.index("You have logged in already", "/home");
		}
	}
	
	public static void login()
	{
		if (session.get("logged_in_userid") == null)
    	{
			render();
    	}
		else
		{
			Errors.index("You have logged in already", "/home");
		}
	}
	
	public static void logout()
	{
		session.clear();
		index();
	}
	
	public static void index()
	{
		if (session.get("logged_in_userid") == null)
    	{
			render();
    	}
		else
		{
			Errors.index("You must log out first", "/home");
		}
	}
	
	public static void register(String firstName, String lastName, String email, String password, String age, String nationality)
	{
		if(!( firstName.equals("") || lastName.equals("") || email.equals("") 
				|| password.equals("") || age.equals("") || nationality.equals("") ))
		{
			Logger.info(firstName + " " + lastName + " " + email + " " + password + " " + age + " " + nationality);
			User user= new User (firstName,lastName,email,password,age,nationality);
			user.save();		
			index();
		}
		else
		{
			Errors.index("You must fill all details", "/signup");
		}
	}
	
	public static void authenticate(String email, String password)
	{
		Logger.info("Attempting to authenticate with " + email + ":" + password);

		User user = User.findByEmail(email);
		if ((user != null) && (user.checkPassword(password) == true))
			{
				Logger.info("Authentication successful" + user.id);
				session.put("logged_in_userid", user.id);
				Home.index();
			}
		else
			{
				Logger.info("Authentication failed");
				login();
			}
	}
}
