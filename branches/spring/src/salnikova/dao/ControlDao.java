package salnikova.dao;

import java.math.BigDecimal;
import java.util.List;

import salnikova.model.Control;
import salnikova.model.Identity;
import salnikova.orm.Manager;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;

public class ControlDao {


	public Control getControl(final Integer id) {
		return m_manager.load(Control.class, id);
	}

	public void deleteControl(final Integer id) {
		Identity identity = m_manager.load(Control.class, id);
		m_manager.delete(identity);
	}


	public List<Control> getControlList() {

		SearchQuery query = new SearchQuery();
		query.getOrders().add(SortOrder.asc("number"));

		return m_manager.search(Control.class, query);

	}

	public Control createControl(final String name, final BigDecimal maxpoints,
			final Integer number) {
		
		Control c = new Control();
		c.setName(name);
		c.setMaxPoint(maxpoints);
		c.setNumber(number);
		
		return m_manager.save(c);
	}


	public void setManager(final Manager mgr) {
		m_manager = mgr;
	}

	private Manager m_manager;
}
