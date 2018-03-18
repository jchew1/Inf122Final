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
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class GameGUI extends JFrame{
	
	final static int sidePanelWidth = 400;
	JPanel mainPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JPanel scorePanel = new JPanel();
	JLabel p1Score = new JLabel();
	JLabel p2Score = new JLabel();
	JPanel consolePanel = new JPanel();
	JLabel consoleLabel = new JLabel();
	ArrayList<ArrayList<BoardTile>> tiles = new ArrayList<ArrayList<BoardTile>>();
	int boardWidth;
	int boardHeight;

	GameGUI(int boardWidth, int boardHeight, String title, int x, int y){
		super(title);
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		setSize(boardWidth+sidePanelWidth, boardHeight);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		mainPanel.setSize(boardWidth+sidePanelWidth, boardHeight);
		mainPanel.setLayout(new BorderLayout());

		setBoardPanel(x, y);
		setScorePanel();
		setConsolePanel();
		setSidePanel();

		add(mainPanel, BorderLayout.CENTER);
		setVisible(true);

		updateScore();
		updateTurn();

	}

	void setBoardPanel(int x, int y){
		int buttonWidth = boardWidth/x;
		int buttonHeight = boardHeight/y;
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

		p1Panel.setLayout(new BoxLayout(p1Panel, BoxLayout.X_AXIS));
		p2Panel.setLayout(new BoxLayout(p2Panel, BoxLayout.X_AXIS));

		JLabel p1 = new JLabel(Driver.user1.getName()+": ");
		p1.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		JLabel p2 = new JLabel(Driver.user2.getName()+": ");
		p2.setFont(new Font("timesRoman", Font.PLAIN, 24));
	
		p1Score.setFont(new Font("TimesRoman", Font.PLAIN, 24));
		p2Score.setFont(new Font("TimesRoman", Font.PLAIN, 24));

		p1Panel.add(p1);
		p1Panel.add(p1Score);
		p2Panel.add(p2);
		p2Panel.add(p2Score);

		p1Panel.setBorder(new EmptyBorder(0,0,50,0));
		p2Panel.setBorder(new EmptyBorder(50,0,0,0));

		scorePanel.add(p1Panel);
		scorePanel.add(p2Panel);
	}

	void updateScore(){
		Integer score1 = Driver.game.getP1Score();
		Integer score2 = Driver.game.getP2Score();
		System.out.printf("p1: %d\n", score1);
		System.out.printf("p2: %d\n", score2);
		if(score1 != null){
			System.out.println("updating p1 score");
			p1Score.setText(score1.toString());
		}
		if(score2 != null){
			System.out.println("updating p2 score");
			p2Score.setText(score2.toString());
		}
	}

	void setConsolePanel(){
		consolePanel.setBorder(new EmptyBorder(200, 0,0,0));
		consoleLabel.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		consolePanel.add(consoleLabel);
	}

	void updateTurn(){
		if(Driver.game.getTurn() == 0){
			consoleLabel.setText("Turn: "+Driver.user1.getName());
			consoleLabel.setForeground(Color.RED);
		}else{
			consoleLabel.setText("Turn: "+Driver.user2.getName());
			consoleLabel.setForeground(Color.BLUE);
		}
	}

	void setSidePanel(){
		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		sidePanel.add(scorePanel);
		sidePanel.add(consolePanel);
		mainPanel.add(sidePanel);
	}

	void updateTiles(ArrayList<Piece> pieces){
		for(int i=0; i<pieces.size(); i++){
			int[] location = Driver.game.board.getPosition(pieces.get(i));
			tiles.get(location[0]).get(location[1]).setImage(pieces.get(i).getIcon());
		}
	}

	void gameComplete(){
		System.out.println("gameComplete winner: "+Driver.game.getWinner());
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
