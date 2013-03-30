package salnikova.orm;

public abstract class SearchCriterion {

	public static SearchCriterion eq(final String prop, final Object value) {
		return new EqualsCriterion(prop, value);
	}

	public static SearchCriterion gt(final String prop, final Object value) {
		return new GreaterCriterion(prop, value);
	}

	public static SearchCriterion lt(final String prop, final Object value) {
		return new LessCriterion(prop, value);
	}

	public abstract String getProperty();

	public abstract Object getValue();

	public abstract String getOperand();

}
