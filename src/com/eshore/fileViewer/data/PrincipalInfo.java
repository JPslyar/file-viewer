package com.eshore.fileViewer.data;

public class PrincipalInfo {
  private String name;
  private String password;
public PrincipalInfo() {
	super();
}
public PrincipalInfo(String name, String password) {
	super();
	this.name = name;
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
  
}
