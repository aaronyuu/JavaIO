package cn.yuchuanxi.javaio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOUtils {
	public static void OutputStreamWriteDemo() throws IOException{
	OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("ee.data"));
	osw.write("测试");
	osw.close();
	
	InputStreamReader isr=new InputStreamReader(new FileInputStream("ee.data"));
	char[] buff=new char[1024];
	int buffLength;
	buffLength=isr.read(buff);
	System.out.println(new String(buff,0,buffLength));
	
	isr.close();
	}
	
}
