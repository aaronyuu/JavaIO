package cn.yuchuanxi.javaio;

import java.io.File;
import java.io.IOException;

public class FileDemoJava {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// File file = new File("a.dat");
		// FileUtils.writefile(file);
		// FileUtils.readfile(file);
		//
		// File file2=new File("b.data");
		// FileUtils.writefileByDataInputStream(file2);
		// System.out.println("通过DataInputStream读取");
		// FileUtils.readfileByDataInputStream(file2);

		// 测试文件拷贝
		File from = new File("D:\\JavaIO\\Halo.mp3");
		File[] to = new File[] { new File("D:\\JavaIO\\1.mp3"),
				new File("D:\\JavaIO\\2.mp3"), new File("D:\\JavaIO\\3.mp3"),
				new File("D:\\JavaIO\\4.mp3") };
		long start = 0;
		long end = 0;
		// start=System.currentTimeMillis();
		// FileUtils.copyFileByByte(from, to[0]);
		// end=System.currentTimeMillis();
		// System.out.println(end-start);

		start = System.currentTimeMillis();
		FileUtils.copyFileByBytes(from, to[1]);
		end = System.currentTimeMillis();
		System.out.println("copyFileByBytes:" + (end - start));

		start = System.currentTimeMillis();
		FileUtils.copyFileByDataOutputStream(from, to[2]);
		end = System.currentTimeMillis();
		System.out.println("copyFileByDataOutputStream:" + (end - start));

		start = System.currentTimeMillis();
		FileUtils.copyFileByBufferedOutputStream(from, to[3]);
		end = System.currentTimeMillis();
		System.out.println("copyFileByBufferedOutputStream:" + (end - start));

		FileUtils.listFile("d:\\javaio");
	}
}
