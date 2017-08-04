package com.eshore.fileViewer.data;

public class FileInfo {
  private String year;//年度
  private String profitCenter;//利润中心
  private String certificateNum;//凭证号
  private String fileName;//文件名
  private String filePath;//文件路劲
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
