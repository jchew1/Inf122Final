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
	public static StrategoPiece createPiece(String tag, int player){
		switch(tag){
			case "empty":
				return new StrategoPiece(-1, false, true, 'e');
			case "water":
				return new StrategoPiece(-1, false, false, 'w');
			case "flag":
				return new StrategoPiece(player, false, true, 'f');
			case "bomb":
				return new StrategoPiece(player, false, true, 'b');
			case "marshal":
				return new StrategoPiece(player, true, true, '0');
			case "general":
				return new StrategoPiece(player, true, true, '1');
			case "colonel":
				return new StrategoPiece(player, true, true, '2');
			case "major":
				return new StrategoPiece(player, true, true, '3');
			case "captain":
				return new StrategoPiece(player, true, true, '4');
			case "lieutenant":
				return new StrategoPiece(player, true, true, '5');
			case "sergeant":
				return new StrategoPiece(player, true, true, '6');
			case "miner":
				return new StrategoPiece(player, true, true, '7');
			case "scout":
				return new StrategoPiece(player, true, true, '8');
			case "spy":
				return new StrategoPiece(player, true, true, '9');
			default:
				return null;
		}
	}
}
