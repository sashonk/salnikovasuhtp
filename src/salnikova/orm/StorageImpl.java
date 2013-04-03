package salnikova.orm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import salnikova.model.Identity;
import salnikova.orm.handlers.Handler;

public class StorageImpl implements Storage {

	private NamedParameterJdbcTemplate npjt;

	public void setNpjt(final NamedParameterJdbcTemplate source) {
		npjt = source;
	}


	@Override
	public <T extends Identity> List<T> search(Class<T> cls, SearchQuery query) {

		StringBuilder sb = new StringBuilder();

		HandlersRegistry registry = HandlersRegistry.getInstance();
		Handler<T> handler = registry.findHandler(cls);
		sb.append(handler.getQueryBase());

		if (query.getCriterions().size() > 0) {
			sb.append(" where ");
		}

		for (SearchCriterion criterion : query.getCriterions()) {
			sb.append(criterion.getProperty());
			sb.append(' ');
			sb.append(criterion.getOperand());
			sb.append(" '");
			sb.append(criterion.getValue());
			sb.append("' and ");
		}

		if (query.getCriterions().size() > 0) {
			sb.replace(sb.lastIndexOf("and"), sb.length(), "");
		}

		if (query.getOrders().size() > 0) {
			sb.append(" order by ");
		}

		for (SortOrder order : query.getOrders()) {
			sb.append(order.getProperty());
			sb.append(' ');
			sb.append(order.getDirection().name());
			sb.append(", ");
		}

		if (query.getOrders().size() > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}

		if (query.getLimit() != null) {
			sb.append(" limit ");
			sb.append(query.getLimit());
			sb.append(' ');
		}

		Map<String, ?> map = new HashMap<>();


		List<T> result = npjt.query(sb.toString(), map, handler.getRowMapper());


		return result;
	}

	@Override
	public <T extends Identity> T save(final T identity) {
		HandlersRegistry registry = HandlersRegistry.getInstance();
		Handler<?> handler = registry.findHandler(identity.getClass());
		
		if (identity.getId() != null) {
			delete(identity);
			SqlParameterSource source = new BeanPropertySqlParameterSource(identity);
			npjt.update(handler.getInsertBase(), source);
		} else {
			Map<String, ?> mm = new HashMap<>();
			int maxId = npjt.queryForInt("select case when count(1) = 0 then 0 else max(id) end from "+handler.getTableName(), mm);
			identity.setId(maxId+1);
			
			SqlParameterSource source = new BeanPropertySqlParameterSource(identity);
			npjt.update(handler.getInsertBase(), source);

		}

		Object object = load(identity.getClass(), identity.getId());
		return (T) object;
	}



	@Override
	public void delete(Identity identity) {
		if(identity.getId()==null){
			throw new IllegalArgumentException(
					"trying to delete unpersisted model");
		}
		
		HandlersRegistry registry = HandlersRegistry.getInstance();
		Handler<?> handler = registry.findHandler(identity.getClass());
		SqlParameterSource source = new BeanPropertySqlParameterSource(identity);
		npjt.update(handler.getDeleteBase(), source);
	}

	@Override
	public <T extends Identity> T load(Class<T> cls, Integer id) {

		if (id == null) {
			throw new NullPointerException();
		}
		HandlersRegistry registry = HandlersRegistry.getInstance();
		Handler<?> handler = registry.findHandler(cls);
		String query = handler.getQueryBase();

		StringBuilder sb = new StringBuilder(query);
		sb.append(" where id = :ID ");

		Map<String, Object> map = new HashMap<>();
		map.put("ID", id);
		
		List<?> result =npjt.query(sb.toString(), map, handler.getRowMapper());
		if (result.size() >0 ) {
			return (T)result.get(0);
		}

		return null;
	}

}
