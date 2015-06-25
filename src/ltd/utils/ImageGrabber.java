package ltd.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ltd.main.LTD;

public class ImageGrabber {
	public int[][] grabPixelColors(String path, int w, int h) throws IOException{
		BufferedImage image = ImageConverter.toBufferedImage(ImageIO.read(new File(path)).getScaledInstance(w, h, 1));
		
		int[][] rgbs = new int[LTD.WIDTH][LTD.HEIGHT];
		for(int x = 0; x < image.getWidth(); x++){
			for(int y = 0; y < image.getHeight(); y++){
				rgbs[x][y] = image.getRGB(x, y);
			}
		}
		
		return rgbs;
		
	}
}
