package com.eshore.fileViewer.contacts;

import java.util.ArrayList;
import java.util.List;

/**
 * 账簿类子类型
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
    public static ArchivesCStatus SAPGENERALTITLE = new ArchivesCStatus("01", "总分类账");			
    public static ArchivesCStatus SAPSUBSIDIARYTITLE = new ArchivesCStatus("02", "明细账");		  
    public static ArchivesCStatus SAPASSETSCARDTITLE = new ArchivesCStatus("03", "固定资产卡片");			
    
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
