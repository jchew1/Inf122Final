
abstract class GameLogic{
	Board board;
	int turn;

	abstract void boardConstructor();
	abstract void getUserMove();
	abstract void checkValidMove();
	abstract void makeMove(int x, int y);
	abstract void checkEnd();

	void changeTurn(){
		turn = turn^1;
	}
 
	//getters and setters
	int getTurn(){return turn;}
	int getBoardX(){return board.getWidth();}
	int getBoardY(){return board.getHeight();}
		
		
	
}
