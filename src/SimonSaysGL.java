import java.util.ArrayList;

public class SimonSaysGL extends GameLogic
{

	boolean[] counters = {false,false};
	ArrayList<ArrayList<String>> player_colors = new ArrayList<ArrayList<String>>();

	public SimonSaysGL() {
		turn = 0;
		player_colors.add(new ArrayList<String>());
		player_colors.add(new ArrayList<String>());
		boardConstructor();
	}

	public void boardConstructor(){
		board = new Board(2,2);
		// this does not work because in boardtile, the icon is only gotten after an action is performed
		board.pieces[0][0] = new SimonSaysPiece(0);
		board.pieces[0][1] = new SimonSaysPiece(1);
		board.pieces[1][0] = new SimonSaysPiece(2);
		board.pieces[1][1] = new SimonSaysPiece(3);
		//change this later with real tiles

	}

	public boolean checkValidMove(int x, int y){
		return true;
	}
	   
	public void makeMove(int row, int col) { 

		System.out.println("TURN = " + turn);

		if (player_colors.get(0).isEmpty()){
			player_colors.get(0).add(board.pieces[row][col].getIcon());
			changeTurn();
		}

		else{
			// if it is current player's turn to start matching pattern
			if (counters[turn] == false){ 
				player_colors.get(turn).clear();
				player_colors.get(turn).add(board.pieces[row][col].getIcon());	
				counters[turn] = true;
			}
			else{ // add in next color of current player's pattern

				// if have not added up to size of opposing player's pattern to be matched
				if (player_colors.get(turn).size() < player_colors.get(turn^1).size()){
					player_colors.get(turn).add(board.pieces[row][col].getIcon());				
				}
				else{
					// finished attempt to match opposing player's pattern
					// check if attempt matches
					if (checkEnd()){ 
						player_colors.get(turn).add(board.pieces[row][col].getIcon()); // extend the pattern
						counters[turn] = false;	
						changeTurn();
					}
					else{
						// pattern is messed up, game over
						System.out.println("END GAME");
						return;
					}
				}
			}

		}


		System.out.println(player_colors.get(0));
		System.out.println(player_colors.get(1));


	}
	   
	public boolean checkEnd(){
		// true means pattern matched, game continues
		// false means pattern did not match, game is over
		return (player_colors.get(0).equals(player_colors.get(1)));
	}

	public Integer getP1Score(){return null;}
	public Integer getP2Score(){return null;}
}
