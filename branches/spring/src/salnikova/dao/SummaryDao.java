package salnikova.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import salnikova.model.Attestation;
import salnikova.model.Control;
import salnikova.model.Group;
import salnikova.model.Student;
import salnikova.orm.SearchCriterion;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;
import salnikova.orm.Storage;

public class SummaryDao {

	
	private final static Log m_log = LogFactory.getLog(SummaryDao.class);

	public List<Control> getControls(final Integer groupId) {
		if (groupId == null) {
			return new LinkedList<>();
		}

		final Group group = m_storage.load(Group.class, groupId);
		if(group.getTutorId()==null || group.getTutorId().equals(0)){
			return new LinkedList<>();
		}
		
		SearchQuery query = new SearchQuery();

		query.getCriterions().add(
					SearchCriterion.eq("ownerId", group.getTutorId()));
		
		query.getOrders().add(SortOrder.asc("number"));

		return m_storage.search(Control.class, query);
	}

	public Map<Student, Map<Integer, Attestation>> getAtts(final Integer groupId) {

		Map<Student, Map<Integer, Attestation>> map = new HashMap<>();
		if(groupId==null){
			return map;
		}

		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("groupId", groupId));

		List<Student> result = m_storage.search(Student.class, q);

		List<Control> controls = getControls(groupId);

		for (Student student : result) {
			SearchQuery qq = new SearchQuery();
			qq.getCriterions().add(
					SearchCriterion.eq("studentId", student.getId()));

			List<Attestation> atts = m_storage.search(Attestation.class, qq);
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




	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
