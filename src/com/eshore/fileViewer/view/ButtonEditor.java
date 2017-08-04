package com.eshore.fileViewer.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.eshore.fileViewer.util.FileUtils;
import com.eshore.fileViewer.util.FtpUtils;
import com.eshore.fileViewer.util.StringHelper;
import com.eshore.fileViewer.util.XmlUtil;
/**
 * 点击下载按钮时状态及事件
 * @author JIANGPENG
 * */
public class ButtonEditor extends DefaultCellEditor 
{ 
    private JButton button; 
    
    private String isFTP = XmlUtil.getValue("isFTP");;
 
    public ButtonEditor(JTable table) 
    { 
        // DefautlCellEditor有此构造器，需要传入一个，但这个不会使用到，直接new一个即可。  
        super(new JTextField()); 
        // 设置点击几次激活编辑。  
        this.setClickCountToStart(1); 
        this.initButton(table); 
    } 
 
    private void initButton(final JTable table) 
    { 
        this.button = new JButton(); 
        // 为按钮添加事件。这里只能添加ActionListner事件，Mouse事件无效。  
        this.button.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
            	String oldName = table.getValueAt(table.getSelectedRow(), 4).toString();
                String oldPath = table.getValueAt(table.getSelectedRow(), 5).toString();
                JFileChooser c = new JFileChooser();
                c.setSelectedFile(new File(oldName));
                int flag = c.showSaveDialog(new JFrame());
                if(flag == JFileChooser.APPROVE_OPTION){//点击保存时保存文件
	                String newPath = c.getSelectedFile().getAbsolutePath();
	                if(StringHelper.isNotNull(isFTP)&&"true".equals(isFTP)){//FTP下载
	                	FtpUtils ftpUtil = new FtpUtils();
	                	ftpUtil.loadFile(oldPath, newPath);
	                }else{//本地复制
	                	try {
	    					FileUtils.copyFile(oldPath, newPath);
	    				} catch (FileNotFoundException e1) {
	    					e1.printStackTrace();
	    				}
	                }
                }
            } 
        }); 
 
    } 
 
    @Override 
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    { 
        // 只为按钮赋值即可。也可以作其它操作。  
        this.button.setText(value == null ? "" : String.valueOf(value)); 
 
        return this.button; 
    } 
    @Override 
    public Object getCellEditorValue() 
    { 
        return this.button.getText(); 
    }  
} 

