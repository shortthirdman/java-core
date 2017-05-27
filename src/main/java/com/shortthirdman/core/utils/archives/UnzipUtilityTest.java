package com.shortthirdman.core.utils.archives;

/**
 * A console application that tests the UnzipUtility class
 *
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class UnzipUtilityTest {

	public static void main(String[] args) {
		String zipFilePath = "e:\\Test\\MyPics.zip";
		String destDirectory = "f:\\Pics";
		UnzipUtility unzipper = new UnzipUtility();

		try {
			unzipper.unzip(zipFilePath, destDirectory);
		} catch (Exception ex) {
			// some errors occurred
			ex.printStackTrace();
		}
	}

}
