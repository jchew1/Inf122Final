
public class StrategoPiece extends Piece {
	
	private String name;
	private String hiddenIcon;
	private String icon;
	private String selectedIcon;
	

	private boolean selectable;
	private boolean targetable;
	private char rank;
	
	private boolean isHidden = true;
	private boolean isSelected = false;
	private int currTurn = 0;
	
	StrategoPiece(int player, boolean selectable, boolean targetable, char rank, String name) {
		super(player);
		this.selectable = selectable;
		this.targetable = targetable;
		this.rank = rank;
		this.name = name;
	}
	
	public boolean isSelectable(){
		return selectable;
	}
	
	public boolean isTargetable(){
		return targetable;
	}
	
	public boolean isEmpty(){
		return rank == 'e';
	}
	
	public char getRank(){
		return rank;
	}
	
	public String getName(){
		return name;
	}

	public void setIcon(String s){
		icon = s;
	}
	
	public void setHiddenIcon(String s){
		hiddenIcon = s;
	}
	
	public void setSelectedIcon(String s){
		selectedIcon = s;
	}
	public void setTurn(int t){
		currTurn = t;
	}
	
	public void setHidden(boolean b){
		isHidden = b;
	}
	
	public void setSelected(boolean b){
		isSelected = b;
	}
	
	@Override
	public String getIcon() {
		if (isSelected){
			return selectedIcon;
		}
		if (player == currTurn || !isHidden){
			return icon;
		}
		return hiddenIcon;
	}

}
