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
	
	

	public Map<Student, Map<Integer, Attestation>> getAttData(final Integer groupId){
		
		String sql = "select id from students where groupid = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		StudentsDao sDao = StudentsDao.get();
		Map<Student, Map<Integer, Attestation>> result = new HashMap<>();

		if (groupId == null) {
			return result;
		}
 
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, groupId);
			rs = st.executeQuery();
			
			while(rs.next()){
				Integer id = rs.getInt("id");
				Student s = sDao.findStudent(id);
				result.put(s, new HashMap<Integer, Attestation>());
			}

			if (result.size() == 0) {
				return result;
			}

			StringBuilder ids = new StringBuilder(); // (1,2,3,4...)
			for (Student s : result.keySet()) {
				ids.append(s.getId());
				ids.append(',');
			}
			ids.deleteCharAt(ids.lastIndexOf(","));


			st.close();
			st = c.prepareStatement(String
					.format("select points, controlid, studentid from attestations where studentid in (%s) ",
					ids.toString()));
			rs = st.executeQuery();
			while (rs.next()) {
				Integer stId = rs.getInt("studentid");
				Integer ctrId = rs.getInt("controlid");
				
				Attestation a = new Attestation();
				a.setControlId(ctrId);
				a.setPoints(rs.getBigDecimal("points"));
				a.setStudentId(stId);
				Student stud = null;
				for(Student s : result.keySet()){
					if(s.getId().equals(stId)){
						stud = s;
						break;
					}
				}
				 result.get(stud).put(ctrId, a);
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


	public Map<Student, Map<Integer, Attestation>> getAttestationData(
final Integer groupId) {
		
		String sql = "select s.id sid, s.firstName firstName, s.secondName secondName, s.groupId groupId, c.id cid, c.name cname, "
				+ "(select a.points from attestations a where a.studentid = s.id and a.controlid = c.id) points"
				+ " from students s, controls c where s.groupid = ?";

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
				s.setFirstName(rs.getString("firstName"));
				s.setSecondName(rs.getString("secondName"));
				s.setId(rs.getInt("sid"));
				s.setGroupId(rs.getInt("groupId"));

				Map<Integer, Attestation> attestations = result.get(s);
				if (attestations == null) {
					attestations = new HashMap<>();
					result.put(s, attestations);
				}
				
				Attestation a = null;
				if (rs.getBigDecimal("points") != null) {
					a = new Attestation();
					a.setControlId(rs.getInt("cid"));
					a.setPoints(rs.getBigDecimal("points"));
					a.setStudentId(s.getId());
				}

				attestations.put(rs.getInt("cid"), a);

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
