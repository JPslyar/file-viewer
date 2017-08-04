package com.eshore.fileViewer.data;

import java.util.Vector;

public class Company {
  private String code;
  private String name ;
  private Vector<ProfitCenter> profitCenterVector;

public Company() {
	super();
}

public Company(String name) {
	super();
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

public Vector<ProfitCenter> getProfitCenterVector() {
	return profitCenterVector;
}

public void setProfitCenterVector(Vector<ProfitCenter> profitCenterVector) {
	this.profitCenterVector = profitCenterVector;
}

	public String toString() {
		return name;
	}

}
