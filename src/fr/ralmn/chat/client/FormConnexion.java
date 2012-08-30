package fr.ralmn.chat.client;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Created by JFormDesigner on Fri Apr 27 18:47:09 CEST 2012
 */

/**
 * @author ralmn
 */
public class FormConnexion extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924945724633267030L;

	public FormConnexion(Frame owner) {
		super(owner);
		initComponents();
	}

	public FormConnexion(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		String p = String.valueOf(this.passwordField1.getPassword());
		Util.writeConfig("username", textField2.getText());
		Util.writeConfig("server", textField1.getText());
		if (remember.isSelected()) {
			Util.writeConfig("password", p);
		} else
			Util.writeConfig("password", "");
		if (p.equals("") || p == null) {
			Main.Login(this.textField1.getText(), this.textField2.getText());
		} else
			Main.Login(this.textField1.getText(), this.textField2.getText(), p);

		Util.writeConfig("username", textField2.getText());
		Util.writeConfig("server", textField1.getText());
		this.setVisible(false);

	}

	private void cancelButtonActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		contentPanel = new JPanel();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		textField1 = new JTextField();
		label1 = new JLabel();
		label2 = new JLabel();
		passwordField1 = new JPasswordField();
		textField2 = new JTextField();
		label3 = new JLabel();
		remember = new JCheckBox();
		autoconnect = new JCheckBox();
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				textField1.setText(Util.getLastServer());
			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}

		});
		// ======== this ========
		setTitle("Connexion");
		Container contentPane = getContentPane();
		contentPane.setLayout(null);

		// ======== contentPanel ========
		{
			contentPanel.setLayout(null);

			{ // compute preferred size
				Dimension preferredSize = new Dimension();
				for (int i = 0; i < contentPanel.getComponentCount(); i++) {
					Rectangle bounds = contentPanel.getComponent(i).getBounds();
					preferredSize.width = Math.max(bounds.x + bounds.width,
							preferredSize.width);
					preferredSize.height = Math.max(bounds.y + bounds.height,
							preferredSize.height);
				}
				Insets insets = contentPanel.getInsets();
				preferredSize.width += insets.right;
				preferredSize.height += insets.bottom;
				contentPanel.setMinimumSize(preferredSize);
				contentPanel.setPreferredSize(preferredSize);
			}
		}
		contentPane.add(contentPanel);
		contentPanel.setBounds(0, 0, 384,
				contentPanel.getPreferredSize().height);

		// ======== buttonBar ========
		{
			buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
			buttonBar.setLayout(new GridBagLayout());
			((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {
					0, 85, 80 };
			((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {
					1.0, 0.0, 0.0 };

			// ---- okButton ----
			okButton.setText("OK");
			okButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					okButtonActionPerformed(e);
				}
			});
			buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 5), 0, 0));

			// ---- cancelButton ----
			cancelButton.setText("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cancelButtonActionPerformed(e);
				}
			});
			buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0,
					0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
					new Insets(0, 0, 0, 0), 0, 0));
		}
		contentPane.add(buttonBar);
		buttonBar.setBounds(5, 110, 384, buttonBar.getPreferredSize().height);

		// ---- textField1 ----
		textField1.setHorizontalAlignment(SwingConstants.LEFT);
		textField1.setText("127.0.0.1");
		contentPane.add(textField1);
		textField1.setBounds(130, 10, 105, 25);

		// ---- label1 ----
		label1.setText("Ip de connexion : ");
		contentPane.add(label1);
		label1.setBounds(10, 5, 110, 35);

		// ---- label2 ----
		label2.setText("Login :");
		contentPane.add(label2);
		label2.setBounds(10, 40, 80, label2.getPreferredSize().height);
		contentPane.add(passwordField1);
		try {
			passwordField1.setText(Util.readConfig("password"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		textField2.setText(Util.readConfig("username"));
		passwordField1.setBounds(130, 65, 105,
				passwordField1.getPreferredSize().height);
		contentPane.add(textField2);
		passwordField1.setToolTipText("Ce champs est facultatif ! ");
		String p = String.valueOf(passwordField1.getPassword());
		textField2
				.setBounds(130, 40, 105, textField2.getPreferredSize().height);

		// ---- label3 ----
		label3.setText("PassWord");
		contentPane.add(label3);
		contentPane.add(remember);
		label3.setBounds(10, 65, 120, label3.getPreferredSize().height);
		remember.setText("Se souvenir de moi");

		remember.setSelected(p.length() > 0);
		remember.setBounds(10, 90, 200, 20);
		remember.setVisible(true);
		remember.updateUI();
		
		remember.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				autoconnect.setEnabled(remember.isSelected());
				if(!remember.isSelected())
					autoconnect.setSelected(false);
			}
		});
		
		
		autoconnect.setSelected(Option.auto_connect);
		autoconnect.setEnabled(remember.isSelected());
		autoconnect.setText("Connexion automatique");
		autoconnect.setBounds(10, 110, 200, 10);
		autoconnect.setVisible(true);
		autoconnect.updateUI();
		contentPane.add(autoconnect);
		
		

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for (int i = 0; i < contentPane.getComponentCount(); i++) {
				Rectangle bounds = contentPane.getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width,
						preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height,
						preferredSize.height);
			}
			Insets insets = contentPane.getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			contentPane.setMinimumSize(preferredSize);
			contentPane.setPreferredSize(preferredSize);
		}
		contentPanel.setBounds(0, 0, 384,
				contentPanel.getPreferredSize().height);

		pack();
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel contentPanel;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField textField1;
	private JLabel label1;
	private JLabel label2;
	private JPasswordField passwordField1;
	private JTextField textField2;
	private JLabel label3;
	private JCheckBox remember;
	private JCheckBox autoconnect;
	
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
