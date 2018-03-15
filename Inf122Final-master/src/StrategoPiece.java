
public class StrategoPiece extends Piece {
	
	private String hiddenIcon;
	private String icon;
	
	private boolean selectable;
	private boolean targetable;
	private char rank;
	
	private boolean isHidden = false;
	private int currTurn = 0;
	
	StrategoPiece(int player, boolean selectable, boolean targetable, char rank) {
		super(player);
		this.selectable = selectable;
		this.targetable = targetable;
		this.rank = rank;
	}
	
	public boolean isSelectable(){
		return selectable;
	}
	
	public boolean isTargetable(){
		return targetable;
	}
	
	public char getRank(){
		return rank;
	}

	public void setIcon(String s){
		icon = s;
	}
	
	public void setHiddenIcon(String s){
		hiddenIcon = s;
	}
	
	public void setBothIcons(String s){
		setIcon(s);
		setHiddenIcon(s);
	}
	public void setTurn(int t){
		currTurn = t;
	}
	
	public void setHidden(boolean b){
		isHidden = b;
	}
	
	@Override
	public String getIcon() {
		if (player == currTurn || !isHidden){
			return icon;
		} else{
			return hiddenIcon;
		}

	}

}
