package com.main;

import java.awt.Dimension;
import java.awt.Point;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

public class CutImage {
	
	private String path;
	
	public CutImage() {
		// TODO Auto-generated constructor stub
		
	}
	
	public CutImage(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
		
	}
	
	public void cut(Point p, Dimension d) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat image = Imgcodecs.imread(path);
		int height = image.rows();
		int width = image.cols();
		System.out.println("Size:" + width + "*" + height);
		
		int dot = path.lastIndexOf('.');
		String fileName = path.substring(0, dot) + "_CUT";
		String fileType = path.substring(dot);
		
		System.out.println("Location:" + p.getX() + "," + p.getY());
		System.out.println("Size:" + d.getWidth() + "*" + d.getHeight());
		Rect rect = new Rect(p.x, p.y, d.width, d.height);
		Mat roi_img = new Mat(image, rect);
		Imgcodecs.imwrite(fileName + fileType, roi_img);
		
	}
	
	public Mat cut(Mat image, Point p, Dimension d) {
		Rect rect = new Rect(p.x, p.y, d.width, d.height);
		Mat roi_img = new Mat(image, rect);
		return roi_img;
	}
	
//	public static void main(String[] args ){
//		
//		CutImage cut = new CutImage("./resources/hongkong.bmp");
//		//²Ã¼ô
//		cut.cut(new Point(50, 50), new Dimension(200, 200));
//		System.out.println("CUT OK");
//		
//    }

}
