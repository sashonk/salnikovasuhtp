package salnikova.orm;

public class LessCriterion extends SearchCriterion {

	public LessCriterion(final String prop, final Object value) {
		m_property = prop;
		m_value = value;
	}

	@Override
	public String getProperty() {
		return m_property;
	}

	@Override
	public Object getValue() {
		return m_value;
	}

	@Override
	public String getOperand() {
		return " < ";
	}

	private final String m_property;
	private final Object m_value;
}
