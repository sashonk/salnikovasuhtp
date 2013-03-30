package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Student;

public class StudentHandler implements Handler<Student> {

	@Override
	public String getQueryBase() {
		return " select id, firstName, secondName, groupId from students ";
	}



	@Override
	public String getUpdateBase() {
		return " update students set firstName = :firstName, secondName = :secondName, groudId = :groupId where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from students where id = :id  ";
	}

	@Override
	public String getInsertBase() {
		return " insert into students (firstName, secondName, groupId) values (:firstName, :secondName, :groupId) ";
	}

	@Override
	public String getTableName() {
		return " students ";
	}

	@Override
	public RowMapper<Student> getRowMapper() {
		return mapper;
	}

	private final RowMapper<Student> mapper = new RowMapper<Student>() {

		@Override
		public Student mapRow(ResultSet rs, int arg1) throws SQLException {
			Student s = new Student();
			s.setId(rs.getInt("id"));
			s.setFirstName(rs.getString("firstName"));
			s.setLastName(rs.getString("secondName"));
			s.setGroupId(rs.getInt("groupId"));
			return s;
		}
	};
}
