import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class StrategoGL extends GameLogic {

	private boolean player0FlagExists = true;
	private boolean player1FlagExists = true;
	private int defaultWidth = 10;
	private int defaultHeight = 10;
	
	private boolean placementPhase0 = true;
	private boolean placementPhase1 = true;
	private Queue<StrategoPiece> player0StartingUnits = new LinkedList<StrategoPiece>();
	private Queue<StrategoPiece> player1StartingUnits = new LinkedList<StrategoPiece>();
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
		System.out.println(turn);
		StrategoPiece targetedPiece = (StrategoPiece) board.pieces[x][y];
		if(placementPhase0 || placementPhase1){						//placing tiles in setup
			ArrayList<Object> result = new ArrayList<Object>();
			result.add(null);
			result.add(handlePlacePiece(targetedPiece));
			return result;
		}
		if(!targetedPiece.isTargetable()){		//selecting or targeting water tiles
			selectedPiece.setSelected(false);
			selectedPiece = null;
			return null;
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
			selectedPiece.setSelected(false);
			targetedPiece.setSelected(true);
			selectedPiece = targetedPiece;
			return null;
		}
		
/*TODO: handle scout's special movement case
		if(selectedPiece.getName() == StrategoPieceFactory.SCOUT && isValidScoutMove(selectedPiece, targetedPiece)){
			movePiece(selectedPiece, targetedPiece);
			changeTurn();
		}
*/
		if(isAdjacent(selectedPiece, targetedPiece)){
			movePiece(selectedPiece, targetedPiece);	//for targeted empty space or enemies
			selectedPiece.setSelected(false);
			selectedPiece = null;
			changeTurn();
			
			ArrayList<Object> result = new ArrayList<Object>();
			result.add(null);
			result.add("Player Moved");

			return result;
		}

		return null;
	}


	//TODO
	private String handlePlacePiece(StrategoPiece targetedPiece){
		int tpPos[] = board.getPosition(targetedPiece);
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		
		if(!targetedPiece.isEmpty()){
			return null;
		}
		
		if(placementPhase0){
			if(tpX > 6){
				return null;
			}
			
			board.pieces[tpX][tpY] =  player0StartingUnits.remove();
		} else if(placementPhase1){
			if(tpX < 3){
				return null;
			}
			board.pieces[tpX][tpY] = player1StartingUnits.remove();
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
				board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				board.pieces[tpX][tpY] = selectedPiece;
				break;
			case StrategoPieceFactory.BOMB:			
				if(selectedPiece.getRank() == '7'){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				}else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				}
				break;
			case StrategoPieceFactory.MARSHAL:			
				if(selectedPiece.getRank() == '9'){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				}else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				}
				break;
			default:
				if(selectedPiece.getRank() < targetedPiece.getRank()){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				} else if (selectedPiece.getRank() == targetedPiece.getRank()){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				} else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
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
/*		int spPos[] = board.getPosition(selectedPiece);
		int tpPos[] = board.getPosition(targetedPiece);
		int spX = spPos[0];
		int spY = spPos[1];
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		if(spX + 1 == tpX || spX - 1 == tpX){
			return spY == 0;
		}
		if(spY + 1 == tpY || spY - 1 == tpY){
			return spX == 0;
		}
		return false;*/
		return true;
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
			return 0;
		} else {
			return 1;
		}
	}
}
