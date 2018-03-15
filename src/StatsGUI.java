import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import javax.swing.border.EmptyBorder;

public class StatsGUI extends JFrame{
	
	JPanel mainPanel = new JPanel();
	JPanel user1Panel = new JPanel();
	JPanel user2Panel = new JPanel();
	
	int width = 600;
	int height = 150;

	StatsGUI(User user1, User user2){
		super("Player Stats");
		setSize(width, height);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel p1 = new JLabel("Player 1: " + user1.name);
		JLabel p2 = new JLabel("Player 2: " + user2.name);

		mainPanel.setSize(width, height);
		user1Panel.setSize(width/2, height);
		user2Panel.setSize(width/2, height);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		user1Panel.setLayout(new BoxLayout(user1Panel, BoxLayout.Y_AXIS));
		user2Panel.setLayout(new BoxLayout(user2Panel, BoxLayout.Y_AXIS));

		user1Panel.setBorder(new EmptyBorder(0,0,0,100));
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
			user1Panel.add(new JLabel(me.getKey() + " Wins: "+ String.valueOf(user1.getStats((String)me.getKey())[0])+ " Ties: "+ String.valueOf(user1.getStats((String)me.getKey())[2])+ " Losses: "+ String.valueOf(user1.getStats((String)me.getKey())[1]))); 
		}

		// Get a set of the entries
		Set user2Set = user2.stats.entrySet();
		// Get an iterator
		Iterator i2 = user2Set.iterator();
		while(i2.hasNext()) {
			Map.Entry me = (Map.Entry)i2.next();
			//System.out.print(me.getKey() + ": ");
			//System.out.println(me.getValue());
			user2Panel.add(new JLabel(me.getKey() + " Wins: "+ String.valueOf(user2.getStats((String)me.getKey())[0])+ " Ties: "+ String.valueOf(user2.getStats((String)me.getKey())[2])+ " Losses: "+ String.valueOf(user2.getStats((String)me.getKey())[1])));
		}

		mainPanel.add(user1Panel);
		mainPanel.add(user2Panel);

		add(mainPanel);
		setVisible(true);
	}
}
	
