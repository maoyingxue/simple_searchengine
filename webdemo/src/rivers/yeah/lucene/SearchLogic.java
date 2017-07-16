package rivers.yeah.lucene;
import java.io.File;        
import java.sql.Connection;     
import java.sql.ResultSet;     
import java.sql.Statement;     
import java.util.ArrayList;     
import java.util.List;     
import org.apache.lucene.analysis.Analyzer;     
import org.apache.lucene.document.Document;     
import org.apache.lucene.document.Field;     
import org.apache.lucene.document.Field.TermVector;     
import org.apache.lucene.index.IndexWriter;     
import org.apache.lucene.queryParser.QueryParser;  
import org.apache.lucene.search.*;  
import org.apache.lucene.store.Directory;     
import org.apache.lucene.store.FSDirectory;  
import org.apache.lucene.util.Version;  
import org.wltea.analyzer.lucene.IKAnalyzer;  
    
    
/**     
* SearchLogic.java   
* @version 1.0   
* @createTime Lucene数据库检索   
*/    
public class SearchLogic {     
    private static Connection conn = null;     
    private static Statement stmt = null;     
    private static  ResultSet rs = null;     
    private String searchDir = "d:\\index1";     
    private static File indexFile = null;     
    private static Searcher searcher = null;     
    private static Analyzer analyzer = null;     
    /** 索引页面缓冲 */    
    private int maxBufferedDocs = 500;     
    /**   
    * 获取数据库数据   
    * @return ResultSet   
    * @throws Exception   
    */    
    public List<SearchBean> getResult(String queryStr) throws Exception {     
        List<SearchBean> result = null;     
        conn = JDBCUtil.getConnection();     
        if(conn == null) {     
            throw new Exception("数据库连接失败！");     
        }     
        String sql = "select id,title, url,content,abstract1 from tb_content";     
        try {     
            stmt = conn.createStatement();     
            rs = stmt.executeQuery(sql);     
            this.createIndex(rs);   //给数据库创建索引,此处执行一次，不要每次运行都创建索引，以后数据有更新可以后台调用更新索引     
            TopDocs topDocs = this.search(queryStr);     
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;     
            result = this.addHits2List(scoreDocs);     
        } catch(Exception e) {     
            e.printStackTrace();     
            throw new Exception("数据库查询sql出错！ sql : " + sql);     
        } finally {     
            if(rs != null) rs.close();     
            if(stmt != null) stmt.close();     
            if(conn != null) conn.close();     
        }              
        return result;     
    }     
  
/**   
* 为数据库检索数据创建索引   
* @param rs   
* @throws Exception   
*/    

	private void createIndex(ResultSet rs) throws Exception {     
        Directory directory = null;     
        IndexWriter indexWriter = null;     
         
        try {     
            indexFile = new File(searchDir);     
            if(!indexFile.exists()) {     
                indexFile.mkdir();     
            }     
            directory = FSDirectory.open(indexFile);     
            analyzer = new IKAnalyzer();     
              
            indexWriter = new IndexWriter(directory, analyzer, true, IndexWriter.MaxFieldLength.UNLIMITED);     
            indexWriter.setMaxBufferedDocs(maxBufferedDocs);     
            Document doc = null;     
            while(rs.next()) {     
                doc = new Document();     
                Field id = new Field("id", String.valueOf(rs.getInt("id")), Field.Store.YES, Field.Index.NOT_ANALYZED, TermVector.NO);     
                Field title = new Field("title", rs.getString("title") == null ? "" : rs.getString("title"), Field.Store.YES,Field.Index.ANALYZED, TermVector.NO);   
                Field url = new Field("url", rs.getString("url") == null ? "" : rs.getString("url"), Field.Store.YES,Field.Index.ANALYZED, TermVector.NO);   
                Field content = new Field("content", rs.getString("content") == null ? "" : rs.getString("content"), Field.Store.YES,Field.Index.ANALYZED, TermVector.NO);
                Field abstract1 = new Field("abstract1", rs.getString("abstract1") == null ? "" : rs.getString("abstract1"), Field.Store.YES,Field.Index.ANALYZED, TermVector.NO);     
                doc.add(id); 
                doc.add(title);  
                doc.add(url); 
                doc.add(content);
                doc.add(abstract1);
                indexWriter.addDocument(doc);     
            }     
                         
            indexWriter.optimize();     
            indexWriter.close();     
        } catch(Exception e) {     
            e.printStackTrace();     
        }      
    }     
   
    /**   
    * 搜索索引   
    * @param queryStr   
    * @return   
    * @throws Exception   
    */    

	private TopDocs search(String queryStr) throws Exception {            
        if(searcher == null) {     
            indexFile = new File(searchDir);     
            searcher = new IndexSearcher(FSDirectory.open(indexFile));       
        }     
             
        QueryParser parser = new QueryParser(Version.LUCENE_30,"content",new IKAnalyzer()); 
        Query query = parser.parse(queryStr); 
          
        TopDocs topDocs = searcher.search(query, searcher.maxDoc());     
        return topDocs;     
    }  
      
    /**   
    * 返回结果并添加到List中   
    * @param scoreDocs   
    * @return   
    * @throws Exception   
    */    
    private List<SearchBean> addHits2List(ScoreDoc[] scoreDocs ) throws Exception {     
        List<SearchBean> listBean = new ArrayList<SearchBean>();     
        SearchBean bean = null;     
        for(int i=0 ; i<scoreDocs.length; i++) {     
            int docId = scoreDocs[i].doc;     
            Document doc = searcher.doc(docId);     
            bean = new SearchBean();     
            bean.setId(doc.get("id"));
            bean.setTitle(doc.get("title"));
            bean.setUrl(doc.get("url"));
            bean.setContent(doc.get("content"));
            bean.setAbstract1(doc.get("abstract1"));
            listBean.add(bean);     
        }     
        return listBean;     
    }  
      
    public static void main(String[] args) {     
        SearchLogic logic = new SearchLogic();     
        try {     
            Long startTime = System.currentTimeMillis();     
            List<SearchBean> result = logic.getResult("法国");     
            int i = 0; 
            //System.out.println("bean.url " + result.getId());
            for(SearchBean bean : result) {     
                if(i == 3)   
                    break;     
     //           System.out.println("bean.name " + bean.getClass().getName() + " : bean.id " + bean.getId()+ " : bean.url " + bean.getUrl());
                System.out.println("bean.url " + bean.getUrl());
                		System.out.println("bean.title"+bean.getTitle());
                		System.out.println("bean.content "+bean.getContent()); 
                		System.out.println("bean.abstract1"+bean.getAbstract1()); 
                i++;     
            }  
         //   System.out.println("bean.url " + ((SearchBean) result).getUrl());
              
            System.out.println("searchBean.result.size : " + result.size());     
            Long endTime = System.currentTimeMillis();     
            System.out.println("查询所花费的时间为：" + (endTime-startTime)/1000);     
        } catch (Exception e) {   
            e.printStackTrace();     
            System.out.println(e.getMessage());     
        }     
    }     
}   
