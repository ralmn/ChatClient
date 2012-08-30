package fr.ralmn.chat.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

/*
 * Created by JFormDesigner on Fri Apr 27 18:33:57 CEST 2012
 */

/**
 * @author ralmn
 */
@SuppressWarnings("serial")
public class formChat extends JFrame {
	public formChat() {
		initComponents();
	}

	private void decoItemMenuActionPerformed(ActionEvent e) {

		try {
			Main.disconnecting();
			this.menuItem1.setEnabled(true);
			setTitle("Chat by RALMN - non connecter");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	private void conActionPerformed(ActionEvent e) {

		FormConnexion co = new FormConnexion(this);
		co.show();

	}

	private void textField1KeyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER
				&& !this.textField1.getText().equals(""))
			sendmsg();
	}

	private void okButtonActionPerformed(ActionEvent e) {
		if (!this.textField1.getText().equals(""))
			sendmsg();
	}

	private void sendmsg() {
		String t = this.textField1.getText();

		this.textField1.setText("");

		if (Main.socket == null) {
			System.err.println("Client non connecter");
			return;
		}

		try {
			PrintWriter out = new PrintWriter(Main.socket.getOutputStream());
			out.println(t);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void menuItem3ActionPerformed(ActionEvent e) {
		try {
			setTitle("Chat by RALMN - non connecter");
			Main.disconnecting();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(1);
	}

	private void thisWindowClosing(WindowEvent e) {
		try {
			Main.disconnecting();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.exit(1);
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		scrollPane1 = new JScrollPane();
		editorPane1 = new JEditorPane();
		buttonBar = new JPanel();
		textField1 = new JTextField();
		okButton = new JButton();
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		menuItem3 = new JMenuItem();
		menu2 = new JMenu();
		menuItem1 = new JMenuItem();
		menuItem2 = new JMenuItem();
		menuItem4 = new JMenuItem();
		// ======== this ========
		setTitle("Chat by RALMN - non connecter");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				thisWindowClosing(e);
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				if (Main.socket == null) {
					menuItem2.setEnabled(false);
				}
			}

		});
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		// ======== dialogPane ========
		{
			dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
			dialogPane.setLayout(new BorderLayout());

			// ======== contentPanel ========
			{
				contentPanel.setLayout(null);

				// ======== scrollPane1 ========
				{
					scrollPane1.setAutoscrolls(true);
					scrollPane1.setFocusable(true);
					scrollPane1
							.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
					scrollPane1.setEnabled(true);

					// ---- editorPane1 ----
					editorPane1.setEditable(false);
					editorPane1.setContentType("text/html");
					editorPane1
							.setText("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n  \n  </body>\r\n</html>\r\n");
					scrollPane1.setViewportView(editorPane1);
				}
				contentPanel.add(scrollPane1);
				scrollPane1.setBounds(0, 0, 635, 255);
				scrollPane1
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

				DefaultCaret c = (DefaultCaret) editorPane1.getCaret();
				c.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

				{ // compute preferred size
					Dimension preferredSize = new Dimension();
					for (int i = 0; i < contentPanel.getComponentCount(); i++) {
						Rectangle bounds = contentPanel.getComponent(i)
								.getBounds();
						preferredSize.width = Math.max(bounds.x + bounds.width,
								preferredSize.width);
						preferredSize.height = Math.max(bounds.y
								+ bounds.height, preferredSize.height);
					}
					Insets insets = contentPanel.getInsets();
					preferredSize.width += insets.right;
					preferredSize.height += insets.bottom;
					contentPanel.setMinimumSize(preferredSize);
					contentPanel.setPreferredSize(preferredSize);
				}
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			// ======== buttonBar ========
			{
				buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
				buttonBar.setLayout(new GridBagLayout());
				((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[] {
						0, 80 };
				((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[] {
						1.0, 0.0 };

				// ---- textField1 ----
				textField1.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						textField1KeyPressed(e);
					}
				});
				buttonBar.add(textField1, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 5), 0, 0));

				// ---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);

			// ======== menuBar1 ========
			{

				// ======== menu1 ========
				{
					menu1.setText("Fichier");

					// ---- menuItem3 ----
					menuItem3.setText("Quitter");
					menuItem3.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuItem3ActionPerformed(e);
						}
					});
					
					// -- menuItem4 --
					menuItem4.setText("Options");
					menuItem4.setEnabled(false);
					menuItem4.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							optionopen();
						}
					});
					
					menu1.add(menuItem4);	
					menu1.add(menuItem3);
				}
				menuBar1.add(menu1);

				// ======== menu2 ========
				{
					menu2.setText("Server");

					// ---- menuItem1 ----
					menuItem1.setText("Connexion");
					menuItem1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							conActionPerformed(e);
						}
					});
					menu2.add(menuItem1);

					// ---- menuItem2 ----
					menuItem2.setText("Deconnexion");
					menuItem2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							decoItemMenuActionPerformed(e);
						}
					});
					menu2.add(menuItem2);
				}
				menuBar1.add(menu2);
			}
			
				
		
			
			dialogPane.add(menuBar1, BorderLayout.NORTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		pack();
		setLocationRelativeTo(getOwner());
		// //GEN-END:initComponents
	}

	private void optionopen() {
		(new OptionForm(this)).setVisible(true);
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	// //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	public JScrollPane scrollPane1;
	public JEditorPane editorPane1;
	private JPanel buttonBar;
	public JTextField textField1;
	private JButton okButton;
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem menuItem3;
	private JMenuItem menuItem4;
	private JMenu menu2;
	public JMenuItem menuItem1;
	public JMenuItem menuItem2;
	// JFormDesigner - End of variables declaration //GEN-END:variables
}
