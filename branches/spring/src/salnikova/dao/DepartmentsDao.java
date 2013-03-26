package salnikova.dao;

import java.util.List;

import salnikova.model.Department;
import salnikova.model.Group;
import salnikova.orm.Manager;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;

public class DepartmentsDao {

	public Department findDepartment(final Integer groupId) {
		Group g = m_manager.load(Group.class, groupId);
		
		if(g!=null){
			return m_manager.load(Department.class, g.getDepId());
		}
		
		return null;
	}

	public List<Department> getDepartments() {
		SearchQuery sq = new SearchQuery();
		sq.getOrders().add(SortOrder.desc("name"));

		return m_manager.search(Department.class, sq);
	}



	public void setManager(final Manager mgr) {
		m_manager = mgr;
	}

	private Manager m_manager;
}
