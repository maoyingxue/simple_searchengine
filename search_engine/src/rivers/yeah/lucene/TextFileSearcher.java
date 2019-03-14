package rivers.yeah.lucene;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import rivers.yeah.model.Document;

public class TextFileSearcher {

	
	public List<Document> search(String key) throws Exception {
		
		 final String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		 final String DBURL="jdbc:sqlserver://localhost:1433;dataBaseName=library;IntegratedSecurity=True";
		 final String DBUSER="sa";
		 final String DBPASS="";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet result=null;
		List<Document> documents = new ArrayList<Document>();
		String keyWord=key;
		String sql="SELECT id,url,title,content,abstract1 FROM tb_content WHERE title LIKE ? OR content LIKE ?";
		Class.forName(DBDRIVER);
		conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		pstmt=conn.prepareStatement(sql);
		Document doc = null;
		try {
	
			pstmt.setString(1,"%"+keyWord+"%");
			pstmt.setString(2,"%"+keyWord+"%");
			result=pstmt.executeQuery();
			if (result != null) {
				while (result.next()) {
					doc = new Document(
							result.getString(1), 
							result.getString(2), 
							result.getString(3), 
							result.getString(4),
							result.getString(5)
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
