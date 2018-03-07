import java.util.Scanner;
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
		Scanner scan = new Scanner(System.in);
		System.out.print("Player1 Username: ");
		user1 = getUser(scan.nextLine());
		System.out.print("Player2 Username: ");
		user2 = getUser(scan.nextLine());

		scan.close();
	}
}

