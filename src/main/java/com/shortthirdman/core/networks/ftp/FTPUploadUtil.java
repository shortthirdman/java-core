package com.shortthirdman.core.networks.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

/**
 * This utility class provides a method for uploading only structure of a
 * directory from local computer to a remote FTP server, based on Apache Commons
 * Net library.
 *
 * @author Swetank Mohanty (shortthirdman)
 *
 */
public class FTPUploadUtil {
	/**
	 * Upload structure of a directory (without uploading files) to a FTP
	 * server.
	 *
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param remoteDirPath
	 *            Path of the destination directory on the server.
	 * @param localParentDir
	 *            Path of the local directory being uploaded.
	 * @param remoteParentDir
	 *            Path of the parent directory of the current directory on the
	 *            server (used by recursive calls).
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static void uploadDirStructure(FTPClient ftpClient, String remoteDirPath, String localParentDir, String remoteParentDir) throws IOException {

		File localDir = new File(localParentDir);
		File[] subFiles = localDir.listFiles();
		if (subFiles != null && subFiles.length > 0) {
			for (File item : subFiles) {
				if (item.isDirectory()) {
					String remoteFilePath = remoteDirPath + "/"
							+ remoteParentDir + "/" + item.getName();
					if (remoteParentDir.equals("")) {
						remoteFilePath = remoteDirPath + "/" + item.getName();
					}

					// create directory on the server
					boolean created = ftpClient.makeDirectory(remoteFilePath);
					if (created) {
						System.out.println("CREATED the directory: "
								+ remoteFilePath);
					} else {
						System.out.println("COULD NOT create the directory: "
								+ remoteFilePath);
					}

					// upload the sub directory
					String parent = remoteParentDir + "/" + item.getName();
					if (remoteParentDir.equals("")) {
						parent = item.getName();
					}

					localParentDir = item.getAbsolutePath();
					uploadDirStructure(ftpClient, remoteDirPath, localParentDir,
							parent);
				}
			}
		}
	}
}