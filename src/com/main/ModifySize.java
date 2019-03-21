package com.main;

import java.awt.Dimension;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ModifySize {
	
	private String path;
	private Mat image;
	
	public ModifySize(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.image = Imgcodecs.imread(path);
	}
	
	public  ModifySize() {
		// TODO Auto-generated constructor stub
	}
	
	public void size(Dimension d) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.image = Imgcodecs.imread(path);
		
		System.out.println("Size:" + image.cols() + "*" + image.rows());
		double p = image.width() / (image.height() + 0.0);
		System.out.println(p);
		
		int dot = path.lastIndexOf('.');
		String fileName = path.substring(0, dot) + "_SIZE";
		String fileType = path.substring(dot);
		fileName += fileType;
		
		Mat dst = new Mat();
		Imgproc.resize(image, dst, new Size(d.getWidth(), d.getHeight()), 0, 0, Imgproc.INTER_AREA);
		
		Imgcodecs.imwrite(fileName, dst);
		
	}
	
	public Mat size(Mat image, Dimension d) {
		Mat dst = new Mat();
		Imgproc.resize(image, dst, new Size(d.getWidth(), d.getHeight()), 0, 0, Imgproc.INTER_AREA);
		return dst;
	}
	
//	public static void main(String[] args) {
//
//		ModifySize size = new ModifySize("./resources/road.jpg");
//		//ÐÞ¸Ä´óÐ¡
//		size.size(new Dimension(1000, 900));
//		System.out.println("SIZE OK");
//    }

}
