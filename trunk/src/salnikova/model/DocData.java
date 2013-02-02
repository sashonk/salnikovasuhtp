package salnikova.model;

public class DocData {

	public Integer getId(){
		return m_id;
	}
	
	public void setId(final Integer id){
		m_id= id;
	}
	
	public void setName(final String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	
	public int getSize(){
		return m_size;
	}
	
	public void setSize(final int size){
		m_size = size;
	}
	
	
	private int m_size;
	private String m_name;
	private Integer m_id;
}
