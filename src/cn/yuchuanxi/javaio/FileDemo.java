package cn.yuchuanxi.javaio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class FileDemo {

	/**
	 * RandomAccessFile(file, "rw") 有读/写的方法   最基础的读写类
	 *  FileOutputStream(file)
	 *  FileInputStream(file)
	 *  DataOutputStream(OutputStream out) 写/输出流
	 * BufferedOutputStream
	 * BufferedInputStream
	 *  FileWriter(file)
	 *  FileReader(file)
	 *  BufferedWriter(Writer out)  相比FileWrite(),好处是有缓存,减少磁盘写次数.
	 *  BufferedReader(Reader in) 有个readLine()方法很实用
	 *  
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
			 * RandomAccessFile 可读,可写,最基础的读写类
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
			
			/**
			 * FileOutputStream
			 * FileInputStream
			 * 相对于RandomAccessFile,貌似没什么优势.
			 */
			FileOutputStream fos=new FileOutputStream(file);
			fos.write("a".getBytes());
			fos.write("中文试试".getBytes());
			/**
			 * getBytes("GBK") ,类型要和当前文本的字符编码格式一致.
			 * 本例中,getBytes("gbk")是乱码.
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
			
			
			
			/**
			 * 对FileOutputStream的包装,
			 *通过writeInt(),writeChar(),readInt(),readChar()等方法, 
			 *实现整型,字符型等数据类型的直接读写.
			 * 	DataOutputStream(OutputStream out) 写/输出流
			 * writeInt(int);
			 * DataInputStream 读/输入流
			 * readInt()
			 * 
			 */
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(file));
			char wChar='c';
			dos.writeChar(wChar);
			long wLong=123L;
			dos.writeLong(wLong);
			dos.close();
			
			DataInputStream dis=new DataInputStream(new FileInputStream(file));
			char rChar =dis.readChar();
			long rLong=dis.readLong();
			System.out.println("通过DataInputStream获取的字符:"+rChar);
			System.out.println("通过DataInputStream.readLong()获取的长整型:"+rLong);
			dis.close();
			
			/**
			 * BufferedOutputStream
			 * BufferedInputStream
			 */
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
			bos.write("测试BufferedOutputStream".getBytes());
			bos.close();
			
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			int bisReadLength=0;
			byte[] bisReadByte=new byte[1024];
			bisReadLength=bis.read(bisReadByte,0,bisReadByte.length);
			System.out.println(new String(bisReadByte,0,bisReadLength));
			bis.close();
			
			/**
			 *  FileWriter(file)
			 *  FileReader(file)
			 *  
			 *  BufferedWriter(Writer out)  相比FileWrite(),好处是有缓存,减少磁盘写次数.
			 *  BufferedReader(Reader in) 有个readLine()方法很实用
			 */
			
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
			
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			System.out.println(br.readLine());
			br.close();
			fr.close();
		
		
				 
			 
			 
	
			
	}

}
