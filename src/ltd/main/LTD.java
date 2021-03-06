package ltd.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import ltd.utils.ImageGrabber;

public class LTD {

	static Random random = new Random();
	public static int WIDTH = 1900;
	public static int HEIGHT = 1080;

	public static void main(String[] args) throws IOException{
		ImageGrabber grabber = new ImageGrabber();



		BufferedImage img = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);

		File[] listOfFiles = new File("images/faces").listFiles();

		ArrayList<int[][]> rgb_data = new ArrayList<int[][]>();

		for(int i = 0; i < listOfFiles.length; i++){
			System.out.println("Reading from: "+listOfFiles[i].getName());
			if(listOfFiles[i].getName().endsWith("g")){
				int[][] pixels = grabber.grabPixelColors(listOfFiles[i].getAbsolutePath(), WIDTH, HEIGHT);

				rgb_data.add(pixels);
			}

		}

		for(int i = 0; i < rgb_data.size(); i++){

			for(int x = 0; x < img.getWidth(); x++){
				for(int y = 0; y < img.getHeight(); y++){
					Color color = new Color(255);



					if(random.nextInt(2) == 0){
						color = getMediumColor(x, y, rgb_data);
					}else{
						color = getMedium(x, y, rgb_data);

					}



					img.setRGB(x, y, color.getRGB());


				}
			}
		}

		ImageIO.write(img, "jpg", new File("f2.jpg"));
	}

	private static Color getMedium (int x, int y, ArrayList<int[][]> arrays){
		int medium = 0;
		for(int i = 0; i < arrays.size(); i++){
			int m = arrays.get(i)[x][y];
			medium += m;
		}

		return new Color(medium/arrays.size());
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
			int g = colors.get(i).getGreen();
			int b = colors.get(i).getBlue();

			m_r += r;
			m_g += g;
			m_b += b;

		}

		return new Color((m_r/arrays.size()), (m_g/arrays.size()), (m_b/arrays.size()));
	}
}
