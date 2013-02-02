package salnikova.web.model;

import java.math.BigDecimal;

import salnikova.model.Control;

public class LaborData {

	
	public void setName(final String name){
		m_name = name;
	}
	
	public String getName(){
		return m_name;
	}
	
	public Integer getId(){
		return m_laborId;
	}
	
	public void setId(final Integer id){
		m_laborId = id;
	}
	
	public void setPoints(final BigDecimal points){
		m_points = points;
	}
	
	public BigDecimal getPoints(){
		return m_points;
	}
	
	public Control getControl(){
		return m_control;
	}
	
	public void setControl(final Control c){
		m_control = c;
	}
	
	private BigDecimal m_points;
	private Integer m_laborId;
	private String m_name;
	private Control m_control;
}
