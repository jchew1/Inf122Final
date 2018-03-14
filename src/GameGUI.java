import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GameGUI extends JFrame{
	
	JPanel boardPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	ArrayList<ArrayList<BoardTile>> tiles = new ArrayList<ArrayList<BoardTile>>();

	GameGUI(int width, int height, String title, int x, int y){
		super(title);
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		

		setBoardPanel(x, y);
		setScorePanel();

		setVisible(true);
	}
	
	void setBoardPanel(int x, int y){
		boardPanel.setLayout(new GridLayout(x,y));
		for(int i=0; i<x; i++){
			tiles.add(new ArrayList<BoardTile>());
			for(int j=0; j<y; j++){
				tiles.get(i).add(new BoardTile(i,j));
				boardPanel.add(tiles.get(i).get(j));
			}
		}
		add(boardPanel, BorderLayout.CENTER);
	}

	void setScorePanel(){
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

		JPanel p1Panel = new JPanel();
		JPanel p2Panel = new JPanel();

		JLabel p1 = new JLabel("Player 1: ");
		JLabel p2 = new JLabel("PLayer 2: ");	

		p1Panel.add(p1);
		p2Panel.add(p2);

		scorePanel.add(p1Panel);
		scorePanel.add(p2Panel);

		add(scorePanel, BorderLayout.LINE_END);

	}
}
