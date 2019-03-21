package com.main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import com.main.utils.MyFile;


public class User {

	private LinkedList<String> userName;
	private LinkedList<String> password;
	
	private boolean isAdmin = false;
	
	public User() {
		userName = new LinkedList<>();
		password = new LinkedList<>();
		File file = new File("./users/user.txt");
		if(!file.exists()) {
			try {
				file.createNewFile();
				File file2=new File("./users/password.txt");
				if(file2.exists()) {
					file2.delete();
				}
				file2.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userName.add("admin");
			password.add("adimn");
			saveUser();
		}
		else{
			try {
				MyFile myFile=new MyFile("./users/user.txt");
				String userList = myFile.read() + " ";
				int p = 0;
				for(int i = 0; i < userList.length() - 1; i++) {
					if(userList.substring(i, i + 1).equals("\n")) {
						userName.add(userList.substring(p, i));
						p = i + 1;
					}
				}
				MyFile myFile2=new MyFile("./users/password.txt");
				String pswList = myFile2.read() + " ";
				int p2 = 0;
				for(int i = 0; i < pswList.length() - 1;i++) {
					if(pswList.substring(i, i + 1).equals("\n")) {
						password.add(pswList.substring(p2, i));
						p2 = i + 1;
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public String[] getUsers() {
		int size = userName.size();
		String[] us = new String[size];
		for(int i = 0; i < size; i++) {
			us[i] = new String(userName.get(i));
		}
		return us;
	}
	
	public String[] getPowers() {
		int size = userName.size();
		String[] ps = new String[size];
		ps[0] = new String("0");
		for(int i = 1; i < size; i++) {
			ps[i] = new String("1");
		}
		return ps;
	}
	
	public int isExist(String name) {
		return userName.indexOf(name);
	}

	public boolean login(String name, String psw) {
		int i = -1;
		if((i = userName.indexOf(name)) == -1) {
			return false;
		}
		else {
			String p = new Encrypt(name, psw).Encrypt();
			if(this.password.get(i).equals(p)) {
				if(i == 0) {
					isAdmin = true;
				}
				else {
					isAdmin = false;
				}
				return true;
			}
			return false;
		}
	}
	
	public boolean addUser(String name, String psw) {
		int i = -1;
		if((i = userName.indexOf(name)) != -1)
			return false;
		userName.add(name);
		String p = new Encrypt(name, psw).Encrypt();
		password.add(p);
		saveUser();
		return true;
	}
	
	public boolean deleteUser(String name) {
		int i = -1;
		if((i = userName.indexOf(name)) == -1)
			return false;
		userName.remove(i);
		password.remove(i);
		saveUser();
		return true;
	}
	
	public boolean changePsw(String name, String newPsw){
		int i = -1;
		if((i = userName.indexOf(name)) == -1)
			return false;
		else {
			String p = new Encrypt(name, newPsw).Encrypt();
			password.set(i, p);
			saveUser();
			return true;
		}
	}
	
	public void saveUser() {
		String name = "";
		String psw = "";
		for(int i = 0; i < userName.size(); i++) {
			name += userName.get(i) + "\r\n";
			psw += password.get(i) + "\r\n";
		}
		try {
			MyFile myFile1 = new MyFile("./users/user.txt");
			if(myFile1.exists()) {
				myFile1.delete();
			}
			myFile1.createNewFile();
			myFile1.writeFileContent(name);
			
			MyFile myFile2 = new MyFile("./users/password.txt");
			if(myFile2.exists()) {
				myFile2.delete();
			}
			myFile2.createNewFile();
			myFile2.writeFileContent(psw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
