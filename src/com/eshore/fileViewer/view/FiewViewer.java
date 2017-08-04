package com.eshore.fileViewer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.eshore.fileViewer.contacts.ArchivesCStatus;
import com.eshore.fileViewer.contacts.ArchivesFStatus;
import com.eshore.fileViewer.data.ArchivesF;
import com.eshore.fileViewer.data.Company;
import com.eshore.fileViewer.data.ProfitCenter;
import com.eshore.fileViewer.data.SearchCondition;
import com.eshore.fileViewer.util.CenterWindow;
import com.eshore.fileViewer.util.FileSearchUtil;
import com.eshore.fileViewer.util.GBC;
import com.eshore.fileViewer.util.StringHelper;
import com.eshore.fileViewer.util.XmlUtil;
/**
 * 主界面
 * @author JIANGPENG
 * */
public class FiewViewer extends JFrame implements ActionListener,ItemListener{
	private JLabel titleLbl,yearLbl,companyLbl,profitCenterLbl,archivesTypeLbl,certificateNumLbl,errorLbl;
	private JTextField certificateNumTxt;
	private JComboBox yearsCombox,companyCombox,profitCenterCombox,archivesFCombox,archivesCCombox;
    private JButton searchBtn;
    private JPanel searchPal,titlePal,resultPal;
    private JTable resultTable;
    private DefaultTableModel resultModel;
    private Vector<ArchivesF> archivesFVector;
    private Vector<Company> companyVector;
    public JSplitPane  mainSplit;
    
    
    /**
     * 初始主窗口
     * */
    public FiewViewer(String name){
     super(name);
     this.setSize(1200,700);
   	 this.setLayout(new BorderLayout());
   	 this.add(buildMainSplit());
     CenterWindow.centerWindow(this);
   	 this.setVisible(true);
   	 this.setResizable(false);
   	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
    
    public JButton buildBtn(String name) {
        JButton btn = new JButton(name);
        return btn;
    }
    /**
     * 初始化标题Panel
     * */
    public JPanel buildTitlePanel(){
    	if (titlePal == null) {
    		titlePal = new JPanel();
    		Box boxtitle=Box.createVerticalBox();
    		titleLbl =new JLabel("文件查询系统");
    		titleLbl.setFont(new Font("",Font.BOLD,25));
    		boxtitle.add(titleLbl);
    		titlePal.add(boxtitle);
    	}
    	return titlePal;
    }
    
    /**
     * 初始化查询面板组件
     * */
    public void initSearchWidget(){
    	yearLbl = new JLabel("年度:",JLabel.CENTER);
    	companyLbl = new JLabel("公司:",JLabel.CENTER);
    	profitCenterLbl = new JLabel("利润中心:",JLabel.CENTER);
    	archivesTypeLbl = new JLabel("档案类型:",JLabel.CENTER);
    	certificateNumLbl = new JLabel("凭证号:",JLabel.CENTER);
    	errorLbl = new JLabel("",JLabel.CENTER);
    	errorLbl.setForeground(Color.RED);
    	
    	certificateNumTxt = new JTextField(20);
    	
    	Vector<String> yearVector = XmlUtil.getStrList(XmlUtil.YEARS_PATH);
    	companyVector = XmlUtil.getCompanys();
    	archivesFVector = XmlUtil.getArchivesType();
    	yearsCombox = new JComboBox(yearVector);
    	companyCombox = new JComboBox(companyVector);
    	profitCenterCombox = new JComboBox(companyVector.get(0).getProfitCenterVector());
    	archivesFCombox = new JComboBox(archivesFVector);
    	archivesCCombox = new JComboBox(archivesFVector.get(0).getArchivesCVector());
    	
    	searchBtn=buildBtn("查找");
    	
    	searchBtn.addActionListener(this);
    	archivesFCombox.addItemListener(this);
    	companyCombox.addItemListener(this);
    	archivesCCombox.addItemListener(this);
    } 
    /**
     * 布局查询面板
     * */
    public JPanel buildSearchPanel() {
        if (searchPal == null) {
        	searchPal = new JPanel();
        	initSearchWidget();
        	searchPal.setLayout(new GridBagLayout());
        	searchPal.add(yearLbl, new GBC(0,0).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(yearsCombox, new GBC(1,0).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(archivesTypeLbl, new GBC(0,1).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(archivesFCombox, new GBC(1,1).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(new JLabel("—>",JLabel.CENTER), new GBC(2,1).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(archivesCCombox, new GBC(3,1).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(companyLbl, new GBC(0,2).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(companyCombox, new GBC(1,2).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(profitCenterLbl, new GBC(2,2).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(profitCenterCombox, new GBC(3,2).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(certificateNumLbl, new GBC(0,3).setFill(GBC.HORIZONTAL).setInset(7));
        	searchPal.add(certificateNumTxt, new GBC(1,3).setFill(GBC.HORIZONTAL).setInset(7));
        	
        	searchPal.add(errorLbl, new GBC(3,3).setFill(GBC.HORIZONTAL).setInset(7).setWeight(2, 1));
        	searchPal.add(searchBtn, new GBC(3,4).setFill(GBC.HORIZONTAL).setInset(7).setWeight(2, 1));
        	
        	searchPal.setBorder(BorderFactory.createTitledBorder("查询条件"));
        }
        return searchPal;
    }
    
    /**
     * 初始化上部分面板
     * */
    public JPanel buildPanel() {
        if (resultPal == null) {
        	resultPal = new JPanel();
        	resultPal.setLayout(new GridBagLayout());
        	resultPal.add(this.buildTitlePanel(), new GBC(0,0).setFill(GBC.HORIZONTAL).setInset(7));
        	resultPal.add(this.buildSearchPanel(), new GBC(0,1).setFill(GBC.HORIZONTAL).setInset(7));	
        }
        return resultPal;
    }
    
    /**
     * 初始化结果表
     * */
	public JTable buildTable(){
		if(resultTable==null){
			resultTable = new JTable();
			resultModel=new DefaultTableModel(){

			public boolean isCellEditable(int row, int column){ 
			    if (column == 6){ 
			        return true; 
			    }else{ 
			        return true; 
			    } 
			  }  
		    };
			resultModel.addColumn("序号");
			resultModel.addColumn("年度");
			resultModel.addColumn("利润中心");
			resultModel.addColumn("凭证号/科目/卡片编号");
			resultModel.addColumn("文件名称");
			resultModel.addColumn("文件路径");
			resultModel.addColumn("操作");
			resultTable.setModel(resultModel);	
			resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			resultTable.getColumnModel().getColumn(0).setPreferredWidth(45);
			resultTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			resultTable.getColumnModel().getColumn(2).setPreferredWidth(200);
			resultTable.getColumnModel().getColumn(3).setPreferredWidth(120);
			resultTable.getColumnModel().getColumn(4).setPreferredWidth(200);
			resultTable.getColumnModel().getColumn(5).setPreferredWidth(500);
			resultTable.getColumnModel().getColumn(6).setPreferredWidth(75);
			resultTable.setRowHeight(30);
			resultTable.getColumnModel().getColumn(6).setCellRenderer(new ButtonRender());//静态时
			resultTable.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(resultTable));//编辑时
		}
	      return resultTable;		
	}
   
    /**
     * 初始化水平分界线组件
     * */
    private JSplitPane buildMainSplit(){
      	if(mainSplit == null){
      		mainSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
      		mainSplit.setOneTouchExpandable(true);
      		mainSplit.setTopComponent(buildPanel());
      		mainSplit.setBottomComponent(new JScrollPane(buildTable()));
      	}
      	return mainSplit;
      }
	/**
	 * 点击查找事件
	 * */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==searchBtn){	
			String certificateNum = "";
			if(certificateNumTxt.isEditable()){
			   certificateNum = certificateNumTxt.getText();
			   if(!StringHelper.isNotNull(certificateNum)||certificateNum.length()<=3){
				   errorLbl.setText(certificateNumLbl.getText()+"长度必须大于3位");
				   return;
			   }
			}
			errorLbl.setText("");
			SearchCondition search = new SearchCondition();
			String year = yearsCombox.getSelectedItem().toString();
			String companyCode = ((Company)companyCombox.getSelectedItem()).getCode();
			String companyName = ((Company)companyCombox.getSelectedItem()).getName();
			String profitCenterCode = ((ProfitCenter)profitCenterCombox.getSelectedItem()).getCode();
			String profitCenterName = ((ProfitCenter)profitCenterCombox.getSelectedItem()).getName();
			String archivesF = ((ArchivesF)archivesFCombox.getSelectedItem()).getType();
			String archivesC = archivesCCombox.getSelectedItem()!=null?archivesCCombox.getSelectedItem().toString():"";
			search.setArchivesC(archivesC);
			search.setArchivesF(archivesF);
			search.setCertificateNum(certificateNum);
			search.setCompanyCode(companyCode);
			search.setCompanyName(companyName);
			search.setProfitCenterCode(profitCenterCode);
			search.setProfitCenterName(profitCenterName);
			search.setYear(year);
			FileSearchUtil searchUtil = new FileSearchUtil();
			searchUtil.qryOffLineList(resultModel, search);
		}
	}
	
    /**
     * combox变动事件
     * */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.SELECTED) {
            if(e.getSource() == archivesFCombox) {
                int index = archivesFCombox.getSelectedIndex();
                archivesCCombox.setModel(new DefaultComboBoxModel(archivesFVector.get(index).getArchivesCVector()));
                String archivesF = archivesFCombox.getSelectedItem().toString();
                if(ArchivesFStatus.KJPZL.equals(archivesF)){
                	certificateNumLbl.setText("凭证号:");
                	certificateNumTxt.setEditable(true);
                	archivesCCombox.setEnabled(false);
                }else if(ArchivesFStatus.ZBL.equals(archivesF)){
                	certificateNumLbl.setText("科目:");
                	certificateNumTxt.setEditable(true);
                	archivesCCombox.setEnabled(true);
                }else{
                	certificateNumLbl.setText("");
                	certificateNumTxt.setText("");
                	certificateNumTxt.setEditable(false);
                	archivesCCombox.setEnabled(false);
                }
            }
            if(e.getSource() == archivesCCombox) {
            	String archivesC = archivesCCombox.getSelectedItem().toString();
            	if(ArchivesFStatus.ZBL.equals(archivesFCombox.getSelectedItem().toString())){
            	   if(ArchivesCStatus.SAPASSETSCARDTITLE.getLabel().equals(archivesC))
                	   certificateNumLbl.setText("卡片编号:");
                   else
                	   certificateNumLbl.setText("科目:");
                }
            }
            if(e.getSource() == companyCombox) {
                int index = companyCombox.getSelectedIndex();
                profitCenterCombox.setModel(new DefaultComboBoxModel(companyVector.get(index).getProfitCenterVector()));
            }
        }  
	}
}
