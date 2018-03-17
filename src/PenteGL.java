import java.util.ArrayList;

public class PenteGL extends GameLogic {
	int userOneCaptures;
	int userTwoCaptures;
	int whiteOrBlackTurn; //0 if white turn, 1 if black turn but white
    						  // always goes first
    
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
		if(checkValidMove(x,y)) {
			board.pieces[x][y].setPlayer(whiteOrBlackTurn);
			checkForCaptureUpRight(x,y);
			checkForCaptureUpLeft(x,y);
			checkForCaptureDownRight(x,y);
			checkForCaptureDownLeft(x,y);
			checkForCaptureRight(x,y);
			checkForCaptureLeft(x,y);
			checkForCaptureUp(x,y);
			checkForCaptureDown(x,y);
			if(checkEnd(x,y)){
				return null;
			}

			whiteOrBlackTurn = (whiteOrBlackTurn == 1) ? 0 : 1;
			changeTurn();
		}
		return null;
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
			gameOver = true;
			return true;
		}
		if(checkForDraw()) {
			System.out.println("Draw!");
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
	
	public void checkForCaptureRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		//going right
		if(x == board.getWidth() - 1) {
			return;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = x+1; i < board.getWidth(); ++i) {
		    		if(board.pieces[i][y].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(i,y));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[i][y].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	}
	
	public void checkForCaptureLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		//going right
		if(x == 0) {
			return;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = x - 1; i >= 0 ; --i) {
		    		if(board.pieces[i][y].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(i,y));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[i][y].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	}
	public void checkForCaptureDown(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		//going right
		if(y == board.getHeight() - 1) {
			return; }
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(y,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[x][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	}
	
	public void checkForCaptureUp(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();

		if(y == 0) {
			return;
		}
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i > 0; --i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(x,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[x][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			
			}
		}
	}	
	//works
	public void checkForCaptureUpRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();

		if(y == 0) {
			return;
		}
		int v = x - 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v < 0) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v--;
			}
		}
	}	
	//works
	public void checkForCaptureUpLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();

		if(y == 0) {
			return;
		}
		int v = x - 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i >= 0; --i) {
				if(v < 0) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v--;
			}
		}
	}	
	
	//works
	public void checkForCaptureDownRight(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();

		if(y == 0) {
			return;
		}
		int v = x + 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
				if(v >= board.getWidth()) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v++;
			}
		}
	}	
	//works
	public void checkForCaptureDownLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();

		if(y == 0) {
			return;
		}
		int v = x + 1;
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i >= 0; --i) {
				if(v >= board.getWidth()) {break;}	
				if(board.pieces[v][i].getPlayer() == 1) { 
					consecutiveBlack += 1;
					removalList.add(new Pair(v,i));
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
				}else{
					if(consecutiveWhite == 2 && board.pieces[v][i].getPlayer() == 1) {
						removeTwo(removalList);
					}
					break;
				
				}
			v++;
			}
		}
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
	public int getWinner(){return turn;}
	public Integer getP1Score(){return userOneCaptures;}
	public Integer getP2Score(){return userTwoCaptures;}
}
