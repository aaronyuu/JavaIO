package cn.yuchuanxi.javaio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class FileUtils {

	public static void copyFileByByte(File from, File to) throws IOException {
		if (!from.exists()) {
			System.out.println("文件不存在");
		}
		if (!from.exists()) {
			System.out.println(from + "不是文件");
			// throw InterceptorOperations
			// throw IORInterceptor;
		}
		FileOutputStream fos = new FileOutputStream(to);
		FileInputStream fis = new FileInputStream(from);
		int c;
		int j = 0;
		while ((c = fis.read()) != -1) {
			fos.write(c);
//			fos.flush();
			if (j++ % 1000000 == 0)
				System.out.println(j);
		}
		fos.flush();
		fos.close();
		fis.close();
	}

	public static void copyFileByBytes(File from, File to) throws IOException {
		if (!from.exists()) {
			System.out.println("文件不存在");
		}
		if (!from.exists()) {
			System.out.println(from + "不是文件");
			// throw InterceptorOperations
			// throw IORInterceptor;
		}
		FileOutputStream fos = new FileOutputStream(to);
		FileInputStream fis = new FileInputStream(from);
		byte[] buf = new byte[1024 * 1024];
		int b;
		while ((b=fis.read(buf)) != -1) {
			fos.write(buf, 0, b);
//			fos.flush();
		}
		fos.flush();
		fos.close();
		fis.close();
	}

	public static void copyFileByDataOutputStream(File from, File to)
			throws IOException {
		if (!from.exists()) {
			throw new IllegalArgumentException("文件:" + from.getAbsolutePath()
					+ "不存在");
		}
		if (!from.exists()) {
			throw new IllegalArgumentException(from + "不是文件,可能是文件夹");
			// throw InterceptorOperations
			// throw IORInterceptor;
		}
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(to));
		DataInputStream dis = new DataInputStream(new FileInputStream(from));

		int i;
		byte[] buf = new byte[1024 * 1024];
		while ((i = dis.read(buf, 0, buf.length)) != -1) {
			dos.write(buf, 0, i);
			
		}
		dos.flush();
		dos.close();
		dis.close();
	}

	public static void copyFileByBufferedOutputStream(File from, File to)
			throws IOException {
		if (!from.exists()) {
			throw new IllegalArgumentException("文件:" + from.getAbsolutePath()
					+ "不存在");
		}
		if (!from.exists()) {
			throw new IllegalArgumentException(from + "不是文件,可能是文件夹");
			// throw InterceptorOperations
			// throw IORInterceptor;
		}
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(to));
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(from));

		int i;
		byte[] buf = new byte[1024 * 1024];
		while ((i = bis.read(buf, 0, buf.length)) != -1) {
			bos.write(buf, 0, i);
			
		}
		bos.flush();
		bos.close();
		bis.close();
	}
	
	/**
	 * 获取指定目录下的文件列表 File类型的目录 目录
	 */
	public static void listFile(File dir) {
		listFile(dir.toString());
	}

	/**
	 * 获取指定目录下的文件列表
	 * 
	 * @param dir
	 *            目录
	 */
	public static void listFile(String dir) {

		File file = new File(dir);
		if (!file.exists()) {
			System.out.println(dir + "不存在!");
		} else if (!file.isDirectory()) {
			System.out.println(dir + "不是目录");
		} else {
			// String[] files=file.list();
			// for (String string : files) {
			// System.out.println(string);
			// }
			File[] files2 = file.listFiles();
			if (files2 != null && files2.length > 0) {
				for (File file2 : files2) {
					System.out.println(file2.getAbsolutePath());
					if (file2.isDirectory()) {
						listFile(file2.getAbsolutePath());
					}
				}
			}

		}
	}

	/**
	 * 读文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void readfile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[20 * 1024];
		int buflength = 0;
		if ((buflength = fis.read(buf)) != -1) {
			for (int k = 0; k < buflength; k++) {
				// System.out.print(buf[k]&0xf);
				System.out.print(buf[k] + " ");
			}

		}

		fis.close();
	}

	public static void readfile(String file) throws IOException {
		readfile(new File(file));
	}

	public static void readfileByDataInputStream(File file) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		dis.mark(0);
		char c = dis.readChar();
		System.out.println(c);
		char c2 = dis.readChar();
		System.out.println(c2);
		int i = dis.readInt();
		System.out.println(i);
		// 以16进制显示
		System.out.println(Integer.toHexString(i));

		// 查看是否支持mark/reset方法
		System.out.println(dis.markSupported());
		// dis.reset();
		//
		// dis.readChar();
		// dis.readChar();
		// System.out.println(dis.readInt());
		//
		dis.close();
	}

	public static void writefileByDataInputStream(File file) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(file));
		dos.writeChar('a');
		dos.writeChar('g');
		dos.writeInt(0x7fffffff);

		dos.close();

	}

	public static void writefile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write('a');
		fos.write('d');
		fos.write(0x7ffffffe);

		fos.close();
	}

	public static void writefile(String file) throws IOException {
		writefile(new File(file));
	}

	public void tempcode() throws IOException {
		File dir = new File("test");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(dir, "aa.txt");
		if (!file.exists()) {
			file.createNewFile();
		}

		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		/**
		 * 写入整形数据
		 */
		int i = 0x7fff8ffe;
		System.out.println(i);
		raf.writeInt(i);

		/**
		 * 写入字符串
		 */
		String s = "China";
		raf.writeBytes(s);

		/**
		 * 写入中文
		 */
		raf.seek(0);
		String s2 = "测试中文";
		byte[] b2 = s2.getBytes("GBK");
		raf.write(b2);

		/**
		 * 读文件测试 读之前先将指针移动到头部
		 */
		raf.seek(0);
		String readline;
		readline = raf.readLine();
		System.out.println(readline);

		// 再次读之前,还要把指针移动到头部
		raf.seek(0);
		byte[] buf = new byte[(int) raf.length()];
		raf.read(buf);
		System.out.println(Arrays.toString(buf));

		// 最后要记得关闭raf
		raf.close();
	}

	public void tempcode2() {
		// File file1 = new File("d:\\aa.txt");
		// File file2 = new File("d:\\mulu");
		//
		// if (!file1.exists()) {
		// System.out.println("该文件不存在,现在创建文件");
		// try {
		// file1.createNewFile();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// if (file1.isDirectory()) {
		// System.out.println(file1.getName() + "是目录");
		// } else if (file1.isFile()) {
		// System.out.println(file1.getName() + "是一个文件");
		// } else {
		// System.out.println("文件不存在!!");
		// }
		//
		// if (!file2.exists()) {
		// System.out.println("该文件不存在,现在创建文件");
		// file2.mkdir();
		// /**
		// * 多级目录使用mkdirs()
		// */
		// // file2.mkdirs();
		// }
		//
		//
		// /**
		// * 判断是否是文件夹
		// */
		// System.out.println(file1.isDirectory());
		// /**
		// * 判断是否是文件
		// */
		// System.out.println(file2.isFile());
		// /**
		// * 删除文件/文件夹
		// */
		// file1.delete();
		// System.out.println(file1);
		// System.out.println(file1.getAbsolutePath());
		// System.out.println(file1.getParent());
		// System.out.println(file1.getName());
		//
		// FileUtils.listFile("D:\\mulu");

	}
}
