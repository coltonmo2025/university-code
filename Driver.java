package MorrisProject4;

import java.awt.Color;
import java.io.IOException;
import java.util.Scanner;

import edu.du.dudraw.DUDraw;

public class Driver {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {

		
		Steganography test1 = new Steganography();

		Scanner obj1 = new Scanner(System.in);

		System.out.println("What would you like to do today?\n [1]encode\n [2]decode");

		String userIn = obj1.nextLine();

		if (userIn.equals("1")) {
			System.out.println("Please enter the name of the public image. \n(include .bmp)");

			String file1 = obj1.nextLine();

			System.out.println("Please enter the name of the secret secret image. \n(include .bmp)");

			String file2 = obj1.nextLine();
			
			Color[][] color1 = BMPIO.readBMPFile(file1);
			Color[][] secret = test1.embedSecretImage(file1, file2);

			DUDraw.setCanvasSize(color1[0].length * 2, color1[0].length);
			DUDraw.setXscale(0, color1[0].length * 2);
			DUDraw.setYscale(0, color1.length);
			
			DUDraw.enableDoubleBuffering();
			for (int i = 0; i < color1.length; i++) {
				for (int j = 0; j < color1[0].length; j++) {
					 DUDraw.setPenColor(color1[i][j]);
					 DUDraw.filledSquare(j,i,1);
					 
				 }
			 }

			
			for (int i = 0; i < color1.length; i++) {
				for (int j = 0; j < color1[0].length; j++) {
					 DUDraw.setPenColor(secret[i][j]);
					 DUDraw.filledSquare(j + color1[0].length,i,1);
					 
				 }
			 }

		}

		else if (userIn.equals("2")) {
			
			Color[][] color1 = null;

			System.out.println(
					"Please enter the name of the File containing the secret Message.\n" + "Include the .bmp please.");

			String file1 = obj1.nextLine();

			try {
				color1 = BMPIO.readBMPFile(file1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Color[][] secret = test1.extractSecretMessage(file1);

			DUDraw.setCanvasSize(secret[0].length * 2, color1[0].length);
			DUDraw.setXscale(0, secret[0].length * 2);
			DUDraw.setYscale(0, secret.length);


			
			DUDraw.enableDoubleBuffering();
			for (int i = 0; i < secret.length; i++) {
				for (int j = 0; j < secret[0].length; j++) {
					DUDraw.setPenColor(color1[i][j]);
					DUDraw.filledSquare(j, i, 1);

				}
			}

			for (int i = 0; i < secret.length; i++) {
				for (int j = 0; j < secret[0].length; j++) {
					DUDraw.setPenColor(secret[i][j]);
					DUDraw.filledSquare(j + secret[0].length, i, 1);

				}
			}

		}
		
		DUDraw.show();

	}

}
