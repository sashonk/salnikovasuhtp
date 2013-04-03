package salnikova;

import java.io.PrintWriter;
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
	
	private DataSource ds;
	
	private DataSource ds2;
	
	private Storage st;
	
	private Storage st2;
	
	public void setDataSource(final DataSource ds){
		this.ds = ds;
	}
	
	public void setDataSource2(final DataSource ds){
		this.ds2 = ds;
	}
	
	public void setStorage(final Storage s){
		st = s;
	}
	
	public void setStorage2(final Storage s){
		st2 = s;
	}
	
	public  void doActions(PrintWriter pw){
		
		
		pw.println("BEGIN<br>");		
		pw.println("creating context<br>");

		
		
		NamedParameterJdbcTemplate npjt = new NamedParameterJdbcTemplate(ds);
		NamedParameterJdbcTemplate npjt2 = new NamedParameterJdbcTemplate(ds2);
										
		pw.println("fetching accounts<br>");
		List<UserData> uds =npjt.query("select name, password from users", new HashMap(), new RowMapper<UserData>(){

			@Override
			public UserData mapRow(ResultSet rs, int arg1)
					throws SQLException {
				UserData ud = new UserData();
				ud.name = rs.getString("name");
				ud.password = rs.getString("password");
				return ud;
			}});
		
		pw.println("saving accounts<br><br>");
		for(UserData ud : uds ){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", ud.name);
			map.put("password", ud.password);
			npjt2.update("insert into users (name, password) values (:name, :password)", map);
		}
		
		
		pw.println("fetching user roles<br>");
		List<Role> roles =npjt.query("select name, role_name from user_roles", new HashMap(), new RowMapper<Role>(){

			@Override
			public Role mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Role r = new Role();
				r.name = rs.getString("name");
				r.role = rs.getString("role_name");
				return r;
			}});
		
		pw.println("saving user roles<br><br>");
		for(Role r : roles){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", r.name);
			map.put("role_name", r.role);
			npjt2.update("insert into user_roles (name, role_name) values (:name, :role_name)", map);
		}
		
		
		pw.println("fetching tutors<br>");
		List<Tutor> tutors = st.search(Tutor.class, new SearchQuery());
		for(Tutor t : tutors){
			st2.save(t);
		}
		
		
		pw.println("fetching departments<br>");
		List<Department> deps = st.search(Department.class, new SearchQuery());
		
		pw.println("saving departments<br><br>");
		for(Department d : deps){
			st2.save(d);
		}
		
		
		pw.println("fetching groups<br>");
		List<Group> groups = st.search(Group.class, new SearchQuery());
		pw.println("saving groups<br><br>");
		for(Group g : groups){
			st2.save(g);
		}
		
		
		pw.println("fetching students<br>");
		Map<String, ?> ms= new HashMap<>();
		List<Student> students = npjt.query("select id, firstName, secondName, groupId from students", ms, new RowMapper<Student>(){

			@Override
			public Student mapRow(ResultSet rs, int arg1) throws SQLException {
				Student s = new Student();
				s.setId(rs.getInt("id"));
				s.setFirstName(rs.getString("firstName"));
				s.setLastName(rs.getString("secondName"));
				s.setMiddleName("");
				s.setGroupId(rs.getInt("groupId"));
				return s;
			}});

		pw.println("saving students<br><br>");
		for(Student s : students){
			st2.save(s);
		}		
		
		
		pw.println("fetching controls<br>");
		List<Control> controls = st.search(Control.class, new SearchQuery());
		pw.println("saving controls<br><br>");
		for(Control c : controls){
			st2.save(c);
		}
		
		
		
		
		
		pw.println("querying attestations<br>");
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
		
		pw.println("saving attestations<br><br>");
		for(Attestation a : data){
			st.save(a);
		}
		
		
		pw.println("fetching documents<br>");
		List<Document> docs = st.search(Document.class, new SearchQuery());
		pw.println("saving documents<br><br>");
		for(Document d : docs){
			st2.save(d);
		} 
		
		pw.println("fetching docdatas<br>");
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
		
		pw.println("saving docdatas<br><br>");
		
		for(DocData d : ddatas){
			Map<String, Object> m2= new HashMap<>();
			m2.put("id", d.getId());
			m2.put("content", d.getContent());						
			npjt2.update("insert into docdata (id, content) values (:id, :content)", m2);
		}

		
		pw.println("END<br>");
		
		
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
