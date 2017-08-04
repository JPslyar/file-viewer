package com.eshore.fileViewer.contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * �˲���������
 * @author daizhenming
 *
 */
public class ArchivesCStatus extends LabelValue { 
	
    private static final long serialVersionUID = 1L;
    
    public ArchivesCStatus() {
        super();
    }

    public ArchivesCStatus(String value, String label) {
        super(value, label);
    }
    public static ArchivesCStatus SAPGENERALTITLE = new ArchivesCStatus("01", "�ܷ�����");			
    public static ArchivesCStatus SAPSUBSIDIARYTITLE = new ArchivesCStatus("02", "��ϸ��");		  
    public static ArchivesCStatus SAPASSETSCARDTITLE = new ArchivesCStatus("03", "�̶��ʲ���Ƭ");			
    
    public List<ArchivesCStatus> getZBChildsStatus(){
    	List<ArchivesCStatus> list = new ArrayList<ArchivesCStatus>();
    	list.add(ArchivesCStatus.SAPGENERALTITLE);
    	list.add(ArchivesCStatus.SAPSUBSIDIARYTITLE);
    	list.add(ArchivesCStatus.SAPASSETSCARDTITLE);
    	return list;
    }
    
    public List<ArchivesCStatus> getZBChildsStatusPart(){
    	List<ArchivesCStatus> list = new ArrayList<ArchivesCStatus>();
    	list.add(ArchivesCStatus.SAPGENERALTITLE);
    	list.add(ArchivesCStatus.SAPSUBSIDIARYTITLE);
    	return list;
    }
}
