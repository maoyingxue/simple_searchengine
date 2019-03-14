package rivers.yeah.service;

import java.util.List;  
import rivers.yeah.dao.DocumentDao;
import rivers.yeah.lucene.SearchBean;
import rivers.yeah.lucene.SearchLogic;
import rivers.yeah.lucene.TextFileSearcher;
import rivers.yeah.model.Document;

public class DocumentService {
	
	private static DocumentDao documentDao = new DocumentDao();
	private static TextFileSearcher searcher = new TextFileSearcher();
	private static SearchLogic search = new SearchLogic();
	public static List<Document> getAllDocuments() {
		return documentDao.getDocuments();
	}
	
	public static List<Document> getIndexDocuments(String key) throws Exception {
		List<Document> documents = null;
		documents = searcher.search(key);
		return documents;
	}
	public static List<SearchBean> getIndexDocument(String key) throws Exception {
		List<SearchBean> documents = null;
		documents = search.getResult(key);

		return documents;
		}
	
}
