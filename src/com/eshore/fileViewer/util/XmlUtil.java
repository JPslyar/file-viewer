package com.eshore.fileViewer.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.eshore.fileViewer.data.ArchivesF;
import com.eshore.fileViewer.data.Company;
import com.eshore.fileViewer.data.PrincipalInfo;
import com.eshore.fileViewer.data.ProfitCenter;
import com.thoughtworks.xstream.XStream;
/**
 * @author JIANGPENG
 * @date 2014-8-26
 * 
 * */
public class XmlUtil {
  /**
   * 默认配置文件路径
   * PRINCIPALINFOS_PATH  登陆用户信息
   * YEARS_PATH 年度列表
   * COMPANYS_PATH  公司、利润中心
   * ARCHIVESTYPES_PATH 档案类型
   * PROPERTIES_PATH 系统参数
   * */
  public static String PRINCIPALINFOS_PATH = "/PrincipalInfos.xml"; 
  public static String YEARS_PATH = "/Years.xml";
  public static String COMPANYS_PATH = "/Companys.xml";
  public static String ARCHIVESTYPES_PATH = "/ArchivesTypes.xml";
  public static String PROPERTIES_PATH = "/Properties.properties";
  
  public static Object getBeanFromXml(String xml,Map<String,Class> aliasMap){
	    Object obj = null;
	    if(StringHelper.isNotNull(xml) && aliasMap != null){
	    		XStream xstream = new XStream();
	    		for (String key : aliasMap.keySet()) {
	    			xstream.alias(key, aliasMap.get(key));
				}
	    		obj = xstream.fromXML(xml);
	    }
	    return obj;
   }
  
  /**
   * 解析用户登录信息，用于登录验证
   * */
  public static Map<String,PrincipalInfo> getPrincipalInfos()throws DocumentException{
	  Map<String,PrincipalInfo> infoMap = new HashMap<String,PrincipalInfo>();
	  SAXReader saxReader = new SAXReader();
      try {
		  Document document = saxReader.read(XmlUtil.class.getResourceAsStream(PRINCIPALINFOS_PATH));
		  // 获取根元素
          Element root = document.getRootElement();
          // 获取所有子元素
          for (Iterator iter = root.elementIterator(); iter.hasNext();){
              Element ePricipalInfo = (Element) iter.next();
              Element ename = ePricipalInfo.element("Name");
              Element epwd = ePricipalInfo.element("PassWord");
              String name = ename.getText();
              String pwd = epwd.getText();
              PrincipalInfo principalInfo = new PrincipalInfo(name,pwd);
              infoMap.put(name, principalInfo);
          }
	  } catch (DocumentException e) {
		  e.printStackTrace();
		  throw e;
	  }
	  return infoMap;
  }
  
  /**
   * 解析xml文件，获取一级子元素列表
   * */
  public static List<Element> getInfoListFromXML(String path){
	  List<Element> elementList = new ArrayList<Element>();
	  SAXReader saxReader = new SAXReader();
	  try {
		  Document document = saxReader.read(XmlUtil.class.getResourceAsStream(path));
		  // 获取根元素
          Element root = document.getRootElement();
          // 获取所有子元素
          for (Iterator iter = root.elementIterator(); iter.hasNext();){
              Element element = (Element) iter.next();
              elementList.add(element);
          }
	  } catch (DocumentException e) {
		  e.printStackTrace();
	  }
	  return elementList;
  }
  /**
   *将一级子元素列表转为字符串列表，用于获取年度，利润中心
   * */
  public static Vector<String> getStrList(String path){
	  List<Element> elementList = getInfoListFromXML(path);
	  Vector<String> strVector = new Vector<String>();
	  if(elementList != null){
	    for (Element element : elementList) {
	    	strVector.add(element.getText());
	    }
	  }
	  return strVector;
  }
  /**
   * 解析档案类型
   * */
  public static Vector<ArchivesF> getArchivesType(){
	  List<Element> elementList = getInfoListFromXML(ARCHIVESTYPES_PATH);
	  Vector<ArchivesF> archivesFVector = new Vector<ArchivesF>();
	  if(elementList != null){
	    for (Element element : elementList) {
	    	String type = element.attributeValue("type"); 
	    	List<Element> childList = element.elements();
	    	Vector<String> archivesCVector = new Vector<String>();
	    	for (Element child : childList) {
	    		archivesCVector.add(child.getText());
			}
	    	ArchivesF archivesF = new ArchivesF();
	    	archivesF.setType(type);
	    	archivesF.setArchivesCVector(archivesCVector);
	    	archivesFVector.add(archivesF);
	    }
	  }
	  return archivesFVector;
  }
  /**
   * 解析公司利润中心
   * */
  public static Vector<Company> getCompanys(){
	  List<Element> elementList = getInfoListFromXML(COMPANYS_PATH);
	  Vector<Company> companyVector = new Vector<Company>();
	  if(elementList != null){
	    for (Element element : elementList) {
	    	String companyCode = element.attributeValue("code");
	    	String companyName = element.attributeValue("name"); 
	    	List<Element> childList = element.elements();
	    	Vector<ProfitCenter> profitCenterVector = new Vector<ProfitCenter>();
	    	for (Element child : childList) {
	    		String code = child.attributeValue("code");
	    		String name = child.attributeValue("name");
	    		ProfitCenter profitCenter = new ProfitCenter(code, name);
	    		profitCenterVector.add(profitCenter);
			}
	    	Company company = new Company();
	    	company.setName(companyName);
	    	company.setCode(companyCode);
	    	company.setProfitCenterVector(profitCenterVector);
	    	companyVector.add(company);
	    }
	  }
	  return companyVector;
  }
  /**
   * 获取系统参数
   * key 键值  反回  value
   * */
  public static String getValue(String key){
      String value = "";
      try {  
          Properties p = new Properties(); 
          InputStream inpf = XmlUtil.class.getResourceAsStream(PROPERTIES_PATH);  
          p.load(inpf); 
          value = p.getProperty(key);
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return value;
  }  
}
