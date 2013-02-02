package salnikova.dao.model;

public class AttestationSearchParameters {
	
	public void setDepId(final Integer depId){
		m_depId = depId;
	}
	
	public Integer getDepId(){
		return m_depId;
	}
	
	public Integer getGroupId(){
		return m_groupId;
	}
	
	public void setGroupId(final Integer groupId){
		m_groupId = groupId;
	}
	
	private Integer m_groupId;	
	private Integer m_depId;
}
