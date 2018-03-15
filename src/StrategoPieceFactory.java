public class StrategoPieceFactory {
	public static final String EMPTY = "empty";
	public static final String WATER = "water";
	
	public static final String FLAG = "flag";
	public static final String BOMB = "bomb";
	
	public static final String MARSHAL = "marshal";
	public static final String GENERAL = "general";
	public static final String COLONEL = "colonel";
	public static final String MAJOR = "major";
	public static final String CAPTAIN = "captain";
	public static final String LIEUTENANT = "lieutenant";
	public static final String SERGEANT = "sergeant";
	public static final String MINER = "miner";
	public static final String SCOUT = "scout";
	public static final String SPY = "spy";
	
	//for reference: player, selectable, targetable, rank
	//red = 0, green/blue = 1
	public static StrategoPiece createPiece(String tag, int player){
		StrategoPiece piece;
		switch(tag){
			case "empty":
				piece = new StrategoPiece(-1, false, true, 'e', EMPTY);
				piece.setIcon("emptySquare.png");
				piece.setHiddenIcon("emptySquare.png");
				piece.setSelectedIcon("emptySquare.png");
				return piece;
			case "water":
				piece = new StrategoPiece(-1, false, false, 'w', WATER);
				piece.setIcon("blue.png");
				piece.setHiddenIcon("blue.png");
				piece.setSelectedIcon("blue.png");
				return piece;
			case "flag":
				piece = new StrategoPiece(player, false, true, 'f', FLAG);
				if(player == 0){
					piece.setIcon("Stratego_F_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
				} else{
					piece.setIcon("Stratego_F_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
				}
				return piece;
			case "bomb":
				piece = new StrategoPiece(player, false, true, 'b', BOMB);
				if(player == 0){
					piece.setIcon("Stratego_B_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
				} else{
					piece.setIcon("Stratego_B_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
				}
				return piece;
			case "marshal":
				piece = new StrategoPiece(player, true, true, '0', MARSHAL);
				if(player == 0){
					piece.setIcon("Stratego_1_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_1_R_H.png");
				} else{
					piece.setIcon("Stratego_1_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_1_R_H.png");
				}
				return piece;
			case "general":
				piece = new StrategoPiece(player, true, true, '1', GENERAL);
				if(player == 0){
					piece.setIcon("Stratego_2_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_2_R_H.png");
				} else{
					piece.setIcon("Stratego_2_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_2_R_H.png");
				}
				return piece;
			case "colonel":
				piece = new StrategoPiece(player, true, true, '2', COLONEL);
				if(player == 0){
					piece.setIcon("Stratego_3_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_3_R_H.png");
				} else{
					piece.setIcon("Stratego_3_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_3_R_H.png");
				}
				return piece;
			case "major":
				piece = new StrategoPiece(player, true, true, '3', MAJOR);
				if(player == 0){
					piece.setIcon("Stratego_4_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_4_R_H.png");
				} else{
					piece.setIcon("Stratego_4_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_4_R_H.png");
				}
				return piece;
			case "captain":
				piece = new StrategoPiece(player, true, true, '4', CAPTAIN);
				if(player == 0){
					piece.setIcon("Stratego_5_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_5_R_H.png");
				} else{
					piece.setIcon("Stratego_5_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_5_R_H.png");
				}
				return piece;
			case "lieutenant":
				piece = new StrategoPiece(player, true, true, '5', LIEUTENANT);
				if(player == 0){
					piece.setIcon("Stratego_6_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_6_R_H.png");
				} else{
					piece.setIcon("Stratego_6_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_6_R_H.png");
				}
				return piece;
			case "sergeant":
				piece = new StrategoPiece(player, true, true, '6', SERGEANT);
				if(player == 0){
					piece.setIcon("Stratego_7_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_7_R_H.png");
				} else{
					piece.setIcon("Stratego_7_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_7_R_H.png");
				}
				return piece;
			case "miner":
				piece = new StrategoPiece(player, true, true, '7', MINER);
				if(player == 0){
					piece.setIcon("Stratego_8_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_8_R_H.png");
				} else{
					piece.setIcon("Stratego_8_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_8_R_H.png");
				}
				return piece;
			case "scout":
				piece = new StrategoPiece(player, true, true, '8', SCOUT);
				if(player == 0){
					piece.setIcon("Stratego_9_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_9_R_H.png");
				} else{
					piece.setIcon("Stratego_9_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_9_R_H.png");
				}
				return piece;
			case "spy":
				piece = new StrategoPiece(player, true, true, '9', SPY);
				if(player == 0){
					piece.setIcon("Stratego_S_R.png");
					piece.setHiddenIcon("Stratego_H_R.png");
					piece.setSelectedIcon("Stratego_S_R_H.png");
				} else{
					piece.setIcon("Stratego_S_B.png");
					piece.setHiddenIcon("Stratego_H_B.png");
					piece.setSelectedIcon("Stratego_S_R_H.png");
				}
				return piece;
			default:
				return null;
		}
	}
}
