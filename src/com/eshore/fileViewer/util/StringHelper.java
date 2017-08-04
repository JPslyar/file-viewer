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
     * �������е�Ԫ��תΪ��","�������ַ���
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
     * �����ַ���ת��Ϊ""
     * 
     * @param ԭʼ�ַ���
     * @return ����ת������ַ���
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
     * ������0ת��Ϊ""���������ַ���ת��Ϊ""
     * 
     * @param strOrigin
     *            strOrigin ԭʼ�ַ����������ַ�����
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
     * �����ַ���װ��Ϊ"0"
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
     * ���ո�" "���ָ��ת��Ϊhtml�Ŀո����
     * 
     * @param ԭʼ�ַ���
     * @return ����ת������ַ���
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
     * ��ȡRequest�����ַ
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
     * �ж��ַ����ǿ�,����ǿգ�����true�����򷵻�false;
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
     * �ֽ����ض��ָ����ָ����ͬһ������Ϣ���ַ���Ϊ�ַ�������
     * 
     * @param strOrigin
     *            ԭʼ�ַ���
     * @param separator
     *            �ָ���
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
     * ������ת����list
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
     * ��������ַ���ת��Ϊ�����ַ������������ַ���ת��Ϊ""
     * 
     * @param strOrigin
     *            ԭʼ�ַ���
     * @return �����ַ���
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
     * �������ַ���ת��ΪISO8859_1�����ʽ���������ַ���ת��Ϊ""
     * 
     * @param strOrigin
     *            strOrigin ԭʼ�ַ����������ַ�����
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
     * 0012����12,aaabb����bb,
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
     * �ж�java�ַ����Ƿ��Ǵ���ascii�ַ���
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
     * �ж����ַ����Ƿ����
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
            return Double.valueOf(tempSum);// ʵ����У�鷢Ʊ���
        }
        return 0.0;
    }

    /**
     * ��ȡ32λ�ַ���
     * 
     * @return
     */
    public static String getUUIDStr() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * ��ȡ����
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
     * ���ߣ��Ƴ��� ����˵�������һ��������������� ����˵����@param bean ����˵����@return Jul 6, 2012
     */
    public static String message(Object bean) {
        if (bean == null)
            return "����Ϊ��!";
        StringBuffer sbf = new StringBuffer();
        Class<?> clazz = bean.getClass();
        sbf.append("\n" + clazz.getName() + " ��鿪ʼ:\n");
        try {
            sbf.append("  ��鹫����Ա������\n");
            Field[] fs = clazz.getFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                sbf.append("    " + clazz.getName() + "." + f.getName() + ": " + f.get(bean) + "\n");
            }
            sbf.append("  ��鹫��������\n");
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
        sbf.append(clazz.getName() + " ������!");
        return sbf.toString();
    }

    /**
     * @���ߣ��Ƴ���
     * @����˵�����ж��ַ����ǲ��Ǵ�����
     * @����˵����@param str
     * @����˵����@return false ���зǷ��ַ��� true����������
     * @ʱ�䣺Jul 13, 2012
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
     * @���ߣ��Ƴ���
     * @����˵��������ָ�������ַ���������ǰ�油0
     * @����˵����@param str ԭ�ַ�
     * @����˵����@param strLength ����ָ������
     * @����˵����@return
     * @ʱ�䣺Aug 1, 2012
     */
    public static String addZeroForNum(String str, int strLength) {
        if (str == null) {
            str = "";
        }
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                sb.append("0").append(str);// ��0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * ��SAPϵͳ�л�ȡ���(����3444.23- �������������λ�����)
     * 
     * @���ߣ�������
     * @����˵����
     * @����˵����@param str
     * @����˵����@return
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
     * �˷����ǰѼ����е��ַ���ƴ�ӳ� 'a','b',c' ����ʽ�� �û����ݿ��ѯ in����ʽ
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
      * �ָ�List
      * @param list ���ָ��list
      * @param pageSize ÿ��list�Ĵ�С
      * @return List<<List<T>> 
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size(); // list�Ĵ�С
        int page = (listSize + (pageSize - 1)) / pageSize; // ҳ��

        List<List<T>> listArray = new ArrayList<List<T>>(); // ����list���� ,��������ָ���list
        for (int i = 0; i < page; i++) { // ���������С����
            List<T> subList = new ArrayList<T>(); // ����ÿһλ����һ���ָ���list
            for (int j = 0; j < listSize; j++) { // �������ָ��list
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize; // ��ǰ��¼��ҳ��(�ڼ�ҳ)
                if (pageIndex == (i + 1)) { // ��ǰ��¼��ҳ�����Ҫ�����ҳ��ʱ
                    subList.add(list.get(j)); // ����list�е�Ԫ�ص��ָ���list(subList)
                }
                if ((j + 1) == ((j + 1) * pageSize)) { // ������һҳʱ�˳���ǰѭ��
                    break;
                }
            }
            listArray.add(subList); // ���ָ���list�����Ӧ�������λ��
        }
        return listArray;
    }
    
    /**
     * �ҳ�listone������listTwo������
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
     * ������ת�����ַ���������SQL
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
     * @Author��pj
     * @ ����˵���� ��ȡ�����ĳ�����ԣ���װ������
     * @param list 
     * @param targetField Ŀǰ��������
     * @return
     * @ �㷨���£�
     * @1��
     * @ �汾��Ϣ:2013-5-10
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
	 * ��Դ�ִ�ǰ����ַ���ָ������
	 * 
	 * @param src
	 *            Դ�ִ�
	 * @param totalLength
	 *            �����ִ��ܳ���
	 * @param padding
	 *            ���������ַ�
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
	 * ���ַ�����������ĳ������������´�
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
	 * �Ƿ��������
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
	 * ����˵�����ж��Ƿ������� <br>
	 * ���������String sPara�� ��Ҫ�жϵ��ַ��� <br>
	 * �������ͣ�boolean����������������ͣ�����true�����򷵻�false
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
	 * ������С���ֵ��ȡ�����е�ÿһ����ֵ
	 * @author Liangr
	 * @param min
	 * @param max
	 * @param few ֻ�жϺ�λ
	 * @return
	 * Feb 18, 2014
	 * List<String>
	 */
	public static List<String> getSeriesNo(String min, String max, int few) {
		List<String> list = new ArrayList<String>();
		// ���ж�λ���Ƿ�һ�£���һ�µĻ���ֱ�ӷ���
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
	 * ��Ӱ��ı��˵���ת���ɱ���ƽ̨�ı��˵���
	 * ���磺��TYA01909010100011405000405TY0 ת��Ϊ TYA01909010100011405000405
	 * @return
	 */
	public static String transCharacter (String imageCode){
		String str = imageCode.substring(imageCode.length()-3,imageCode.length()-1);
		boolean flag = Pattern.compile("[a-zA-Z].+$").matcher(str).matches();
		//��Ӱ�񵥺�ʱ����TRUE
		if(flag){
			imageCode = imageCode.substring(0, imageCode.length()-3);
		}
        return imageCode;
	}
	
    /**
     * @���ߣ�������
     * @����˵������ָ�����ַ�����ʼ��ɾ������֮�������
     * @����˵����@param sb ԭ�ַ�
     * @����˵����@param sub ָ���ַ���
     * @����˵����@return
     * @ʱ�䣺2014/11/21
     */
    public static StringBuffer delLastStringBuf(StringBuffer sb,String sub) {
    	return sb.delete(sb.toString().indexOf(sub),sb.toString().length());
    }
    

}