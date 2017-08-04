package com.eshore.fileViewer.contacts;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("unchecked")
public abstract class LabelValue implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String value;
    
    private String label;
    
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

 
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public LabelValue() {
    }

    public LabelValue(String value , String label) {
        this.value = value;
        this.label = label;
    }
    
    public String toString() {
        return value;
    }
    
    public boolean equals(LabelValue obj) {
        if (value != null) {
            return value.equals(obj.getValue());
        }
        return false;
    }
    
    public boolean isNotEmpty() {
        if (StringUtils.isNotBlank(value)) {
            return true;
        }
        return false;
    }
    
    public boolean isEmpty() {
        return !isNotEmpty();
    }
    
    public boolean isNotEmptyValue(String value) {
        return !isEmptyValue(value);
    }
    
    public boolean isEmptyValue(String value) {
        LabelValue labelValue = getLabelValue(value);
        return labelValue == null ? true : false;
    }
    
    public <T> List<T> toList() {
        List<T> list = new ArrayList<T>();
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Class<?> type = field.getType();
                if (LabelValue.class.isAssignableFrom(type)) {
                    Object object;
                    object = field.get(null);
                    if (object instanceof LabelValue) {
                        list.add((T)object);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new TreeMap<String, String>();
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Class<?> type = field.getType();
                if (LabelValue.class.isAssignableFrom(type)) {
                    Object object;
                    object = field.get(null);
                    if (object instanceof LabelValue) {
                        LabelValue  lv = (LabelValue) object;
                        map.put(lv.getValue(), lv.getLabel());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public <T extends LabelValue> T getLabelValue(String value) {
        LabelValue lv = null;
        if(StringUtils.isBlank(value)){
            return (T) lv;
        }
        try {
            Field[] fields = this.getClass().getDeclaredFields();
            for (Field field : fields) {
                Class<?> type = field.getType();
                if (LabelValue.class.isAssignableFrom(type)) {
                    Object object;
                    object = field.get(null);
                    if (object instanceof LabelValue) {
                        LabelValue  lvOjb = (LabelValue) object;
                        if(value.equals(lvOjb.getValue())){
                            lv = lvOjb;
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) lv;
    }
    
    public String getLabel(String value) {
        LabelValue lv = getLabelValue(value);
        return lv == null ? "" : lv.getLabel();
    }

}
