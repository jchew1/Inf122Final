import java.util.LinkedList;
import java.util.Queue;

public class StrategoGL extends GameLogic {

	private boolean player0FlagExists = true;
	private boolean player1FlagExists = true;
	private int defaultWidth = 10;
	private int defaultHeight = 10;
	
	private boolean placementPhase = true;
	private Queue<Piece> player0StartingUnits = new LinkedList<Piece>();
	private Queue<Piece> player1StartingUnits = new LinkedList<Piece>();
	private StrategoPiece selectedPiece;
	
	public StrategoGL(){
		turn = 0;
		boardConstructor();
		fillStartingUnits();
	}
	
	@Override
	public void boardConstructor() {
		board = new Board(defaultWidth,defaultHeight);
		for(int i = 0; i < board.getWidth(); ++i){
			for(int j = 0; j < board.getHeight(); ++j){
				board.pieces[i][j] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
			}
		}
		board.pieces[2][4] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[2][5] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[3][4] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[3][5] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[6][4] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[6][5] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[7][4] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
		board.pieces[7][5] = StrategoPieceFactory.createPiece(StrategoPieceFactory.WATER, turn);
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
	}
	
	@Override
	public String makeMove(int x,int y) {
		StrategoPiece targetedPiece = (StrategoPiece) board.pieces[x][y];
		if(placementPhase){						//placing tiles in setup
			handlePlacePiece(targetedPiece);
			return null;
		}
		if(!targetedPiece.isTargetable()){		//selecting or targeting water tiles
			selectedPiece = null;
			return null;
		}
		if(selectedPiece == null){				//to select a piece
			if(targetedPiece.isSelectable() && targetedPiece.getPlayer() == turn){
				selectedPiece = targetedPiece;
			}
		}
		
		if(targetedPiece.getPlayer() == turn){	//targeting your own piece
			selectedPiece = targetedPiece;
		}
		
/*TODO: handle scout's special movement case
		if(selectedPiece.getRank() == '8' && validScoutMove(selectedPiece, targetedPiece)){
			movePiece(selectedPiece, targetedPiece);
			changeTurn();
		}
*/
		if(isAdjacent(selectedPiece, targetedPiece)){
			movePiece(selectedPiece, targetedPiece);	//for targeted empty space or enemies
			changeTurn();
		}

		return null;
	}


	//TODO
	private void handlePlacePiece(StrategoPiece targetedPiece){
		int tpPos[] = board.getPosition(targetedPiece);
		int tpX = tpPos[0];
		int tpY = tpPos[1];
	}
	
	private void movePiece(StrategoPiece selectedPiece, StrategoPiece targetedPiece){
		int spPos[] = board.getPosition(selectedPiece);
		int tpPos[] = board.getPosition(targetedPiece);
		int spX = spPos[0];
		int spY = spPos[1];
		int tpX = tpPos[0];
		int tpY = tpPos[1];
		
		switch(targetedPiece.getRank()){
			case 'e':			//move into empty space
				board.pieces[spX][spY] = targetedPiece;
				board.pieces[tpX][tpY] = selectedPiece;
				return;
			case 'f':			//capture flag
				if(turn == 0){
					player1FlagExists = false;
				} else{
					player0FlagExists = false;
				}
				board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				board.pieces[tpX][tpY] = selectedPiece;
				break;
			case 'b':			//bomb
				if(selectedPiece.getRank() == '7'){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				}else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				}
				break;
			case '1':			//marshal
				if(selectedPiece.getRank() == '9'){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				}else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				}
				break;
			default:
				if(selectedPiece.getRank() > targetedPiece.getRank()){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = selectedPiece;
				} else if (selectedPiece.getRank() == targetedPiece.getRank()){
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
					board.pieces[tpX][tpY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				} else{
					board.pieces[spX][spY] = StrategoPieceFactory.createPiece(StrategoPieceFactory.EMPTY, turn);
				}
		}
		selectedPiece.setHidden(false);
		targetedPiece.setHidden(false);
	}
	
	private boolean isAdjacent(Piece selectedPiece, Piece targetedPiece){
		int spPos[] = board.getPosition(selectedPiece);
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
		return false;
	}

	@Override
	public boolean checkValidMove(int x, int y) {
		return true;
	}

	@Override
	public boolean checkEnd() {
		if(!player0FlagExists){
			//player 2 wins and do something?
			return true;
		}
		if(!player1FlagExists){
			//player1 wins and do something?
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
	}

	public Integer getP1Score(){return null;}
	public Integer getP2Score(){return null;}
	public int getWinner(){return 0;}
}
