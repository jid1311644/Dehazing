package com.main.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class MatToBufImg {
	
	private Mat matrix;
	private MatOfByte mob;
	private String fileExten;

	public MatToBufImg(Mat image, String fileExtension) {
		// TODO Auto-generated constructor stub
		this.matrix = image;
		this.fileExten = fileExtension;
		this.mob = new MatOfByte();
	}
	
	public BufferedImage getImage() {
		Imgcodecs.imencode(fileExten, matrix, mob);
		byte[] byteArry = mob.toArray();
		BufferedImage bufImg = null;
		try {
			InputStream in = new ByteArrayInputStream(byteArry);
			bufImg = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bufImg;
	}
	
}
