import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

/**
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */
@SuppressWarnings("serial")
public class AddWordOrUpdateDefs extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDef;

	
	/**
	 * Create the frame.
	 */
	public AddWordOrUpdateDefs(Dictionary dict, String word) {
		// clear Definition
		dict.clearDef(word);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldDef = new JTextField();
		textFieldDef.setBounds(12, 89, 438, 26);
		contentPane.add(textFieldDef);
		textFieldDef.setColumns(10);
		
		
		// Back to menu
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (dict.getDefinition(word).size() == 0) {
					NoDef noDef = new NoDef(dict, word);
					noDef.setVisible(true);
					noDef.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				}
				else {
					MainFrame mainFrame = new MainFrame(dict);
					mainFrame.setVisible(true);
					mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dict.save(dict.getDictFile());
					dispose();
				}
				
			}
		});
		btnBack.setBounds(327, 237, 117, 29);
		contentPane.add(btnBack);

		JLabel lblNewDef = new JLabel("New Definition:");
		lblNewDef.setBounds(12, 74, 432, 16);
		contentPane.add(lblNewDef);

		JLabel lblWord = new JLabel(word);
		lblWord.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblWord.setBounds(12, 26, 432, 36);
		contentPane.add(lblWord);

		// Add definitions
		JButton btnAdd = new JButton("Add Definition");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newDef = textFieldDef.getText();
				dict.addDef(word, newDef);
				textFieldDef.setText("");
			}
		});
		btnAdd.setBounds(6, 237, 136, 29);
		contentPane.add(btnAdd);
	}

}
