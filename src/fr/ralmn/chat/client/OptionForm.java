package fr.ralmn.chat.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/*
 * Created by JFormDesigner on Thu May 03 23:12:54 CEST 2012
 */

/**
 * @author ralmn
 */
@SuppressWarnings("serial")
public class OptionForm extends JDialog {
	public OptionForm(Frame owner) {
		super(owner);
		initComponents();
	}

	public OptionForm(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void toggleEmoActionPerformed(ActionEvent e) {

		if (toggleButton1.isSelected()) {
			toggleButton1.setText("Emoticones activer");
		} else {
			toggleButton1.setText("Emoticones desactiver");
		}
		Option.emoticone = toggleButton1.isSelected();

		Util.writeConfig("emoticones", toggleButton1.isSelected() + "");

	}

	private void toggleSoundsActionPerformed(ActionEvent e) {
		if (toggleButton2.isSelected()) {
			toggleButton2.setText("Sons activer");
		} else {
			toggleButton2.setText("Sons desactiver");
		}
		Option.sound = toggleButton2.isSelected();

		Util.writeConfig("sound", toggleButton2.isSelected() + "");
	}

	private void okButtonActionPerformed(ActionEvent e) {
		this.dispose();
	}


	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		panel1 = new JPanel();
		toggleButton1 = new JToggleButton();
		toggleButton2 = new JToggleButton();
		buttonBar = new JPanel();
		okButton = new JButton();

		//======== this ========
		setTitle("Option");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(null);

			//======== panel1 ========
			{
				panel1.setBorder(new TitledBorder("Options de bases"));
				panel1.setLayout(null);

				//---- toggleButton1 ----
				toggleButton1.setText("Emoticones activer");
				toggleButton1.setToolTipText("Activer l'affichage des Emoticones dans le chat (DEFAULT : ACTIVER)");
				toggleButton1.setSelected(true);
				toggleButton1.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						toggleEmoActionPerformed(e);
					}
				});
				panel1.add(toggleButton1);
				toggleButton1.setBounds(25, 20, 200, 20);

				//---- toggleButton2 ----
				toggleButton2.setText("Sons activer");
				toggleButton2.setToolTipText("Activer l'avertisement sonore lors d'un nouveaux messages si la fenetre est reduite");
				toggleButton2.setSelected(true);
				toggleButton2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						toggleSoundsActionPerformed(e);
					}
				});
				panel1.add(toggleButton2);
				toggleButton2.setBounds(55, 45, 140, 20);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for(int i = 0; i < panel1.getComponentCount(); i++) {
						Rectangle bounds = panel1.getComponent(i).getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
						preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
					}
					Insets insets = panel1.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					panel1.setMinimumSize(preferredSize);
					panel1.setPreferredSize(preferredSize);
				}
			}
			dialogPane.add(panel1);
			panel1.setBounds(10, 15, 240, 90);

			//======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
				((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar);
			buttonBar.setBounds(12, 292, 705, buttonBar.getPreferredSize().height);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for(int i = 0; i < dialogPane.getComponentCount(); i++) {
					Rectangle bounds = dialogPane.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
				}
				Insets insets = dialogPane.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				dialogPane.setMinimumSize(preferredSize);
				dialogPane.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel panel1;
	private JToggleButton toggleButton1;
	private JToggleButton toggleButton2;
	private JPanel buttonBar;
	private JButton okButton;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
