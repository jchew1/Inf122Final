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
	
	int[] getPosition(Piece p){
		int result[] = new int[2];
		for(int i = 0; i < width; ++i){
			for(int j = 0; j < height; ++j){
				if(pieces[i][j] == p){
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		return null;
	}
}
