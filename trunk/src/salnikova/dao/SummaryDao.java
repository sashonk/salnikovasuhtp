package salnikova.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import salnikova.model.Attestation;
import salnikova.model.Student;

public class SummaryDao extends Dao {

	private static SummaryDao instance;

	public static SummaryDao get() {
		if (instance == null) {
			instance = new SummaryDao();
		}
		return instance;
	}



	public Map<Student, Map<Integer, Attestation>> getAttestationData(
final Integer groupId) {
		
		String sql = "select a.points points, (select case when points is NULL then 1 else 0 end) cs,  a.controlId control, (select c.number from controls c where c.id = control) number, s.firstName name, s.secondName secondName, s.groupId g, s.id sid from "
				+ "students s left outer join attestations a on a.studentId = s.id where s.groupId = ?  order by secondName desc, number asc";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Map<Student, Map<Integer, Attestation>> result = new HashMap<>();

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, groupId);

			rs = st.executeQuery();
			while (rs.next()) {

				Student s = new Student();
				s.setFirstName(rs.getString("name"));
				s.setSecondName(rs.getString("secondName"));
				s.setId(rs.getInt("sid"));
				s.setGroupId(rs.getInt("g"));

				Map<Integer, Attestation> attestations = result.get(s);
				if (attestations == null) {
					attestations = new HashMap<>();
					result.put(s, attestations);
				}
				
				Attestation a = null;
				if (rs.getInt("cs") == 0) {
					a = new Attestation();
					a.setControlId(rs.getInt("control"));
					a.setPoints(rs.getBigDecimal("points"));
					a.setStudentId(s.getId());
				}

				attestations.put(a.getControlId(), a);

			}

		} catch (Exception ex) {
			m_log.error(ex);
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (Exception ex) {
				m_log.error(ex);

			}
		}

		return result;
	}
	
	


	
	public void createAttestation(final Integer studentId,
			final Integer controlId) {
		//TODO
		throw new IllegalStateException("not implemented");			
	}

	
	public BigDecimal getPointsRaffled() {
		//TODO
		throw new IllegalStateException("not implemented");			
	}
}
