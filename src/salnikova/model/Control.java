package salnikova.model;

import java.math.BigDecimal;
import java.util.Date;

public class Control {

	
	public String getName(){
		return m_name;
	}
	
	public void setName(final String name){
		m_name= name;
	}
	
	public String getDescription(){
		return m_description;
	}
	
	public void setDescription(final String value){
		m_description= value;
	}
	
	public void setDueDate(final Date date){
		m_dueDate = date;
	}
	
	public Date getDueDate(){
		return m_dueDate;
	}
	
	public void setMaxPoint(final BigDecimal value) {
		m_maxPoint = value;
	}
	
	public BigDecimal getMaxPoint() {
		return m_maxPoint;
	}
	
	public Integer getId(){
		return m_id;
	}
	
	public void setId(final Integer id){
		m_id = id;
	}
	
	public void setNumber(final Integer number) {
		m_number = number;
	}
	
	public Integer getNumber() {
		return m_number;
	}

	private BigDecimal m_maxPoint;
	private Date m_dueDate;
	private String m_description;
	private String m_name;
	private Integer m_id ;
	private Integer m_number;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m_id == null) ? 0 : m_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Control other = (Control) obj;
		if (m_id == null) {
			if (other.m_id != null)
				return false;
		} else if (!m_id.equals(other.m_id))
			return false;
		return true;
	}
}
