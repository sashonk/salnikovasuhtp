package salnikova.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import salnikova.model.DocData;
import salnikova.model.Document;
import salnikova.orm.SearchCriterion;
import salnikova.orm.SearchQuery;
import salnikova.orm.Storage;

public class DocDao {

	public Document createDoc(final String name, final byte[] content,
			final Integer controlId) {
		
		Document d = new Document();
		d.setControlId(controlId)	;
		d.setName(name);
		d.setSize((long)content.length);				
		d = m_storage.save(d);
		
		Map<String, Object> mm = new HashMap<>();
		mm.put("id", d.getId());
		mm.put("content", content);
		npjt.update("insert into docdata (id, content) values (:id, :content)", mm);
		
		return d;
	}
	
	
	public Document findDocument(final Integer controlId) {
		SearchQuery q = new SearchQuery();
		q.getCriterions().add(SearchCriterion.eq("controlId", controlId));
		
		List<Document> data = m_storage.search(Document.class, q);
		if(data.size()>0){
			return data.get(0);
		}
		
		return null;
	}

	public Document getDocument(final Integer id) {
		return m_storage.load(Document.class, id);
	}

	public DocData getDocData(final Integer id) {
		Map<String, Object> mm = new HashMap<>();
		mm.put("id", id);
		return npjt.queryForObject("select content from docdata where id = :id", mm, new RowMapper<DocData>(){

			@Override
			public DocData mapRow(ResultSet rs, int arg1) throws SQLException {
				DocData dd = new DocData();
				dd.setId(id);
				dd.setContent(rs.getBytes("content"));
				
				return dd;
			}
			
		});
	}



	private NamedParameterJdbcTemplate npjt;

	public void setNpjt(final NamedParameterJdbcTemplate value) {
		npjt = value;
	}

	public void setStorage(final Storage st) {
		m_storage = st;
	}

	private Storage m_storage;
}
