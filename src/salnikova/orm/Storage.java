package salnikova.orm;

import java.util.List;

import salnikova.model.Identity;

public interface Storage {

	public <T extends Identity> List<T> search(final Class<T> cls,
			final SearchQuery query);

	public <T extends Identity> T save(final T identity);
	
	public <T extends Identity> T load(final Class<T> cls, final Integer id);

	public void delete(final Identity identity);

}
