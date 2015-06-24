package ltd.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import ltd.shapes.Shape;
import ltd.utils.ImageGrabber;

public class LTD {
	
	static Random random = new Random();
	
	public static void main(String[] args) throws IOException{
		ImageGrabber grabber = new ImageGrabber();

		Shape faceshape = new Shape("face");
		faceshape.addExample("images/faces/face.jpg");
		faceshape.addExample("images/faces/face2.jpg");
		faceshape.addExample("images/faces/face3.jpg");

		BufferedImage img = new BufferedImage(1024, 1024,BufferedImage.TYPE_INT_RGB);

		File[] listOfFiles = new File("images/faces").listFiles();
		
		ArrayList<int[][]> rgb_data = new ArrayList<int[][]>();

		for(int i = 0; i < listOfFiles.length; i++){
			System.out.println("Reading from: "+listOfFiles[i].getName());
			int[][] pixels = grabber.grabPixelColors(listOfFiles[i].getAbsolutePath(), 640, 480);
	
			rgb_data.add(pixels);
			
		}
		
		for(int i = 0; i < rgb_data.size(); i++){
			
			for(int x = 0; x < img.getWidth(); x++){
				for(int y = 0; y < img.getHeight(); y++){
					img.setRGB(x, y, getMediumColor(x, y, rgb_data).getRGB());
					
				}
			}
		}
		
		ImageIO.write(img, "jpg", new File("test2.jpg"));
	}

	private static int getMedium (int x, int y, ArrayList<int[][]> arrays){
		int medium = 0;
		for(int i = 0; i < arrays.size(); i++){
			int m = arrays.get(i)[x][y];
			medium += m;
		}
		
		return medium/arrays.size();
	}
	
	private static Color getMediumColor (int x, int y, ArrayList<int[][]> arrays){
		ArrayList<Color> colors = new ArrayList<Color>();
		int m_r = 0;
		int m_g = 0;
		int m_b = 0;
		for(int i = 0; i < arrays.size(); i++){
			int rgb = arrays.get(i)[x][y];
			colors.add(new Color(rgb));
		}



		for(int i = 0; i < colors.size(); i++){
			int r = colors.get(i).getRed();
			m_r += r;

		}

		for(int i = 0; i < colors.size(); i++){
			int g = colors.get(i).getGreen();
			m_g += g;
		}

		for(int i = 0; i < colors.size(); i++){
			int b = colors.get(i).getBlue();
			m_b += b;
		}


		return new Color((m_r/colors.size()), (m_g/colors.size()), (m_b/colors.size()));
	}
}
