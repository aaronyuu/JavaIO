package cn.yuchuanxi.javaio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDemo {

	/**
	 * @throws IOException 
	 * FileOutputStream
	 * FileInputStream
	 * FileWriter
	 * FileReader
	 * BufferedReader
	 */
	public static void main(String[] args) throws IOException {
			File file=new File("aa.dat");
			FileOutputStream fos=new FileOutputStream(file);
			fos.write("a".getBytes());
			fos.close();
			

			
			FileInputStream fis = new FileInputStream(file);
			byte[] str1 = new byte[1024];
			int strlength;
			strlength=fis.read(str1,0,str1.length);
			System.out.println(new String(str1,0,strlength));
			fis.close();
			
			FileWriter fw=new FileWriter(file);
			fw.write("hahaa≤‚ ‘÷–Œƒ");
			fw.close();
			
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			System.out.println(br.readLine());
			br.close();
	
			
	}

}
