package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Document;

public class DocumentHandler implements Handler<Document> {

	@Override
	public String getQueryBase() {
		return " select id, controlId, size, name from documents ";
	}


	@Override
	public String getUpdateBase() {
		return " update documents set controlId = :controlId, size = :size, name = :name where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from documents where id = :id ";
	}

	@Override
	public String getInsertBase() {
		return " insert into documents (controlId, size, name) values (:controlId, :size, :name) ";
	}

	@Override
	public String getTableName() {
		return " documents ";
	}

	private final RowMapper<Document> mapper = new RowMapper<Document>() {

		@Override
		public Document mapRow(ResultSet rs, int arg1) throws SQLException {
			Document doc = new Document();
			doc.setControlId(rs.getInt("controlId"));
			doc.setId(rs.getInt("id"));
			doc.setName(rs.getString("name"));
			doc.setSize(rs.getLong("size"));
			return doc;
		}
	};

	@Override
	public RowMapper<Document> getRowMapper() {
		return mapper;
	}
}
