package com.incture.utility.ftp.files;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
public class Ftp {


	//"/home/contintegration/Desktop/demo/"
	//"/home/contintegration/Desktop/Final_Demo/"
	public static void main(String[] args) throws SocketException, IOException {
		
		compareFTPFilesDirectory("/home/contintegration/Desktop/Test/","/home/contintegration/Desktop/Test2/");
		//uploadDirectory("D:/uploadFiles/", "/home/contintegration/Desktop/Upload/");
		//download_Directory( "D:/download/", "/home/contintegration/Desktop/Upload/");
	}


	/**
	 * compareFTPFilesDirectory --> is to compare files & folder in the src & destination directory
	 * @param dir1 --> source directory eg:-/home/contintegration/Desktop/demo/
	 * @param dir2 --> destination directory eg:-/home/contintegration/Desktop/Final_Demo/
	 * @throws SocketException
	 * @throws IOException
	 */
	public static void compareFTPFilesDirectory(String dir1,String dir2) throws SocketException, IOException{


		ArrayList<FTPFile> arrayDir1Files=new ArrayList<>();
		arrayDir1Files.addAll(getFTPFilesList(dir1));
		ArrayList<FTPFile> arrayDir2Files=new ArrayList<>();
		arrayDir2Files.addAll(getFTPFilesList(dir2));


		findUniqueFiles( dir1, dir2);

		for(FTPFile dir1File:arrayDir1Files)
		{
			for(FTPFile dir2File:arrayDir2Files)
			{
				if(dir1File.getName().equals(dir2File.getName()))
				{
					if(dir1File.isDirectory())
						compareFTPFilesDirectory(dir1+dir1File.getName()+"/", dir2+dir2File.getName()+"/");
					else if(dir1File.isFile())
						compareFTPFiles(dir1, dir2,dir1File.getName());
				}
			}
		}



		/*int desLength=0;
		if(dir1Files.length>dir2Files.length)
		{
			desLength=dir2Files.length;
			arrayDir1Files.removeAll(arrayDir2Files);
			for(FTPFile file:arrayDir1Files)
			{
				System.out.println(file.getName());
			}

		}else{
			desLength=dir1Files.length;
		}

		for(int i=0;i<desLength;i++){

			if(dir1Files[i].isFile())
			compareFTPFiles(dir1, dir2,dir1Files[i].getName() );
			else if(dir1Files[i].isDirectory())
				compareFTPFilesDire(dir1+dir1Files[i]+"/", dir2+dir1Files[i]+"/");
		}*/




	}

	public static void findUniqueFiles(	String dir1,String dir2) throws SocketException, IOException{


		ArrayList<FTPFile> arrayDir1Files=new ArrayList<>();
		arrayDir1Files.addAll(getFTPFilesList(dir1));
		ArrayList<FTPFile> arrayDir2Files=new ArrayList<>();
		arrayDir2Files.addAll(getFTPFilesList(dir2));


		ArrayList<String> dir1ListFileNames=new ArrayList<>();ArrayList<String> dir2ListFileNames=new ArrayList<>();

		for(FTPFile filedir1:arrayDir1Files){
			dir1ListFileNames.add(filedir1.getName());
		}
		for(FTPFile filedir2:arrayDir2Files){
			dir2ListFileNames.add(filedir2.getName());
		}






		/* dir1ListFileNames=new ArrayList<>();
		dir1ListFileNames.add("a");dir1ListFileNames.add("b");dir1ListFileNames.add("c");dir1ListFileNames.add("d");
		 dir2ListFileNames=new ArrayList<>();
		dir2ListFileNames.add("a");dir2ListFileNames.add("b");dir2ListFileNames.add("e");dir2ListFileNames.add("f");
		 */
		for(String fdir:dir1ListFileNames){
			if(dir2ListFileNames.contains(fdir))
			{
				System.out.println(fdir+"- is present both in dir");
				//System.out.println("Common file or folder in both directory		"+fdir+" - "+"directory1 is "+dir1+" & directory2 is "+dir2);
			}
		}
		for(String fdir1:dir1ListFileNames){
			if(!dir2ListFileNames.contains(fdir1))
			{
				System.out.println(fdir1+"- is present only in dir1");
				//System.out.println("Only in directory1	"+fdir1+"	"+"Path is "+dir1);
			}
		}
		for(String fdir2:dir2ListFileNames){

			if(!dir1ListFileNames.contains(fdir2))
			{
				System.out.println(fdir2+"- is present only in dir2");
				//System.out.println("Only in directory2	"+fdir2+"	"+"Path is "+dir2);
			}

		}

	}

	public static InputStream getFTPFile(String path) throws SocketException, IOException{


		FTPClient ftpCon1=Ftp.getFTPConnection();


		InputStream inSR=ftpCon1.retrieveFileStream(path);

		closeFTPConnection(ftpCon1);

		return inSR;

	}
	public static ArrayList<FTPFile> getFTPFilesList(String dir) throws SocketException, IOException{

		FTPClient connection=getFTPConnection();

		FTPFile[] dir1Files=connection.listFiles(dir);


		ArrayList<FTPFile> arrayDir1Files=new ArrayList<>();
		arrayDir1Files.addAll(Arrays.asList(dir1Files));

		closeFTPConnection(connection);

		return arrayDir1Files;
	}

	public static FTPClient getFTPConnection() throws SocketException, IOException{

		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.5.36", 21);
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println("Connection failed");
			return null;
		}

		boolean success = ftpClient.login("contintegration", "ConTIntegratioN@321");
		if (!success) {
			System.out.println("Could not login to the server");
			return null;
		}
		return ftpClient;	
	}

	public static void closeFTPConnection(FTPClient ftpClient) throws IOException{
		if(ftpClient.isConnected())
			ftpClient.disconnect();

	}

	/**
	 * compareFTPFiles --> this method is to compare the files only in current src & destination directorys
	 * @param compPath1 Eg:- /home/contintegration/Desktop/demo/
	 * @param compPath2
	 * @param fileName  Eg:- File_2.odt
	 * @throws IOException
	 */
	public static void compareFTPFiles(String compPath1,String compPath2,String fileName) throws IOException{

		FTPClient ftpCon1=Ftp.getFTPConnection();
		FTPClient ftpCon2=Ftp.getFTPConnection();


		InputStream inSR=ftpCon1.retrieveFileStream(compPath1+fileName);
		InputStream outSR=ftpCon2.retrieveFileStream(compPath2+fileName);
		InputStreamReader inISR=new InputStreamReader(inSR);
		InputStreamReader ourISR=new InputStreamReader(outSR);


		BufferedReader reader1 = new BufferedReader(inISR);

		BufferedReader reader2 = new BufferedReader(ourISR);

		String line1 = reader1.readLine();

		String line2 = reader2.readLine();

		boolean areEqual = true;

		int lineNum = 1;

		while (line1 != null || line2 != null)
		{
			if(line1 == null || line2 == null)
			{
				areEqual = false;

				break;
			}
			else if(! line1.equalsIgnoreCase(line2))
			{
				areEqual = false;

				break;
			}

			line1 = reader1.readLine();

			line2 = reader2.readLine();

			lineNum++;
		}

		if(areEqual)
		{
			System.out.println(fileName+"- has same content.");
			//System.out.println("Common file \""+fileName+"\" has same content.");
		}
		else
		{
			/*System.out.println("Two files have different content. They differ at line "+lineNum);*/

			System.out.println(fileName+"-  Mismatch data  @   "+line1+" and File2 has "+line2+" at line "+lineNum);
			//System.out.println("Common file \""+fileName+"\" has Mismatch data	at line "+lineNum+" <>directory1 has \""+line1+"\" and  <> directory2 has \" has "+line2);


		}

		reader1.close();

		reader2.close();

		Ftp.closeFTPConnection(ftpCon1);
		Ftp.closeFTPConnection(ftpCon2);
	}

	/**
	 * uploadFile --> is to upload file to remote ftp server
	 * @param localFilePath eg:- D:/uploadFiles/Testing.pdf
	 * @param remotePath eg:-/home/contintegration/Desktop/Final_Demo/
	 * @return
	 * @throws IOException
	 */
	public static boolean uploadFile(String localFilePath, String remotePath) throws IOException {
		FTPClient ftpClient=Ftp.getFTPConnection();

		File localFile = new File(localFilePath);
		boolean flag=false;
		InputStream inputStream = new FileInputStream(localFile);
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			flag= ftpClient.storeFile(remotePath+localFile.getName(), inputStream);
		}catch(Exception e){

			System.out.println(e.getMessage());

		}
		finally {
			inputStream.close();
		}
		Ftp.closeFTPConnection(ftpClient);
		return flag;
	}

	/**
	 * uploadDirectory --> is to upload from local drive to remote FTP server 
	 * @param localDir  -->D:/uploadFiles/
	 * @param remoteDir -->/home/contintegration/Desktop/Final_Demo/
	 * @throws IOException
	 */
	public static void uploadDirectory( String localDir, String remoteDir)throws IOException {

		FTPClient ftpClient=Ftp.getFTPConnection();
		ftpClient.makeDirectory(remoteDir);

		File localDirFiles = new File(localDir);
		File[] files = localDirFiles.listFiles();

		if (files != null && files.length > 0) {
			for (File file : files) {
				if (file.isFile()) {
					// upload the file
					String localFilePath = file.getAbsolutePath();
					System.out.println("About to upload the file: " + localFilePath);
					boolean uploaded = uploadFile(localFilePath, remoteDir);
					if (uploaded) {
						System.out.println("UPLOADED a file to: " + remoteDir);
					} else {
						System.out.println("COULD NOT upload the file: " + localFilePath);
					}
				} else if(file.isDirectory()) {

					String remoteFileDir=remoteDir+file.getName()+"/";
					boolean created = ftpClient.makeDirectory(remoteFileDir);
					if (created) {
						System.out.println("CREATED the directory: " + remoteFileDir);
					} else {
						System.out.println("COULD NOT create the directory: " + remoteFileDir);
					}
					String  localFileDir = file.getAbsolutePath()+"\\";
					uploadDirectory(localFileDir, remoteFileDir);
				}
			}
		}
	}
	/**
	 * "/home/contintegration/Desktop/Final_Demo/PayloadXL3.xml", "D:/uploadFiles/"
	 * @param remoteFilePath /home/contintegration/Desktop/Final_Demo/PayloadXL3.xml
	 * @param localPath  D:/uploadFiles/
	 * @throws SocketException 
	 * @throws IOException
	 */
	public static void downloadFile(String remoteFilePath, String localPath) throws SocketException, IOException {

		FTPClient ftpclient=  Ftp.getFTPConnection();

		String fileName=remoteFilePath.split("/")[remoteFilePath.split("/").length-1];

		FileOutputStream fos = new FileOutputStream(localPath+fileName);
		ftpclient.retrieveFile(remoteFilePath, fos);
		fos.flush();
		fos.close();
		System.out.println("File downloaded");
		Ftp.closeFTPConnection(ftpclient);

	}


	/**
	 * Downloading Complete Folder from FTP server to Local system
	 * @param ftpClient an instance of  org.apache.commons.net.ftp.FTPClient
	 * @param localDir is path of the Local system Directory where  Folder will be stored
	 * @param remoteDir is path of Server directory 
	 * @throws IOException if any network or IO error occurred
	 */

	public static void download_Directory( String localDir, String remoteDir)throws IOException {
		/* File localDirFiles = new File(localDir);
	    File[] files = localDirFiles.listFiles();
	    if (files != null && files.length > 0) {
	        for (File file : files) {*/
		new File(String.valueOf(localDir)).mkdir();
		
		FTPClient ftpClient=Ftp.getFTPConnection();
		FTPFile[] subFiles = ftpClient.listFiles(remoteDir);

		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();


				if (aFile.isFile()) {
					// download the file
					//  String remoteFilePath = aFile.
					System.out.println("About to download the file: " + remoteDir);
					boolean downloaded = downloadsingleFile(remoteDir+aFile.getName(), localDir);
					if (downloaded) {
						System.out.println("DOWNLOAD a file to: " + localDir);
					} else {
						System.out.println("COULD NOT Download  the file: " + remoteDir);
					}
				} else if(aFile.isDirectory()) {

					// create directory on the Local
					String localFileDir=localDir+aFile.getName()+"/";
					String RemoteFileDir=remoteDir+aFile.getName()+"/";
					File directory = new File(String.valueOf(localFileDir));
					if (!directory.exists()) {
						directory.mkdir();
					}


					//String  remoteFileDir = aFile.getAbsolutePath();
					download_Directory(localFileDir, RemoteFileDir);
				}
			}
		}
	}
	/**
	 * Downloading single file from remote directory to local directory
	 * @param remoteFilePath is the path of file on server
	 * @param localPath is the path of File in local system
	 * @return true if file is downloaded successfully, otherwise false
	 * @throws IOException if any network or IO error occurred
	 */

	public static boolean downloadsingleFile(String remoteFilePath, String localPath) throws IOException {

		FTPClient ftpclient=  Ftp.getFTPConnection();

		String fileName=remoteFilePath.split("/")[remoteFilePath.split("/").length-1];

		FileOutputStream fos = new FileOutputStream(localPath+fileName);
		ftpclient.retrieveFile(remoteFilePath, fos);
		fos.flush();
		fos.close();
		Ftp.closeFTPConnection(ftpclient);
		return true;

	}


}
