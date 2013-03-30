package salnikova.web;

public class FormField {

	public enum Type {
		TEXT, PASSWORD;

		@Override
		public String toString() {
			return name();
		}
	}

	public FormField(final String name, final String displayName,
			final int order, final Type type, final boolean required) {
		m_required = required;
		m_name = name;
		m_order = order;
		m_displayName = displayName;
		m_type = type;
		m_value = "";
	}

	public void setValue(final String value) {
		m_value = value;
	}

	public String getValue() {
		return m_value;
	}

	public int getOrder() {
		return m_order;
	}

	public boolean isRequired() {
		return m_required;
	}

	public String getName() {
		return m_name;
	}

	public String getDisplayName() {
		return m_displayName;
	}

	public Type getType() {
		return m_type;
	}

	private final int m_order;

	private final String m_name;

	private final boolean m_required;

	private String m_value;

	private final String m_displayName;

	private final Type m_type;
}
