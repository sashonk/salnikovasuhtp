package salnikova.orm;

import java.util.HashMap;
import java.util.Map;

import salnikova.model.Attestation;
import salnikova.model.Control;
import salnikova.model.Department;
import salnikova.model.Document;
import salnikova.model.Group;
import salnikova.model.Identity;
import salnikova.model.Student;
import salnikova.model.Tutor;
import salnikova.orm.handlers.AttestationHandler;
import salnikova.orm.handlers.ControlHandler;
import salnikova.orm.handlers.DepartmentlHandler;
import salnikova.orm.handlers.DocumentHandler;
import salnikova.orm.handlers.GroupHandler;
import salnikova.orm.handlers.Handler;
import salnikova.orm.handlers.StudentHandler;
import salnikova.orm.handlers.TutorHandler;

public class HandlersRegistry {

	private static HandlersRegistry instance;

	public static HandlersRegistry getInstance() {
		if (instance == null) {
			instance = new HandlersRegistry();
			instance.configure();
		}

		return instance;
	}

	private void configure() {
		m_handlers.put(Student.class.getName(), new StudentHandler());
		m_handlers.put(Control.class.getName(), new ControlHandler());
		m_handlers.put(Department.class.getName(), new DepartmentlHandler());
		m_handlers.put(Document.class.getName(), new DocumentHandler());
		m_handlers.put(Group.class.getName(), new GroupHandler());
		m_handlers.put(Attestation.class.getName(), new AttestationHandler());
		m_handlers.put(Tutor.class.getName(), new TutorHandler());
	}

	private HandlersRegistry() {

	}

	public <T extends Identity> Handler<T> findHandler(final Class<T> cls) {
		return (Handler<T>) m_handlers.get(cls.getName());
	}

	private final Map<String, Handler<?>> m_handlers = new HashMap<>();
}
