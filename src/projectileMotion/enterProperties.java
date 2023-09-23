package projectileMotion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.io.*;
import java.util.Scanner;

import com.opencsv.*;
import javax.swing.*;




public class enterProperties {
	enterProperties(String Name, String Mass, String Velocity, String Diameter, String Angle, String Height, boolean newSim){
		JFrame frame = new JFrame("Enter Properties");
		
		
		JLabel instruction = new JLabel("Please enter the properties of the projectile:");
		instruction.setFont(new Font("Verdana", Font.BOLD, 18));
		instruction.setBounds(300,50,800,50);
		
		JLabel mass = new JLabel("Mass(kg):");
		mass.setFont(new Font("Verdana", Font.PLAIN, 18));
		mass.setBounds(300,100,100,50);
		
		JTextField massInput = new JTextField(Mass);
		massInput.setBounds(400,105,350,40);
		
		JLabel velocity = new JLabel("Velocity(m/s):");
		velocity.setFont(new Font("Verdana", Font.PLAIN, 18));
		velocity.setBounds(300,150,200,50);
		
		JTextField velocityInput = new JTextField(Velocity);
		velocityInput.setBounds(450,155,300,40);
		
		JLabel diameter = new JLabel("Diameter(m):");
		diameter.setFont(new Font("Verdana", Font.PLAIN, 18));
		diameter.setBounds(300,200,200,50);
		
		JTextField diameterInput = new JTextField(Diameter);
		diameterInput.setBounds(450,205,300,40);
		
		JLabel angle = new JLabel("Angle(degrees):");
		angle.setFont(new Font("Verdana", Font.PLAIN, 18));
		angle.setBounds(300,250,200,50);
		
		JTextField angleInput = new JTextField(Angle);
		angleInput.setBounds(460,255,290,40);
		
		JLabel height = new JLabel("Height(m):");
		height.setFont(new Font("Verdana", Font.PLAIN, 18));
		height.setBounds(300,300,200,50);
		
		JTextField heightInput = new JTextField(Height);
		heightInput.setBounds(420,305,330,40);
		
		JButton submitData = new JButton("Submit");
		submitData.setBounds(450,380,150,50);
		
		
		frame.add(instruction);
		frame.add(mass);
		frame.add(massInput);
		frame.add(velocity);
		frame.add(velocityInput);
		frame.add(diameter);
		frame.add(diameterInput);
		frame.add(angle);
		frame.add(angleInput);
		frame.add(height);
		frame.add(heightInput);
		frame.add(submitData);
		
		frame.setSize(1000,700);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        submitData.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		try {
        			Float.parseFloat(massInput.getText());
        		} catch (NumberFormatException e) {
        			JOptionPane.showMessageDialog(frame, "Mass has to be a number",
							"Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		if(Float.parseFloat(massInput.getText()) < 0 || Float.parseFloat(massInput.getText()) > 10000) {
        			JOptionPane.showMessageDialog(frame, "Mass has to be between 0 and 10,000",
							"Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		try {
        			Float.parseFloat(velocityInput.getText());
        		} catch (NumberFormatException e) {
        			JOptionPane.showMessageDialog(frame, "Veloicty has to be a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		if(Float.parseFloat(velocityInput.getText()) < 0 || Float.parseFloat(velocityInput.getText()) > 10000) {
        			JOptionPane.showMessageDialog(frame, "Velocity has to be between 0 and 10,000", "Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		try {
        			Float.parseFloat(diameterInput.getText());
        		} catch (NumberFormatException e) {
        			JOptionPane.showMessageDialog(frame, "Diameter has to be a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		if(Float.parseFloat(diameterInput.getText()) < 0 || Float.parseFloat(velocityInput.getText()) > 10000) {
        			JOptionPane.showMessageDialog(frame, "Diameter has to be between 0 and 10,000", "Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		try {
        			Float.parseFloat(angleInput.getText());
        		} catch (NumberFormatException e) {
        			JOptionPane.showMessageDialog(frame, "Angle has to be a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		if(Float.parseFloat(angleInput.getText()) < 0 || Float.parseFloat(angleInput.getText()) > 90) {
        			JOptionPane.showMessageDialog(frame, "Angle has to be between 0 and 90 degrees", "Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		
        		try {
        			Float.parseFloat(heightInput.getText());
        		} catch (NumberFormatException e) {
        			JOptionPane.showMessageDialog(frame, "Height has to be a number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        			return;
        		}
        		if(Float.parseFloat(heightInput.getText()) < 0 || Float.parseFloat(velocityInput.getText()) > 10000) {
        			JOptionPane.showMessageDialog(frame, "Height has to be between 0 and 10,000", "Invalid Input",JOptionPane.ERROR_MESSAGE);
        			return;
        		}

				if(newSim){
					new simulation(null, massInput.getText(), velocityInput.getText(), diameterInput.getText(), angleInput.getText(), heightInput.getText(), true);
					frame.dispose();
				}
        		else{
					try{
						String filepath = "savedSims.csv";
						String tempfile = "empty.csv";
						File oldFile = new File(filepath);
						File newFile = new File(tempfile);

						FileWriter fw = new FileWriter(tempfile, true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter pw = new PrintWriter(bw);

						x = new Scanner(new File(filepath));
						x.useDelimiter("[,\n]");
						while (x.hasNext()){
							String nameFound = x.next();
							String massFound = x.next();
							String velFound = x.next();
							String diaFound = x.next();
							String angFound = x.next();
							String heightFound = x.next();
							if(nameFound.equals("\"" + Name + "\"")){
								pw.print(nameFound +  ",\"" + massInput.getText() + "\",\"" + velocityInput.getText() + "\",\"" + diameterInput.getText() + "\",\"" + angleInput.getText() + "\",\"" + heightInput.getText() + "\"\n");
							}
							else{
								pw.print(nameFound + "," + massFound + "," + velFound + "," + diaFound + "," + angFound + "," + heightFound + "\n");
							}
						}

						x.close();
						pw.flush();
						pw.close();
						oldFile.delete();
						File dump = new File(filepath);
						newFile.renameTo(dump);

						File myObj = new File("empty.csv");
						myObj.createNewFile();
					}
					catch (Exception e) {
						e.printStackTrace();
					}


					new simulation(Name, massInput.getText(), velocityInput.getText(), diameterInput.getText(), angleInput.getText(), heightInput.getText(), false);
					frame.dispose();
				}
        	}
        });
	}


	private Scanner x;
}
