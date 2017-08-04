package com.eshore.fileViewer.view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * ∆Ù∂Ø¿‡
 * @author JIANGPENG
 * */
public class Main {
	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMistAquaLookAndFeel");
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	    LoginViewer loginViewer= new LoginViewer();
	    loginViewer.setVisible(true);
	    loginViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
