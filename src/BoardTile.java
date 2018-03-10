import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class BoardTile extends JButton implements ActionListener{

	int x;
	int y;
	
	BoardTile(int x, int y){
		this.x = x;
		this.y = y;
		addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.printf("Tile Pressed %d,%d\n", x, y);
		Driver.game.makeMove(x,y);
	}

}
