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
 * ������ذ�ťʱ״̬���¼�
 * @author JIANGPENG
 * */
public class ButtonEditor extends DefaultCellEditor 
{ 
    private JButton button; 
    
    private String isFTP = XmlUtil.getValue("isFTP");;
 
    public ButtonEditor(JTable table) 
    { 
        // DefautlCellEditor�д˹���������Ҫ����һ�������������ʹ�õ���ֱ��newһ�����ɡ�  
        super(new JTextField()); 
        // ���õ�����μ���༭��  
        this.setClickCountToStart(1); 
        this.initButton(table); 
    } 
 
    private void initButton(final JTable table) 
    { 
        this.button = new JButton(); 
        // Ϊ��ť����¼�������ֻ�����ActionListner�¼���Mouse�¼���Ч��  
        this.button.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
            	String oldName = table.getValueAt(table.getSelectedRow(), 4).toString();
                String oldPath = table.getValueAt(table.getSelectedRow(), 5).toString();
                JFileChooser c = new JFileChooser();
                c.setSelectedFile(new File(oldName));
                int flag = c.showSaveDialog(new JFrame());
                if(flag == JFileChooser.APPROVE_OPTION){//�������ʱ�����ļ�
	                String newPath = c.getSelectedFile().getAbsolutePath();
	                if(StringHelper.isNotNull(isFTP)&&"true".equals(isFTP)){//FTP����
	                	FtpUtils ftpUtil = new FtpUtils();
	                	ftpUtil.loadFile(oldPath, newPath);
	                }else{//���ظ���
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
        // ֻΪ��ť��ֵ���ɡ�Ҳ����������������  
        this.button.setText(value == null ? "" : String.valueOf(value)); 
 
        return this.button; 
    } 
    @Override 
    public Object getCellEditorValue() 
    { 
        return this.button.getText(); 
    }  
} 

