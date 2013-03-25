package salnikova.web;

public class Upload {

	public void setName(final String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	public void setContent(final byte[] b) {
		m_content = b;
	}

	public byte[] getContent() {
		return m_content;
	}

	private byte[] m_content;

	private String m_name;
}
