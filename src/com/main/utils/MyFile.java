package com.main.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MyFile extends File {
	
private String path = "";	//�ļ�Ŀ¼
	
	public MyFile(String p){
		super(p);
		this.path = p;
	}
	
	public String read() throws IOException{	//��ȡ�ı��ļ�������
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
		//���ļ���д���ַ����ķ���ʵ�֣�filePath�ļ�Ŀ¼��newstrҪ����Ĵ�
        Boolean bool = false;
        //String filein = newstr+"\r\n";//��д����У�����
        String temp  = "";
        
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(path);//�ļ�·��(�����ļ�����)
            //���ļ�����������
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            
            //�ļ�ԭ������
            for(int i = 0; (temp = br.readLine()) != null; i++){
                buffer.append(temp);
                // ������֮��ķָ��� �൱�ڡ�\n��
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
            //��Ҫ���ǹر�
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
