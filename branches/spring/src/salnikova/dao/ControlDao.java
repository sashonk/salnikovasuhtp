package salnikova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import salnikova.model.Control;

public class ControlDao extends Dao {

	private static ControlDao instance;

	public static ControlDao get() {
		if (instance == null) {
			instance = new ControlDao();
		}
		return instance;
	}

	public Control getControl(final Integer id) {
		String sql = "select id, name, maxpoints, number from controls where id = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {

				Control control = new Control();
				control.setId(rs.getInt("id"));
				control.setMaxPoint(rs.getBigDecimal("maxpoints"));
				control.setName(rs.getString("name"));
				control.setNumber(rs.getInt("number"));

				return control;
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

	public void deleteControl(final Integer id) {
		String sql = "delete from controls where id = ?";

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

	public List<Control> getControlList() {
		String sql = "select id, name, maxpoints, number from controls order by number asc";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Control> result = new LinkedList<>();
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);

			rs = st.executeQuery();
			while (rs.next()) {

				Control control = new Control();
				control.setId(rs.getInt("id"));
				control.setMaxPoint(rs.getBigDecimal("maxpoints"));
				control.setName(rs.getString("name"));
				control.setNumber(rs.getInt("number"));

				result.add(control);
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

	public Control createControl(final Control ctrl) {
		String sql = "insert into controls (name, maxpoints, number) values(?, ? , ?)";

		Connection c = null;
		PreparedStatement st = null;

		try {
			c = m_dataSource.getConnection();
			c.setAutoCommit(false);
			st = c.prepareStatement(sql);
			st.setString(1, ctrl.getName());
			st.setBigDecimal(2, ctrl.getMaxPoint());
			st.setInt(3, ctrl.getNumber());
			st.executeUpdate();
			st.close();
			
			
			st = c.prepareStatement("select id, name, maxpoints, number from controls where id = (select max(id) from controls)");
			ResultSet rs = st.executeQuery();

			Control ct = null;
			if (rs.next()) {
				ct = new Control();
				ct.setId(rs.getInt("id"));
				ct.setMaxPoint(rs.getBigDecimal("maxpoints"));
				ct.setName(rs.getString("name"));
				ct.setNumber(rs.getInt("number"));
			}
			
			c.commit();

			return ct;
		} catch (Exception ex) {
			try {
				c.rollback();
			} catch (SQLException e) {
				m_log.error(e);
			}
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
}
