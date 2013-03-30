package salnikova.model;

import java.math.BigDecimal;

public class Attestation implements Identity {

	@Override
	public void setId(Integer id) {
		m_id = id;
	}

	@Override
	public Integer getId() {
		return m_id;
	}
			
	public Integer getControlId(){
		return m_controlId;
	}
	
	public void setControlId(final Integer id){
		m_controlId = id;
	}
	
	public void setPoints(final BigDecimal value){
		m_points = value;
	}
	
	public BigDecimal getPoints(){
		return m_points;
	}
	
	public void setStudentId(final Integer id){
		m_studentId = id;
	}
	
	public Integer getStudentId(){
		return m_studentId;
	}
	
	private Integer m_controlId;
	private BigDecimal m_points;
	private Integer m_studentId;
	private Integer m_id;
}
