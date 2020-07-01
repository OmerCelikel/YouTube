package adanadanMiamiye;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdDraw;

public class denizTuru {

	public static void main(String[] args) {
		//StdDraw 
		// set the size of the drawing canvas
				StdDraw.setCanvasSize(1000, 1000);
				// set the scale of the coordinate system
				StdDraw.setXscale(-100.0, 100.0);
				StdDraw.setYscale(-100.0, 100.0);
				StdDraw.clear(StdDraw.DARK_GRAY);
				//resim ekleme
				StdDraw.picture(15, 10, "world.png");
		double yarýçap = 2;
		
		String filename_database = "city.txt";
		String filename_trip = "trip2.txt";
		
		//Database yani tüm þehirler
		File file = new File(filename_database);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println(filename_database + ": Dosya Bulunamadý!\nProgramdan Çýkýlýyor...");
			System.exit(1);
		}
		
		//Gezi Dosyam
		File file2 = new File(filename_trip);
		Scanner input2 = null;
		try {
			input2 = new Scanner(file2);
		} catch (FileNotFoundException e) {
			System.out.println(filename_trip + ": trip file can not be found!\nExiting program...");
			System.exit(1);
		}
		
		//þehirler Array e konup okunacak
		ArrayList<City> cityDB = new ArrayList<>();
		String temp = input.nextLine();
		while(input.hasNext()) {
			String line = input.nextLine();
			String[] parts = line.split(";");
			String cityName = parts[0];
			cityName = cityName.trim();
			cityName = cityName.toLowerCase();
			cityDB.add(new City(cityName, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), parts[3], Integer.parseInt(parts[4])));
		}
		input.close();
		System.out.println("Eklenen Þehir Sayýsý: " + cityDB.size());
		
		ArrayList<String> trip = new ArrayList<>();
		while(input2.hasNext()) {
			String line = input2.nextLine();
			line = line.trim();
			line = line.toLowerCase();
			trip.add(line);
		}
		
		//Trip dosyamdakiler Büyük Database dosyasýnda aranacak
		//Eþleþenleri alacaðýz
		
		ArrayList<City> tripCities = new ArrayList<>();
		
		for(String s: trip) {
			boolean found = false;
			for(City c: cityDB) {
				if(s.equals(c.getName())) {
					tripCities.add(c);
					found = true;
					break;
				}
			}
			//Kullanýcý þehir ismini yanlýþ girdiyse
			if(found == false) {
				System.out.println("Datamýzda yazdýðýn þehir yok.");
				System.out.println("Lütfen tekrar kontrol ediniz. ");
				System.out.println("Programdan çýkýlýyor....");
				System.exit(1);
			}
		}
		
		//Toplam Yolculuk mesafesi Hesaplama 
		
		double totalDistance = 0.0;
		int count = 0;
		for (int i = 0; i < tripCities.size()-1; i++) {
			double x1 = ((double)tripCities.get(i).getLongitude())/10000.0;
			double x2 = ((double)tripCities.get(i+1).getLongitude())/10000.0;
			double y1 = ((double)tripCities.get(i).getLatitude())/10000.0;
			double y2 = ((double)tripCities.get(i+1).getLatitude())/10000.0;
			double dx = Math.abs(x1-x2)*85.0;
			double dy = Math.abs(y1-y2)*111.0;
			double d = Math.pow(dx*dx+dy*dy, 0.5);
			totalDistance += d;
			
			System.out.printf("%-15s [x:%8.4f, y:%8.4f]  ->  %-15s [x:%8.4f, y:%8.4f] Distance: %8.0f km\n", 
					tripCities.get(i).getName(), x1, y1, 
					tripCities.get(i+1).getName(), x2,y2,
					d);
			
			double kalemKalýnðý = 0.009;
			StdDraw.setPenRadius(kalemKalýnðý);
			//Yerleri
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.circle(x1, y1, yarýçap);
			//Yol Haritasý
			
			
			
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x1, y1 , x2 , y2);
			count++;
		}
		//Son Yer için
		StdDraw.setPenColor(StdDraw.BLUE);
		double x2 = ((double)tripCities.get(count).getLongitude())/10000.0;
		double y2 = ((double)tripCities.get(count).getLatitude())/10000.0;
		StdDraw.circle(x2, y2, yarýçap);
		System.out.printf("\nToplam Mesafe: %.0f km", totalDistance);
		
			
		
	}//main ends
	

}//class ends
