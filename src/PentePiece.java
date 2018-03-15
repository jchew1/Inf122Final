class PentePiece extends Piece {
	String[] icons = {"whiteCircle.png", "blackCircle.png", "emptySquare.png"};
	PentePiece(int player){
		super(player);
	}
	@Override
	String getIcon() {
		// TODO Auto-generated method stub
		if(player == -1) {		
			return icons[2];
		}
		return icons[player];
	}

}
