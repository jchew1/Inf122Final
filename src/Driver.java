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
	static GameGUI gameGUI;

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
		while(true){
			while(currentGame==null){
				try{
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("currentGameNull");
				}catch(Exception e){}
			}

			switch(currentGame){
				case "TicTacToe":
					game = new TicTacToeGL();
					break;
				case "Pente":
					game = new PenteGL();
					break;
				case "SimonSays":
					game = new SimonSaysGL();
					break;
				case "Stratego":
					game = new StrategoGL();
					break;
			}
			gameGUI = new GameGUI(600,600,currentGame, game.getBoardX(), game.getBoardY());
			while(!game.checkEnd()){
				try{
					TimeUnit.MILLISECONDS.sleep(500);
					System.out.println("gameNotOver");
				}catch(Exception e){}
			}
			gameGUI.gameComplete();

			switch(game.getWinner()){
				case -1: 
					user1.addStat(currentGame, 0);
					user2.addStat(currentGame, 0);
					break;
				case 0:
					user1.addStat(currentGame, 1);
					user2.addStat(currentGame, -1);
					break;
				case 1:
					user1.addStat(currentGame, -1);
					user2.addStat(currentGame, 1);
					break;
				default:
					System.out.println("error game get winner");
			}
			System.out.printf("user1 wins: %d, losses: %d, ties: %d\n",user1.getStats(currentGame)[0],user1.getStats(currentGame)[1],user1.getStats(currentGame)[2]);
			System.out.printf("user2 wins: %d, losses: %d, ties: %d\n",user2.getStats(currentGame)[0],user2.getStats(currentGame)[1],user2.getStats(currentGame)[2]);
			currentGame = null;
			main.setVisible(true);
		}
	}
}

