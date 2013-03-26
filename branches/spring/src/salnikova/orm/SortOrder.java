package salnikova.orm;

public abstract class SortOrder {
	
	public static SortOrder asc(final String prop) {
		return new SortOrder() {
			@Override
			public String getProperty() {
				return prop;
			}

			@Override
			public Direction getDirection() {
				return Direction.ASC;
			}

		};
	}

	public static SortOrder desc(final String prop) {
		return new SortOrder() {
			@Override
			public String getProperty() {
				return prop;
			}

			@Override
			public Direction getDirection() {
				return Direction.DESC;
			}

		};
	}

	public abstract Direction getDirection();

	public abstract String getProperty();

	public enum Direction {
		ASC, DESC
	}
}
