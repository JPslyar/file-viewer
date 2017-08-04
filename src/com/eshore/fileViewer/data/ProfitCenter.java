package com.eshore.fileViewer.data;

public class ProfitCenter {
  private String code;
  private String name;
public ProfitCenter() {
	super();
}
public ProfitCenter(String code, String name) {
	super();
	this.code = code;
	this.name = name;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
 
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
