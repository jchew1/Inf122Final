import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import java.util.ArrayList;

class BoardTile extends JButton implements ActionListener{

	int x;
	int y;
	int buttonWidth;
	int buttonHeight;
	
	BoardTile(int x, int y, int buttonWidth, int buttonHeight){
		this.x = x;
		this.y = y;
		this.buttonWidth = buttonWidth;
		this.buttonHeight = buttonHeight;
		addActionListener(this);
		this.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		setImage(Driver.game.board.pieces[x][y].getIcon());
	}
	
	public void actionPerformed(ActionEvent e){
		System.out.printf("Tile Pressed %d,%d\n", x, y);
		ArrayList<Object> boardChange = Driver.game.makeMove(x,y);
		setImage(Driver.game.board.pieces[x][y].getIcon());
		if(boardChange != null){
			if(boardChange.get(0) != null){
				Driver.gameGUI.updateTiles((ArrayList<Piece>)boardChange.get(0));
			}
			if(boardChange.get(1) != null){
				Driver.gameGUI.boardPanel.setVisible(false);
				JOptionPane.showMessageDialog(Driver.gameGUI, boardChange.get(1));
				Driver.gameGUI.boardPanel.setVisible(true);
			}
		}
		Driver.gameGUI.updateScore();
		Driver.gameGUI.updateTurn();
	}

	void setImage(String icon){
		try{
			BufferedImage img = ImageIO.read(getClass().getResource("resources/" + icon));
			Image scaled = img.getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
			this.setIcon(new ImageIcon(scaled));
		}catch(Exception e){
			System.out.println(e);
		}
	}

}
