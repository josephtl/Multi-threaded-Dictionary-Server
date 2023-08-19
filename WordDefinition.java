import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */
@SuppressWarnings("serial")
public class WordDefinition extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public WordDefinition(Dictionary dict, String word) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Show word
		JLabel lblNewLabel = new JLabel(word);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(6, 6, 148, 51);
		contentPane.add(lblNewLabel);

		// Show Definition
		ArrayList<String> defs = dict.getDefinition(word);
		String defString = "";
		for (int i = 0; i < defs.size(); i++) {
			int number = i + 1;
			defString += number + ". ";
			defString += defs.get(i);
			if (i < defs.size() - 1) {
				defString += "<br>";
			}
		}

		JLabel lblWord = new JLabel("<html>" + defString + "</html>");
		lblWord.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblWord.setVerticalAlignment(SwingConstants.TOP);
		lblWord.setBounds(16, 58, 248, 139);
		contentPane.add(lblWord);

		// Back to Menu
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mainFrame = new MainFrame(dict);
				mainFrame.setVisible(true);
				mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dispose();

			}
		});
		btnBack.setBounds(6, 237, 117, 29);
		contentPane.add(btnBack);
	}
}
