package salnikova.dao.model;

public class StudentSearchParameters {

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
	
	public void setSurname(final String value){
		m_surname = value;
	}
	
	public String getSurname(){
		return m_surname;
	}
	
	private Integer m_groupId;
	private Integer m_depId;
	private String m_surname;

}
