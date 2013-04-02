package salnikova.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import salnikova.model.Group;
import salnikova.model.Student;
import salnikova.model.Tutor;
import salnikova.orm.SearchCriterion;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;
import salnikova.orm.Storage;
import salnikova.util.Utils;

public class TutorsDao {

	public Tutor getTutor(final Integer id) {
		return m_storage.load(Tutor.class, id);
	}

	public void delete(final Integer id) {
		Tutor t = m_storage.load(Tutor.class, id);
		m_storage.delete(t);
		
		Map<String ,Object> mm = new HashMap<>();
		mm.put("name", t.getLogin());
		npjt.update("delete from users where name = :name", mm);
		npjt.update("delete from user_roles where name = :name", mm);
	}


	public boolean isTutorOf(final Tutor tutor, final Student student) {
		Group g = m_storage.load(Group.class, student.getGroupId());
		return tutor.getId().equals(g.getTutorId());
	}

	public Tutor findProfile(final String login) {
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("login", login));

		List<Tutor> list = m_storage.search(Tutor.class, q);
		if (list.size() > 0) {
			return list.get(0);
		}

		return null;
	}

	public Tutor findTutor(final Integer groupId) {
		Group g = m_storage.load(Group.class, groupId);
		if (g.getTutorId() != null) {
			return m_storage.load(Tutor.class, g.getTutorId());
		}

		return null;
	}

	public List<Tutor> getAll() {
		SearchQuery q = new SearchQuery();
		q.getOrders().add(SortOrder.asc("lastName"));
		return m_storage.search(Tutor.class, q);
	}
	
	public List<Group> getGroups(final Integer tutorId){
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("tutorId", tutorId));
		q.getOrders().add(SortOrder.asc("depId"));

		List<Group> groups = m_storage.search(Group.class, q);
		return groups;
	}

	public Tutor create(final String firstName, final String middleName,
			final String lastName, final String post, final String degree,
			final String login, final String passwordPlain, final String email) {

		Tutor t = new Tutor();
		t.setDegree(degree);
		t.setPost(post);
		t.setFirstName(firstName);
		t.setMiddleName(middleName);
		t.setLastName(lastName);
		t.setEmail(email);
		t.setLogin(login);

		final String passwordSha = Utils.getSha(passwordPlain);
		Map<String, Object> map = new HashMap<>();
		map.put("name", login);
		map.put("password", passwordSha);
		map.put("role_name", "admin");
		npjt.update(
				"insert into users (name, password) values (:name, :password)",
				map);
		

		npjt.update(
				"insert into user_roles (name, role_name) values (:name, :role_name)",
				map);

		return m_storage.save(t);
	}

	private NamedParameterJdbcTemplate npjt;

	public void setNpjt(final NamedParameterJdbcTemplate value) {
		npjt = value;
	}

	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
