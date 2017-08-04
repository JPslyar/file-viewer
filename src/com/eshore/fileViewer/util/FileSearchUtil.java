package com.eshore.fileViewer.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.eshore.fileViewer.contacts.ArchivesCStatus;
import com.eshore.fileViewer.contacts.ArchivesFStatus;
import com.eshore.fileViewer.contacts.FileSuffix;
import com.eshore.fileViewer.data.FileInfo;
import com.eshore.fileViewer.data.SearchCondition;

/**
 * �ļ�������
 * @author JIANGPENG
 */
public class FileSearchUtil {
	private String sysPath;
	private String isFTP;
	private String rootPath;
	
	public FileSearchUtil(){
		isFTP = XmlUtil.getValue("isFTP");
		sysPath = XmlUtil.getValue("sysPath");
		rootPath = XmlUtil.getValue("rootPath");
	}
	/**
	 * �ļ����Һ���
	 * @param DefaultTableModel result ��������ģ�ͣ�SearchCondition search �ļ����Ҳ�����װ��
	 * */
	public  DefaultTableModel qryOffLineList(DefaultTableModel result,SearchCondition search){
		if(StringHelper.isNotNull(isFTP)&&"true".equals(isFTP)){
			return qryOffLineListByFTP(result, search);
		}else{
			return qryOffLineListByLocal(result, search);
		}
	}
	
	/**
	 * �����ļ�����
	 * */
	public  DefaultTableModel qryOffLineListByLocal(DefaultTableModel result,SearchCondition search){		
		File file;
		String linkPath="";
		String filePath="";
		List<FileInfo> fileList = new ArrayList<FileInfo>();		
		//���ƾ֤��
		if(ArchivesFStatus.KJPZL.equals(search.getArchivesF())){//
			linkPath=sysPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-3,search.getCertificateNum().length())+"/"+search.getCertificateNum();
			filePath=linkPath;
			file=new File(filePath);
			if (file.exists()) {  
				getFileByRecursionLocal(fileList, file);
			}
		}
		//�˲���
		if(ArchivesFStatus.ZBL.equals(search.getArchivesF())){
 			linkPath=sysPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getArchivesC();
			if(ArchivesCStatus.SAPSUBSIDIARYTITLE.getLabel().equals(search.getArchivesC())){
				String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
				for (String month : months) {
					String tempPath = linkPath+"/"+month+"/"+"��ϸ"+search.getCertificateNum()+FileSuffix.PDF;
					File tempFile=new File(tempPath);
					if  (tempFile.exists()) {
						getFileByRecursionLocal(fileList, tempFile);
					}
				}
			}else if(ArchivesCStatus.SAPGENERALTITLE.getLabel().equals(search.getArchivesC())){
				linkPath=linkPath+"/"+"��"+search.getCertificateNum()+FileSuffix.PDF;
				filePath=linkPath;
				file=new File(filePath);
				if  (file.exists()) {  
					getFileByRecursionLocal(fileList, file);
				}
			}else{
				linkPath=linkPath+"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-3,search.getCertificateNum().length())+"/"+search.getCertificateNum()+FileSuffix.PDF;
				filePath=linkPath;
				file=new File(filePath);
				if  (file.exists()) {  
					getFileByRecursionLocal(fileList, file);
				}
			}				
		}
		//���񱨸���
		if(ArchivesFStatus.CWBGL.equals(search.getArchivesF())){
			//��ʽ�����񱨸���/����/��������������
			linkPath=sysPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/";
			File tempFile =new File(linkPath); 
			//�ж��ļ�·����ʽ
			if  (!tempFile.exists()  || !tempFile.isDirectory()) {     
				linkPath=sysPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/";
			}
			filePath=linkPath;
			file=new File(filePath);
			if  (file.exists()) {  
				getFileByRecursionLocal(fileList, file);
			}
		}
		//������
		if(ArchivesFStatus.OTHER.equals(search.getArchivesF())){
			linkPath=sysPath+"/"+search.getYear()+"/"+search.getArchivesF();
			filePath=linkPath;
			file=new File(filePath);
			//�ж��ļ�·����ʽ
			if  (file.exists()) {     
				getFileByRecursionLocal(fileList, file);
			}
		}
		
		while(result.getRowCount()>0){
			result.removeRow(result.getRowCount()-1);
		} 
		for (int i=0;i<fileList.size();i++) {
			FileInfo info = fileList.get(i);
			Object[] row = new Object[7];
			row[0] = i+1+"";
			row[1] = search.getYear();
			row[2] = search.getProfitCenterCode()+search.getProfitCenterName();
			row[3] = search.getCertificateNum();
			row[4] = info.getFileName();
			row[5] = info.getFilePath();
			row[6] = "����";
			result.addRow(row);
		}
		return result;
	}
	/**
	 * FTP�ļ�����
	 * */
	public  DefaultTableModel qryOffLineListByFTP(DefaultTableModel result,SearchCondition search){		
		String linkPath="";
		String filePath="";
		List<FileInfo> fileList = new ArrayList<FileInfo>();
		//���ƾ֤��
		try {
			if(ArchivesFStatus.KJPZL.equals(search.getArchivesF())){//"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-4,search.getCertificateNum().length())
				linkPath = rootPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-3,search.getCertificateNum().length())+"/"+search.getCertificateNum();
				FTPClient ftpClient = new FtpUtils().getFtpClient();
				if(ftpClient.changeWorkingDirectory(linkPath)){
				   getFileByRecursionFTP(fileList,ftpClient,null);
				}
			}
			//�˲���
			if(ArchivesFStatus.ZBL.equals(search.getArchivesF())){
	 			linkPath = rootPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getArchivesC();
				if(ArchivesCStatus.SAPSUBSIDIARYTITLE.getLabel().equals(search.getArchivesC())){
					String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
					for (String month : months) {
						FTPClient ftpClient = new FtpUtils().getFtpClient();
						String tempPath = linkPath+"/"+month;
						String fileName = "��ϸ"+search.getCertificateNum()+FileSuffix.PDF;
						if(ftpClient.changeWorkingDirectory(tempPath)){
						   getFileByRecursionFTP(fileList,ftpClient,fileName);
						}
					}
				}else if(ArchivesCStatus.SAPGENERALTITLE.getLabel().equals(search.getArchivesC())){
					String fileName = "��"+search.getCertificateNum()+FileSuffix.PDF;
					filePath=linkPath;
					FTPClient ftpClient = new FtpUtils().getFtpClient();
					if(ftpClient.changeWorkingDirectory(filePath)){
					   getFileByRecursionFTP(fileList,ftpClient,fileName);
					}
				}else{
					String fileName= search.getCertificateNum()+FileSuffix.PDF;
					filePath=linkPath+"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-3,search.getCertificateNum().length());
					FTPClient ftpClient = new FtpUtils().getFtpClient();
					if(ftpClient.changeWorkingDirectory(filePath)){
					   getFileByRecursionFTP(fileList,ftpClient,fileName);
					}
				}				
			}
			//���񱨸���
			if(ArchivesFStatus.CWBGL.equals(search.getArchivesF())){
				//��ʽ�����񱨸���/����/��������������
				linkPath=rootPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/";
				FTPClient ftpClient = new FtpUtils().getFtpClient();
				//�ж��ļ�·����ʽ
				if  (ftpClient.changeWorkingDirectory(linkPath)) {     
					getFileByRecursionFTP(fileList,ftpClient,null);
				}else{
					linkPath=rootPath+"/"+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/";
					filePath=linkPath;
					if(ftpClient.changeWorkingDirectory(filePath)){
						getFileByRecursionFTP(fileList,ftpClient,null);
					}
				}
			}
			//������
			if(ArchivesFStatus.OTHER.equals(search.getArchivesF())){
				linkPath=rootPath+"/"+search.getYear()+"/"+search.getArchivesF();
				filePath=linkPath;
				FTPClient ftpClient = new FtpUtils().getFtpClient();
				if(ftpClient.changeWorkingDirectory(filePath)){
				   getFileByRecursionFTP(fileList,ftpClient,null);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(result.getRowCount()>0){
			result.removeRow(result.getRowCount()-1);
		} 
		for (int i=0;i<fileList.size();i++) {
			FileInfo info = fileList.get(i);
			Object[] row = new Object[7];
			row[0] = i+1+"";
			row[1] = search.getYear();
			row[2] = search.getProfitCenterCode()+search.getProfitCenterName();
			row[3] = search.getCertificateNum();
			row[4] = info.getFileName();
			row[5] = info.getFilePath();
			row[6] = "����";
			result.addRow(row);
		}
		return result;
	}
	
	
	public void getFileByRecursionLocal(List<FileInfo> fileList, File file){
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				getFileByRecursionLocal(fileList,file2);
			}
		}else{
			String absolutePath = FileUtils.fixPath(file.getAbsolutePath());
			String fileName = file.getName();
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFilePath(absolutePath);
			fileInfo.setFileName(fileName);
			fileList.add(fileInfo);
		}
	}
	
	
	public void getFileByRecursionFTP(List<FileInfo> fileList,FTPClient client,String fname){
		try {
			FTPFile[] listFiles = null;
			if( null!=fname ){
			    listFiles = client.listFiles(fname);
			}else{
				listFiles = client.listFiles();
			}
			for (FTPFile ftpFile : listFiles) {
				if (ftpFile.isDirectory()) {
					String dir = client.printWorkingDirectory()+"/"+ftpFile.getName();
					FTPClient ftpClient = new FtpUtils().getFtpClient();//���µĿͷ��˴�����ֹ·������
					if(ftpClient.changeWorkingDirectory(dir)){
					   getFileByRecursionFTP(fileList,ftpClient,null);
					}
				}else{
					String absolutePath = FileUtils.fixPath(client.printWorkingDirectory()+"/"+ftpFile.getName());
					String fileName = ftpFile.getName();
					FileInfo fileInfo = new FileInfo();
					fileInfo.setFilePath(absolutePath);
					fileInfo.setFileName(fileName);
					fileList.add(fileInfo);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
