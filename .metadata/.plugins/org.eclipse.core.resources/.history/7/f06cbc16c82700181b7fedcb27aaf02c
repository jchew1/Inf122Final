
public class PenteGL extends GameLogic {
        PenteGL(){
            turn = 0;            
            boardConstructor();
        }
	@Override
	public void boardConstructor() {
		// TODO Auto-generated method stub
		board = new Board(10, 10);
		for(int i = 0; i < board.getWidth(); ++i){
			for(int v = 0; v < board.getHeight(); ++v) {
				board.pieces[i][v] = new PentePiece(-1);
			}
		}
	}

	@Override
	public boolean checkValidMove(int x, int y) {
		// TODO Auto-generated method stub
		return board.pieces[x][y].player == -1;
	}

	@Override
	public void makeMove(int x, int y) {
		// TODO Auto-generated method stub
		if(checkValidMove(x,y)) {
			board.pieces[x][y].setPlayer(turn);
			if(checkEnd()){
				return;
			}
			changeTurn();
		}
	}

	@Override
	public boolean checkEnd() {
		// TODO Auto-generated method stub
		return false;
	}

}
