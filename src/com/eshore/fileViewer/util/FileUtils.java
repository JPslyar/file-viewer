package com.eshore.fileViewer.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;

/**
 * @author Liangr Nov 12, 2013
 */
public class FileUtils {
	
	private static MimetypesFileTypeMap map = null;

	/**
	 * �޸�·�����⣬�����Ƕ��"//" �����ǡ����֡�"\\"����������
	 * 
	 * @author Liangr
	 * @param path
	 * @return Nov 14, 2013 String
	 */
	public static String fixPath(String path) {
		if(StringUtils.isBlank(path)){
			return path;
		}
		String[] split = path.split("://");
		if (split != null && split.length == 2) {
			String head = split[0];
			String last = split[1];
			last = last.replaceAll("\\\\", "/");
			last = last.replaceAll("///", "/");
			last = last.replaceAll("//", "/");
			path = head + "://" + last;
		} else {
			path = path.replaceAll("\\\\", "/");
			path = path.replaceAll("///", "/");
			path = path.replaceAll("//", "/");
		}
		return path;
	}

	public static long getFileSizes(File f) {// ȡ���ļ���С
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fis = null;
				}
			}
		}
		return s;
	}
	
	public static long getFileSizes(String file) {// ȡ���ļ���С
		File f = new File(file);
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fis = null;
				}
			}
		}
		return s;
	}
	
	public static long getFileSizes(byte[] buf) {// ȡ���ļ���С
		long s = 0;
		InputStream fis = null;
		try {
			fis = new ByteArrayInputStream(buf);
			s = fis.available();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				fis = null;
			}
		}
		return s;
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String filePath) {
		String name = null;
		File file = null;
		try {
			file = new File(filePath);
			if (file.exists()) {
				name = file.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}

	/**
	 * ��ȡ�ļ���׺
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}
	
	/**
	 * ��ȡ�ļ�����
	 * @author Liangr
	 * @param fileName
	 * @return
	 * 2014-2-24
	 * String
	 */
	public static String getFileType(File file) {
		if (map == null) {
			try {
				InputStream in = FileUtils.class.getResourceAsStream("/conf/mime.types");
				map = new MimetypesFileTypeMap(in);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (map == null) {
			return null;
		}
		String contentType = map.getContentType(file);
		return contentType;
	}

	public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
		File sFile = new File(inputFile);
		File oFile = new File(outputFile);
		if (!oFile.getParentFile().exists()) {
			oFile.getParentFile().mkdirs();
		}
		FileInputStream fis = new FileInputStream(sFile.getPath());
		FileOutputStream fos = new FileOutputStream(oFile);
		byte[] buffer = new byte[32 * 1024]; // IO������:32KB
		int i = 0;
		try {
			while ((i = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
			try {
				fis.close();
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
