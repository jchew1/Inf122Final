import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class BoardTile extends JButton implements ActionListener{
	
	BoardTile(){
		addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.println("Tile Pressed");
	}

}
