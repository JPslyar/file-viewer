package com.eshore.fileViewer.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

import com.eshore.fileViewer.contacts.ConfigFileName;

/**
 * ftp
 * @author luoyongjun
 * @version 1.0
 * 
 */
public class FtpUtils {

    private static String userName;         
    private static String password;         
    private static String ip;               
    private static int port;                
    private FTPClient ftpClient = null;
    
    public FtpUtils() {
    }

    static{
    	ip = XmlUtil.getValue("url");
		port = Integer.parseInt(XmlUtil.getValue("port"));
		userName = XmlUtil.getValue("userName");
		password = XmlUtil.getValue("passWord");
    }
    
    
    public   boolean loadFile(String remoteFileName, String localFileName) { 
    	boolean flag = true; 
    	connectServer(); 
    	BufferedOutputStream buffOut = null; 
    	try { 
    		buffOut = new BufferedOutputStream(new FileOutputStream(localFileName)); 
    		flag = ftpClient.retrieveFile(remoteFileName, buffOut); 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	} finally { 
    		try { 
    			if (buffOut != null) 
    				buffOut.close(); 
    		} catch (Exception e) { 
    			e.printStackTrace(); 
    		} 
    	} 
    	return flag; 
    } 
    public   void listRemoteAllFiles() { 
    	connectServer(); 
    	try { 
    		String[] names = ftpClient.listNames(); 
    		for (int i = 0; i < names.length; i++) { 
    			System.out.println(names[i]); 
    		} 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    } 
    
    /** 
     * �ر����� 
     */ 
    public   void closeConnect() { 
    	try { 
    		if (ftpClient != null) { 
    			ftpClient.logout(); 
    			ftpClient.disconnect(); 
    		} 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    } 
    public   void setFileType(int fileType) { 
    	try { 
    		boolean isfalg = connectServer(); 
    		ftpClient.setFileType(fileType); 
    	} catch (Exception e) { 
    		e.printStackTrace(); 
    	} 
    } 
    
    protected   FTPClient getFtpClient() { 
    	connectServer(); 
    	return ftpClient; 
    } 
    
    public   boolean connectServer() { 
    	boolean flag = true; 
    	if (ftpClient == null) { 
    		int reply; 
    		try { 
    			//setArg(); 
    			ftpClient = new FTPClient(); 
    			ftpClient.setControlEncoding("GBK"); 
    			ftpClient.connect(ip,port); 
    			if(StringHelper.isNotNull(userName) && StringHelper.isNotNull(password)){
    			    ftpClient.login(userName, password); 
    			}
    			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
    			reply = ftpClient.getReplyCode(); 
    			ftpClient.setDataTimeout(5000); 
    			if (!FTPReply.isPositiveCompletion(reply)) { 
    				ftpClient.disconnect(); 
    				flag = false; 
    			} 
    		} catch (SocketException e) { 
    			flag = false; 
    			e.printStackTrace(); 
    			JOptionPane.showMessageDialog(null,"无法连接到ftp://"+ip+":"+port,"连接错误",JOptionPane.ERROR_MESSAGE);
    		} catch (IOException e) { 
    			flag = false; 
    			e.printStackTrace(); 
    			JOptionPane.showMessageDialog(null,"无法连接到ftp://"+ip+":"+port,"连接错误",JOptionPane.ERROR_MESSAGE);
    		} 
    	} 
    	return flag; 
    } 
    
    public   void changeWorkingDirectory(String directory) { 
    	try { 
    		connectServer(); 
    		ftpClient.changeWorkingDirectory(directory); 
    	} catch (IOException ioe) { 
    		ioe.printStackTrace(); 
    	} 
    } 
    public   void changeToParentDirectory() { 
    	try { 
    		connectServer(); 
    		ftpClient.changeToParentDirectory(); 
    	} catch (IOException ioe) { 
    		ioe.printStackTrace(); 
    	} 
    } 
    
    
    private   FTPClientConfig getFtpConfig() { 
    	FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX); 
    	ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING); 
    	return ftpConfig; 
    } 
    
    private   String iso8859togbk(Object obj) { 
    	try { 
    		if (obj == null) 
    			return ""; 
    		else 
    			return new String(obj.toString().getBytes("iso-8859-1"), "GBK"); 
    	} catch (Exception e) { 
    		return ""; 
    	} 
    } 
    

}
