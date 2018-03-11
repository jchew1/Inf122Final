import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

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
		setImage(Driver.game.board.pieces[x][y].getIcon());
	}

	void setImage(String icon){
		try{
			Image img = ImageIO.read(getClass().getResource("resources/" + icon));
			this.setIcon(new ImageIcon(img));
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
