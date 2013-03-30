package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Tutor;

public class TutorHandler implements Handler<Tutor> {

	@Override
	public String getQueryBase() {
		return " select id, firstName, middleName, lastName, post, degree, email, login from tutors ";
	}

	@Override
	public String getUpdateBase() {
		return " update tutors set firstName = :firstName, middleName = :middleName, lastName = :lastName, post = :post, degree = :degree, email = :email, login = :login where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from tutors where id = :id  ";
	}

	@Override
	public String getInsertBase() {
		return " insert into tutors (firstName, middleName, lastName, post, degree, email, login) values (:firstName, :middleName, :lastName, :post, :degree, :email, :login) ";
	}

	@Override
	public String getTableName() {
		
		return " tutors ";
	}

	@Override
	public RowMapper<Tutor> getRowMapper() {
		return mapper;
	}

	private final static RowMapper<Tutor> mapper = new RowMapper<Tutor>() {

		@Override
		public Tutor mapRow(ResultSet rs, int r) throws SQLException {
			Tutor t = new Tutor();
			t.setDegree(rs.getString("degree"));
			t.setFirstName(rs.getString("firstName"));
			t.setMiddleName(rs.getString("middleName"));
			t.setLastName(rs.getString("lastName"));
			t.setId(rs.getInt("id"));
			t.setEmail(rs.getString("email"));
			t.setPost(rs.getString("post"));
			t.setLogin(rs.getString("login"));

			return t;
		}
	};
}
