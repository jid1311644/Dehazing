package com.main.utils;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;

public class BufImgToMat {
	
	private BufferedImage origin;
	private int matType;
	
	public BufImgToMat(BufferedImage image, int matType) {
		// TODO Auto-generated constructor stub
		this.origin = image;
		this.matType = matType;
	}
	
	public Mat getMat() {
		
		byte[] pixels = ((DataBufferByte) origin.getRaster().getDataBuffer()).getData();
		Mat mat = Mat.ones(origin.getHeight(), origin.getWidth(), matType);
		mat.put(0, 0, pixels);
		return mat;
		
	}

}
