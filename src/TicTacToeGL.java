public class TicTacToeGL extends GameLogic
{
	   private char[][] board; 
	   
	   private char currentPlayerMark;
	   private int width = 3;
	   private int height = 3;
	   public TicTacToeGL() {
		   currentPlayerMark = 'X';
		   for (int i = 0; i < 3; i++) {
			   for (int j = 0; j < 3; j++) {
				   board[i][j] = ' ';	   
	               }
	           }
		   }
	   public int getx(){
		   return width;
	   }
	   public int gety(){
		   return height;
	   }
	   public boolean isBoardFull(){
		   boolean isFull = true;
		   for(int i = 0; i<3;i++){
			   for(int j = 0; j<3;j++){
				   if (board[i][j] == ' '){
					   isFull = false;
					   }
			   }
		   }
		   return isFull;
	   }
	   
	   public boolean checkForWin() {
		   if((checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin())){
			   return true;
		   }
		   return false;
		   }
	   
	   private boolean checkColumnsForWin() {
		   for (int i = 0; i < 3; i++) {
			   if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
				   return true;
				   }
		   }
		   return false;
	   }
	   
	   private boolean checkRowsForWin() {
		   for (int i = 0; i < 3; i++) {
			   if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
				   return true;
				   }
			   }
		   return false;
		   }

	   private boolean checkRowCol(char c1, char c2, char c3) {
		   if ((c1 != ' ') && (c1 == c2) && (c2 == c3)){
			   return true;
		   }
		   return false;
		}
	   
	   private boolean checkDiagonalsForWin() {
		   return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	   }
	   public void changeTurn() {
		   if (currentPlayerMark == 'X') {
			   currentPlayerMark = 'O';
			   }
		   else {
			   currentPlayerMark = 'X';
		   }
		   }
	   
	   public boolean checkValidMove(int row, int col){
		   if ((row >= 0) && (row < 3)) {
			   if ((col >= 0) && (col < 3)) {
				   if (board[row][col] == ' ') {
					   return true;
				   }
			   }
		   }
		   return false;
	   }
	   
	   public void makeMove(int row, int col) {         
		   board[row][col] = currentPlayerMark;
		   }
	   
	   public boolean checkEnd(){
		   if (checkForWin()==true || isBoardFull()==true){
			   return true;
		   }
		   return false;
	   }
	   public int getTurn(){
		   	return currentPlayerMark;
		   }
	void boardConstructor(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}
	void getUserMove()
	{
		// TODO Auto-generated method stub
		
	}
