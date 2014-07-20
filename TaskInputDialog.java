package com.slayer.manager.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;


public class TaskInputDialog extends JDialog implements ActionListener{
	
	private final JSlider levelSlider;
	private final JTextField amountBox;
	private final JComboBox<String> monsterBox;
	private final JComboBox<String> masterBox;
	private final JPanel optionsPanel;
	
	private final JButton okButton;
	private final JPanel buttonPanel;
	private final JButton cancelButton;
	
	private boolean cancel;
	
	private TaskInputDialog(final String title, final JFrame parent){
		super(parent, title, true);
		setLayout(new BorderLayout());
		
		cancel = false;
		
		levelSlider = new JSlider(1, 99, 1);
		levelSlider.setMajorTickSpacing(10); //any way to make the text box smaller?
		levelSlider.setPaintLabels(true);
		levelSlider.setPaintTicks(true);
		levelSlider.setValue(Constants.DEFAULT_LEVEL);
		
		amountBox = new JTextField();
		
		monsterBox = new JComboBox<String>(Constants.MONSTERS);
		
		masterBox = new JComboBox<String>(Constants.MASTERS);
		masterBox.setSelectedIndex(Constants.DEFAULT_MASTER);
		
		optionsPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		optionsPanel.add(new JLabel("Slayer Level"));
		optionsPanel.add(levelSlider);
		optionsPanel.add(new JLabel("Slayer Monster"));
		optionsPanel.add(monsterBox);
		optionsPanel.add(new JLabel("Slayer Master"));
		optionsPanel.add(masterBox);
		optionsPanel.add(new JLabel("Amount"));
		optionsPanel.add(amountBox);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		
		buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		
		add(optionsPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(final ActionEvent e){
		final Object source = e.getSource();
		if(source.equals(okButton) || source.equals(cancelButton)){
			cancel = source.equals(cancelButton);
			dispose();
		}
	}
	
	private boolean valid(){
		try{
			Integer.parseInt(amountBox.getText().trim());
			return true;
		}catch(Exception ex){
			return false;
		}
	}
	
	public static Task showInputDialog(final String title, final JFrame parent){
		final TaskInputDialog dialog = new TaskInputDialog(title, parent);
		dialog.pack();
		dialog.setVisible(true);
		while(dialog.isVisible());
		if(dialog.cancel)
			return null;
		if(!dialog.valid()){
			JOptionPane.showMessageDialog(parent, "Invalid data input", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		final int level = dialog.levelSlider.getValue();
		final String monster = (String)dialog.monsterBox.getSelectedItem();
		final String master = (String)dialog.masterBox.getSelectedItem();
		final int amount = Integer.parseInt(dialog.amountBox.getText().trim());
		return new Task(level, master, monster, amount);
		
	}

}
