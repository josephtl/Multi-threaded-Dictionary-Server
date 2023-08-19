import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtWord;
	private JButton btnSearch;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnUpdate;


	/**
	 * Create the frame.
	 */
	public MainFrame(Dictionary dict) {

		setTitle("Dictionary");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 440, 440, 440, 0 };
		gbl_contentPane.rowHeights = new int[] { 26, 26, 26, 26, 26, 26, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel labelNewWord = new JLabel("Please type a word to search:");
		GridBagConstraints gbc_labelNewWord = new GridBagConstraints();
		gbc_labelNewWord.gridwidth = 3;
		gbc_labelNewWord.fill = GridBagConstraints.BOTH;
		gbc_labelNewWord.insets = new Insets(0, 0, 5, 5);
		gbc_labelNewWord.gridx = 0;
		gbc_labelNewWord.gridy = 1;
		contentPane.add(labelNewWord, gbc_labelNewWord);

		txtWord = new JTextField();
		GridBagConstraints gbc_txtWord = new GridBagConstraints();
		gbc_txtWord.gridwidth = 3;
		gbc_txtWord.fill = GridBagConstraints.BOTH;
		gbc_txtWord.insets = new Insets(0, 0, 5, 5);
		gbc_txtWord.gridx = 0;
		gbc_txtWord.gridy = 2;
		contentPane.add(txtWord, gbc_txtWord);
		txtWord.setColumns(10);

		// Add new word
		btnAdd = new JButton("Add New Word");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String newWord = txtWord.getText();
				if (dict.checkWord(newWord)) {
					WordExisted exist = new WordExisted(newWord);
					exist.setModal(true);
					exist.setVisible(true);
					exist.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else if (newWord == null || newWord.trim().isEmpty()) {
				} else {
					dict.addWord(newWord);
					AddWordOrUpdateDefs addword = new AddWordOrUpdateDefs(dict, newWord);

					addword.setVisible(true);
					addword.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				}

			}
		});

		// Search word
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtWord.getText();
				if (dict.checkWord(text)) {
					WordDefinition search = new WordDefinition(dict, text);
					search.setVisible(true);
					search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				} else if (text == null || text.trim().isEmpty()) {
					
				} else {
					NoWordFound noWordFound = new NoWordFound(dict, text);
					noWordFound.setModal(true);
					noWordFound.setVisible(true);
					noWordFound.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}

			}
		});

		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 2;
		contentPane.add(btnSearch, gbc_btnSearch);

		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.gridwidth = 4;
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 5;
		contentPane.add(btnAdd, gbc_btnAdd);

		// Remove word
		btnRemove = new JButton("Remove Word");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtWord.getText();
				if (dict.checkWord(text)) {
					
					dict.removeWord(text);
					dict.save(dict.getDictFile());
					WordRemoved wordRemoved = new WordRemoved(text);
					wordRemoved.setModal(true);
					wordRemoved.setVisible(true);
					wordRemoved.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} else if (text == null || text.trim().isEmpty()) {
				} else {
					NoWordFound noWordFound = new NoWordFound(dict, text);
					noWordFound.setModal(true);
					noWordFound.setVisible(true);
					noWordFound.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		GridBagConstraints gbc_btnRemove = new GridBagConstraints();
		gbc_btnRemove.gridwidth = 4;
		gbc_btnRemove.fill = GridBagConstraints.BOTH;
		gbc_btnRemove.insets = new Insets(0, 0, 5, 0);
		gbc_btnRemove.gridx = 0;
		gbc_btnRemove.gridy = 6;
		contentPane.add(btnRemove, gbc_btnRemove);

		// Update
		btnUpdate = new JButton("Update Meaning");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtWord.getText();
				if (dict.checkWord(text)) {
				
					UpdateWarning warning = new UpdateWarning(dict, text);
					warning.setVisible(true);
					warning.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					dispose();
				} else if (text == null || text.trim().isEmpty()) {
				} else {
					NoWordFound noWordFound = new NoWordFound(dict, text);
					noWordFound.setModal(true);
					noWordFound.setVisible(true);
					noWordFound.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.gridwidth = 4;
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.gridx = 0;
		gbc_btnUpdate.gridy = 7;
		contentPane.add(btnUpdate, gbc_btnUpdate);
	}
	

}
