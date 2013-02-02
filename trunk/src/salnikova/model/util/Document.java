package salnikova.model.util;

public class Document {
		
	public Integer getId(){
		return m_id;
	}
	
	public void setId(final Integer id){
		m_id= id;
	}
	
	public void setContent(final byte[] content){
		m_content = content;
	}
	
	public byte[] getContent(){
		return m_content;
	}
	
	public void setName(final String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	

	
	private byte[] m_content;
	private Integer m_id;
	private String m_name;

}
