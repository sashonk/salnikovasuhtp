package salnikova.model;


public class Group implements Identity {

	public String getName() {
		return m_name;
	}
	
	public void setName(final String name){
		m_name = name;
	}
	
	@Override
	public Integer getId(){
		return m_id;
	}
	
	@Override
	public void setId(final Integer value){
		m_id = value;
	}
	
	public void setDepId(final Integer depId){
		m_depId = depId;
	}
	
	public Integer getDepId(){
		return m_depId;
	}
	
	public Integer getGrade() {
		return m_grade;
	}

	public void setGrade(final Integer grade) {
		m_grade = grade;
	}


	public void setTutorId(final Integer value) {
		m_tutorId = value;
	}

	public Integer getTutorId() {
		return m_tutorId;
	}

	private Integer m_depId;
	private String m_name;
	private Integer m_id;
	private Integer m_grade;
	private Integer m_tutorId;

}
