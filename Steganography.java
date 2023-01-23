package MorrisProject4;

import java.awt.Color;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Steganography {

	public Steganography() {
		// TODO Auto-generated constructor stub
	}

	public Color[][] extractSecretMessage(String fileName) {

		Color[][] secMessage = null;

		try {
			secMessage = BMPIO.readBMPFile(fileName);

		} catch (IOException e) {
			System.out.println(e);
		}

		for (int i = 0; i < secMessage.length; i++) {
			for (int j = 0; j < secMessage[0].length; j++) {
				
				int red = (secMessage[i][j].getRed() % 16) * 16;
				int blue = (secMessage[i][j].getBlue() % 16) * 16;
				int green = (secMessage[i][j].getGreen() % 16) * 16;
				
				secMessage[i][j] = new Color(red, green, blue);
				   
			}
		}

		// return statement in case the correct code
		return secMessage;

	}

	public Color[][] embedSecretImage(String publicIm, String secretImage) {
		
		try {
			String embedPublicIm = "embedded_" + publicIm;
			
			//makes a copy and puts in into another 
			Files.copy(Paths.get(publicIm), Paths.get(embedPublicIm), StandardCopyOption.REPLACE_EXISTING);
			Color[][] im1 = BMPIO.readBMPFile(publicIm);
			Color[][] im2 = BMPIO.readBMPFile(secretImage);
			
			RandomAccessFile raf = new RandomAccessFile(embedPublicIm, "rw");
			raf.seek(54);
			
			for (int i = 0; i < im1.length; i++) {
				for (int j = 0; j < im1[i].length; j++) {
					
					if (i < im2.length && j < im2[i].length) {
						int r2 = (im1[i][j].getRed() - (im1[i][j].getRed() % 16)) + (im2[i][j].getRed() / 16);
						int b2 = (im1[i][j].getBlue() - (im1[i][j].getBlue() % 16)) + (im2[i][j].getBlue() / 16);
						int g2 = (im1[i][j].getGreen() - (im1[i][j].getGreen() % 16)) + (im2[i][j].getGreen() / 16);
						
						im1[i][j] = new Color(r2, g2, b2);
					} 
					else {
						int r2 = (im1[i][j].getRed() - (im1[i][j].getRed() % 16));
						int b2 = (im1[i][j].getBlue() - (im1[i][j].getBlue() % 16));
						int g2 = (im1[i][j].getGreen() - (im1[i][j].getGreen() % 16));
						
						im1[i][j] = new Color(r2, b2, g2);
						
					}
					
					raf.write(im1[i][j].getBlue());  
					raf.write(im1[i][j].getGreen()); 
					raf.write(im1[i][j].getRed()); 
					
				}
			
			}
			
			
			raf.close();
			return im1;
				

		} catch (IOException e) {
			System.out.println(e);
		}

		return null;

	}

}
