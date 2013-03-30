package salnikova.model;

import salnikova.util.HasName;


public class Tutor implements Identity, HasName {

	public void setFirstName(final String value) {
		m_firstName = value;
	}

	@Override
	public String getFirstName() {
		return m_firstName;
	}

	public void setMiddleName(final String value) {
		m_middleName = value;
	}

	@Override
	public String getMiddleName() {
		return m_middleName;
	}

	public void setLastName(final String value) {
		m_lastName = value;
	}

	@Override
	public String getLastName() {
		return m_lastName;
	}

	public void setPost(final String value) {
		m_post = value;
	}

	public String getPost() {
		return m_post;
	}

	public void setDegree(final String value) {
		m_degree = value;
	}

	public String getDegree() {
		return m_degree;
	}

	private String m_post;

	private String m_degree;

	@Override
	public void setId(Integer id) {
		m_id = id;
	}

	@Override
	public Integer getId() {
		return m_id;
	}

	public String getEmail() {
		return m_email;
	}

	public void setEmail(final String email) {
		m_email = email;
	}

	public void setLogin(final String login){
		m_login = login;
	}

	public String getLogin() {
		return m_login;
	}

	private String m_lastName;
	private String m_middleName;
	private String m_firstName;
	private Integer m_id;
	private String m_email;
	private String m_login;
}
