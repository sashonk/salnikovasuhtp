package salnikova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import salnikova.model.Attestation;
import salnikova.model.Control;
import salnikova.model.Student;
import salnikova.orm.Manager;
import salnikova.orm.SearchCriterion;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;

public class SummaryDao {

	private DataSource m_dataSource;
	
	private final static Log m_log = LogFactory.getLog(SummaryDao.class);

	private StudentsDao m_studentsDao;

	private NamedParameterJdbcTemplate npjt;

	public void setNpjt(final NamedParameterJdbcTemplate value) {
		npjt = value;
	}

	public void setStudentsDao(final StudentsDao dao) {
		m_studentsDao = dao;
	}

	public Map<Student, Map<Integer, Attestation>> getAtts(final Integer groupId) {

		Map<Student, Map<Integer, Attestation>> map = new HashMap<>();

		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("groupId", groupId));

		List<Student> result = m_manager.search(Student.class, q);
		
		SearchQuery query = new SearchQuery();
		query.getOrders().add(SortOrder.asc("number"));
		List<Control> controls = m_manager.search(Control.class, query);

		for (Student student : result) {
			SearchQuery qq = new SearchQuery();
			qq.getCriterions().add(
					SearchCriterion.eq("studentId", student.getId()));

			List<Attestation> atts = m_manager.search(Attestation.class, qq);
			Map<Integer, Attestation> mm = new HashMap<>();

			for (Control c : controls) {
				Attestation att = null;
				inner: for (Attestation a : atts) {
					if (a.getControlId().equals(c.getId())) {
						att = a;
						break inner;
					}
				}

				mm.put(c.getId(), att);
			}

			map.put(student, mm);
		}
		return map;
	};

	public Map<Student, Map<Integer, Attestation>> getAttestations(
			final Integer groupId) {
		
		String sql = "select id from students where groupid = ?";

		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		StudentsDao sDao = m_studentsDao;
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

			StringBuilder ids = new StringBuilder();
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



	public void setDataSource(DataSource ds) {
		m_dataSource = ds;

	}

	public void setOwnershipDao(final OwnershipDao dao) {
		m_ownerDao = dao;
	}

	private OwnershipDao m_ownerDao;

	public void setManager(final Manager mgr) {
		m_manager = mgr;
	}

	private Manager m_manager;
}
