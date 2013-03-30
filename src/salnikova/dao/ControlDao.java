package salnikova.dao;

import java.math.BigDecimal;
import java.util.List;

import salnikova.model.Control;
import salnikova.model.Identity;
import salnikova.orm.SearchQuery;
import salnikova.orm.SortOrder;
import salnikova.orm.Storage;

public class ControlDao {


	public Control getControl(final Integer id) {
		return m_storage.load(Control.class, id);
	}

	public void deleteControl(final Integer id) {
		Identity identity = m_storage.load(Control.class, id);
		m_storage.delete(identity);
	}


	public List<Control> getControlList() {

		SearchQuery query = new SearchQuery();
		query.getOrders().add(SortOrder.asc("number"));

		return m_storage.search(Control.class, query);

	}

	public Control createControl(final String name, final BigDecimal maxpoints,
			final Integer number, final Integer ownerId) {
		
		Control c = new Control();
		c.setName(name);
		c.setMaxPoint(maxpoints);
		c.setNumber(number);
		c.setOwnerId(ownerId);
		
		return m_storage.save(c);
	}


	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
