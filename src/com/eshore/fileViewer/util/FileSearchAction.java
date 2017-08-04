package com.eshore.fileViewer.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.eshore.fileViewer.contacts.ArchivesCStatus;
import com.eshore.fileViewer.contacts.ArchivesFStatus;
import com.eshore.fileViewer.contacts.FileSuffix;
import com.eshore.fileViewer.data.FileInfo;
import com.eshore.fileViewer.data.SearchCondition;

/**
 * ���������������
 */
public class FileSearchAction {
	private static String sysPath = XmlUtil.getValue("sysPath");
	
	public static DefaultTableModel qryOffLineList(DefaultTableModel result,SearchCondition search){		
		File file;
		String linkPath="";
		String filePath="";
		List<FileInfo> fileList = new ArrayList<FileInfo>();		
		//���ƾ֤��
		if(ArchivesFStatus.KJPZL.equals(search.getArchivesF())){//"/"+search.getCertificateNum().substring(search.getCertificateNum().length()-4,search.getCertificateNum().length())
			linkPath=sysPath+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getCertificateNum();
			filePath=linkPath;
			file=new File(filePath);
			if (file.exists()) {  
				getFileByRecursion(fileList, file);
			}
		}
		//�˲���
		if(ArchivesFStatus.ZBL.equals(search.getArchivesF())){
 			linkPath=sysPath+search.getYear()+"/"+search.getArchivesF()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/"+search.getArchivesC();
			if(ArchivesCStatus.SAPSUBSIDIARYTITLE.getLabel().equals(search.getArchivesC())){
				String[] months = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
				for (String month : months) {
					String tempPath = linkPath+"/"+month+"/"+"��ϸ"+search.getCertificateNum()+FileSuffix.PDF;
					File tempFile=new File(tempPath);
					if  (tempFile.exists()) {
						getFileByRecursion(fileList, tempFile);
					}
				}
			}else if(ArchivesCStatus.SAPGENERALTITLE.getLabel().equals(search.getArchivesC())){
				linkPath=linkPath+"/"+"��"+search.getCertificateNum()+FileSuffix.PDF;
				filePath=linkPath;
				file=new File(filePath);
				if  (file.exists()) {  
					getFileByRecursion(fileList, file);
				}
			}else{
				linkPath=linkPath+"/"+search.getCertificateNum()+FileSuffix.PDF;
				filePath=linkPath;
				file=new File(filePath);
				if  (file.exists()) {  
					getFileByRecursion(fileList, file);
				}
			}				
		}
		//���񱨸���
		if(ArchivesFStatus.CWBGL.equals(search.getArchivesF())){
			//��ʽ�����񱨸���/����/��������������
			linkPath=sysPath+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/"+search.getProfitCenterCode()+search.getProfitCenterName()+"/";
			File tempFile =new File(linkPath); 
			//�ж��ļ�·����ʽ
			if  (!tempFile.exists()  || !tempFile.isDirectory()) {     
				linkPath=sysPath+search.getYear()+"/"+search.getArchivesF()+"/"+search.getCompanyCode()+search.getCompanyName()+"/";
			}
			filePath=linkPath;
			file=new File(filePath);
			if  (file.exists()) {  
				getFileByRecursion(fileList, file);
			}
		}
		//������
		if(ArchivesFStatus.OTHER.equals(search.getArchivesF())){
			linkPath=sysPath+search.getYear()+"/"+search.getArchivesF();
			filePath=linkPath;
			file=new File(filePath);
			//�ж��ļ�·����ʽ
			if  (file.exists()) {     
				getFileByRecursion(fileList, file);
			}
		}
		while(result.getRowCount()>0){
			result.removeRow(result.getRowCount()-1);
		} 
		for (int i=0;i<fileList.size();i++) {
			FileInfo info = fileList.get(i);
			Object[] row = new Object[7];
			row[0] = i+1+"";
			row[1] = search.getYear();
			row[2] = search.getProfitCenterCode()+search.getProfitCenterName();
			row[3] = search.getCertificateNum();
			row[4] = info.getFileName();
			row[5] = info.getFilePath();
			row[6] = "����";
			result.addRow(row);
		}
		return result;
	}
	
	public static void getFileByRecursion(List<FileInfo> fileList, File file){
		if (file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				getFileByRecursion(fileList,file2);
			}
		}else{
			String absolutePath = FileUtils.fixPath(file.getAbsolutePath());
			String fileName = file.getName();
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFilePath(absolutePath);
			fileInfo.setFileName(fileName);
			fileList.add(fileInfo);
		}
	}
	
	
//	/**
//	 * �����������鿴������Ϣ
//	 * ���ߣ�daizhenming
//	 * ʱ�䣺Apr 2, 2015
//	 * @return
//	 */
//	public String viewImageInfo(){
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String curDate =  sdf.format(new Date());
//		String attachTree ="[{'name':'������Ϣ'}]";
//		if(offLineQueryVo==null){
//			return null;
//		}else{
//			//����
//			 try {
//				String filePath=URLDecoder.decode(offLineQueryVo.getFilePath(), "utf-8");
//				String waterMan=URLDecoder.decode(offLineQueryVo.getWaterMan(), "utf-8");
//				offLineQueryVo.setWaterMan(waterMan);
//				offLineQueryVo.setFilePath(filePath);
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			} 
//			attachTree = offLineMgrService.queryDetailOffLineFiles(offLineQueryVo.getFilePath(),offLineQueryVo.getWaterMan());
//		}
//		String waterText = offLineQueryVo.getWaterMan()+curDate;
//		request.setAttribute("attachTree", attachTree);
//		request.setAttribute("waterText", waterText);	
//		//Ĭ�Ͽհ�ҳ,ȥ��תȦ
//		String swfFile = ConfigUtil.SERVER_CONTEXT+ConfigUtil.PAPER_PATH;
//		request.setAttribute("swfFile", swfFile);
//		return "offLineAttachInfo";
//	}
//	
//	
//	public String getSwfFilePath(){
//		String attachPath="";
//		String waterMan="";
//			//���������
//			try {
//				attachPath = URLDecoder.decode(offLineQueryVo.getFilePath(), "utf-8");
//				waterMan = URLDecoder.decode(offLineQueryVo.getWaterMan(), "utf-8");
//			} catch (UnsupportedEncodingException e1) {
//				e1.printStackTrace();
//			}
//		String swfFile = "";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String curDate =  sdf.format(new Date());
//		//ƴ��ˮӡ���ݣ����磺���� 2013-11-11
//		String waterText =waterMan+curDate;
//		if (StringHelper.isNotNull(attachPath)) {	
//			try {
//				swfFile = offLineMgrService.convert2SwfFile(attachPath,waterText);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//        StringBuffer expression = new StringBuffer();
//        expression.append("callbackSwfFilePath('").append(FileUtils.fixPath(swfFile)+"',");
//        expression.append("'").append(swfFile+"','"+swfFile+"')");
//        TreeUtil.writeXmlDocument(ServletActionContext.getResponse(), expression.toString());
//        return null;
//	}
//	
//	
//	/**
//	 * test
//	 * ��ѯ�ѻ�����
//	 * @return
//	 */
//	public static String queryOffLineFiles(String path) throws BizException{
////		String path=SysPropertyUtil.getSysDrives();
////		String[] fileList=file.list();
////		for (String string : fileList) {
////			System.out.println(string);
////		}
////		String parent=file.getParent();
////		System.out.println("getParent()="+parent);
////		String  path=file.getPath();
////		System.out.println("getPath()="+path);
//		File file =new File(path);
//		if(!file.isFile()){
//		String[] filepath=file.list();
//		for (String string : filepath) {
//			System.out.println(string.toString());
//		}
//		}
//		if(!file.isFile()){//������ļ���������ִ��
//			File[] files = file.listFiles();
//			for (File file2 : files) {
//				if (file2.isDirectory()) {
//					String[] flies = file2.list();//�ļ����������ļ�
//					for (String string : flies) {
//						System.out.println(string);
//						String newPath = file2.getPath() + "\\" + string;
//						System.out.println(newPath);
//						queryOffLineFiles(newPath);//�ݹ�Ƕ��
//					}
//				} else {
//					System.out.println(file2.toString());
//				}
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * �������������Ͳ��Ҷ�Ӧ���ӵ�������,��ǰ̨JSPҳ�����
//	 * @return
//	 */
//	public String getZBChildsStatusArrayGroup()throws Exception {
//		String parentId = request.getParameter("parentId");
//		List<ZBChildsStatus>zBChildsStatusList = new ArrayList<ZBChildsStatus>();
//		//������˲��� �г�������������
//		if("222".equals(parentId)){
//			zBChildsStatusList=new ZBChildsStatus().toList();
//		}
//		JSONObject json = new JSONObject();
//		JSONArray zBChildsStatusArray = new JSONArray();
//		for(Iterator<ZBChildsStatus> ite =zBChildsStatusList.iterator(); ite.hasNext();) {
//		    JSONObject zBChildsStatusJson = new JSONObject();
//		    ZBChildsStatus zBChildsStatus = ite.next();
//		    zBChildsStatusJson.put("zBChildsStatusVaule", zBChildsStatus.getValue());
//		    zBChildsStatusJson.put("zBChildsStatusLabel", zBChildsStatus.getLabel());
//		    zBChildsStatusArray.add(zBChildsStatusJson);
//		}
//		json.put("zBChildsStatusArray", zBChildsStatusArray);
//		render(json.toString());
//		return null;
//	} 
//	
	
}
