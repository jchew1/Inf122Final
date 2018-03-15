
class TicTacToePiece extends Piece{
	String[] icons = {"TicTacToe_O.png", "TicTacToe_X.png", "emptySquare.png"};

	TicTacToePiece(int player){
		super(player);
	}

	String getIcon(){
		if(player == -1){
			return icons[2];
		}
		return icons[player];
	}
}

