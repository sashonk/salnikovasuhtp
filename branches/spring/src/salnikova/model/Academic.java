package salnikova.model;


public class Academic implements Identity {

	public void setFirstName(final String value) {
		m_firstName = value;
	}

	public String getFirstName() {
		return m_firstName;
	}

	public void setMiddleName(final String value) {
		m_middleName = value;
	}

	public String getMiddleName() {
		return m_middleName;
	}

	public void setLastName(final String value) {
		m_lastName = value;
	}

	public String getLastName() {
		return m_lastName;
	}

	@Override
	public void setId(Integer id) {
		m_id = id;
	}

	@Override
	public Integer getId() {
		return m_id;
	}

	private String m_lastName;
	private String m_middleName;
	private String m_firstName;
	private Integer m_id;
}
