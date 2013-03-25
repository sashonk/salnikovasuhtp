package salnikova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import salnikova.model.Department;
import salnikova.model.Group;

public class GroupsDao extends Dao {
	private static GroupsDao instance;

	public static GroupsDao get() {
		if (instance == null) {
			instance = new GroupsDao();
		}
		return instance;

	}

	public void delete(final Integer id) {
		String sql = "delete from groups where id = ?";

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

	public void createGroup(final Group group) {

		String sql = "insert into groups (name, depid, grade) values(?, ? , ?)";

		Connection c = null;
		PreparedStatement st = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setString(1, group.getName());
			st.setInt(2, group.getDepId());
			st.setInt(3, group.getGrade());
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

	public List<Department> getDepartments() {
		StringBuilder sql = new StringBuilder(
				"select id, name from departments order by name");
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Department> result = new LinkedList<>();
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql.toString());

			rs = st.executeQuery();
			while (rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("id"));
				dep.setName(rs.getString("name"));
				result.add(dep);
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

	public Group getGroup(final Integer id) {
		StringBuilder sql = new StringBuilder(
				"select id, name, depid from groups where id = ?");

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql.toString());
			st.setInt(1, id);

			rs = st.executeQuery();
			if (rs.next()) {
				Group group = new Group();
				group.setDepId(rs.getInt("depid"));
				group.setId(rs.getInt("id"));
				group.setName(rs.getString("name"));
				return group;
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

	public List<Group> getGroups(final Integer depId) {
		StringBuilder sql = new StringBuilder(
				"select id, name, depid from groups");
		if (depId != null) {
			sql.append(" where depid = ? ");
		}

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		List<Group> result = new LinkedList<>();
		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql.toString());

			if (depId != null) {
				st.setInt(1, depId);
			}

			rs = st.executeQuery();
			while (rs.next()) {
				Group group = new Group();
				group.setDepId(rs.getInt("depid"));
				group.setId(rs.getInt("id"));
				group.setName(rs.getString("name"));
				result.add(group);
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
}
