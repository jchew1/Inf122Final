import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class StrategoGL extends GameLogic {

	private boolean player0FlagExists = true;
	private boolean player1FlagExists = true;
	private int defaultWidth = 10;
	private int defaultHeight = 10;
	
	private boolean placementPhase0 = true;
	private boolean placementPhase1 = true;
	private Queue<StrategoPiece> player0StartingUnits = new LinkedList<StrategoPiece>();
	private Queue<StrategoPiece> player1StartingUnits = new LinkedList<StrategoPiece>();
	private ArrayList<StrategoPiece> activeUnits = new ArrayList<StrategoPiece>();
	private StrategoPiece selectedPiece;
	
	
	public StrategoGL(){
		turn = 0;
		boardConstructor();
		fillStartingUnits();
		System.out.println("Place " + player0StartingUnits.peek());
	}
	
	@Override
	public void boardConstructor() {
		board = new Board(defaultWidth,defaultHeight);
		for(int i = 0; i < board.getWidth(); ++i){
			for(int j = 0; j < board.getHeight(); ++j){
				board.pieces[i][j] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
			}
		}
		board.pieces[4][2] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[5][2] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[4][3] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[5][3] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[4][6] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[5][6] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[4][7] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[5][7] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		//prompt user to add pieces to the board
		
	}

	private void fillStartingUnits(){
		player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.FLAG, 0));
		player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.FLAG, 1));
		
		for(int i = 0; i < 6; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.BOMB, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.BOMB, 1));
		}
		
		player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MARSHAL, 0));
		player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MARSHAL, 1));
		
		player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.GENERAL, 0));
		player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.GENERAL, 1));
		
		for(int i = 0; i < 2; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.COLONEL, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.COLONEL, 1));
		}
		
		for(int i = 0; i < 3; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MAJOR, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MAJOR, 1));
		}
		
		for(int i = 0; i < 4; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.CAPTAIN, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.CAPTAIN, 1));
		}
		
		for(int i = 0; i < 4; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.LIEUTENANT, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.LIEUTENANT, 1));
		}
		
		for(int i = 0; i < 4; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SERGEANT, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SERGEANT, 1));
		}
		
		for(int i = 0; i < 5; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MINER, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.MINER, 1));
		}
		
		for(int i = 0; i < 8; ++i){
			player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SCOUT, 0));
			player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SCOUT, 1));
		}
		
		player0StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SPY, 0));
		player1StartingUnits.add(StrategoPieceFactory.createPiece(StrategoPieceFactory.SPY, 1));
	}
	
	@Override
	public ArrayList<Object> makeMove(int x,int y) {
		StrategoPiece targetedPiece = (StrategoPiece) board.pieces[x][y];
		if(placementPhase0 || placementPhase1){						//placing tiles in setup
			ArrayList<Object> result = new ArrayList<Object>();	//array of tiles to update, string message
			result.add(null);
			String message = handlePlacePiece(targetedPiece);
			result.add(message);
			if(message != null){
				result.set(0, activeUnits);
			}
			return result;
		}
		if(!targetedPiece.isTargetable()){		//selecting or targeting water tiles
			ArrayList<Object> result = new ArrayList<Object>();	//array of tiles to update, string message
			ArrayList<StrategoPiece> piecesToBeRefreshed = new ArrayList<StrategoPiece>();
			selectedPiece.setSelected(false);
			piecesToBeRefreshed.add(selectedPiece);
			selectedPiece = null;

			result.add(piecesToBeRefreshed);
			result.add(null);
			return result;
		}
		if(selectedPiece == null){				//to select a piece
			if(targetedPiece.isSelectable() && targetedPiece.getPlayer() == turn){
				selectedPiece = targetedPiece;
				selectedPiece.setSelected(true);
				return null;
			}
			return null;
		}
		
		if(targetedPiece.getPlayer() == turn){	//targeting your own piece
			ArrayList<Object> result = new ArrayList<Object>();	//array of tiles to update, string message
			ArrayList<StrategoPiece> piecesToBeRefreshed = new ArrayList<StrategoPiece>();
			piecesToBeRefreshed.add(selectedPiece);
			piecesToBeRefreshed.add(targetedPiece);
			selectedPiece.setSelected(false);
			targetedPiece.setSelected(true);
			selectedPiece = targetedPiece;
			result.add(piecesToBeRefreshed);
			result.add(null);
			return result;
		}
		
		
		if(isAdjacent(selectedPiece, targetedPiece) || (selectedPiece.getName() == StrategoPieceFactory.SCOUT && isValidScoutMove(selectedPiece, targetedPiece))){
			int spPos[] = board.getPosition(selectedPiece);
			int spX = spPos[0];
			int spY = spPos[1];
			ArrayList<Object> result = new ArrayList<Object>();	//array of tiles to update, string message
			movePiece(selectedPiece, targetedPiece);	//for targeted empty space or enemies
			selectedPiece.setSelected(false);
			selectedPiece = null;
			changeTurn();
			
			ArrayList<StrategoPiece> piecesToBeRefreshed = new ArrayList<StrategoPiece>();
			piecesToBeRefreshed.addAll(activeUnits);
			piecesToBeRefreshed.add((StrategoPiece) board.pieces[spX][spY]);
			
			result.add(piecesToBeRefreshed);
			String turnMessage = "";
			if(turn == 1){
				turnMessage = "Red";
			} else{
				turnMessage = "Blue";
			}
			result.add(turnMessage + " Moved");

			return result;
		}

		return null;
	}


	private String handlePlacePiece(StrategoPiece targetedPiece){
		int tpPos[] = board.getPosition(targetedPiece);
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		
		if(!targetedPiece.isEmpty()){
			return null;
		}
		
		if(placementPhase0){
			if(tpX > 3){
				return null;
			}
			StrategoPiece piece = player0StartingUnits.remove();
			board.pieces[tpX][tpY] = piece;
			activeUnits.add(piece);
		} else if(placementPhase1){
			if(tpX < 6){
				return null;
			}
			StrategoPiece piece = player1StartingUnits.remove();
			board.pieces[tpX][tpY] = piece;
			activeUnits.add(piece);
		}
			
			
		if(!player0StartingUnits.isEmpty()){
			System.out.println("Place " + player0StartingUnits.peek().getName());	//TODO switch to UI console
		} else if(!player1StartingUnits.isEmpty()){
			System.out.println("Place " + player1StartingUnits.peek().getName());	//TODO switch to UI console
		}
		
		if (placementPhase0 && player0StartingUnits.isEmpty()){
			placementPhase0 = false;
			changeTurn();
			return "ChangingTurn";
		} else if(placementPhase1 && player1StartingUnits.isEmpty()){
			placementPhase1 = false;
			changeTurn();
			return "ChangingTurn";
		}
		return null;
	}
	
	private void movePiece(StrategoPiece selectedPiece, StrategoPiece targetedPiece){
		int spPos[] = board.getPosition(selectedPiece);
		int tpPos[] = board.getPosition(targetedPiece);
		int spX = spPos[0];
		int spY = spPos[1];
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		
		switch(targetedPiece.getName()){
			case StrategoPieceFactory.EMPTY:
				board.pieces[spX][spY] = targetedPiece;
				board.pieces[tpX][tpY] = selectedPiece;
				return;
			case StrategoPieceFactory.FLAG:			
				if(turn == 0){
					player1FlagExists = false;
				} else{
					player0FlagExists = false;
				}
				activeUnits.remove(targetedPiece);
				board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				board.pieces[tpX][tpY] = selectedPiece;
				selectedPiece.setHidden(false);
				break;
			case StrategoPieceFactory.BOMB:			
				if(selectedPiece.getRank() == '7'){
					activeUnits.remove(targetedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
					selectedPiece.setHidden(false);
				}else{
					activeUnits.remove(selectedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					targetedPiece.setHidden(false);
				}
				break;
			case StrategoPieceFactory.MARSHAL:			
				if(selectedPiece.getRank() == '9'){
					activeUnits.remove(targetedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
					selectedPiece.setHidden(false);
				}else{
					activeUnits.remove(selectedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					targetedPiece.setHidden(false);
				}
				break;
			default:
				if(selectedPiece.getRank() < targetedPiece.getRank()){
					activeUnits.remove(targetedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
					selectedPiece.setHidden(false);
				} else if (selectedPiece.getRank() == targetedPiece.getRank()){
					activeUnits.remove(targetedPiece);
					activeUnits.remove(selectedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				} else{
					activeUnits.remove(selectedPiece);
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					targetedPiece.setHidden(false);
				}
		}
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		selectedPiece.setSelected(false);
	}
	
	private boolean isAdjacent(Piece selectedPiece, Piece targetedPiece){
		int spPos[] = board.getPosition(selectedPiece);
		int tpPos[] = board.getPosition(targetedPiece);
		int spX = spPos[0];
		int spY = spPos[1];
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		if(spX + 1 == tpX || spX - 1 == tpX){
			return spY == tpY;
		}
		if(spY + 1 == tpY || spY - 1 == tpY){
			return spX == tpX;
		}
		return false;

	}

	private boolean isValidScoutMove(StrategoPiece selectedPiece, StrategoPiece targetedPiece){
		int spPos[] = board.getPosition(selectedPiece);
		int tpPos[] = board.getPosition(targetedPiece);
		int spX = spPos[0];
		int spY = spPos[1];
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		
		if(spX != tpX && spY != tpY){
			return false;
		}
		
		if(spX == tpX){
			if(tpY > spY){
				for(int y = spY+1; y < tpY; ++y){
					if(!((StrategoPiece)board.pieces[spX][y]).isEmpty()){
						return false;
					}
				}
			} else if(tpY < spY){
				for(int y = spY-1; y < tpY; --y){
					if(!((StrategoPiece)board.pieces[spX][y]).isEmpty()){
						return false;
					}
				}
			}
			((StrategoPiece)selectedPiece).setHidden(false);
			return true;
		} else if (spY == tpY){
			if(tpX > spX){
				for(int x = spX+1; x < tpX; ++x){
					if(!((StrategoPiece)board.pieces[x][spY]).isEmpty()){
						return false;
					}
				}
			} else if(tpX < spX){
				for(int x = spX-1; x < tpX; --x){
					if(!((StrategoPiece)board.pieces[x][spY]).isEmpty()){
						return false;
					}
				}
			}
			((StrategoPiece)selectedPiece).setHidden(false);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean checkValidMove(int x, int y) {
		return true;
	}

	@Override
	public boolean checkEnd() {
		if(!player0FlagExists){
			return true;
		}
		if(!player1FlagExists){
			return true;
		}
		return false;
	}
	
	@Override
	public void changeTurn(){
		super.changeTurn();
		for(int i = 0; i < board.getWidth(); ++i){
			for(int j = 0; j < board.getHeight(); ++j){
				((StrategoPiece)board.pieces[i][j]).setTurn(turn);
			}
		}
		for(StrategoPiece piece: player0StartingUnits){
			piece.setTurn(turn);
		}
		for(StrategoPiece piece: player1StartingUnits){
			piece.setTurn(turn);
		}
	}

	public Integer getP1Score(){return null;}
	public Integer getP2Score(){return null;}
	public int getWinner(){
		if(!player0FlagExists){
			return 1;
		} else {
			return 0;
		}
	}
}
