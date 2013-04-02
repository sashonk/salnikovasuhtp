package salnikova;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import salnikova.model.Attestation;
import salnikova.model.Control;
import salnikova.model.Department;
import salnikova.model.DocData;
import salnikova.model.Document;
import salnikova.model.Group;
import salnikova.model.Student;
import salnikova.model.Tutor;
import salnikova.orm.SearchQuery;
import salnikova.orm.Storage;

public class Script {
	public static void main(String [] argc){
		System.out.println("BEGIN");
		
		System.out.println("creating context");
		ApplicationContext ctx = new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		DataSource ds = ctx.getBean("ds",DataSource.class);
		DataSource ds2 = ctx.getBean("ds2",DataSource.class);
		Storage st = ctx.getBean("storage", Storage.class);
		Storage st2 = ctx.getBean("storage2", Storage.class);
		
		
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(ds);
		NamedParameterJdbcTemplate npjt2 = new NamedParameterJdbcTemplate(ds2);
		

			
			
		
		System.out.println("fetching accounts");
		List<UserData> uds =npjt.query("select name, password from users", new HashMap(), new RowMapper<UserData>(){

			@Override
			public UserData mapRow(ResultSet rs, int arg1)
					throws SQLException {
				UserData ud = new UserData();
				ud.name = rs.getString("name");
				ud.password = rs.getString("password");
				return ud;
			}});
		
		System.out.println("saving accounts\n");
		for(UserData ud : uds ){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", ud.name);
			map.put("password", ud.password);
			npjt2.update("insert into users (name, password) values (:name, :password)", map);
		}
		
		
		System.out.println("fetching user roles");
		List<Role> roles =npjt.query("select name, role_name from user_roles", new HashMap(), new RowMapper<Role>(){

			@Override
			public Role mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Role r = new Role();
				r.name = rs.getString("name");
				r.role = rs.getString("role_name");
				return r;
			}});
		
		System.out.println("saving user roles\n");
		for(Role r : roles){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", r.name);
			map.put("role_name", r.role);
			npjt2.update("insert into user_roles (name, role_name) values (:name, :role_name)", map);
		}
		
		
		System.out.println("fetching tutors");
		List<Tutor> tutors = st.search(Tutor.class, new SearchQuery());
		for(Tutor t : tutors){
			st2.save(t);
		}
		
		
		System.out.println("fetching departments");
		List<Department> deps = st.search(Department.class, new SearchQuery());
		
		System.out.println("saving departments\n");
		for(Department d : deps){
			st2.save(d);
		}
		
		
		System.out.println("fetching groups");
		List<Group> groups = st.search(Group.class, new SearchQuery());
		System.out.println("saving groups\n");
		for(Group g : groups){
			st2.save(g);
		}
		
		
		System.out.println("fetching students");
		List<Student> students = st.search(Student.class, new SearchQuery());
		System.out.println("saving students\n");
		for(Student s : students){
			st2.save(s);
		}		
		
		
		System.out.println("fetching controls");
		List<Control> controls = st.search(Control.class, new SearchQuery());
		System.out.println("saving controls\n");
		for(Control c : controls){
			st2.save(c);
		}
		
		
		
		
		
		System.out.println("querying attestations");
		List<Attestation> data = npjt.query("select studentId, controlId, points from attestations", new HashMap(), new RowMapper<Attestation>(){

			@Override
			public Attestation mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Attestation a = new Attestation();
				a.setStudentId(rs.getInt("studentid"));
				a.setControlId(rs.getInt("controlid"));
				a.setPoints(rs.getBigDecimal("points"));
				
				return a;
			}});
		
		System.out.println("saving attestations\n");
		for(Attestation a : data){
			st.save(a);
		}
		
		
		System.out.println("fetching documents");
		List<Document> docs = st.search(Document.class, new SearchQuery());
		System.out.println("saving documents\n");
		for(Document d : docs){
			st2.save(d);
		} 
		
		System.out.println("fetching docdatas");
		Map<String, ?> m = new HashMap<>();
		List<DocData> ddatas = npjt.query("select id, content from docdata", m, new RowMapper<DocData>() {

			@Override
			public DocData mapRow(ResultSet rs, int arg1) throws SQLException {
				DocData dd = new DocData();
				dd.setId(rs.getInt("id"));
				dd.setContent(rs.getBytes("content"));
				return dd;
			}
		});
		
		System.out.println("saving docdatas\n");
		
		for(DocData d : ddatas){
			Map<String, Object> m2= new HashMap<>();
			m2.put("id", d.getId());
			m2.put("content", d.getContent());						
			npjt2.update("insert into docdata (id, content) values (:id, :content)", m2);
		}

		
		System.out.println("END");
		
		
	}
	
/*	
	final static class Attestation{
		
		
		
		public int studentid;
		public int controlid;
		public BigDecimal points;
	}*/
	
	final static class UserData{
		public String name;
		public String password;
	}
	
	final static class Role{
		public String name;
		 String role;
	}
}
