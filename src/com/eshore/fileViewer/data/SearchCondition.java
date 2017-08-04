package com.eshore.fileViewer.data;

public class SearchCondition {
	private String year;//年度
	private String companyCode;//公司code
	private String companyName;//公司名
	private String profitCenterCode;//利润中心code
	private String profitCenterName;//利润中心名
	private String archivesF;//父档案类型
	private String archivesC;//子档案类型
	private String certificateNum;//凭证号
	
	public SearchCondition() {
		super();
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getProfitCenterCode() {
		return profitCenterCode;
	}
	public void setProfitCenterCode(String profitCenterCode) {
		this.profitCenterCode = profitCenterCode;
	}
	public String getProfitCenterName() {
		return profitCenterName;
	}
	public void setProfitCenterName(String profitCenterName) {
		this.profitCenterName = profitCenterName;
	}
	public String getArchivesF() {
		return archivesF;
	}
	public void setArchivesF(String archivesF) {
		this.archivesF = archivesF;
	}
	public String getArchivesC() {
		return archivesC;
	}
	public void setArchivesC(String archivesC) {
		this.archivesC = archivesC;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	
}
