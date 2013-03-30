package salnikova.model;


public class DocData implements Identity {

	@Override
	public Integer getId(){
		return m_id;
	}
	
	@Override
	public void setId(final Integer id){
		m_id= id;
	}

	public void setContent(final byte[] content) {
		m_content = content;
	}
	
	public byte[] getContent() {
		return m_content;
	}

	private Integer m_id;
	private byte[] m_content;
}
