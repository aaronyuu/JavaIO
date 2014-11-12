package cn.yuchuanxi.javaio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDemo {

	/**
	 * @throws IOException 
	 * File
	 * FileOutputStream
	 * FileInputStream
	 * FileWriter
	 * FileReader
	 * BufferedReader
	 */
	public static void main(String[] args) throws IOException {
		/**
		 * File file1=new File("文件路径");
		 * 文件路径可以是相对路径,也可以是绝对路径.如 new File("c:\\abc\\123.mp3")
		 * 因为"\"是转意字符,因此要用两个"\\"
		 */
			File file=new File("aa.dat");
			
			/**
			 * 如果不存在,先创建文件,
			 * 如果已经存在,先删除,再创建文件.
			 * 避免读到错误数据.
			 */
			if (!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
			
			
			/**
			 * RandomAccessFile(file, "rw") 有读/写的方法
			 *  FileOutputStream(file)
			 *  FileInputStream(file)
			 *  FileWriter(file)
			 *  FileReader(file)
			 *  BufferedReader(fr)
			 *  
			 */
			RandomAccessFile raf= new RandomAccessFile(file, "rw");
			raf.write('b');
			/**
			 * 写入完之后getFilePointer指到1的位置,
			 * 在读之前要先回到0的位置,
			 * 使用seek(0)回到0位置.
			 */
			System.out.println("写入完一个字符后的位置:"+raf.getFilePointer());
			raf.seek(0);
			System.out.println("Seek(0)之后 的位置:"+raf.getFilePointer());
			int r=raf.read();
			char c=(char)r;
			System.out.println("读到的十进制数据"+r+",转换成字符"+c);
			raf.close();
			
			
			FileOutputStream fos=new FileOutputStream(file);
			fos.write("a".getBytes());
			fos.write("中文试试".getBytes());
			/**
			 * getBytes("GBK") ,类型要和当前文本的字符编码格式一致.
			 * 本例中,getBytes("utf-16be")是乱码.
			 */
			fos.write("中文试试UTF-8".getBytes("utf-8"));
			fos.write("中文试试GBK".getBytes("gbk"));
			fos.close();
			
			
			FileInputStream fis = new FileInputStream(file);
			byte[] str1 = new byte[1024];
			int strlength;
			strlength=fis.read(str1,0,str1.length);
			System.out.println(new String(str1,0,strlength));
			fis.close();
			
			FileWriter fw=new FileWriter(file);
			fw.write("hahaa测试中文,");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write("测试BufferedWriter");
			/**
			 * 使用BufferedWriter,必须使用flush刷新缓存.
			 */
			bw.flush();
			/**
			 * 使用关闭要记得关闭输入输出流,否则会有未知错误.
			 */
			fw.close();
			bw.close();
			
			/**
			 * 读操作
			 */
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			System.out.println(br.readLine());
			br.close();
			fr.close();
		
			/**
			 * 测试文字编码转换
			 */
				 
			 
			 
	
			
	}

}
