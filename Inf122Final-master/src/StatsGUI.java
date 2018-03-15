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
import java.util.*;

public class StatsGUI extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JPanel user1Panel = new JPanel();
	JPanel user2Panel = new JPanel();
	
	String[] games = new String[]{"TicTacToe", "Pente", "SimonSays", "Stratego"};
	JComboBox<String> gamesList = new JComboBox<String>(games);

	int width =600;
	int height = 150;

	StatsGUI(User user1, User user2){
		super("User Stats");
		setSize(width, height);
		setResizable(false);
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		//p1g1: player 1 game type 1
		JLabel p1 = new JLabel("Player: " + user1.name);
		JLabel p2 = new JLabel("Player 2: " + user2.name);
		// JLabel p1g1 = new JLabel("gameName: Stratego    Wins: "+ user.stats.get("Stratego") + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p1g2 = new JLabel("gameName: TicTacToe   Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p1g3 = new JLabel("gameName: Pente       Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p1g4 = new JLabel("gameName: SimonSays   Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());

		// JLabel p2g1 = new JLabel("gameName: Stratego    Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p2g2 = new JLabel("gameName: TicTacToe   Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p2g3 = new JLabel("gameName: Pente       Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());
		// JLabel p2g4 = new JLabel("gameName: SimonSays   Wins: "+ user.stats.get() + "Losses: " + user.stats.get() + "Ties: " + user.stats.get());

		mainPanel.setSize(width, height);
		user1Panel.setSize(width/2, height);
		user2Panel.setSize(width/2, height);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		user1Panel.setLayout(new BoxLayout(user1Panel, BoxLayout.Y_AXIS));
		user2Panel.setLayout(new BoxLayout(user2Panel, BoxLayout.Y_AXIS));


		user1Panel.add(p1);
		user2Panel.add(p2);

		// Get a set of the entries
      Set user1Set = user1.stats.entrySet();
      
      // Get an iterator
      Iterator i = user1Set.iterator();
     

      // Display elements
      while(i.hasNext()) {
         Map.Entry me = (Map.Entry)i.next();
         //System.out.print(me.getKey() + ": ");
         //System.out.println(me.getValue());

         user1Panel.add(new JLabel("gameName: "+ me.getKey() + "    Wins: "+ String.valueOf(user1.getStats((String)me.getKey())[0])+ "    Ties: "+ String.valueOf(user1.getStats((String)me.getKey())[1])+ "    Losses: "+ String.valueOf(user1.getStats((String)me.getKey())[2]))); 

      }




      Set user2Set = user2.stats.entrySet();
      
      // Get an iterator
      Iterator i2 = user2Set.iterator();

      while(i2.hasNext()) {
         Map.Entry me = (Map.Entry)i2.next();
         //System.out.print(me.getKey() + ": ");
         //System.out.println(me.getValue());

         user2Panel.add(new JLabel("gameName: "+ me.getKey() + "    Wins: "+ String.valueOf(user2.getStats((String)me.getKey())[0])+ "    Ties: "+ String.valueOf(user2.getStats((String)me.getKey())[1])+ "    Losses: "+ String.valueOf(user2.getStats((String)me.getKey())[2])));
      }




		
		// user1Panel.add(p1);
		// user1Panel.add(p1g1);
		// user1Panel.add(p1g2);
		// user1Panel.add(p1g3);
		// user1Panel.add(p1g4);
		
		// user2Panel.add(p2);
		// user2Panel.add(p2g1);
		// user2Panel.add(p2g2);
		// user2Panel.add(p2g3);
		// user2Panel.add(p2g4);



		mainPanel.add(user1Panel);
		mainPanel.add(user2Panel);



		add(mainPanel);
		setVisible(true);
	}
}
	