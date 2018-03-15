
class SimonSaysPiece extends Piece{

	String[] icons = {"blue.png", "green.png", "yellow.png", "red.png"};

	SimonSaysPiece(int player){
		super(player);
	}


	String getIcon(){
		return icons[player];
	}
}

