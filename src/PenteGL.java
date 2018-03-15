import java.util.ArrayList;
public class PenteGL extends GameLogic {
    int userOneCaptures;
	int userTwoCaptures;
    int whiteOrBlackTurn; //0 if white turn, 1 if black turn but white
    						  // always goes first
    
	PenteGL(){
    		turn = 0;            
    		boardConstructor();
    }
    
	@Override
	public void boardConstructor() {
		// TODO Auto-generated method stub
		board = new Board(10, 10);
		for(int i = 0; i < board.getWidth(); ++i){
			for(int v = 0; v < board.getHeight(); ++v) {
				board.pieces[i][v] = new PentePiece(-1);
			}
		}
	}

	@Override
	public boolean checkValidMove(int x, int y) {
		// TODO Auto-generated method stub
		return board.pieces[x][y].player == -1;
	}

	@Override
	public void makeMove(int x, int y) {
		// TODO Auto-generated method stub
		if(checkValidMove(x,y)) {
			board.pieces[x][y].setPlayer(whiteOrBlackTurn);
			checkForCaptureRight(x,y);
			checkForCaptureRight(x,y);
			checkForCaptureUp(x,y);
			checkForCaptureDown(x,y);
			if(checkEnd(x,y)){
				return;
			}

			whiteOrBlackTurn = (whiteOrBlackTurn == 1) ? 0 : 1;
			changeTurn();
		}
	}

	public boolean winByGoingFiveRight(int x, int y) {
		int consWhite = 0;
		int consBlack = 0;
		if(whiteOrBlackTurn == 0) {
			for(int i = x; i < board.getWidth(); ++i) {
				if(board.pieces[x][y].getPlayer() == 0) {
					consWhite += 1;
				}else {
					if(consWhite == 5) {
						System.out.println("White wins by having 5 pieces in a row towards the right");
						return true;
					}
					break;
				}
			}
		}else{
			for(int i = x; i < board.getWidth(); ++i) {
				if(board.pieces[x][y].getPlayer() == 1) {
					consBlack += 1;
				}else {
					if(consBlack == 5) {
						System.out.println("Black wins by having 5 pieces in a row towards the right");
						return true;
					}
					break;
				}
			}
		}
		return false;
	}
	
	public boolean checkEnd(int x, int y) {
		// TODO Auto-generated method stub

		if(winByGoingFiveRight(x, y) || userOneCaptures == 5 || userTwoCaptures == 5) {
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
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = x + 1; i < board.getWidth(); ++i) {
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
			for(int i = x + 1; i < board.getWidth(); ++i) {
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
	
	public void checkForCaptureLeft(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		//going right
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
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y + 1; i < board.getHeight(); ++i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
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
			for(int i = y + 1; i < board.getHeight(); ++i) {
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
	
	public void checkForCaptureUp(int x, int y) {
		//Check all to the right
		int consecutiveBlack = 0;
		int consecutiveWhite = 0;
		ArrayList<Pair> removalList= new ArrayList<Pair>();
		//going right
		if(whiteOrBlackTurn == 0) { //whites move
			for(int i = y - 1; i <= 0; --i) {
		    		if(board.pieces[x][i].getPlayer() == 1) { 
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
			for(int i = y - 1; i <= 0; --i) {
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
	public boolean checkEnd() {return false;}
}
