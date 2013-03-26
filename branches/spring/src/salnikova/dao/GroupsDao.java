package salnikova.dao;

import java.util.List;

import salnikova.model.Group;
import salnikova.model.Identity;
import salnikova.orm.Manager;
import salnikova.orm.SearchCriterion;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;

public class GroupsDao {


	public void delete(final Integer id) {
		Identity group = m_manager.load(Group.class, id);

		m_manager.delete(group);
	}

	public Group createGroup(final String name, final Integer depId,
			final Integer grade) {
		Group g = new Group();
		g.setName(name);
		g.setGrade(grade);
		g.setDepId(depId);

		return m_manager.save(g);
	}

	public Group getGroup(final Integer id) {
		return m_manager.load(Group.class, id);

	}

	public List<Group> getGroups(final Integer depId) {
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("depId", depId));
		q.getOrders().add(SortOrder.asc("name"));

		return m_manager.search(Group.class, q);
	}



	public void setManager(final Manager mgr) {
		m_manager = mgr;
	}

	private Manager m_manager;
}
