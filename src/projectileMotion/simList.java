package projectileMotion;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import com.opencsv.*;

import javax.swing.*;

public class simList {
	private String[][] savedSims;

	simList(){
		int numberOfSims = totalSims("savedSims.csv");

		savedSims = new String[numberOfSims][6];

		savedSims = getSavedSims("savedSims.csv");
		JFrame frame = new JFrame("Simulation List");

		
		JPanel heading = new JPanel();
		JLabel savedHeading = new JLabel("Saved Simulations");
		savedHeading.setFont(new Font("Verdana", Font.PLAIN, 18));
		heading.add(savedHeading);
		
		JPanel list = new JPanel(new GridLayout(numberOfSims,1));

		JButton[] simButtons = new JButton[numberOfSims];

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String simName = ((JButton) e.getSource()).getText();

				for(int i = 0; i < numberOfSims; i++){
					if(savedSims[i][0].equals(simName)){
						new simulation(savedSims[i][0], savedSims[i][1], savedSims[i][2], savedSims[i][3], savedSims[i][4], savedSims[i][5], false);
					}
				}

				frame.dispose();
			}
		};

		for(int i = 0; i < numberOfSims; i++){
			simButtons[i] = new JButton(savedSims[i][0]);
			simButtons[i].addActionListener(listener);
			list.add(simButtons[i]);
		}
		
		JPanel end = new JPanel();
		JButton newSim = new JButton("New Simulation");
		end.add(newSim);
		
		
		frame.add(heading);
		frame.add(list);
		frame.add(end);
		
		frame.setSize(1000,700);
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        newSim.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
        		new enterProperties("", "", "","","","",true);
        		frame.dispose();
        	}
        });




	}

	public int totalSims(String FILENAME) {
		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME));
			String input;
			int count = 0;
			while((input = bufferedReader.readLine()) != null)
			{
				count++;
			}

			return count;
		}
		catch (Exception e){
			return -1;
		}

	}

	public String[][] getSavedSims(String file)
	{
		String simsInCSV[][] = new String[1000][6];
		try {
			FileReader filereader = new FileReader(file);
			CSVReader csvReader = new CSVReader(filereader);
			String[] nextRecord;



			int i = 0;
			while ((nextRecord = csvReader.readNext()) != null) {

				int j = 0;
				for (String cell : nextRecord){

					simsInCSV[i][j] = cell;
					j++;
				}
				i++;
			}
			return simsInCSV;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}