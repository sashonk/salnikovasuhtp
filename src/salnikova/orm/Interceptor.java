package salnikova.orm;

import salnikova.model.Identity;

public interface Interceptor<T extends Identity> {
	public boolean onSave();
}
