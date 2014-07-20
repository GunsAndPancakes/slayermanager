package com.slayer.manager.gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Settings extends JFrame implements ActionListener {

	String[] levelArray = new String[100];
	JLabel defaultLevel;
	JLabel defaultMaster;
	JComboBox defaultLevelBox;
	JComboBox defaultMasterBox;
	JButton saveButton;

	public Settings() {
		this.setLayout(null);
		initLevels();

		defaultLevel = new JLabel();
		defaultLevel.setLocation(2, 5);
		defaultLevel.setSize(124, 25);
		defaultLevel.setText("Default Slayer Level:");
		add(defaultLevel);

		defaultMaster = new JLabel();
		defaultMaster.setLocation(2, 38);
		defaultMaster.setSize(129, 25);
		defaultMaster.setText("Default Slayer Master:");
		add(defaultMaster);

		defaultLevelBox = new JComboBox(levelArray);
		defaultLevelBox.setLocation(159, 6);
		defaultLevelBox.setSize(149, 25);
		defaultLevelBox.setEditable(false);
		defaultLevelBox.setSelectedIndex(1);
		add(defaultLevelBox);

		defaultMasterBox = new JComboBox(Constants.MASTERS);
		defaultMasterBox.setLocation(159, 39);
		defaultMasterBox.setSize(149, 25);
		defaultMasterBox.setEditable(false);
		add(defaultMasterBox);

		saveButton = new JButton();
		saveButton.setLocation(90, 88);
		saveButton.setSize(118, 25);
		saveButton.addActionListener(this);
		saveButton.setText("Save Settings");
		add(saveButton);

		setTitle("Setting");
		setSize(322, 160);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(saveButton)) {
			int master = defaultMasterBox.getSelectedIndex();
			int level = defaultLevelBox.getSelectedIndex();
			PrintWriter fileoutput = null;
			try {
				fileoutput = new PrintWriter(new FileWriter("settings.txt"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			fileoutput.println("" + master);
			fileoutput.println("" + level);
			fileoutput.close();
			setVisible(false);
		}
	}

	public static void main(String[] args) {

	}

	public void initLevels() {
		for (int i = 1; i < levelArray.length; i++) {
			levelArray[i] = "" + i;
		}
	}

}
