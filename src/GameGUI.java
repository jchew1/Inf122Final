import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GameGUI extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	ArrayList<ArrayList<BoardTile>> tiles = new ArrayList<ArrayList<BoardTile>>();
	int boardWidth;
	int boardHeight;

	GameGUI(int boardWidth, int boardHeight, String title, int x, int y){
		super(title);
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		setSize(boardWidth+100, boardHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		mainPanel.setSize(boardWidth+100, boardHeight);
		mainPanel.setLayout(new BorderLayout());

		setBoardPanel(x, y);
		setScorePanel();

		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);
		printTileSizes(x,y);

	}

	void printTileSizes(int x, int y){
		for(int i=0; i<x; i++){
			for(int j=0; j<y; j++){
				System.out.printf("%d, %d: %d, %d\n", i, j, tiles.get(i).get(j).getWidth(), tiles.get(i).get(j).getHeight());
			}
		}
	}
	
	void setBoardPanel(int x, int y){
		int buttonWidth = boardWidth/x;
		int buttonHeight = boardHeight/y;
		System.out.printf("button: %d, %d\n", buttonWidth, buttonHeight);
		boardPanel.setLayout(new GridLayout(x,y));
		for(int i=0; i<x; i++){
			tiles.add(new ArrayList<BoardTile>());
			for(int j=0; j<y; j++){
				tiles.get(i).add(new BoardTile(i,j,buttonWidth, buttonHeight));
				boardPanel.add(tiles.get(i).get(j));
			}
		}
		mainPanel.add(boardPanel, BorderLayout.LINE_START);
	}

	void setScorePanel(){
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));

		JPanel p1Panel = new JPanel();
		JPanel p2Panel = new JPanel();

		JLabel p1 = new JLabel(Driver.user1.getName());
		JLabel p2 = new JLabel(Driver.user2.getName());

		p1Panel.add(p1);
		p2Panel.add(p2);

		scorePanel.add(p1Panel);
		scorePanel.add(p2Panel);

		mainPanel.add(scorePanel, BorderLayout.LINE_END);

	}
}
