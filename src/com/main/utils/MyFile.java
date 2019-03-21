package com.main.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyFile extends File {
	
private String path = "";	//文件目录
	
	public MyFile(String p){
		super(p);
		this.path = p;
	}
	
	public String read() throws IOException{	//读取文本文件的内容
		File f = new File(path);
		FileInputStream fileInputStream = new FileInputStream(f);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String s = "";
		String s0 = null;
		while((s0 = bufferedReader.readLine()) != null){
			s += s0 + "\n";
		}
		bufferedReader.close();
		inputStreamReader.close();
		fileInputStream.close();
		return s;
	}
	
	public boolean writeFileContent(String newstr) throws IOException{
		//向文件中写入字符串的方法实现，filePath文件目录，newstr要输入的串
        Boolean bool = false;
        //String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";
        
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(path);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //文件原有内容
            for(int i = 0; (temp = br.readLine()) != null; i++){
                buffer.append(temp);
                // 行与行之间的分隔符 相当于“\n”
                buffer = buffer.append(System.getProperty("line.separator"));
            }
            buffer.append(newstr);
            
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
	}

}
