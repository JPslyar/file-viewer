package com.eshore.fileViewer.data;

public class FileInfo {
  private String year;//���
  private String profitCenter;//��������
  private String certificateNum;//ƾ֤��
  private String fileName;//�ļ���
  private String filePath;//�ļ�·��
public FileInfo() {
	super();
}
public FileInfo(String year, String profitCenter, String certificateNum,
		String fileName, String filePath) {
	super();
	this.year = year;
	this.profitCenter = profitCenter;
	this.certificateNum = certificateNum;
	this.fileName = fileName;
	this.filePath = filePath;
}
public String getYear() {
	return year;
}
public void setYear(String year) {
	this.year = year;
}
public String getProfitCenter() {
	return profitCenter;
}
public void setProfitCenter(String profitCenter) {
	this.profitCenter = profitCenter;
}
public String getCertificateNum() {
	return certificateNum;
}
public void setCertificateNum(String certificateNum) {
	this.certificateNum = certificateNum;
}
public String getFileName() {
	return fileName;
}
public void setFileName(String fileName) {
	this.fileName = fileName;
}
public String getFilePath() {
	return filePath;
}
public void setFilePath(String filePath) {
	this.filePath = filePath;
}
  
  
}
