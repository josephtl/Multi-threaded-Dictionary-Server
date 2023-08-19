import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/**
 * @author Joseph Liu
 * author email: tianlinl@student.unimelb.edu.au
 * author student number: 1101805
 */
@SuppressWarnings("serial")
public class NoDef extends JDialog {

	private final JPanel contentPanel = new JPanel();



	/**
	 * Create the dialog.
	 */
	public NoDef(Dictionary dict, String word) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Please add definition!");
			lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(56, 94, 337, 38);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnOk = new JButton("OK");
				btnOk.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddWordOrUpdateDefs addWord = new AddWordOrUpdateDefs(dict, word);
						addWord.setVisible(true);
						addWord.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						dispose();
					}
				});
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				JButton btnDeleteWord = new JButton("Delete Word");
				btnDeleteWord.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dict.removeWord(word);
						MainFrame mainFrame = new MainFrame(dict);
						mainFrame.setVisible(true);
						mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						dispose();
					}
				});
				btnDeleteWord.setActionCommand("Cancel");
				buttonPane.add(btnDeleteWord);
			}
		}
	}

}
