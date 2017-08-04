package com.eshore.fileViewer.util;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
/**
 * Swing ´°¿Ú¾ÓÖÐº¯Êý
 * @author JIANGPENG
 * */
public class CenterWindow {
	  public static void centerWindow(Window w){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension windowSize = w.getSize();
		w.setBounds((int)(screenSize.getWidth() - windowSize.getWidth())/2,
				(int) (screenSize.getHeight() - windowSize.getHeight())/2,
				(int)windowSize.getWidth(),(int)windowSize.getHeight());
	  }
	}