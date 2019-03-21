package com.main;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class RotateImage {
	
	public final static int CLOCKWISE = 0;
	public final static int MIRROR = 2;
	
	private String path;
	
	int i = 0;
	
	public RotateImage() {
		// TODO Auto-generated constructor stub
	}
	
	public RotateImage(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	public void rotate(int ways) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat image = Imgcodecs.imread(path);
		Mat dst0 = new Mat();
		Mat dst1 = new Mat();
		
		int dot = path.lastIndexOf('.');
		String fileName = path.substring(0, dot) + "_ROTATE";
		String fileType = path.substring(dot);
		
		switch (ways) {
		case CLOCKWISE:
			Core.transpose(image, dst0);
			Core.flip(dst0, dst1, 1);
			fileName = fileName + "_CLOCKWISE" + fileType;
            Imgcodecs.imwrite(fileName, dst1);  
			break;
		case MIRROR:
			Core.flip(image, dst1, 1);
			fileName = fileName + "_MIRROR" + fileType;
            Imgcodecs.imwrite(fileName, dst1);  
			break;
		default:
			break;
		}
	}
	
	public Mat rotate(Mat image, int ways) {
		Mat dst0 = new Mat();
		Mat dst1 = new Mat();
		switch (ways) {
		case CLOCKWISE:
			Core.transpose(image, dst0);
			Core.flip(dst0, dst1, 1); 
			break;
		case MIRROR:
			Core.flip(image, dst1, 1);  
			break;
		default:
			break;
		}
		
		Imgcodecs.imwrite("G:/new/test" + (i++) + ".png", dst1);  
		
		return dst1;
	}
	
//	public static void main(String[] args) {
//		RotateImage rotate = new RotateImage("./resources/gugong.bmp");
//		//Ðý×ª
//		rotate.rotate(CLOCKWISE);
//		rotate.rotate(MIRROR);
//		
//		System.out.println("ROTATE OK");
//	}

}
