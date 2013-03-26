package salnikova.orm;

import java.util.LinkedList;
import java.util.List;


public class SearchQuery {
	public void setLimit(final Integer limit) {
		m_limit = limit;
	}

	public Integer getLimit() {
		return m_limit;
	}


	@Override
	public String toString() {
		return null;
	}

	public List<SearchCriterion> getCriterions() {
		return m_criterions;
	}

	public List<SortOrder> getOrders() {
		return m_orders;
	}

	private Integer m_limit;
	private final List<SearchCriterion> m_criterions = new LinkedList<>();
	private final List<SortOrder> m_orders = new LinkedList<>();
}
