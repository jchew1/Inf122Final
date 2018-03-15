
abstract class Piece{
	int player;

	Piece(int player){
		this.player = player;
	}

	// getters and setters
	int getPlayer(){return player;}
	void setPlayer(int p){player = p;}
	abstract String getIcon();
}
