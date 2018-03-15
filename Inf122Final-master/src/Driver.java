import java.util.HashMap;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import java.util.*;

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
		
		MainMenu main = new MainMenu(600,200,"Main Menu");
		while(true){
			while(currentGame==null){
				try{
					TimeUnit.SECONDS.sleep(1);
				}catch(Exception e){}
			}

			switch(currentGame){
				case "TicTacToe":
					game = new TicTacToeGL();
					break;
				case "Pente":
					//game = new PenteGL();
					break;
				case "SimonSays":
					game = new SimonSaysGL();
					break;
				case "Stratego":
					//game = new StrategoGL();
					break;
			}
			GameGUI gg = new GameGUI(600,600,currentGame, game.getBoardX(), game.getBoardY());
			while(!game.checkEnd()){
				try{
					TimeUnit.SECONDS.sleep(1);
				}catch(Exception e){}
				System.out.println("game not over");
			}
			gg.gameComplete();
			currentGame = null;
			main.setVisible(true);
		}
	}
}

