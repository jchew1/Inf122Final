
abstract class GameLogic{
	Board board;
	int turn;

	abstract void boardConstructor(int width, int height);
	abstract void getUserMove();
	abstract void checkValidMove();
	abstract void makeMove();
	abstract void checkEnd();

	void changeTurn(){
		turn = turn^1;
	}
 
	//getters and setters
	int getTurn(){return turn;}
}
