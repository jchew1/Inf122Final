
class Piece{
	int player;
	String icon;

	Piece(int player, String icon){
		this.player = player;
		this.icon = icon;
	}

	// getters and setters
	int getPlayer(){return player;}
	void setPlayer(int p){player = p;}
	String getIcon(){return icon;}
	void setIcon(String icon){this.icon = icon;}
}
