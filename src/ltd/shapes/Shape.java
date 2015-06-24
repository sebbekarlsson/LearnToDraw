package ltd.shapes;

import java.io.IOException;

import ltd.utils.ImageGrabber;

public class Shape {
	public String name;
	public int[][] rgbs = new int[1024][1024];
	
	public Shape(String name){
		this.name = name;
	}
	
	public void addExample(String path){
		ImageGrabber grabber = new ImageGrabber();
		int[][] rgbs;
		try {
			rgbs = grabber.grabPixelColors(path, 640, 480);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
