package hareketliToplar;

import java.awt.Color;
import java.util.Random;

import edu.princeton.cs.introcs.StdDraw;

public class mainClass {

	public static void main(String[] args) {
		//�er�eve
		StdDraw.setCanvasSize(1024, 1024);
		StdDraw.setXscale(-1,1);
		StdDraw.setYscale(-1,1);
		//
		StdDraw.enableDoubleBuffering();
		
		int topSay�s� = 400;
		
		double[][] toplar = new double[topSay�s�][4];	
		
		double yar��ap = 0.03;
		Color[] toprengi = new Color[topSay�s�];
		
		Random random = new Random();
		
		//her top i�in
		for (int i = 0; i < toplar.length; i++) {
			do {
				toplar[i][0]= 0; //x koordinat�
			} while (Math.abs(toplar[i][0])> 1.0 - yar��ap);
			
			do {
				toplar[i][1]= 0; //y koordinat�
			} while (Math.abs(toplar[i][1])> 1.0 - yar��ap);
			
			double yava�latma = 50.0;
			// x h�z�
			toplar[i][2]= (random.nextDouble()-0.5 ) / yava�latma; //-0.5 , 0.5
			// y h�z�
			toplar[i][3]= (random.nextDouble()-0.5 ) / yava�latma; 
			
			toprengi[i] = renkOlu�tur();
			
		}// for bitti
		
		
		//animasyonu olu�turmak
		while(true) {
			//her top i�in
			for (int i = 0; i < toplar.length; i++) {
				if (Math.abs(toplar[i][0] + toplar[i][2])> 1.0 - yar��ap) {
					 toplar[i][2] = -1 *  toplar[i][2];
				}
				if (Math.abs(toplar[i][1] + toplar[i][3])> 1.0 - yar��ap) {
					 toplar[i][3] = -1 *  toplar[i][3];
				}
				//konum g�ncelleme
				
				toplar[i][0] = toplar[i][0] + toplar[i][2]; // x koordinat� + x h�z�
				toplar[i][1] = toplar[i][1] + toplar[i][3];
			}//for bitti
			StdDraw.clear(StdDraw.BOOK_LIGHT_BLUE);
			
			for (int i = 0; i < toplar.length; i++) {
				StdDraw.setPenColor(toprengi[i]);
				StdDraw.filledCircle(toplar[i][0], toplar[i][1], yar��ap);
			}
			StdDraw.show();
			//Yava�latma
			StdDraw.pause(20);
			
			
		}//while bitti 
		
	}//main 
	
	public static Color renkOlu�tur() {
		Random random = new Random();
		//de�er se�
		int k�rm�z� = random.nextInt(256);
		int mavi = random.nextInt(256);
		int ye�il = random.nextInt(256);
		Color randomColor = new Color(k�rm�z�,mavi,ye�il);
		
		return randomColor;
		
	}

}
