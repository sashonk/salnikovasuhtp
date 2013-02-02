package salnikova.model;



public class Labor {
		
	public Integer getControlId(){
		return m_controlId;
	}
	
	public void setControlId(final Integer id){
		m_controlId = id;
	}
	
	public void setStudentId(final Integer id){
		m_studentId = id;
	}
	
	public Integer getStudentId(){
		return m_studentId;
	}
	
	public Integer getId(){
		return m_id;
	}
	
	public void setId(final Integer id){
		m_id= id;
	}

	public Integer getDocumentId(){
		return m_documentId;
	}
	
	public void setDocumentId(final Integer docId){
		m_documentId = docId;
	}
	
	private Integer m_documentId;
	private Integer m_studentId;
	private Integer m_id;
	private Integer m_controlId;
	
}
