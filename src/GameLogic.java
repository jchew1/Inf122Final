import java.util.ArrayList;

abstract class GameLogic{
	Board board;
	int turn;

	public abstract void boardConstructor();
	public abstract boolean checkValidMove(int x, int y);
	// makeMove(x,y)[0] = ArrayList<Piece> (changed pieces)
	// makeMove(x,y)[1] = String (turn message)
	public abstract ArrayList<Object> makeMove(int x, int y);
	public abstract boolean checkEnd();
	public abstract Integer getP1Score();
	public abstract Integer getP2Score();
	public abstract int getWinner();

	void changeTurn(){
		turn = turn^1;
	}
 
	//getters and setters
	int getTurn(){return turn;}
	int getBoardX(){return board.getWidth();}
	int getBoardY(){return board.getHeight();}
	
}
