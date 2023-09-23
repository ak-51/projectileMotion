package projectileMotion;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;
import com.opencsv.*;

public class simulation {
	simulation(String name, String mass, String velocity, String diameter, String angle, String height, boolean newSim){
		JFrame frame;
		if(name != null){
			frame = new JFrame("Simulation: " + name);
		}
		else{
			frame = new JFrame("Simulation");
		}

		JPanel model = new model( Double.parseDouble(velocity) , Double.parseDouble(angle), Double.parseDouble(height));
		model.setBounds(0, 0, 800, 600);
		frame.add(model);
		
		JPanel properties = new JPanel(new GridLayout(6,1));
		JLabel propHeading = new JLabel("Properties", SwingConstants.CENTER);
		propHeading.setFont(new Font("Verdana", Font.BOLD, 18));
		JLabel velLabel = new JLabel("Velocity: " + velocity + " m/s");
		JLabel massLabel = new JLabel("Mass: " + mass + " kg");
		JLabel diaLabel = new JLabel("Diameter: " + diameter + " m");
		JLabel angLabel = new JLabel("Angle: " + angle + "°");
		JLabel heiLabel = new JLabel("Height: " + height + " m");
		properties.add(propHeading);
		properties.add(velLabel);
		properties.add(massLabel);
		properties.add(diaLabel);
		properties.add(angLabel);
		properties.add(heiLabel);
		properties.setBackground(new Color(102,255,102));
		properties.setBounds(800, 0, 200, 200);


		JPanel velAtPoint = new JPanel(new GridLayout(4, 1));
		JLabel askVel = new JLabel("Velocity at time(s):", SwingConstants.CENTER);
		askVel.setFont(new Font("Verdana", Font.BOLD, 18));
		JTextField enterTime = new JTextField();
		JButton showVel = new JButton("Show Velocity");
		JLabel velDisp = new JLabel();
		velAtPoint.add(askVel);
		velAtPoint.add(enterTime);
		velAtPoint.add(showVel);
		velAtPoint.add(velDisp);
		velAtPoint.setBackground(new Color(255,102,102));
		velAtPoint.setBounds(800, 200, 200, 150);

		JPanel results = new JPanel(new GridLayout(4, 1));
		JLabel resHeading = new JLabel("Results", SwingConstants.CENTER);
		resHeading.setFont(new Font("Verdana", Font.BOLD, 18));
		JLabel maxDist = new JLabel("<html>Maximum Displacement: <br/>" + String.format("%.2f",maxDisplacement( Double.parseDouble(velocity) , Double.parseDouble(angle), Double.parseDouble(height))) + " m</html>");
		JLabel maxHgt = new JLabel("<html>Maximum Height: <br/>" + String.format("%.2f",maxHeight( Double.parseDouble(velocity) , Double.parseDouble(angle), Double.parseDouble(height))) + " m</html>");
		JLabel tTaken = new JLabel("<html>Time Taken: <br/>" + String.format("%.2f",timeTaken( Double.parseDouble(velocity) , Double.parseDouble(angle), Double.parseDouble(height)) ) + " s</html>");
		results.add(resHeading);
		results.add(maxDist);
		results.add(maxHgt);
		results.add(tTaken);
		results.setBackground(new Color(51,204,255));
		results.setBounds(800, 350, 200, 150);

		JButton saveButton = new JButton("Save");
		JButton editButton = new JButton("Edit");

		JButton backToSimList = new JButton("Simulation List");
		backToSimList.setBounds(800,535,200,37);
		frame.add(backToSimList);

		JButton replaySim = new JButton("Replay");
		replaySim.setBounds(900,500,100,40);
		frame.add(replaySim);

		if(newSim){
			saveButton.setBounds(800,500,100,40);
			frame.add(saveButton);
		}

		else{
			editButton.setBounds(800,500,100,40);
			frame.add(editButton);
		}
		
		frame.add(properties);
		frame.add(velAtPoint);
		frame.add(results);


		
		frame.setSize(1000,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		showVel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputTime = enterTime.getText();
				double tTime = timeTaken( Double.parseDouble(velocity) , Double.parseDouble(angle), Double.parseDouble(height));
				if(Double.parseDouble(inputTime) > tTime){
					velDisp.setText("The projectile stops by then.");
				}
				else if(Double.parseDouble(inputTime) < 0){
					velDisp.setText("Time cannot be less than 0.");
				}
				else{
					velDisp.setText(String.format("%.2f", velAtTime(Double.parseDouble(velocity), Double.parseDouble(angle), Double.parseDouble(inputTime))) + " m/s");
				}

			}
		});

		backToSimList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new simList();
				frame.dispose();
			}
		});

		replaySim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new simulation(name, mass, velocity, diameter, angle, height, newSim);
				frame.dispose();
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new enterProperties(name, mass, velocity, diameter, angle, height, false);
				frame.dispose();
			}
		});

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (newSim){
					String newSimName = JOptionPane.showInputDialog(frame,"Name of simulation:");
					File file = new File("savedSims.csv");
					try {
						// create FileWriter object with file as parameter
						FileWriter outputfile = new FileWriter(file, true);

						// create CSVWriter object filewriter object as parameter
						CSVWriter writer = new CSVWriter(outputfile);

						// adding data to csv
						String[] data = {newSimName, mass, velocity, diameter, angle, height};
						writer.writeNext(data);

						// closing writer connection
						writer.close();

					}
					catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new simulation(newSimName, mass, velocity, diameter, angle, height, false);
					frame.dispose();
				}

				else{

				}

			}
		});
	}

	public double velAtTime(double vel, double ang, double time){
		double g = 9.81;
		double xVec = vel * Math.cos((ang * Math.PI)/180);
		double yVec =  (vel * Math.sin((ang * Math.PI)/180)) - (g * time);
		double velT = Math.sqrt((xVec * xVec) + (yVec * yVec));
		return velT;
	}

	public double maxDisplacement(double vel, double ang, double hgt){
		double t = timeTaken(vel, ang, hgt);

		double maxDis = vel * Math.cos((ang * Math.PI)/180) * t;

		return maxDis;
	}

	public double maxHeight(double vel, double ang, double hgt){
		double g = 9.81;
		double hmax = vel * vel;
		hmax = hmax * Math.sin((ang * Math.PI)/180) * Math.sin((ang * Math.PI)/180);
		hmax = hmax / (2 * g);
		hmax = hmax + hgt;

		return hmax;
	}

	public double timeTaken(double vel, double ang, double hgt){
		double vy = vel * Math.sin((ang * Math.PI)/180);
		double g = 9.81;
		double ti = Math.sqrt(vy * vy + 2 * g * hgt);
		ti = ti + vy;
		ti = ti/g;
		return ti;
	}
}
