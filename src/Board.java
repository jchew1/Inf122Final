import java.util.ArrayList;

class Board{
	int width;
	int height;
	ArrayList<ArrayList<Piece>> pieces;

	Board(int width, int height){
		this.width = width;
		this.height = height;
		pieces = new ArrayList<ArrayList<Piece>>();
		for(int i=0; i<height; i++){
			pieces.add(new ArrayList<Piece>());
		}
	}

	int getWidth(){return width;}
	int getHeight(){return height;}
}
