package projectileMotion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Home{
	JFrame frame;
	
	Home(){
		frame = new JFrame("Home");

		String introContent = "This simulation will allow you to test out the motion of a projectile \nmoving at a certain speed at a certain angle. You will also be able \nto change other properties such as its height, mass and diameter.\nSimulations can be saved, and saved simulations can be edited.";

		JTextArea introTextArea = new JTextArea(introContent);
		introTextArea.setFont(new Font("Verdana", Font.PLAIN, 24));
		introTextArea.setBounds(100,100,800,130);
		introTextArea.setEditable(false);
		
		JButton beginSim = new JButton("Begin Simulation");
		beginSim.setBounds(400,300,150,50);

		frame.add(introTextArea);
		frame.add(beginSim);
		
		frame.setSize(1000,700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        beginSim.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		new simList();
        		frame.dispose();
        	}
        });
	}
	
	public static void main(String[] args) {
		new Home();
	}
}