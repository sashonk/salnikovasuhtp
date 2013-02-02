package salnikova.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import salnikova.model.Attestation;
import salnikova.model.Control;
import salnikova.model.Student;

public class StudentsDao extends Dao {

	private static StudentsDao instance;

	public static StudentsDao get() {
		if (instance == null) {
			instance = new StudentsDao();
		}
		return instance;
	}

	public Map<Control, Attestation> getAttestations(final Integer studentId) {
		String sql = "select c.id control, c.maxpoints maxpoints, c.name name, (select a.points from attestations a where a.studentid = ? and a.controlid = control) points, "
				+ "(select case when points is NULL then 1 else 0 end) cs from controls c";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		Map<Control, Attestation> result = new HashMap<>();
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, studentId);

			rs = st.executeQuery();
			while (rs.next()) {

				Control control = new Control();
				control.setId(rs.getInt("control"));
				control.setMaxPoint(rs.getBigDecimal("maxpoints"));
				control.setName(rs.getString("name"));

				Attestation a = null;
				if (rs.getInt("cs") == 0) {
					a = new Attestation();
					a.setControlId(control.getId());
					a.setPoints(rs.getBigDecimal("points"));
					a.setStudentId(studentId);
				}
				result.put(control, a);
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

	public void createStudent(final Student s) {
		String sql = "insert into students (firstName, secondName, groupId) values(?, ? , ?)";

		Connection c = null;
		PreparedStatement st = null;


		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setString(1, s.getFirstName());
			st.setString(2, s.getSecondName());
			st.setInt(3, s.getGroupId());
			st.executeUpdate();

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

	}

	public Student findStudent(final Integer id) {
		String sql = "select firstname, secondname, groupid from students where id = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;


		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, id);


			rs = st.executeQuery();
			if (rs.next()) {

				Student student = new Student();
				student.setFirstName(rs.getString("firstname"));
				student.setSecondName(rs.getString("secondname"));
				student.setGroupId(rs.getInt("groupid"));
				student.setId(id);

				return student;
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

		return null;
	}
	
	public void deleteStudent(final Integer id) {
		String sql = "delete from students where id = ?";

		Connection c = null;
		PreparedStatement st = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, id);
			st.executeUpdate();

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
	}

	public void deleteAttestation(final Integer studentId,
			final Integer controlId) {
		Connection c = null;
		PreparedStatement st = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement("delete from attestations where studentid = ? and controlid = ?");
			st.setInt(1, studentId);
			st.setInt(2, controlId);
			st.executeUpdate();

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
	}

	public void createAttestation(final Integer studentId,
			final Integer controlId, final BigDecimal points) {

		ControlDao cDao = ControlDao.get();
		Control control = cDao.getControl(controlId);

		if (control.getMaxPoint().compareTo(points) < 0) {
			throw new IllegalArgumentException(
					"points provided exceed maxpoints for this control");
		}

		deleteAttestation(studentId, controlId);

		if (points.compareTo(BigDecimal.ZERO) < 0) {
			return;
		}

		Connection c = null;
		PreparedStatement st = null;
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement("insert into attestations (points, studentid, controlid) values (?, ? , ?)");
			st.setBigDecimal(1, points);
			st.setInt(2, studentId);
			st.setInt(3, controlId);
			st.executeUpdate();


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

	}
	
	public BigDecimal getTotalPoints(final Integer studentId) {
		String sql = "select sum(points) total from attestations where studentid = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, studentId);

			rs = st.executeQuery();
			if (rs.next()) {

				BigDecimal total = rs.getBigDecimal("total");

				return total == null ? BigDecimal.ZERO : total;
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

		return null;
	}

	
	public BigDecimal getPointsRaffled() {
		//TODO
		throw new IllegalStateException("not implemented");
	}
}
