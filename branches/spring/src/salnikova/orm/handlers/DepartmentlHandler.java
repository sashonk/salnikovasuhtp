package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Department;


public class DepartmentlHandler implements Handler<Department> {

	@Override
	public String getQueryBase() {
		return " select id, name from departments ";
	}



	@Override
	public String getUpdateBase() {
		return " update departments set name = :name where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from departments where id = :id ";
	}

	@Override
	public String getInsertBase() {
		return " insert into departments (id, name) values (:id, :name) ";
	}

	@Override
	public String getTableName() {
		return " departments ";
	}

	@Override
	public RowMapper<Department> getRowMapper() {
		return mapper;
	}

	private final RowMapper<Department> mapper = new RowMapper<Department>() {

		@Override
		public Department mapRow(ResultSet rs, int arg1) throws SQLException {
			Department dep = new Department();
			dep.setId(rs.getInt("id"));
			dep.setName(rs.getString("name"));
			return dep;
		}
	};
}
