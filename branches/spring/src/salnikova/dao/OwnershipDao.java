package salnikova.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import salnikova.model.Identity;
import salnikova.orm.Manager;

public class OwnershipDao {


	public boolean owns(final String user, final Identity identity) {

		if (user == null || identity == null) {
			throw new NullPointerException();
		}

		final String owner = getOwner(identity);
		if (owner == null) {
			return false;
		}

		return owner.equals(user);
	}

	public String getOwner(final Identity identity) {
		Map<String, Object> map = new HashMap<>();
		map.put("ID", identity.getId());
		map.put("CLASS", identity.getClass().getName());
		final String owner = npjt
				.queryForObject(
						"select user from ownerships where  identityId = :ID and identityClass = :CLASS",
						map, String.class);
		return owner;
	}

	public void deleteOwnership(final String user, final Identity identity) {
		Map<String, Object> map = new HashMap<>();
		map.put("ID", identity.getId());
		map.put("USER", user);
		map.put("CLASS", identity.getClass().getName());
		npjt.update(
						"delete from ownerships where user= :USER and identityId = :ID and identityClass = :CLASS",
						map);
	}

	public boolean createOwnership(final String user, final Identity identity,
			final boolean overwrite
			) {

		final String currentOwner = getOwner(identity);
		if (currentOwner != null) {
			if (!overwrite) {
				return false;
			}

			Map<String, Object> map = new HashMap<>();
			map.put("ID", identity.getId());
			map.put("USER", user);
			map.put("CLASS", identity.getClass().getName());
			npjt.update(
					"update ownerships set user = :USER where identityId= :ID and identityClass=:CLASS",
					map);
		}
 else {
			Map<String, Object> map = new HashMap<>();
			map.put("ID", identity.getId());
			map.put("USER", user);
			map.put("CLASS", identity.getClass().getName());
			npjt.update(
					"insert into ownerships (user, identityId, identityClass) values (:USER, :ID, :CLASS)",
					map);
		}

		return true;
	}



	public void setNpjt(final NamedParameterJdbcTemplate value) {
		npjt = value;
	}

	private NamedParameterJdbcTemplate npjt;

	public void setManager(final Manager mgr) {
		m_manager = mgr;
	}

	private Manager m_manager;
}
