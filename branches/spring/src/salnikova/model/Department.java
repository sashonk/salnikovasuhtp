package salnikova.model;


public class Department implements Identity {

	public String getName(){
		return m_name;
	}
	
	public void setName(final String name){
		m_name= name;
	}
	
	@Override
	public void setId(final Integer id){
		m_id = id;
	}

	@Override
	public Integer getId(){
		return m_id;
	}
	
	private String m_name;
	private Integer m_id;
}
