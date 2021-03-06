package salnikova.dao;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

import salnikova.model.DocData;
import salnikova.model.Document;

public class DocDao extends Dao {
	private static DocDao instance;

	public static DocDao get() {
		if (instance == null) {
			instance = new DocDao();
		}
		return instance;
	}

	public void createDoc(final String name, final byte[] content,
			final Integer controlId) {
		String sql = "insert into documents (name, size, controlId) values (? , ? , ?)";

		Connection c = null;
		PreparedStatement st = null;

		try {
			c = m_dataSource.getConnection();
			c.setAutoCommit(false);
			st = c.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, content.length);
			st.setInt(3, controlId);
			st.executeUpdate();
			st.close();

			st = c.prepareStatement("select max(id) id from documents");
			ResultSet rs = st.executeQuery();
			rs.next();
			Integer max = rs.getInt("id");
			st.close();

			st = c.prepareStatement("insert into docdata (id, content) values (? ,?) ");
			st.setInt(1, max);
			ByteArrayInputStream bais = new ByteArrayInputStream(content);
			st.setBlob(2, bais);
			st.executeUpdate();

			c.commit();
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
	}
	
	
	public Document findDocument(final Integer controlId) {

		String sql = "select id, name, controlId, size  from documents where controlId = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, controlId);
			rs = st.executeQuery();

			if (rs.next()) {
				Document dd = new Document();
				dd.setId(rs.getInt("id"));
				dd.setName(rs.getString("name"));
				dd.setSize(rs.getLong("size"));
				dd.setControlId(rs.getInt("controlId"));

				return dd;
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

	public Document getDocument(final Integer id) {

		String sql = "select id, name, controlId, size  from documents where id = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Document dd = new Document();
				dd.setId(rs.getInt("id"));
				dd.setName(rs.getString("name"));
				dd.setSize(rs.getLong("size"));
				dd.setControlId(rs.getInt("controlId"));

				return dd;
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

	public DocData getDocData(final Integer id) {
		String sql = "select id, content from docdata where id = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			c = m_dataSource.getConnection();
			st = c.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				DocData dd = new DocData();
				dd.setId(rs.getInt("id"));
				Blob b = rs.getBlob("content");
				dd.setContent(IOUtils.toByteArray(b.getBinaryStream()));

				return dd;
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
}
