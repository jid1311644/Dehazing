package com.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ContrastControl {
	
	private String path;
	
	public ContrastControl() {
		// TODO Auto-generated constructor stub
	}
	
	public ContrastControl(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}
	
	public void contrast(int valueContrast) {
		
		try {
			int contrast_average = 127;
			
			BufferedImage bi, back;
			bi = ImageIO.read(new File(path));
			int w = bi.getWidth();
			int h = bi.getHeight();
			back = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			
			int pix;
			for(int i = 0; i < w; i++) {
				for(int j = 0; j < h; j++) {
					int pixel = bi.getRGB(i, j);
					Color color = new Color(pixel);
					
					double red = color.getRed();
					double green = color.getGreen();
					double blue = color.getBlue();
					double constrast = valueContrast / 100.0;
					if(valueContrast == 100) {
						red = (color.getRed() >= contrast_average)? 255: 0;
						green = (color.getGreen() >= contrast_average)? 255: 0;
						blue = (color.getBlue() >= contrast_average)? 255: 0; 
					}					
					else if(valueContrast >= 0 && valueContrast < 100) {
						red = contrast_average + (color.getRed() - contrast_average) / (1 - constrast);
						red = red >= 0? red: 0.0;
						red = red <=255? red: 255.0;
						green = contrast_average + (color.getGreen() - contrast_average) / (1 - constrast);
						green = green >=0? green: 0.0;
						green = green <=255? green: 255.0;
						blue = contrast_average + (color.getBlue() - contrast_average) / (1 - constrast);
						blue = blue >=0? blue: 0.0;
						blue = blue <=255? blue: 255.0;
					}
					else if(valueContrast >= -100 && valueContrast < 0){
						red = contrast_average + (color.getRed() - contrast_average) * (1 + constrast);
						green = contrast_average + (color.getGreen() - contrast_average) * (1 + constrast);
						blue = contrast_average + (color.getBlue() - contrast_average) * (1 + constrast);
					}
					
					int r = (int) red;
					int g = (int) green;
					int b = (int) blue;
					color = new Color(r, g, b);
					int x = color.getRGB();
					back.setRGB(i, j, x);
					
				}
			}
			int dot = path.lastIndexOf('.');
			String fileName = path.substring(0, dot) + "_CONTRAST";
			String fileType = path.substring(dot);
			fileName += fileType;
			ImageIO.write(back, fileType.substring(1), new File(fileName));
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public BufferedImage contrast(BufferedImage bi, int valueContrast) {
		
		int contrast_average = 127;
		BufferedImage back;
		int w = bi.getWidth();
		int h = bi.getHeight();
		back = new BufferedImage(w, h, bi.getType());
		
		int pix;
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				int pixel = bi.getRGB(i, j);
				Color color = new Color(pixel);
				
				double red = color.getRed();
				double green = color.getGreen();
				double blue = color.getBlue();
				double constrast = valueContrast / 100.0;
				if(valueContrast == 100) {
					red = (color.getRed() >= contrast_average)? 255: 0;
					green = (color.getGreen() >= contrast_average)? 255: 0;
					blue = (color.getBlue() >= contrast_average)? 255: 0; 
				}					
				else if(valueContrast >= 0 && valueContrast < 100) {
					red = contrast_average + (color.getRed() - contrast_average) / (1 - constrast);
					red = red >= 0? red: 0.0;
					red = red <=255? red: 255.0;
					green = contrast_average + (color.getGreen() - contrast_average) / (1 - constrast);
					green = green >=0? green: 0.0;
					green = green <=255? green: 255.0;
					blue = contrast_average + (color.getBlue() - contrast_average) / (1 - constrast);
					blue = blue >=0? blue: 0.0;
					blue = blue <=255? blue: 255.0;
				}
				else if(valueContrast >= -100 && valueContrast < 0){
					red = contrast_average + (color.getRed() - contrast_average) * (1 + constrast);
					green = contrast_average + (color.getGreen() - contrast_average) * (1 + constrast);
					blue = contrast_average + (color.getBlue() - contrast_average) * (1 + constrast);
				}
				
				int r = (int) red;
				int g = (int) green;
				int b = (int) blue;
				color = new Color(r, g, b);
				int x = color.getRGB();
				back.setRGB(i, j, x);
				
			}
		}
		
		return back;
		
	}
	
//	public static void main(String[] args) {
//		ContrastControl contrast = new ContrastControl("C:/Users/Mentor/Desktop/QQ截图20180427141648.png");
//		//对比度调节 -100~100
//		contrast.contrast(100);
//		System.out.println("CONTRAST OK");
//	}

}
