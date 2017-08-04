package com.eshore.fileViewer.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 * œ¬‘ÿ∞¥≈•‰÷»æ¿‡
 * @author JIANGPENG
 * */
public class ButtonRender extends DefaultTableCellRenderer 
{
    private JButton button;

    public ButtonRender()
    {
        this.initButton();
        this.setHorizontalAlignment(JLabel.CENTER);
    }

    private void initButton()
    {
    	this.setLayout(null);
        this.button = new JButton();
         this.button.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent e)
           {
           }
         });
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,int column)
    {
        this.button.setText(value == null ? "" : String.valueOf(value));
        return this.button;
    }

}