package salnikova.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Student;

public class StudentMapper implements RowMapper<Student> {

	@Override
	public Student mapRow(ResultSet rs, int row) throws SQLException {
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setFirstName(rs.getString("firstName"));
		student.setSecondName(rs.getString("secondName"));
		student.setGroupId(rs.getInt("groupId"));
		return student;
	}

}
