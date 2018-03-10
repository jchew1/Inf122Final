import java.util.HashMap;

class Driver{

	static HashMap<String, User> users = new HashMap<String, User>();
	static User user1;
	static User user2;

	static User getUser(String username){
		User u = users.get(username);
		if(u == null){
			u = new User(username);	
			return u;
		}
		return u;
	}

	public static void main(String[] args){
		MainMenu main = new MainMenu(500,200,"Main Menu");
		
		//new GameGUI(500,500,"newgame",7,7);

	}
}

