package salnikova.model;

public class Department {

	public String getName(){
		return m_name;
	}
	
	public void setName(final String name){
		m_name= name;
	}
	
	public void setId(final Integer id){
		m_id = id;
	}
	
	public Integer getId(){
		return m_id;
	}
	
	private String m_name;
	private Integer m_id;
}
