package com.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class SaturationControl {

	private String path;
	
	public SaturationControl() {
		// TODO Auto-generated constructor stub
	}
	
	public SaturationControl(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	public void saturation(int valueSaturation) {
		try {
			BufferedImage bi, back;
			bi = ImageIO.read(new File(path));back = new BufferedImage(
					bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_BGR);
			double percent = valueSaturation / 100.0;
			int w = bi.getWidth();
			int h = bi.getHeight();
			for(int i = 0; i < w; i++) {
				for(int j = 0; j < h; j++) {
					int pixel = bi.getRGB(i, j);
					Color color = new Color(pixel);
					int r = color.getRed();
					int g = color.getGreen();
					int b = color.getBlue();
					int rgbMax = (r > g? r: g) > b? (r > g? r: g): b;
					int rgbMin = (r < g? r: g) < b? (r < g? r: g): b;
					double delta = (rgbMax - rgbMin) / 255.0;
					
					double value;
					double L;
					double S;	//饱和度
					if(delta == 0) {
						
					}
					else if(delta > 0) {
						value = (rgbMax + rgbMin) / 255.0;
						L = value / 2.0;
						if(L < 0.5) {
							S = delta / value;
						}
						else {
							S = delta / (2 - value);
						}
						
						double alpha;
						if(percent >= 0) {
							if(percent + S >= 1) {
								alpha = S;
							}
							else {
								alpha = 1 - percent;
							}
							alpha = 1 / alpha - 1;
							
							int red = (int) (r + (r - L * 255) * alpha);
							int green = (int) (g + (g - L * 255) * alpha);
							int blue = (int) (b + (b - L * 255) * alpha);
							color = new Color(red, green, blue);
							back.setRGB(i, j, color.getRGB());
						}
						else {
							alpha = percent;
							
							int red = (int) (L * 255 + (r - L * 255) * (1 + alpha));
							int green = (int) (L * 255 + (g - L * 255) * (1 + alpha));
							int blue = (int) (L * 255 + (b - L * 255) * (1 + alpha));
							color = new Color(red, green, blue);
							back.setRGB(i, j, color.getRGB());
						}
					}
					
				}
			}
			int dot = path.lastIndexOf('.');
			String fileName = path.substring(0, dot) + "_SATURATION";
			String fileType = path.substring(dot);
			fileName += fileType;
			ImageIO.write(back, fileType.substring(1), new File(fileName));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage saturation(BufferedImage bi, int valueSaturation) {
		BufferedImage back;
		back = new BufferedImage(bi.getWidth(), bi.getHeight(), bi.getType());
		double percent = valueSaturation / 100.0;
		int w = bi.getWidth();
		int h = bi.getHeight();
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int pixel = bi.getRGB(i, j);
				Color color = new Color(pixel);
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				int rgbMax = (r > g? r: g) > b? (r > g? r: g): b;
				int rgbMin = (r < g? r: g) < b? (r < g? r: g): b;
				double delta = (rgbMax - rgbMin) / 255.0;
				
				double value;
				double L;
				double S;	//饱和度
				if(delta == 0) {
					
				}
				else if(delta > 0) {
					value = (rgbMax + rgbMin) / 255.0;
					L = value / 2.0;
					if(L < 0.5) {
						S = delta / value;
					}
					else {
						S = delta / (2 - value);
					}
					
					double alpha;
					if(percent >= 0) {
						if(percent + S >= 1) {
							alpha = S;
						}
						else {
							alpha = 1 - percent;
						}
						alpha = 1 / alpha - 1;
						
						int red = (int) (r + (r - L * 255) * alpha);
						int green = (int) (g + (g - L * 255) * alpha);
						int blue = (int) (b + (b - L * 255) * alpha);
						color = new Color(red, green, blue);
						back.setRGB(i, j, color.getRGB());
					}
					else {
						alpha = percent;
						
						int red = (int) (L * 255 + (r - L * 255) * (1 + alpha));
						int green = (int) (L * 255 + (g - L * 255) * (1 + alpha));
						int blue = (int) (L * 255 + (b - L * 255) * (1 + alpha));
						color = new Color(red, green, blue);
						back.setRGB(i, j, color.getRGB());
					}
				}
				
			}
		}
		
		return back;
	}
	
//	public static void main(String[] args) {
//		SaturationControl saturation = new SaturationControl("C:/Users/Mentor/Desktop/QQ截图20180427141648.png");
//		//饱和度 -100~100
//		saturation.saturation(90);
//		
//		System.out.println("SATURATION OK");
//	}
	
}
