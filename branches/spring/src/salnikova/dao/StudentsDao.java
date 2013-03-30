package salnikova.dao;

import java.math.BigDecimal;
import java.util.HashMap;
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

public class StudentsDao {


	private final static Log m_log = LogFactory.getLog(StudentsDao.class);

	public Map<Control, Attestation> getAttestations(final Integer studentId) {
		
		Student student = m_storage.load(Student.class, studentId);
		Group g = m_storage.load(Group.class, student.getGroupId());
		
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("ownerId", g.getTutorId()));
		q.getOrders().add(SortOrder.asc("number"));
		List<Control> controls = m_storage.search(Control.class, q);

		SearchQuery query = new SearchQuery();
		query.getCriterions().add(SearchCriterion.eq("studentId", studentId));
		List<Attestation> data = m_storage.search(Attestation.class, query);
		
		Map<Control, Attestation> map = new HashMap<>();
		for (Control control : controls) {

			Attestation attestation = null;
			inner: for (Attestation a : data) {
				if (a.getControlId().equals(control.getId())) {
					attestation = a;
					break inner;
				}
			}

			map.put(control, attestation);
		}

		return map;

	}

	public Student createStudent(final String firstName,
			final String secondName, final Integer groupId) {
		Student s = new Student();
		s.setFirstName(firstName);
		s.setSecondName(secondName);
		s.setGroupId(groupId);

		return m_storage.save(s);

	}

	public List<Student> getStudents(final Integer groupId) {
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("groupId", groupId));
		q.getOrders().add(SortOrder.asc("secondName"));

		return m_storage.search(Student.class, q);

	}

	public Student findStudent(final Integer id) {

		return m_storage.load(Student.class, id);

	}
	
	public void deleteStudent(final Integer id) {
		Student student = m_storage.load(Student.class, id);
		m_storage.delete(student);
	}

	public void deleteAttestation(final Integer id) {
		Attestation a = m_storage.load(Attestation.class, id);
		m_storage.delete(a);
	}

	public Attestation createAttestation(final Integer studentId,
			final Integer controlId, final BigDecimal points) {
		Control c = m_storage.load(Control.class, controlId);
		if (c.getMaxPoint().compareTo(points) < 0) {
			throw new IllegalArgumentException(
					"value entered exceeds max points");
		}

		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("studentId", studentId));
		q.getCriterions().add(SearchCriterion.eq("controlId", controlId));
		List<Attestation> attestations = m_storage.search(Attestation.class, q);

		for (Attestation a : attestations) {
			m_storage.delete(a);
		}


		if (points.compareTo(BigDecimal.ZERO) > 0) {
			Attestation a = new Attestation();
			a.setControlId(controlId);
			a.setPoints(points);
			a.setStudentId(studentId);
			return m_storage.save(a);
		}

		return null;
	}
	

	public BigDecimal getTotalPoints(final Integer studentId) {

		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("studentId", studentId));
		List<Attestation> data = m_storage.search(Attestation.class, q);

		BigDecimal total = BigDecimal.ZERO;
		for (Attestation a : data) {
			total = total.add(a.getPoints());
		}

		return total;

	}


	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
