import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
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
public class NoWordFound extends JDialog {

	private final JPanel contentPanel = new JPanel();



	/**
	 * Create the dialog.
	 */
	public NoWordFound(Dictionary dict, String word) {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{65, 101, 78, 55, 101, 0};
		gridBagLayout.rowHeights = new int[]{10, 60, 16, 16, 92, 29, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				GridBagConstraints gbc_contentPanel = new GridBagConstraints();
				gbc_contentPanel.anchor = GridBagConstraints.NORTHWEST;
				gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
				gbc_contentPanel.gridx = 4;
				gbc_contentPanel.gridy = 0;
				getContentPane().add(contentPanel, gbc_contentPanel);
				contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
				{
					JLabel lblNewLabel = new JLabel("Could not find \"" + word + "\" in the dictionary!");
					lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
					GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
					gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
					gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
					gbc_lblNewLabel.gridwidth = 4;
					gbc_lblNewLabel.gridx = 1;
					gbc_lblNewLabel.gridy = 2;
					getContentPane().add(lblNewLabel, gbc_lblNewLabel);
				}
		
				JLabel lblNewLabel_1 = new JLabel("Please try another word!");
				lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.gridwidth = 2;
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 3;
				getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		{
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();
				}
			});
			GridBagConstraints gbc_btnOk = new GridBagConstraints();
			gbc_btnOk.gridx = 4;
			gbc_btnOk.gridy = 5;
			getContentPane().add(btnOk, gbc_btnOk);
			btnOk.setActionCommand("OK");
			getRootPane().setDefaultButton(btnOk);
		}
	}
}
