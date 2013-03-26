package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Group;

public class GroupHandler implements Handler<Group> {

	@Override
	public String getQueryBase() {
		return " select id, name, depId, grade from groups ";
	}



	@Override
	public String getUpdateBase() {
		return " update groups set name = :name , depId = :depId, grade = :grade where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from groups where id= :id ";
	}

	@Override
	public String getInsertBase() {
		return " insert into groups (name, depId, grade) values (:name, :depId, :grade) ";
	}

	@Override
	public String getTableName() {
		return " groups ";
	}

	@Override
	public RowMapper<Group> getRowMapper() {
		return mapper;
	}

	private final RowMapper<Group> mapper = new RowMapper<Group>() {

		@Override
		public Group mapRow(ResultSet rs, int arg1) throws SQLException {
			Group g = new Group();
			g.setId(rs.getInt("id"));
			g.setGrade(rs.getInt("grade"));
			g.setName(rs.getString("name"));
			g.setDepId(rs.getInt("depId"));

			return g;
		}
	};
}
