package com.eshore.fileViewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.dom4j.DocumentException;

import com.eshore.fileViewer.contacts.ConfigFileName;
import com.eshore.fileViewer.data.PrincipalInfo;
import com.eshore.fileViewer.util.CenterWindow;
import com.eshore.fileViewer.util.GBC;
import com.eshore.fileViewer.util.XmlUtil;
/**
 * 登录窗口
 * @author JIANGPENG
 * */
public class LoginViewer extends JFrame implements ActionListener{

	 private JLabel userNameLbl,pwdLbl,errorLbl,configLbl;
     private JTextField userNameTxt,configTxt;
	 private JPasswordField pwdTxt;
	 public JButton  btok,btno;
	 public LoginViewer(){
	    	this.setSize(400,300);
	    	this.add(buildTitlePanel(),BorderLayout.NORTH);
	    	this.add(buildUserPanel(),BorderLayout.CENTER);
	    	this.add(buildBtnPanel(),BorderLayout.SOUTH);
	    	CenterWindow.centerWindow(this);
	    	this.setResizable(false);	    	
	    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	    }
	 public JPanel buildTitlePanel(){
	    	JPanel panel = new JPanel();
	    	JLabel lbl = new JLabel("文件查看系统");
	    	lbl.setFont(new Font("",Font.BOLD,15));
	    	lbl.setForeground(Color.RED);
	    	panel.add(lbl);
	    	return panel;
	    }
	 public JPanel buildBtnPanel(){
	    	JPanel panel = new JPanel(); 
	    	panel.setLayout(new FlowLayout());
	    	btok=buildActionBtn("登录");
	    	btno=buildActionBtn("退出");
	    	btok.addActionListener(this);
	    	btno.addActionListener(this); 
	    	panel.add(btok);
	    	panel.add(btno);    	
	    	return panel;
	    }
	 public JPanel buildUserPanel(){
	    	JPanel panel = new JPanel();
	        initialUserLab();
	        panel.setLayout(new GridBagLayout());
	        
	        panel.add(userNameLbl, new GBC(0,0).setFill(GBC.HORIZONTAL).setInset(7,40,7,0));
	        panel.add(this.buildUserNameTxt(), new GBC(1,0).setFill(GBC.HORIZONTAL).setInset(7,40,7,40).setWeight(2, 0));

	        panel.add(pwdLbl, new GBC(0,1).setFill(GBC.HORIZONTAL).setInset(7,40,7,0));
	        panel.add(this.buildUserPwdTxt(), new GBC(1,1).setFill(GBC.HORIZONTAL).setInset(7,40,7,40).setWeight(2, 0));
	        
	        panel.add(errorLbl, new GBC(1,2).setFill(GBC.HORIZONTAL).setInset(7,40,7,40));
	        
	        panel.add(configLbl, new GBC(0,3).setFill(GBC.HORIZONTAL).setInset(7,40,7,0));
	        panel.add(this.buildConfigTxt(), new GBC(1,3).setFill(GBC.HORIZONTAL).setInset(7,40,7,40));
	        
	        panel.setBorder(BorderFactory.createTitledBorder("登录信息"));
	    	return panel;
	    }
	  
	 public void initialUserLab(){
	    	userNameLbl = new JLabel("用户名:");
	    	pwdLbl = new JLabel("密 码:");
	    	errorLbl = new JLabel(" ");
	    	configLbl = new JLabel("配置文件路径");
	    	errorLbl.setForeground(Color.RED);
	    }
	    
	 public JTextField buildUserNameTxt(){
	    	if(userNameTxt == null){
	    		userNameTxt = new JTextField();
	    	}
	    	return userNameTxt;
	    }
	    
	 public JPasswordField buildUserPwdTxt(){
	    	if(pwdTxt == null){
	    		pwdTxt = new JPasswordField();
	    	}
	    	return pwdTxt;
	    }
	 
	 public JTextField buildConfigTxt(){
	    	if(configTxt == null){
	    		configTxt = new JTextField();
	    	}
	    	return configTxt;
	  }
	   
    public JButton buildActionBtn(String name){
		   JButton btn = new JButton(name);
		   return btn;
	   }
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btok){
			String configDir = configTxt.getText();
			File configFileDir = new File(configDir);
			if(configFileDir.exists()&&configFileDir.isDirectory()){
				File[] listFiles = configFileDir.listFiles();
				Map<String,File> fileMap = new HashMap<String, File>();
				for (File file : listFiles) {
					String name = file.getName();
					fileMap.put(name, file);
				}
				//判断配置文件是否正常
				if(fileMap.get(ConfigFileName.ARCHIVESTYPES)==null){
					JOptionPane.showMessageDialog(null,"缺少配置文件："+ConfigFileName.ARCHIVESTYPES,"提示",JOptionPane.ERROR_MESSAGE);return;	
				}else if(fileMap.get(ConfigFileName.COMPANYS)==null){
					JOptionPane.showMessageDialog(null,"缺少配置文件："+ConfigFileName.COMPANYS,"提示",JOptionPane.ERROR_MESSAGE);return;	
				}else if(fileMap.get(ConfigFileName.PRINCIPALINFOS)==null){
					JOptionPane.showMessageDialog(null,"缺少配置文件："+ConfigFileName.PRINCIPALINFOS,"提示",JOptionPane.ERROR_MESSAGE);return;	
				}else if(fileMap.get(ConfigFileName.PROPERTIES)==null){
					JOptionPane.showMessageDialog(null,"缺少配置文件："+ConfigFileName.PROPERTIES,"提示",JOptionPane.ERROR_MESSAGE);return;
				}else if(fileMap.get(ConfigFileName.YEARS)==null){
					JOptionPane.showMessageDialog(null,"缺少配置文件："+ConfigFileName.YEARS,"提示",JOptionPane.ERROR_MESSAGE);return;
				}else{
					XmlUtil.ARCHIVESTYPES_PATH = fileMap.get(ConfigFileName.ARCHIVESTYPES).getAbsolutePath();
					XmlUtil.COMPANYS_PATH = fileMap.get(ConfigFileName.COMPANYS).getAbsolutePath();
					XmlUtil.PROPERTIES_PATH = fileMap.get(ConfigFileName.PROPERTIES).getAbsolutePath();
					XmlUtil.YEARS_PATH = fileMap.get(ConfigFileName.YEARS).getAbsolutePath();
					XmlUtil.PRINCIPALINFOS_PATH = fileMap.get(ConfigFileName.PRINCIPALINFOS).getAbsolutePath();
				}
			}else{
				//使用项目下的默认配置文件
			}
			//登陆				
			String name = userNameTxt.getText();
			String pwd = pwdTxt.getText();
			try {
				boolean isLogin = loginAction(name, pwd);
				if(isLogin){
					errorLbl.setText("");
					new FiewViewer("文件查看系统");
					this.dispose();
				}else{
					errorLbl.setText("用户名或密码错误");
				}
			} catch (DocumentException e1) {
				e1.printStackTrace();
				errorLbl.setText("登录配置文件解析错误");
			}
	    }
        if(e.getSource()==btno){
		    System.exit(0);
	    }
	}
	
	
	public boolean loginAction(String name,String pwd) throws DocumentException{
	  Map<String,PrincipalInfo> mapInfo = XmlUtil.getPrincipalInfos();
	  PrincipalInfo principalInfo = mapInfo.get(name);
	  if(principalInfo == null){
		  return false;
	  }else{
		 if(pwd.equals(principalInfo.getPassword())){
			 return true;
		 } else{
			 return false;
		 }
	  }
	}
}
