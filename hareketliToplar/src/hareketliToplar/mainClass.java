package hareketliToplar;

import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class mainClass {

	public static void main(String[] args) {
		//çerçeve
		StdDraw.setCanvasSize(1024, 1024);
		StdDraw.setXscale(-1,1);
		StdDraw.setYscale(-1,1);
		//
		StdDraw.enableDoubleBuffering();
		
		int topSayýsý = 400;
		
		double[][] toplar = new double[topSayýsý][4];	
		
		double yarýçap = 0.03;
		Color[] toprengi = new Color[topSayýsý];
		
		Random random = new Random();
		
		//her top için
		for (int i = 0; i < toplar.length; i++) {
			do {
				toplar[i][0]= 0; //x koordinatý
			} while (Math.abs(toplar[i][0])> 1.0 - yarýçap);
			
			do {
				toplar[i][1]= 0; //y koordinatý
			} while (Math.abs(toplar[i][1])> 1.0 - yarýçap);
			
			double yavaþlatma = 50.0;
			// x hýzý
			toplar[i][2]= (random.nextDouble()-0.5 ) / yavaþlatma; //-0.5 , 0.5
			// y hýzý
			toplar[i][3]= (random.nextDouble()-0.5 ) / yavaþlatma; 
			
			toprengi[i] = renkOluþtur();
			
		}// for bitti
		
		
		//animasyonu oluþturmak
		while(true) {
			//her top için
			for (int i = 0; i < toplar.length; i++) {
				if (Math.abs(toplar[i][0] + toplar[i][2])> 1.0 - yarýçap) {
					 toplar[i][2] = -1 *  toplar[i][2];
				}
				if (Math.abs(toplar[i][1] + toplar[i][3])> 1.0 - yarýçap) {
					 toplar[i][3] = -1 *  toplar[i][3];
				}
				//konum güncelleme
				
				toplar[i][0] = toplar[i][0] + toplar[i][2]; // x koordinatý + x hýzý
				toplar[i][1] = toplar[i][1] + toplar[i][3];
			}//for bitti
			StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
			
			for (int i = 0; i < toplar.length; i++) {
				StdDraw.setPenColor(toprengi[i]);
				StdDraw.filledCircle(toplar[i][0], toplar[i][1], yarýçap);
			}
			StdDraw.show();
			//Yavaþlatma
			StdDraw.pause(20);
			
			
		}//while bitti 
		
	}//main 
	
	public static Color renkOluþtur() {
		Random random = new Random();
		//deðer seç
		int kýrmýzý = random.nextInt(256);
		int mavi = random.nextInt(256);
		int yeþil = random.nextInt(256);
		Color randomColor = new Color(kýrmýzý,mavi,yeþil);
		
		return randomColor;
		
	}

}
