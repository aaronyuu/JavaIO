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
			System.out.println("�ļ�������");
		}
		if (!from.exists()) {
			System.out.println(from + "�����ļ�");
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
			System.out.println("�ļ�������");
		}
		if (!from.exists()) {
			System.out.println(from + "�����ļ�");
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
			throw new IllegalArgumentException("�ļ�:" + from.getAbsolutePath()
					+ "������");
		}
		if (!from.exists()) {
			throw new IllegalArgumentException(from + "�����ļ�,�������ļ���");
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
			throw new IllegalArgumentException("�ļ�:" + from.getAbsolutePath()
					+ "������");
		}
		if (!from.exists()) {
			throw new IllegalArgumentException(from + "�����ļ�,�������ļ���");
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
	 * ��ȡָ��Ŀ¼�µ��ļ��б� File���͵�Ŀ¼ Ŀ¼
	 */
	public static void listFile(File dir) {
		listFile(dir.toString());
	}

	/**
	 * ��ȡָ��Ŀ¼�µ��ļ��б�
	 * 
	 * @param dir
	 *            Ŀ¼
	 */
	public static void listFile(String dir) {

		File file = new File(dir);
		if (!file.exists()) {
			System.out.println(dir + "������!");
		} else if (!file.isDirectory()) {
			System.out.println(dir + "����Ŀ¼");
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
	 * ���ļ�
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
		// ��16������ʾ
		System.out.println(Integer.toHexString(i));

		// �鿴�Ƿ�֧��mark/reset����
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
		 * д����������
		 */
		int i = 0x7fff8ffe;
		System.out.println(i);
		raf.writeInt(i);

		/**
		 * д���ַ���
		 */
		String s = "China";
		raf.writeBytes(s);

		/**
		 * д������
		 */
		raf.seek(0);
		String s2 = "��������";
		byte[] b2 = s2.getBytes("GBK");
		raf.write(b2);

		/**
		 * ���ļ����� ��֮ǰ�Ƚ�ָ���ƶ���ͷ��
		 */
		raf.seek(0);
		String readline;
		readline = raf.readLine();
		System.out.println(readline);

		// �ٴζ�֮ǰ,��Ҫ��ָ���ƶ���ͷ��
		raf.seek(0);
		byte[] buf = new byte[(int) raf.length()];
		raf.read(buf);
		System.out.println(Arrays.toString(buf));

		// ���Ҫ�ǵùر�raf
		raf.close();
	}

	public void tempcode2() {
		// File file1 = new File("d:\\aa.txt");
		// File file2 = new File("d:\\mulu");
		//
		// if (!file1.exists()) {
		// System.out.println("���ļ�������,���ڴ����ļ�");
		// try {
		// file1.createNewFile();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// if (file1.isDirectory()) {
		// System.out.println(file1.getName() + "��Ŀ¼");
		// } else if (file1.isFile()) {
		// System.out.println(file1.getName() + "��һ���ļ�");
		// } else {
		// System.out.println("�ļ�������!!");
		// }
		//
		// if (!file2.exists()) {
		// System.out.println("���ļ�������,���ڴ����ļ�");
		// file2.mkdir();
		// /**
		// * �༶Ŀ¼ʹ��mkdirs()
		// */
		// // file2.mkdirs();
		// }
		//
		//
		// /**
		// * �ж��Ƿ����ļ���
		// */
		// System.out.println(file1.isDirectory());
		// /**
		// * �ж��Ƿ����ļ�
		// */
		// System.out.println(file2.isFile());
		// /**
		// * ɾ���ļ�/�ļ���
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
