package brojeviSlovima;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.math.BigDecimal;

import javax.swing.*;

public class MyFrame implements ActionListener {
	JButton button;
	JTextField amaunt, inWords;
	JLabel labelAmaunt, labelInWords;
	JCheckBox saveToClipboard;
	GridBagConstraints gbc = new GridBagConstraints();
	
	MyFrame() {
		
		JFrame jf = new JFrame();
		// exit on frame close
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel(new GridBagLayout());
		
		
		// add TextField
		amaunt = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		amaunt.setPreferredSize(new Dimension(200, 27));
		amaunt.setVisible(true);
		amaunt.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent key) {
				if(key.getKeyCode() == KeyEvent.VK_ENTER ){
					if( amaunt.getText().length() > 0) {
							BigDecimal bd = new BigDecimal(amaunt.getText());
							BrojeviSlovima brojeviSlovima = new BrojeviSlovima();
							brojeviSlovima.inToString(amaunt.getText());
							inWords.setText(brojeviSlovima.inWords);
					}
				}
			}

		});
		
		jp.add(amaunt, gbc);
		
		inWords = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		inWords.setPreferredSize(new Dimension(200, 27));
		inWords.setVisible(true);
		jp.add(inWords, gbc);
		
		
		
		
		
		// labels
		labelAmaunt = new JLabel("Унеси цифру: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		jp.add(labelAmaunt, gbc);
		
		labelInWords = new JLabel("Цифра словима: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		jp.add(labelInWords, gbc);
		
		// checkbox
		saveToClipboard = new JCheckBox("копирај аутоматски", null, true);
		gbc.gridx = 2;
		gbc.gridy = 0;
		jp.add(saveToClipboard, gbc);
		
		// button
		button = new JButton("Пребаци у слова");
		button.getAlignmentX();
		button.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 2;
		jp.add(button, gbc);
		
		// add ENTER press listener
		
		
		// add jpanel to jframe
		jf.add(jp);
		
		// middle of the screen
		jf.setLocationByPlatform(true);
		jf.setLocationRelativeTo(null);
		jf.setTitle("Бројеви словима");
		jf.setSize(500, 200);
		
		jf.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button) {
			
			amaunt.selectAll();
				
			BrojeviSlovima brojeviSlovima = new BrojeviSlovima();
			brojeviSlovima.inToString(amaunt.getText());
			inWords.setText(brojeviSlovima.inWords);
			
			if(toClipboard() == true) {
				copyToClipboard(inWords.getText());
			}
			
		}
		
	}
	
	private void copyToClipboard(String str) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		StringSelection strSel = new StringSelection(str);
		clipboard.setContents(strSel, null);
	}
	
	private Boolean toClipboard() {
		if (saveToClipboard.isSelected() == true) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
