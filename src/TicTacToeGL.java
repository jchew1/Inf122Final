public class TicTacToeGL extends GameLogic
{
	public TicTacToeGL() {
		turn = 0;
		boardConstructor();
	}

	public void boardConstructor(){
		board = new Board(3,3);
		for(int i=0; i<board.getWidth(); i++){
			for(int j=0; j<board.getHeight(); j++){
				board.pieces[i][j] = new TicTacToePiece(-1);
			}
		}
	}

	public boolean isBoardFull(){
		for(int i = 0; i<3;i++){
			for(int j = 0; j<3;j++){
				if (board.pieces[i][j].player == -1){ 
					return false;
				}
			}
		}
		return true;
	}
	   
	public boolean checkForWin() {
		if((checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin())){
			return true;
		}
		return false;
	}
	   
	private boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board.pieces[0][i], board.pieces[1][i], board.pieces[2][i]) == true) {
				return true;
			}
		}
		return false;
	}
	   
	private boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (checkRowCol(board.pieces[i][0], board.pieces[i][1], board.pieces[i][2]) == true) {
				return true;
			}
		}
		return false;
	}

   
	private boolean checkDiagonalsForWin() {
		return ((checkRowCol(board.pieces[0][0], board.pieces[1][1], board.pieces[2][2]) == true) || (checkRowCol(board.pieces[0][2], board.pieces[1][1], board.pieces[2][0]) == true));
	}
   
	private boolean checkRowCol(Piece c1, Piece c2, Piece c3) {
		if ((c1.player != -1) && (c1.player == c2.player) && (c2.player == c3.player)){
			return true;
		}
		return false;
	}

	public boolean checkValidMove(int x, int y){
		return board.pieces[x][y].player == -1;
	}
	   
	public void makeMove(int row, int col) {         
		if(checkValidMove(row, col)){
			board.pieces[row][col].setPlayer(turn);
			if(checkEnd()){
				return;
			}
			changeTurn();
		}
	}
	   
	public boolean checkEnd(){
		if (checkForWin()==true || isBoardFull()==true){
			System.out.println("game finished");
			return true;
		}
		return false;
	}
}
