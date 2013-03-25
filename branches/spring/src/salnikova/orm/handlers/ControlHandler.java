package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Control;

public class ControlHandler implements Handler<Control> {

	@Override
	public String getQueryBase() {
		return " select id, name, maxPoints, number from controls ";
	}



	@Override
	public String getUpdateBase() {
		return " update controls set name = :name, maxPoints = :maxPoint, number = :number where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from controls where id = :id ";
	}

	@Override
	public String getInsertBase() {
		return " insert into controls (name, maxPoints, number) values (:name, :maxPoint, :number) ";
	}

	@Override
	public String getTableName() {
		return " controls ";
	}

	@Override
	public RowMapper<Control> getRowMapper() {
		return mapper;
	}

	private final RowMapper<Control> mapper = new RowMapper<Control>() {

		@Override
		public Control mapRow(ResultSet rs, int row) throws SQLException {
			Control control = new Control();
			control.setId(rs.getInt("id"));
			control.setName(rs.getString("name"));
			control.setNumber(rs.getInt("number"));
			control.setMaxPoint(rs.getBigDecimal("maxPoints"));

			return control;
		}
	};
}