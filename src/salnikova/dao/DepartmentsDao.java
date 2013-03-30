package salnikova.dao;

import java.util.List;

import salnikova.model.Department;
import salnikova.model.Group;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;
import salnikova.orm.Storage;

public class DepartmentsDao {

	public Department findDepartment(final Integer groupId) {
		Group g = m_storage.load(Group.class, groupId);
		
		if(g!=null){
			return m_storage.load(Department.class, g.getDepId());
		}
		
		return null;
	}

	public List<Department> getDepartments() {
		SearchQuery sq = new SearchQuery();
		sq.getOrders().add(SortOrder.desc("name"));

		return m_storage.search(Department.class, sq);
	}



	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
