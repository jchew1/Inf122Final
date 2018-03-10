import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

class Driver{

	static HashMap<String, User> users = new HashMap<String, User>();
	static User user1;
	static User user2;
	static String currentGame;
	static GameLogic game;

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
		while(currentGame==null){
			try{
			TimeUnit.SECONDS.sleep(1);
			}catch(Exception e){}
		}
		switch(currentGame){
			case "TicTacToe":
				/*
				GameLogic game = new TicTacToeGL();
				GameGUI gg = new GameGUI(1000,1000,"TicTacToe", game.getBoardX(), game.getBoardY());
				while(game.checkEnd()==false){
					game.makeMove(x,y);				
				}
				*/
				break;
			case "Pente":
				break;
			case "Battleship":
				break;
			case "Stratego":
				break;
		}
		new GameGUI(500,500,"newgame",7,7);

	}
}

