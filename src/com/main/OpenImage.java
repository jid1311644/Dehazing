package com.main;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class OpenImage {
	
	private JFrame frame;
	private Mat image;
	private String fileExtension;
	private String path;

	public OpenImage(JFrame f) {
		// TODO Auto-generated constructor stub
		this.frame = f;
	}
	
	public Mat getImage() {
		return image;
	}

	public String getFileExtension() {
		return fileExtension;
	}
	
	public String getPath() {
		return path;
	}

	public boolean open() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select a image..");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG & BMP Images", "jpg", "png", "bmp");
		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		
		if(chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			path = chooser.getSelectedFile().getAbsolutePath();
			fileExtension = path.substring(path.length() - 4);
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			image = Imgcodecs.imread(path);
			return true;
		}
		return false;
	}
	
}
