package salnikova.sql;

import salnikova.mappers.StudentMapper;

public class StudentRealm {

	public static String QueryForAll = String.format(
			"select %s, %s, %s, %s from students", Fields.id, Fields.firstName,
			Fields.secondName, Fields.groupId);

	public static String QueryForAllByGroup = String.format(
			"select %s, %s, %s, %s from students where %s = :%s", Fields.id,
			Fields.firstName,
			Fields.secondName, Fields.groupId);

	public static String QueryFor1ById = String.format(
			"select %s, %s, %s, %s from students where id = :%s", Fields.id,
			Fields.firstName, Fields.secondName, Fields.groupId, Fields.id);

	public static final class Fields {
		public static final String id = "id";
		public static final String firstName = "firstName";
		public static final String secondName = "secondName";
		public static final String groupId = "groupId";
	}

	public static final StudentMapper mapper = new StudentMapper();
}
