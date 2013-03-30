package salnikova.model;


public class Document implements Identity {

	@Override
	public Integer getId(){
		return m_id;
	}
	
	@Override
	public void setId(final Integer id){
		m_id= id;
	}
	
	public void setSize(final Long size) {
		m_size = size;
	}
	
	public Long getSize() {
		return m_size;
	}

	public void setName(final String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	
	public Integer getControlId() {
		return m_ownerId;
	}

	public void setControlId(final Integer id) {
		m_ownerId = id;
	}
	

	private Integer m_id;
	private String m_name;
	private Integer m_ownerId;
	private Long m_size;

}
