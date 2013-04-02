package salnikova.orm.handlers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import salnikova.model.Attestation;

public class AttestationHandler implements Handler<Attestation> {

	@Override
	public String getQueryBase() {
		return " select id, studentId, points, controlId from attestations ";
	}

	@Override
	public String getUpdateBase() {
		return " update attestations set studentId = :studentId, controlId = :controlId, points = :points where id = :id ";
	}

	@Override
	public String getDeleteBase() {
		return " delete from attestations where id= :id ";
	}

	@Override
	public String getInsertBase() {
		return " insert into attestations (id, points, studentid, controlId) values (:id, :points, :studentId, :controlId) ";
	}

	@Override
	public String getTableName() {
		return "attestations";
	}

	@Override
	public RowMapper<Attestation> getRowMapper() {
		return mapper;
	}

	private static final RowMapper<Attestation> mapper = new RowMapper<Attestation>() {

		@Override
		public Attestation mapRow(ResultSet rs, int row) throws SQLException {
			Attestation a = new Attestation();
			a.setId(rs.getInt("id"));
			a.setControlId(rs.getInt("controlId"));
			a.setStudentId(rs.getInt("studentId"));
			a.setPoints(rs.getBigDecimal("points"));
			return a;
		}
	};

}
