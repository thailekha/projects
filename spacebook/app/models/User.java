package models;

import javax.persistence.Entity;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.OneToMany;
import play.db.jpa.Model;
import play.db.jpa.Blob;

@Entity
public class User extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;
  public String age;
  public String nationality;
  public String statusText;
  public Blob profilePicture;
  
  @OneToMany(mappedBy = "sourceUser")
  public List<Friendship> friendships = new ArrayList<Friendship>();
  
  @OneToMany(mappedBy = "to")
  public List<Message> inBox = new ArrayList<Message>();
  
  @OneToMany(mappedBy = "from")
  public List<Message> outBox = new ArrayList<Message>();

  public User(String firstName, String lastName, String email, String password, String age, String nationality)
  {
	  this.firstName = firstName;
	  this.lastName  = lastName;
	  this.email     = email;
	  this.password  = password;
	  this.age = age;
	  this.nationality = nationality;
	  statusText = "Sample status";
  }
  
  public void editUser(String firstName, String lastName, String age, String password)
  {
	  if(!firstName.equals(""))
	  {
		  this.firstName = firstName;
	  }
	  if(!lastName.equals(""))
	  {
		  this.lastName = lastName;
	  }
	  if(!age.equals(""))
	  {
		  this.age = age;
	  }
	  if(!password.equals(""))
	  {
		  this.password = password;
	  }
  }
  
  public static User findByEmail(String email)
  {
	  return find("email", email).first();
  }
  
  public boolean checkPassword(String password)
  {
	  return this.password.equals(password);
  }
  
 
  public void beFriend(User friend)
  {
	  int count = 0;
	  for(Friendship friendship: friendships)
	  {
		  if(friendship.targetUser == friend)
		  {
			  count++;
		  }
	  }		  
	  if (count == 0)
	  {
		  Friendship friendship = new Friendship(this, friend);
		  friendships.add(friendship);
		  friendship.save();
		  save();
	  }
  }
  
  public void unfriend(User friend)
  {
	  Friendship thisFriendship = null;
	  
	  for(Friendship friendship: friendships)
	  {
		  if(friendship.targetUser == friend)
		  {
			  thisFriendship = friendship;
		  }
	  }
	  friendships.remove(thisFriendship);
	  thisFriendship.delete();
	  save();
  }
  
  public void sendMessage(User to, String messageText, String subject)
  {
	  Message message = new Message (this, to, messageText, subject);
	  outBox.add(message);
	  to.inBox.add(message);
	  message.save();
  }
}