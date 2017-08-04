package com.eshore.fileViewer.data;

import java.util.Vector;

public class ArchivesF{
  private String type;
  private Vector<String> archivesCVector;

public ArchivesF() {
	super();
}

public Vector<String> getArchivesCVector() {
	return archivesCVector;
}


public void setArchivesCVector(Vector<String> archivesCVector) {
	this.archivesCVector = archivesCVector;
}


public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

	public String toString() {
		return type;
	}
  
}
