import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class MainMenu extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JTextField p1t = new JTextField(20);
	JTextField p2t = new JTextField(20);
	String[] games = new String[]{"TicTacToe", "Stratego", "Pente", "Simon Says"};
	JComboBox<String> gamesList = new JComboBox<String>(games);

	MainMenu(int width, int height, String title){
		super(title);
		setSize(width, height);
		setResizable(false);
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		setPlayersPanel();
		setGamesPanel();

		add(mainPanel);

		setVisible(true);
	}

	void setPlayersPanel(){
		JPanel playersPanel = new JPanel();
		JPanel player1Panel = new JPanel();
		JPanel player2Panel = new JPanel();

		JLabel p1 = new JLabel("Player 1:");
		JLabel p2 = new JLabel("Player 2:");

		player1Panel.add(p1);
		player1Panel.add(p1t);
		player2Panel.add(p2);
		player2Panel.add(p2t);

		playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
		playersPanel.add(player1Panel);
		playersPanel.add(player2Panel);

		mainPanel.add(playersPanel);
	}

	void setGamesPanel(){
		JPanel gamesPanel = new JPanel();
		
		JPanel gamesListPanel = new JPanel();
		gamesListPanel.add(gamesList);

		JPanel startPanel = new JPanel();
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startButtonPressed();
			}
		});
		startPanel.add(startButton);

		gamesPanel.setLayout(new BoxLayout(gamesPanel, BoxLayout.Y_AXIS));
		gamesPanel.add(gamesListPanel);
		gamesPanel.add(startPanel);

		mainPanel.add(gamesPanel);

	}

	void startButtonPressed(){
		System.out.println("start button pressed");
		Driver.getUser(p1t.getText());
		Driver.getUser(p2t.getText());
		Driver.currentGame = (String)gamesList.getSelectedItem();
		setVisible(false);
	}

	void getUsernames(){
		System.out.println(p1t.getText());
		System.out.println(p2t.getText());
	}
}
