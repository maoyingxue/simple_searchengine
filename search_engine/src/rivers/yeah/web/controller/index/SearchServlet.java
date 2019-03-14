package rivers.yeah.web.controller.index;

import java.io.IOException;   
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rivers.yeah.lucene.SearchBean;
import rivers.yeah.model.Document;
import rivers.yeah.service.DocumentService;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4226423610878682962L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//◊¢“‚…Ë÷√±‡¬Î
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Charset","UTF-8");
		
		String key = req.getParameter("key");
		List<SearchBean> results = null;
		//List<Document> results = null;
	//try {
	//results = DocumentService.getIndexDocuments(key);
	//} catch (Exception e) {
		// TODO Auto-generated catch block
	//	e.printStackTrace();
	//}
		//results = DocumentService.getAllDocuments();
  try {
			results = DocumentService.getIndexDocument(key);
	} catch (Exception e) {
			// TODO Auto-generated catch block
		e.printStackTrace();
		}
		req.setAttribute("model",results);
		req.getRequestDispatcher("result.jsp").forward(req, resp);
	}
	public static void main(String[] args) {

		System.out.println(DocumentService.getAllDocuments());
	}
}
