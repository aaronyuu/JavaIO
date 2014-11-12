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
		 * File file1=new File("�ļ�·��");
		 * �ļ�·�����������·��,Ҳ�����Ǿ���·��.�� new File("c:\\abc\\123.mp3")
		 * ��Ϊ"\"��ת���ַ�,���Ҫ������"\\"
		 */
			File file=new File("aa.dat");
			
			/**
			 * ���������,�ȴ����ļ�,
			 * ����Ѿ�����,��ɾ��,�ٴ����ļ�.
			 * ���������������.
			 */
			if (!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
			
			
			/**
			 * RandomAccessFile(file, "rw") �ж�/д�ķ���
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
			 * д����֮��getFilePointerָ��1��λ��,
			 * �ڶ�֮ǰҪ�Ȼص�0��λ��,
			 * ʹ��seek(0)�ص�0λ��.
			 */
			System.out.println("д����һ���ַ����λ��:"+raf.getFilePointer());
			raf.seek(0);
			System.out.println("Seek(0)֮�� ��λ��:"+raf.getFilePointer());
			int r=raf.read();
			char c=(char)r;
			System.out.println("������ʮ��������"+r+",ת�����ַ�"+c);
			raf.close();
			
			
			FileOutputStream fos=new FileOutputStream(file);
			fos.write("a".getBytes());
			fos.write("��������".getBytes());
			/**
			 * getBytes("GBK") ,����Ҫ�͵�ǰ�ı����ַ������ʽһ��.
			 * ������,getBytes("utf-16be")������.
			 */
			fos.write("��������UTF-16be".getBytes("utf-16be"));
			fos.write("��������GBK".getBytes("gbk"));
			fos.close();
			
			
			FileInputStream fis = new FileInputStream(file);
			byte[] str1 = new byte[1024];
			int strlength;
			strlength=fis.read(str1,0,str1.length);
			System.out.println(new String(str1,0,strlength));
			fis.close();
			
			FileWriter fw=new FileWriter(file);
			fw.write("hahaa��������,");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write("����BufferedWriter");
			/**
			 * ʹ��BufferedWriter,����ʹ��flushˢ�»���.
			 */
			bw.flush();
			/**
			 * ʹ�ùر�Ҫ�ǵùر����������,�������δ֪����.
			 */
			fw.close();
			bw.close();
			
			/**
			 * ������
			 */
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			System.out.println(br.readLine());
			
			br.close();
	
			
	}

}
