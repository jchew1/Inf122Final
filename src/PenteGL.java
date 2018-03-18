import java.util.ArrayList;

public class PenteGL extends GameLogic {
	int userOneCaptures;
	int userTwoCaptures;
	int whiteOrBlackTurn; //0 if white turn, 1 if black turn but white
        int winner;    						  // always goes first
    
	boolean gameOver = false;

	PenteGL(){
    		turn = 0;            
		boardConstructor();
        }
    
	@Override
	public void boardConstructor() {
		board = new Board(10, 10);
		for(int i = 0; i < board.getWidth(); ++i){
			for(int v = 0; v < board.getHeight(); ++v) {
				board.pieces[i][v] = new PentePiece(-1);
			}
		}
	}

	@Override
	public boolean checkValidMove(int x, int y) {
		return board.pieces[x][y].player == -1;
	}

	@Override
	public ArrayList<Object> makeMove(int x, int y) {
                ArrayList<Piece>removalPieces = new ArrayList<Piece>();
		ArrayList<Object> piecesWithMessage = new ArrayList<Object>();
                if(checkValidMove(x,y)) {
			board.pieces[x][y].setPlayer(whiteOrBlackTurn);
			removalPieces.addAll(checkForCaptureUpRight(x,y));
			removalPieces.addAll(checkForCaptureUpLeft(x,y));
			removalPieces.addAll(checkForCaptureDownRight(x,y));
			removalPieces.addAll(checkForCaptureDownLeft(x,y));
			removalPieces.addAll(checkForCaptureRight(x,y));
			removalPieces.addAll(checkForCaptureLeft(x,y));
			removalPieces.addAll(checkForCaptureUp(x,y));
			removalPieces.addAll(checkForCaptureDown(x,y));
			if(checkEnd(x,y)){
				return null;
			}

			whiteOrBlackTurn = (whiteOrBlackTurn == 1) ? 0 : 1;
			changeTurn();
		}
                piecesWithMessage.add(removalPieces); //add list of pieces removed
                piecesWithMessage.add(null); //add as string message
		return piecesWithMessage;
	}
	
	public boolean winByRowUpDown(int x, int y) {
		int cons = 0;
		for(int i = y - 1; i >= 0; i--) {
			if(board.pieces[x][i].getPlayer() == whiteOrBlackTurn) {
				cons += 1;
			}else {
				break;
			}
		}
		for(int i = y + 1; i < board.getHeight(); i++) {
			if(board.pieces[x][i].getPlayer() == whiteOrBlackTurn) {
				cons += 1;
			}else {
				break;
			}
		}
		return cons == 4;
	}


	public boolean winByRowLeftRight(int x, int y) {
		int cons = 0;
		for(int i = x - 1; i >= 0; i--) {
			if(board.pieces[i][y].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		for(int i = x + 1; i < board.getHeight(); i++) {
			if(board.pieces[i][y].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		return cons == 4;
	}
	

	public boolean winByDiagonalRightUpLeftDown(int x, int y) {
		int cons = 0;
		int v = y;
		for(int i = x + 1; i < board.getWidth(); i++) {
			v--;
			if(v < 0) { break; }
			if(board.pieces[i][v].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		v = y;
		for(int i = x - 1; i >= 0; i--) {
			v++;
			if(v >= board.getHeight()){
				break;
			}
			if(board.pieces[i][v].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		return cons == 4;
	}
	
	public boolean winByDiagonalLeftUpRightDown(int x, int y) {
		int cons = 0;
		int v = y;
		for(int i = x - 1; i >= 0; i--) {
			v--;
			if(v < 0) { break; }
			if(board.pieces[i][v].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		v = y;
		for(int i = x + 1; i < board.getWidth(); i++) {
			v++;
			if(v >= board.getHeight()){
				break;
			}
			if(board.pieces[i][v].getPlayer() == whiteOrBlackTurn) {
				cons++;
			}else {
				break;
			}
		}
		return cons == 4;
	}
	public boolean checkForDraw() {
		for(int i = 0; i < board.getWidth(); ++i) {
			for(int v = 0; v < board.getHeight(); ++v) {
				if(board.pieces[i][v].getPlayer() == -1) {
					return false;
				}
			}
		}
		return true;
	}
	public boolean checkEnd(int x, int y) {

		if(winByRowUpDown(x,y) || winByRowLeftRight(x,y) || 
		   winByDiagonalRightUpLeftDown(x,y) || winByDiagonalLeftUpRightDown(x,y) ||
			userOneCaptures == 5 || userTwoCaptures == 5) {
			if(whiteOrBlackTurn == 1) { System.out.println("Winner is player 2!");};
			if(whiteOrBlackTurn == 0) { System.out.println("Winner is player 1!");};
			winner = whiteOrBlackTurn;
                        gameOver = true;
			return true;
		}
		if(checkForDraw()) {
			System.out.println("Draw!");
                        winner = -1;
			return true;
		}
		return false;
	}

	public void removeTwo(ArrayList<Pair> removalList) {
		int x;
		int y;
		for(int i = 0; i < removalList.size(); ++i){
			x = removalList.get(i).x;
			y = removalList.get(i).y;
			board.pieces[x][y].setPlayer(-1);
			System.out.printf("removed piece at x: %d y: %d\n", x, y);
		}
		if(whiteOrBlackTurn == 0) {
			userOneCaptures += 1;
		}else {
			userTwoCaptures += 1;
		}
		return;
	}
	
	public ArrayList<Piece> checkForCaptureRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList = new ArrayList<Pair>();
	        ArrayList<Piece> removalPiece = new ArrayList<Piece>();
        	//going right
		if(x == board.getWidth() - 1) {
			return removalPiece;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = x+1; i < board.getWidth(); ++i) {
		    		if(board.pieces[i][y].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(i,y));
                                        removalPiece.add(board.pieces[i][y]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[i][y].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
			}
		}else { //blacks move
			for(int i = x+1; i < board.getWidth(); ++i) {
				if(board.pieces[i][y].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(i,y));
                                        removalPiece.add(board.pieces[i][y]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[i][y].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	
        return removalPiece;
        }
	
	public ArrayList<Piece> checkForCaptureLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		ArrayList<Piece> removalPiece = new ArrayList<Piece>();
                //going right
		if(x == 0) {
			return removalPiece;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = x - 1; i >= 0 ; --i) {
		    		if(board.pieces[i][y].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(i,y));
                                        removalPiece.add(board.pieces[i][y]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[i][y].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
			}
		}else { //blacks move
			for(int i = x - 1; i >= 0; --i) {
				if(board.pieces[i][y].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(i, y));
                                        removalPiece.add(board.pieces[i][y]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[i][y].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
        return removalPiece;
	}

	public ArrayList<Piece> checkForCaptureDown(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		//going right
		if(y == board.getHeight() - 1) {
			return removalPiece; }
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(x,i));
                                        removalPiece.add(board.pieces[x][i]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[x][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
			}
		}else { //blacks move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(board.pieces[x][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(x, i));
                                        removalPiece.add(board.pieces[x][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[x][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	return removalPiece;
        }
	
	public ArrayList<Piece> checkForCaptureUp(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		if(y == 0) {
			return removalPiece;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i > 0; --i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(x,i));
		    		        removalPiece.add(board.pieces[x][i]);
                                }else{
					if(consecutiveBlack == 2 && board.pieces[x][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
			}
		}else { //blacks move
			for(int i = y - 1; i > 0; --i) {
				if(board.pieces[x][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(x,i));
                                        removalPiece.add(board.pieces[x][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[x][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	return removalPiece;
        }	

	//works
	public ArrayList<Piece> checkForCaptureUpRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		if(y == 0) {
			return removalPiece;
		}
		int v = x - 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v < 0) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[v][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
		    	v--;
			}
		}else { //blacks move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v < 0) {break;}
				if(board.pieces[v][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v--;
			}
		}
        return removalPiece;
	}	

	//works
	public ArrayList<Piece> checkForCaptureUpLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		if(y == 0) {
			return removalPiece;
		}
		int v = x - 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i >= 0; --i) {
				if(v < 0) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[v][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
		    	v--;
			}
		}else { //blacks move
			for(int i = y - 1; i >= 0; --i) {
				if(v < 0) {break;}
				if(board.pieces[v][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v--;
			}
		}
        return removalPiece;
	}	
	
	//works
	public ArrayList<Piece> checkForCaptureDownRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		if(y == 0) {
			return removalPiece;
		}
		int v = x + 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v >= board.getWidth()) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[v][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
		    	v++;
			}
		}else { //blacks move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v >= board.getWidth()) {break;}
				if(board.pieces[v][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v++;
			}
		}
        return removalPiece;
	}	

	//works
	public ArrayList<Piece> checkForCaptureDownLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
                ArrayList<Piece> removalPiece = new ArrayList<Piece>();
		if(y == 0) {
			return removalPiece;
		}
		int v = x + 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i >= 0; --i) {
				if(v >= board.getWidth()) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
		    		}else{
					if(consecutiveBlack == 2 && board.pieces[v][i].getPlayer() == 0) {
						removeTwo(removalList);
					}
					break;
				}
		    	v++;
			}
		}else { //blacks move
			for(int i = y - 1; i >= 0; --i) {
				if(v >= board.getWidth()) {break;}
				if(board.pieces[v][i].getPlayer() == 0) {
					consecutiveWhite += 1;
					removalList.add(new Pair(v,i));
                                        removalPiece.add(board.pieces[v][i]);
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v++;
			}
		}
        return removalPiece;
	}	
	
	class Pair{
		int x;
		int y;
		Pair(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	//Never using this
	@Override
	public boolean checkEnd() {return gameOver;}
	@Override
        public int getWinner(){return winner;} //0 = white 1 = black -1 = draw
	public Integer getP1Score(){return userOneCaptures;}
	public Integer getP2Score(){return userTwoCaptures;}
}
