/**
 * 
 */
package com.eshore.fileViewer.util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Administrator
 */
public class StringHelper {
    /**
     * 将集合中的元素转为以","隔开的字符串
     * 
     * @param list
     * @return String
     * @author Administrator
     */

    public static final String convertToString(List<String> list) {
        String str = "";
        if (list != null && list.size() > 1) {
            for (Iterator<String> it = list.iterator(); it.hasNext();) {
                String temp = (String) it.next();
                str += temp + ",";

            }
        }
        else if (list.size() == 1) {
            str = (String) list.get(0);
        }
        if (str.length() > 10) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 将空字符串转换为""
     * 
     * @param 原始字符串
     * @return 返回转换后的字符串
     */
    public static final String convertStringNull(String strOrig) {
        String strReturn = "";
        if (strOrig == null || strOrig.equals(null)) {
            strReturn = "";
        }
        else {
            strReturn = trim(strOrig.trim());
        }
        return strReturn;
    }

    /**
     * 将数字0转换为""，并将空字符串转换为""
     * 
     * @param strOrigin
     *            strOrigin 原始字符串（中文字符串）
     * @return add by hansomee 3/5/2004
     */
    public static String convertZeroToSpace(String strOrigin) {
        if (strOrigin == null || strOrigin.equals(null) || strOrigin.equals("0")) {
            strOrigin = "";
        }
        else {
            strOrigin = strOrigin.trim();
        }

        return strOrigin;
    }

    /**
     * 将空字符串装换为"0"
     * 
     * @param strOrigin
     * @return
     * @author shenweilong
     */
    public static String convertNullToZero(String strOrigin) {
        String str = "";
        if ("".equals(strOrigin)) {
            str = "0";
        }
        else {
            str = strOrigin;
        }
        return str;
    }

    /**
     * 将空格串" "或空指针转换为html的空格编码
     * 
     * @param 原始字符串
     * @return 返回转换后的字符串
     */
    public static final String filterNullStringToHTMLSpace(String strOrigin) {
        String rets = "";
        if (strOrigin == null) {
            rets = "&nbsp;";
        }
        else if (strOrigin.length() == 0) {
            rets = "&nbsp;";
        }
        else {

            for (int i = 0; i < strOrigin.length(); i++) {
                if (strOrigin.charAt(i) == ' ') {
                    rets += "&nbsp;";

                }
                else {
                    rets += strOrigin.charAt(i);

                }
            }

        }
        return rets;
    }
    
    /**
     * 获取Request请求地址
     * @param requestUrl
     * @return
     */
    public static String getRequestAddress(String requestUrl) {
        String rex= "http://[a-zA-Z0-9.:]*/";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(requestUrl);
        String tempString = "";
        while (matcher.find()) {
            tempString = matcher.group();
        }
        return tempString;
    }

    /**
     * 判断字符串非空,如果非空，返回true，否则返回false;
     * 
     * @param value
     * @return
     */
    public static final boolean isNotNull(String value) {
        if (value != null && !"".equals(value)) {
            return true;
        }
        return false;
    }

    /**
     * 分解以特定分隔符分隔多个同一类型信息的字符串为字符串数组
     * 
     * @param strOrigin
     *            原始字符串
     * @param separator
     *            分隔符
     * @return
     */
    public static final String[] parserString(String strOrigin, String separator) {
        try {
            StringTokenizer st;
            String strItem;

            if (strOrigin == null) {
                return null;
            }
            st = new StringTokenizer(strOrigin, separator);
            String[] returnValue = new String[st.countTokens()];
            int index = 0;
            while (st.hasMoreTokens()) {
                strItem = (String) st.nextToken();
                if (strItem != null && strItem.trim().length() != 0) {
                    returnValue[index++] = strItem;
                }
            }
            return returnValue;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 把数组转换成list
     * @param arr
     * @return
     */
    public static final List<String> arrToList(String[] arr){
        List<String> list=new ArrayList<String>();
        if(arr==null){
    		return list;
    	}
        for (String str : arr) {
            list.add(str);
        }
        return list;
    }
    
    /**
     * @param s
     * @param separatorSign
     * @return String[]
     * @add hansomee 6/11/2003
     */

    public static String[] split(String s, String separatorSign) {
        try {
            if (s == null)
                return null;
            int index = 0;
            java.util.Vector<Integer> vec = new java.util.Vector<Integer>();
            while (true) {
                index = s.indexOf(separatorSign, index);
                if (index < 0)
                    break;
                vec.addElement(new Integer(index++));
            }

            int size = vec.size();

            if (size <= 0) {
                String[] ret = new String[1];
                ret[0] = s;
                return ret;
            }

            String[] strarr = new String[size + 1];

            Integer[] indArr = new Integer[size];
            vec.copyInto(indArr);

            // sort the index array for getting the string.
            java.util.Arrays.sort(indArr);

            int ind = 0;
            int len = strarr.length;
            for (int j = 0; j < (len - 1); j++) {
                strarr[j] = s.substring(ind, indArr[j].intValue());
                ind = indArr[j].intValue() + 1;
            }
            if (len > 0)
                strarr[len - 1] = s.substring(ind);

            return strarr;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将传入的字符串转换为中文字符串，并将空字符串转换为""
     * 
     * @param strOrigin
     *            原始字符串
     * @return 中文字符串
     */
    public static String toChineseStr(String strOrigin) {
        if (strOrigin == null || strOrigin.equals(null)) {
            strOrigin = "";
        }
        else {
            strOrigin = strOrigin.trim();
        }

        try {
            strOrigin = new String(strOrigin.getBytes("ISO8859_1"), "UTF-8");
        } catch (Exception e) {
        }
        return strOrigin;
    }

    /**
     * 将中文字符串转换为ISO8859_1编码格式，并将空字符串转换为""
     * 
     * @param strOrigin
     *            strOrigin 原始字符串（中文字符串）
     * @return
     */
    public static String toStandardStr(String strOrigin) {
        if (strOrigin == null || strOrigin.equals(null)) {
            strOrigin = "";
        }
        else {
            strOrigin = strOrigin.trim();
        }

        try {
            strOrigin = new String(strOrigin.getBytes("UTF-8"), "ISO8859_1");
        } catch (Exception e) {
        }
        return strOrigin;
    }

    public static String trim(String s) {
        try {
            if (isNotNull(s)) {
                String whitespace = new String(" ");
                s = s.replaceFirst(whitespace, "");
                s = new StringBuffer(s).reverse().toString();
                s = s.replaceFirst(whitespace, "");
                s = new StringBuffer(s).reverse().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String getStringFromList(Collection<String> collection) {
        StringBuffer buffer = new StringBuffer();

        for (Iterator<String> it = collection.iterator(); it.hasNext();) {

            buffer.append((String) it.next());
            buffer.append(";");
        }

        return buffer.substring(0, buffer.length() - 1);
    }

    /**
     * 0012返回12,aaabb返回bb,
     * 
     * @param s
     * @param c
     * @return
     */
    public static String getSubStrByCondition(String s, char c) {
        if (s == null || "".equals(s)) {
            return "";
        }
        char[] toCharArray = s.toCharArray();
        int m = 0;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] != c) {
                m = i;
                break;
            }
        }
        return s.substring(m, s.length());
    }

    /**
     * 判断java字符串是否是纯的ascii字符集
     */
    public static boolean isPureAscii(String str) {
        if (str == null || "".equals(str))
            return false;
        for (int i = 0, len = str.length(); i < len; i++) {
            char ch = str.charAt(i);
            if (ch > 127 || ch < 0)
                return false;
        }
        return true;
    }

    /**
     * 判断两字符串是否相等
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean isTheSame(String str1, String str2) {
        boolean result;
        if (str1 != null && str1.equals(str2)) {
            result = true;
        }
        else {
            result = false;
        }
        return result;

    }

    public static Double stringToDouble(String sum) {
        if (!"".equals(sum)) {
            String tempSum = sum.replaceAll(",", "");
            return Double.valueOf(tempSum);// 实际已校验发票金额
        }
        return 0.0;
    }

    /**
     * 获取32位字符串
     * 
     * @return
     */
    public static String getUUIDStr() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取交集
     * 
     * @param handlers
     * @param icHandlers
     */
    public static Set<Object> getCommonElements(Set<Object> set1, Set<Object> set2) {
        Set<Object> tempSet = null;
        if (set1 != null && set2 != null) {
            tempSet = new HashSet<Object>();
            tempSet.addAll(set1);
            tempSet.retainAll(set2);
        }
        return tempSet;
    }

    /**
     * 作者：黄楚勇 方法说明：输出一个对象里面的内容 参数说明：@param bean 参数说明：@return Jul 6, 2012
     */
    public static String message(Object bean) {
        if (bean == null)
            return "对象为空!";
        StringBuffer sbf = new StringBuffer();
        Class<?> clazz = bean.getClass();
        sbf.append("\n" + clazz.getName() + " 检查开始:\n");
        try {
            sbf.append("  检查公共成员变量：\n");
            Field[] fs = clazz.getFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                sbf.append("    " + clazz.getName() + "." + f.getName() + ": " + f.get(bean) + "\n");
            }
            sbf.append("  检查公共方法：\n");
            Method[] ms = clazz.getMethods();
            for (int i = 0; i < ms.length; i++) {
                Method m = ms[i];
                if ((!m.getReturnType().getName().equals("void") && m.getParameterTypes().length == 0)) {
                    sbf.append("    " + clazz.getName() + "." + m.getName() + "(): "
                            + m.invoke(bean) + "\n");
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        sbf.append(clazz.getName() + " 检查结束!");
        return sbf.toString();
    }

    /**
     * @作者：黄楚勇
     * @方法说明：判断字符串是不是纯数字
     * @参数说明：@param str
     * @参数说明：@return false 含有非法字符； true：都是数字
     * @时间：Jul 13, 2012
     */
    public static boolean isNumeric(String str) {
        boolean result = false;
        if (str != null && !((str.trim()).equals(""))) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);
            if (isNum.matches()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @作者：黄楚勇
     * @方法说明：生成指定长度字符串，不足前面补0
     * @参数说明：@param str 原字符
     * @参数说明：@param strLength 生成指定长度
     * @参数说明：@return
     * @时间：Aug 1, 2012
     */
    public static String addZeroForNum(String str, int strLength) {
        if (str == null) {
            str = "";
        }
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// 左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 从SAP系统中获取金额(处理3444.23- 这种情况，负号位于最后)
     * 
     * @作者：梁来养
     * @方法说明：
     * @参数说明：@param str
     * @参数说明：@return
     * @2012-9-17
     */
    public static Double getCostFromSap(String str) {
        StringBuffer orinalValue = new StringBuffer();
        String trim = str.trim();
        if (trim.lastIndexOf("-") >= 0) {
            orinalValue.append("-").append(trim.substring(0, trim.lastIndexOf("-")));
        }
        else {
            orinalValue.append(trim);
        }
        return new Double(orinalValue.toString());
    }

    /**
     * 此方法是把集合中的字符串拼接成 'a','b',c' 的形式。 用户数据库查询 in的形式
     * 
     * @param col
     * @return
     */
    public static String CollToStr(Collection<String> col) {
        StringBuffer sb = new StringBuffer("'");
        for (Iterator<String> iter = col.iterator(); iter.hasNext();) {
            String str = (String) iter.next();
            sb.append(str);
            sb.append("','");
        }
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 2);
        }
        else {
            return "null";
        }
    }

    /**
      * 分割List
      * @param list 待分割的list
      * @param pageSize 每段list的大小
      * @return List<<List<T>> 
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size(); // list的大小
        int page = (listSize + (pageSize - 1)) / pageSize; // 页数

        List<List<T>> listArray = new ArrayList<List<T>>(); // 创建list数组 ,用来保存分割后的list
        for (int i = 0; i < page; i++) { // 按照数组大小遍历
            List<T> subList = new ArrayList<T>(); // 数组每一位放入一个分割后的list
            for (int j = 0; j < listSize; j++) { // 遍历待分割的list
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // 当前记录的页码(第几页)
                if (pageIndex == (i + 1)) { // 当前记录的页码等于要放入的页码时
                    subList.add(list.get(j)); // 放入list中的元素到分割后的list(subList)
                }
                if ((j + 1) == ((j + 1) * pageSize)) { // 当放满一页时退出当前循环
                    break;
                }
            }
            listArray.add(subList); // 将分割后的list放入对应的数组的位中
        }
        return listArray;
    }
    
    /**
     * 找出listone不存在listTwo的数据
     * @param listOne
     * @param listTwo
     * @return
     */
    public static List<String> listOneNotContainListTwoDatas(List<String> listOne, List<String> listTwo){
        List<String> result = new ArrayList<String>();
        for(String one:listOne) {
            boolean flag = false;
            for(String two:listTwo) {
                if(one.equals(two)) {
                    flag = true;
                    break;
                }
            }
            if(!flag){
                result.add(one);
            }
        }
        return result;
    }
    
    /**
     * 将数组转换成字符串，用于SQL
     * @param ss
     * @return
     */
    public static String convertStringForSql(String[] ss){
        StringBuffer string = new StringBuffer("");
        if(ss == null || ss.length == 0){
            return "";
        }
        for(String s : ss){
            string.append("'" + s + "',");
        }
        return string.substring(0,string.length() - 1);
    }
    
    public static String[] convertToStringArray(List<String> list){
        String s[] = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            s[i] = list.get(i);
        }
        return s;
    }

    /**
     * @Author：pj
     * @ 方法说明： 获取对象的某个属性，封装成数组
     * @param list 
     * @param targetField 目前属性名称
     * @return
     * @ 算法如下：
     * @1：
     * @ 版本信息:2013-5-10
     */
    public static String[] convertToStringArray(List<?> list,
            String targetField) {
        String[] ids = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Object obj=list.get(i);
            try {
                Field filed =obj.getClass().getDeclaredField(targetField);
                filed.setAccessible(true);
                ids[i]=filed.get(obj).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ids;
    }
    
    /**
     * Generates the name of a XML namespace from a given class name and protocol. The returned
     * namespace will take the form <code>protocol://domain</code>, where <code>protocol</code> is
     * the given protocol, and <code>domain</code> the inversed package name of the given class
     * name.
     * <p/>
     * For instance, if the given class name is <code>org.codehaus.xfire.services.Echo</code>, and
     * the protocol is <code>http</code>, the resulting namespace would be
     * <code>http://services.xfire.codehaus.org</code>.
     * 
     * @param className
     *            the class name
     * @param protocol
     *            the protocol (eg. <code>http</code>)
     * @return the namespace
     */
    public static String makeNamespaceFromClassName(String className, String protocol) {
        int index = className.lastIndexOf(".");

        if (index == -1) {
            return protocol + "://" + "DefaultNamespace";
        }

        String packageName = className.substring(0, index);

        StringTokenizer st = new StringTokenizer(packageName, ".");
        String[] words = new String[st.countTokens()];

        for (int i = 0; i < words.length; ++i) {
            words[i] = st.nextToken();
        }

        StringBuilder sb = new StringBuilder(80);

        for (int i = words.length - 1; i >= 0; --i) {
            String word = words[i];

            // seperate with dot
            if (i != words.length - 1) {
                sb.append('.');
            }

            sb.append(word);
        }

        return protocol + "://" + sb.toString();
    }
    
	/**
	 * 在源字串前填充字符到指定长度
	 * 
	 * @param src
	 *            源字串
	 * @param totalLength
	 *            填充后字串总长度
	 * @param padding
	 *            用于填充的字符
	 * @return
	 */
	public static String lpad(String src, int totalLength, char padding) {
		if (src == null)
			return src;
		
		StringBuilder sb = new StringBuilder(src);
		while (sb.length() < totalLength)
			sb.insert(0, padding);

		return sb.toString();
	}
	/**
	 * 向字符串中逐个添加某个串，并输出新串
	 * @param src
	 * @param ins
	 * @return
	 */
	public static String insertCharToStringOneByOne(String src,String ins){
		StringBuffer old=new StringBuffer(src);
		String news = ""; 
		for(int i=0;i<old.length();i++){  
            if(i*1+1>old.length()){  
            	news += old.substring(i*1, old.length());  
                break;  
            }  
            news += old.substring(i*1, i*1+1)+ins;  
        }
		return news;
	}
	
	/**
	 * 是否包含中文
	 * @author Liangr
	 * @param c
	 * @return
	 * Nov 20, 2013
	 * boolean
	 */
	public static boolean isChinese(String s) {
		if(StringUtils.isBlank(s)){
			return false;
		}
		char[] ch = s.toCharArray();
		boolean flag = false;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * <br>
	 * 方法说明：判断是否是数字 <br>
	 * 输入参数：String sPara。 需要判断的字符串 <br>
	 * 返回类型：boolean。如果都是数字类型，返回true；否则返回false
	 */
	public static boolean isNumber(String sPara) {
		String str = "1234567890";
		char[] cNum = str.toCharArray();
		int iPLength = sPara.length();
		for (int i = 0; i < iPLength; i++) {
			char cTemp = sPara.charAt(i);
			boolean bTemp = false;
			for (int j = 0; j < cNum.length; j++) {
				if (cTemp == cNum[j]) {
					bTemp = true;
					break;
				}
			}
			if (!bTemp)
				return false;
		}
		return true;
	}
	
	/**
	 * 根据最小最大值获取区间中的每一个数值
	 * @author Liangr
	 * @param min
	 * @param max
	 * @param few 只判断后几位
	 * @return
	 * Feb 18, 2014
	 * List<String>
	 */
	public static List<String> getSeriesNo(String min, String max, int few) {
		List<String> list = new ArrayList<String>();
		// 先判断位数是否一致，不一致的话，直接返回
		if (min.length() != max.length()) {
			return null;
		}
		String startSub = min.substring(0, min.length() - few);
		String startNumber = min.substring(min.length() - few, min.length());
		String endSub = max.substring(0, max.length() - few);
		String endNumber = max.substring(max.length() - few, max.length());
		if (!startSub.equals(endSub)) {
			return null;
		}
		if (!isNumber(startNumber) || !isNumber(endNumber)) {
			return null;
		}

		for (int i = Integer.valueOf(startNumber); i <= Integer.valueOf(endNumber); i++) {
			String value = StringUtils.leftPad(String.valueOf(i), few, "0");
			String num = startSub + value;
			list.add(num);
		}
		return list;
	}
	
	/**
	 * 将影像的报账单号转换成报账平台的报账单号
	 * 例如：将TYA01909010100011405000405TY0 转换为 TYA01909010100011405000405
	 * @return
	 */
	public static String transCharacter (String imageCode){
		String str = imageCode.substring(imageCode.length()-3,imageCode.length()-1);
		boolean flag = Pattern.compile("[a-zA-Z].+$").matcher(str).matches();
		//是影像单号时返回TRUE
		if(flag){
			imageCode = imageCode.substring(0, imageCode.length()-3);
		}
        return imageCode;
	}
	
    /**
     * @作者：戴振明
     * @方法说明：从指定的字符串开始，删除掉其之后的所有
     * @参数说明：@param sb 原字符
     * @参数说明：@param sub 指定字符串
     * @参数说明：@return
     * @时间：2014/11/21
     */
    public static StringBuffer delLastStringBuf(StringBuffer sb,String sub) {
    	return sb.delete(sb.toString().indexOf(sub),sb.toString().length());
    }
    

}