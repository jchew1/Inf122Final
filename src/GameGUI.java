import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.event.WindowEvent;

public class GameGUI extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	JPanel consolePanel = new JPanel();
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
		setConsolePanel();
		setSidePanel();

		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);

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

		JLabel p1 = new JLabel(Driver.user1.getName()+": ");
		JLabel p2 = new JLabel(Driver.user2.getName()+": ");

		p1Panel.add(p1);
		p2Panel.add(p2);

		scorePanel.add(p1Panel);
		scorePanel.add(p2Panel);


	}

	void setConsolePanel(){
		JLabel consoleLabel = new JLabel();
		consolePanel.add(consoleLabel);
	}

	void setSidePanel(){
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.add(scorePanel);
		sidePanel.add(consolePanel);
		mainPanel.add(sidePanel);
	}

	void gameComplete(){
		switch(Driver.game.getWinner()){
			case -1:
				JOptionPane.showMessageDialog(this, "DRAW");
				break;
			case 0:
				JOptionPane.showMessageDialog(this, "WINNER: "+Driver.user1.getName());
				break;
			case 1:
				JOptionPane.showMessageDialog(this, "WINNER: "+Driver.user2.getName());
				break;
			default:
				System.out.println("winner error");
		}
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
