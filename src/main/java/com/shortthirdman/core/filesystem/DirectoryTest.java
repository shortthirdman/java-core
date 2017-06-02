package com.shortthirdman.core.filesystem;

import java.io.*;

/**
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class DirectoryTest implements FilenameFilter {
	int iCount = 0;
	public String str1 = null;

	/**
	 * @param Filter
	 * @param FilePath
	 * @throws Exception
	 */
	public void process(String Filter, String FilePath) throws Exception {
		str1 = Filter;
		File file = new File(FilePath);
		String [] str = file.list(this);
		File file1 = new File(FilePath + "/" + Filter);

		if(!file1.isDirectory()) {
			System.out.println(iCount++); 
			file1.mkdir();
			for(int i = 0; i < str.length; i++) {
				System.out.println("Writing the file " + str[i] + "....");
				File file2 = new File(FilePath + "/" + str[i]);
				File file3 = new File(FilePath+ "/" + Filter + "/" + str[i]);
				moveFiles(file2,file3);
				System.out.println("Completed");
			}
		}
		// file.mkdir();
	}

	/* (non-Javadoc)
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	public boolean accept(File dir, String s) {
		if(s.startsWith(str1)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main (String args[]) throws Exception {
		String path = "D:/Maruti/Test";

		// TestMaruti tm = new TestMaruti();

		File allFiles = new File(path);

		String [] strFiles = allFiles.list();

		for(int j = 0; j < strFiles.length; j++) {
			String flName = strFiles[j];
			flName = flName.substring(0, flName.indexOf("."));
//			tm.process(flName,path);
		}
	}

	/**
	 * @param source
	 * @param destination
	 * @throws IOException
	 */
	public void moveFiles(File source,File destination) throws IOException {
		InputStream in = new FileInputStream(source);

		OutputStream out = new FileOutputStream(destination);

		byte [] buf = new byte[4096];

		int len;

		while((len = in.read(buf)) > 0){
			out.write(buf,0,len);
		} 
		in.close();
		out.close();
		source.delete();
	}
}