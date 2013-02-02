package salnikova.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Dao {

	Dao(){		
		m_log = LogFactory.getLog(getClass());

		  try {
				Context ctx = new InitialContext();
				m_dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
				
			  } catch (NamingException e) {
				  m_log.error(e);
			 }


	}
	
	protected DataSource m_dataSource;
	protected Log m_log ;
}
