import java.util.ArrayList;

class Board{
	int width;
	int height;
	Piece[][] pieces;

	Board(int width, int height){
		this.width = width;
		this.height = height;
		pieces = new Piece[width][height];
	}

	int getWidth(){return width;}
	int getHeight(){return height;}
}
