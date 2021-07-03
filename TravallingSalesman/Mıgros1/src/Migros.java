import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Scanner;


import edu.princeton.cs.introcs.StdDraw;

/**
 * In this assignment, I compute distance of a sales order. I tried to get shortest path for all posibilities.
 * @author Ömer Oðuz
 *
 */
public class Migros {
	// Function to find the minimum weight  
	// Hamiltonian Cycle 
	
	/**
	 * This method is my main algorithm.
	It will give me the shortest path.
	 * @param table It keeps my distances between places
	 * @param v Checks visited or not
	 * @param currentPosition It keeps where Am I going
	 * @param countPlaces	My total elemts
	 * @param count
	 * @param cost How long is the road
	 * @param distance It gives distance
	 * @return
	 */
	static double travellingSalesman(double[][] table, boolean[] v, double currentPosition, double countPlaces, double count, double cost, double distance){ 

		
		//I check until the last node. If I am at the end node, I will get the minimum.
		
		if (count == countPlaces && table[(int) currentPosition][0] > 0){ 
			distance = Math.min(distance, cost + table[(int) currentPosition][0]); 
			return distance; 
		} 

		//when the loop is going on if there is minumum I keep it
		for (int i = 0; i < countPlaces; i++){   

			if (v[i] == false && table[(int) currentPosition][i] > 0){   

				// If mark visited
				v[i] = true; 
				distance = travellingSalesman(table, v, i, countPlaces, count + 1, cost + table[(int) currentPosition][i], distance); 

				// if not
				v[i] = false; 

				//  StdDraw.setPenColor(StdDraw.GREEN);
				//StdDraw.line(x1, y1 , x2 , y2);
			} 

		} 

		return distance; 

	} 

	public static void main(String[] args) {



		Scanner input1 = new Scanner(System.in);

		// Using one trip file,	provided by the user. 
		System.out.println("Welcome to this travelling salesman.! \nYou have entered 3 files. "
				+ "\nThe Salesman will go to houses according to the data of your choice but shortest way.\nPlease choose one");
		System.out.println("Options : data1.txt, data2.txt, data3.txt");
		String filename_coords = input1.nextLine();
		System.out.println("\nThe file you choosed: " + filename_coords);


		File dosya = new File(filename_coords);



		// set the size of the drawing canvas
		StdDraw.setCanvasSize(700, 700);
		// set the scale of the coordinate system
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);
		StdDraw.clear(StdDraw.DARK_GRAY);
		// create an array for each circles


		//Create a Scanner for the file
		Scanner input = null;

		try {
			input = new Scanner(dosya);
			//Capturing the error
		} catch (FileNotFoundException e) {
			System.out.println(filename_coords + ": houses file can not be found! \n Exiting program... ");
			System.exit(1);
		}

		//read houses file
		ArrayList<Coords> places = new ArrayList<>();


		// count is for put numbers to the circles
		int countPlaces =0;
		while (input.hasNext()) {

			String line = input.nextLine();
			String[] parts = line.split("," );
			String xCoord = parts[0];
			String yCoord = parts[1];

			//in data migros is 3rd so I can solve it element numbers
			if (parts.length == 2) {
				StdDraw.setPenColor(StdDraw.YELLOW);

				System.out.println("HOUSE");
				double radiusHouse =  0.02;
				StdDraw.filledCircle(Double.parseDouble(xCoord), Double.parseDouble(yCoord),radiusHouse);
			}
			else {
				double radiusMigros =  0.04;
				StdDraw.setPenColor(StdDraw.BLUE);

				StdDraw.filledCircle(Double.parseDouble(xCoord), Double.parseDouble(yCoord),radiusMigros);
				System.out.println("It is migros");
			}

			places.add(new Coords(Double.parseDouble(parts[0]),Double.parseDouble(parts[1])));

			System.out.println(xCoord +"  "+  yCoord + " " );


			// Put numbers to circles
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(Double.parseDouble(xCoord), Double.parseDouble(yCoord), String.valueOf(countPlaces+1));

			countPlaces++;
		}
		input.close();
		//countPlaces gives how many points
		System.out.println(countPlaces);
		StdDraw.show();



		/*
		 * I couldn't draw a line between circles
		 * 	
		double totalDistance = 0.0;
		for (int i = 0; i < places.size()-1; i++) {
			System.out.println("DÖNGÜ BAÞLADI");
			double x1 = (double)places.get(i).getxCoord();
			//System.out.println(i +". x1:" + x1);
			double x2 = (double)places.get(i+1).getxCoord();
			//System.out.println(i +". x2:" + x2);
			double y1 = (double)places.get(i).getyCoord();
			//System.out.println(i +". Y1:" + y1);
			double y2 = (double)places.get(i+1).getyCoord();
			//System.out.println(i +". Y2:" + y2);
			double dx = Math.abs(x2-x1);
			//System.out.println("Dx : " + dx + " X ler farký == " + (x1-x2));
			double dy = Math.abs(y2-y1);
			//System.out.println("Dy : " + dy + " Y ler farký == " + (y1-y2));
			double d = Math.pow(dx*dx+dy*dy, 0.5);
			totalDistance = d;


			//StdDraw.setPenColor(StdDraw.GREEN);
			//StdDraw.line(x1, y1 , x2 , y2);

			System.out.println("count" + countPlaces);

			System.out.println(i+". distance : "+ d);
		}
		 */


		//It gives distance between of every coordinates
		double[][] table = new double[countPlaces][countPlaces];

		for (int row=0; row < countPlaces; row++){
			for (int col=0; col < countPlaces; col++){

				double x1 = (double)places.get(row).getxCoord();
				//System.out.println(i +". x1:" + x1);
				double x2 = (double)places.get(col).getxCoord();
				//System.out.println(i +". x2:" + x2);
				double y1 = (double)places.get(row).getyCoord();
				//System.out.println(i +". Y1:" + y1);
				double y2 = (double)places.get(col).getyCoord();
				//System.out.println(i +". Y2:" + y2);
				double dx = Math.abs(x1-x2);
				//System.out.println("Dx : " + dx + " X ler farký == " + (x1-x2));
				double dy = Math.abs(y1-y2);
				//System.out.println("Dy : " + dy + " Y ler farký == " + (y1-y2));
				double d = Math.pow(dx*dx+dy*dy, 0.5);
				//totalDistance = d;
				//System.out.println(col+". distance : "+ d);

				table[row][col] =  d;
			}

		}


		//Display distances
		for (int row = 0; row < table.length; row++) {
			double sutun_top = 0;
			for (int col = 0; col < table.length; col++) {
				sutun_top = sutun_top +  table[col][row];
			}
			//System.out.println(row + ".sutun toplamý : " + sutun_top);
		}
		
		for (int row = 0; row < countPlaces; row++) {
			for (int col = 0; col < countPlaces; col++) {
				System.out.printf("%12f", table[row][col]);
			}
			System.out.println();
		}


		// countPlaces used for nodes number

		// boolean will check ýf I went node or not
		boolean[] v = new boolean[countPlaces]; 

		// If 0th I visited node as it shown true
		v[0] = true; 

		//to start ý took largest number it will increase
		double distance = Integer.MAX_VALUE; 

		// Find the minimum weight Hamiltonian Cycle 
		distance =  travellingSalesman(table, v, 0.0, countPlaces, 1.0, 0.0, distance); 

		// ans is the minimum weight Hamiltonian Cycle 
		System.out.println("SHORTEST DISTANCE IS =  " + distance); 





	}//main ends


}
