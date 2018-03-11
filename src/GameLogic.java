
abstract class GameLogic{
	Board board;
	int turn;

	public abstract void boardConstructor();
	public abstract boolean checkValidMove(int x, int y);
	public abstract void makeMove(int x, int y);
	public abstract boolean checkEnd();

	void changeTurn(){
		turn = turn^1;
	}
 
	//getters and setters
	int getTurn(){return turn;}
	int getBoardX(){return board.getWidth();}
	int getBoardY(){return board.getHeight();}
		
		
	
}
