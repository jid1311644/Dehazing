package com.main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BrightnessControl {
	
	private String path;
	
	public BrightnessControl(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	public BrightnessControl() {
		// TODO Auto-generated constructor stub
	}

	public void light(int valueBrightness) {
		try {
			BufferedImage bi, back;
			bi = ImageIO.read(new File(path));
			back = new BufferedImage(
					bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_BGR);
			int w = bi.getWidth();
			int h = bi.getHeight();
			for(int i = 0; i < w; i++) {
				for(int j = 0; j < h; j++) {
					int pixel = bi.getRGB(i, j);
					Color color = new Color(pixel);
					int red = color.getRed() + valueBrightness;
					if(red > 255) {
						red = 255;
					}
					if(red < 0) {
						red = 0;
					}
					int green = color.getGreen() + valueBrightness;
					if(green > 255) {
						green = 255;
					}
					if(green < 0) {
						green =0;
					}
					int blue = color.getBlue() + valueBrightness;
					if(blue > 255) {
						blue = 255;
					}
					if(blue < 0) {
						blue =0;
					}
					color = new Color(red, green, blue);
					back.setRGB(i, j, color.getRGB());
				}
			}
			int dot = path.lastIndexOf('.');
			String fileName = path.substring(0, dot) + "_LIGHT";
			String fileType = path.substring(dot);
			fileName += fileType;
			ImageIO.write(back, fileType.substring(1), new File(fileName));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public BufferedImage light(BufferedImage bi, int valueBrightness) {
		BufferedImage back;
		back = new BufferedImage(
				bi.getWidth(), bi.getHeight(), bi.getType());
		int w = bi.getWidth();
		int h = bi.getHeight();
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int pixel = bi.getRGB(i, j);
				Color color = new Color(pixel);
				int red = color.getRed() + valueBrightness;
				if(red > 255) {
					red = 255;
				}
				if(red < 0) {
					red = 0;
				}
				int green = color.getGreen() + valueBrightness;
				if(green > 255) {
					green = 255;
				}
				if(green < 0) {
					green =0;
				}
				int blue = color.getBlue() + valueBrightness;
				if(blue > 255) {
					blue = 255;
				}
				if(blue < 0) {
					blue =0;
				}
				color = new Color(red, green, blue);
				back.setRGB(i, j, color.getRGB());
			}
		}
		return back;
	}
	
	
//	public static void main(String[] args) {
//		BrightnessControl light = new BrightnessControl("./resources/train.bmp");
//		//调节亮度参数范围-100~100
//		light.light(-100);
//		System.out.println("LIGHT OK");
//	}
	

}
