package salnikova.dao.model;

public class ControlSearchParameters {

	public boolean isSortByEndDate(){
		return m_sortByEnd;
	}
	
	public void setSortByEndDate(final boolean value){
		m_sortByEnd = value;
	}
	
	public void setGroupId(final Integer value){
		m_groupId = value;
	}
	
	public Integer getGroupId(){
		return m_groupId;
	}
	
	private Integer m_groupId;
	private boolean m_sortByEnd;
}
