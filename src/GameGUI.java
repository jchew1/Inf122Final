import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GameGUI extends JFrame{
	
	JPanel boardPanel = new JPanel();
	ArrayList<ArrayList<BoardTile>> tiles = new ArrayList<ArrayList<BoardTile>>();

	GameGUI(int width, int height, String title, int x, int y){
		super(title);
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		boardPanel.setLayout(new GridLayout(x,y));
		for(int i=0; i<x; i++){
			tiles.add(new ArrayList<BoardTile>());
			for(int j=0; j<y; j++){
				tiles.get(i).add(new BoardTile(i,j));
				boardPanel.add(tiles.get(i).get(j));
			}
		}

		add(boardPanel);

		setVisible(true);
	}
}
