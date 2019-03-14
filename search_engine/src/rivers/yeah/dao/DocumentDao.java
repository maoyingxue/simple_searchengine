package rivers.yeah.dao;

import java.sql.PreparedStatement;   
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rivers.yeah.model.Document;

public class DocumentDao extends BaseDao {

	public List<Document> getDocuments() {
		String sql = "SELECT id,url,title,content,abstract1 FROM tb_content";
		ResultSet rs = null;
		List<Document> documents = new ArrayList<Document>();
		PreparedStatement prep = getPrepStmt(sql, false);
		Document doc = null;
		
		try {
	
			prep.execute();
			rs = prep.getResultSet();
			if (rs != null) {
				while (rs.next()) {
					doc = new Document(
							rs.getString(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getString(4),
							rs.getString(5)
							);
					documents.add(doc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (documents.size() == 0) {
			return null;
		} else {
			return documents;
		}
	}
}
