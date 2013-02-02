package salnikova.model;





public class Student {

	public String getFirstName(){
		return m_firstName;
	}
	public String getSecondName(){
		return m_secondName;
	}
	
	public void setFirstName(final String name){
		m_firstName = name;
	}
	
	public void setSecondName(final String name){
		m_secondName = name;
	}
	
	public Integer getId(){
		return m_id;
	}
	
	public void setId(final Integer id){
		m_id= id;
	}
	
	
	public Integer getGroupId(){
		return m_groupId;
	}
	
	public void setGroupId(final Integer groupId){
		m_groupId = groupId;
	}

	private String m_secondName;
	private String m_firstName;
	private Integer m_groupId;
	private Integer m_id;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (m_id == null) {
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		return true;
	}
}
