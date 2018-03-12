import java.util.ArrayList;

public class SimonSaysGL extends GameLogic
{

	boolean end = false;
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

	public boolean checkEquals(){
		// System.out.println(player_colors.get(0));
		// System.out.println(player_colors.get(1));
		return (player_colors.get(0).equals(player_colors.get(1)));
	}
	   
	public void makeMove(int row, int col) { 

		System.out.println("TURN = " + turn);

		if (player_colors.get(0).isEmpty()){
			System.out.println("set new");
			player_colors.get(0).add(board.pieces[row][col].getIcon());
			changeTurn();
		}

		else{
			if (counters[turn] == false){
				player_colors.get(turn).clear();
				player_colors.get(turn).add(board.pieces[row][col].getIcon());	
				counters[turn] = true;
			}
			else{
				if (player_colors.get(turn).size() < player_colors.get(turn^1).size()){
					player_colors.get(turn).add(board.pieces[row][col].getIcon());				
				}
				else{
					if (checkEquals()){
						player_colors.get(turn).add(board.pieces[row][col].getIcon());
						counters[turn] = false;	
						changeTurn();
					}
					else{
						end = true;
					}
				}


			}

		}

		if (checkEnd()){
			System.out.println("END GAME");
			return;
		}

		System.out.println(player_colors.get(0));
		System.out.println(player_colors.get(1));


	}
	   
	public boolean checkEnd(){
		return end;
	}
}
