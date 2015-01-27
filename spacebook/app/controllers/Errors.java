package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Errors extends Controller
{
	public static void index(String errorDetails, String backRoute)
	{
		render(errorDetails, backRoute);
	}
}
