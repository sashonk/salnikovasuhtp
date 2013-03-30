package salnikova.orm.handlers;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Identity;

public interface Handler<T extends Identity> {
	public String getQueryBase();

	public String getUpdateBase();

	public String getDeleteBase();

	public String getInsertBase();

	public String getTableName();

	public RowMapper<T> getRowMapper();
}
