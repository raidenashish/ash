package FAQ.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.*;


@WebServlet(urlPatterns={"/Fetch"},loadOnStartup=1)
public class Fetch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Category> formCategory = new ArrayList<Category>();
	private ArrayList<subCategory> formSubCategory= new ArrayList<subCategory>();
  
    public Fetch() {
        super();
       
    }
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
		
 		
		
		
	}
 

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<subCategory> subCategories = new ArrayList<subCategory>();
		ArrayList<Query> queries = new ArrayList<Query>();
		try {
					connectToDB ctd = new connectToDB();
	          		Connection c = (Connection)ctd.getC();
				
					Statement stmt = c.createStatement();
		            ResultSet rs = stmt.executeQuery( "select * from category" );
				
				while(rs.next()){
					Category tmpCat = new Category();
					tmpCat.setCatName(rs.getString("categoryName"));
					tmpCat.setCID(rs.getString("CID"));
					categories.add(tmpCat);
				}
				stmt = c.createStatement();
	            rs = stmt.executeQuery( "select * from subcategory" );
	        	while(rs.next()){
					subCategory tmpSCat = new subCategory();
					tmpSCat.setSubCatName(rs.getString("name"));
					tmpSCat.setCID(rs.getString("CID"));
					tmpSCat.setUID(rs.getString("UID"));
					subCategories.add(tmpSCat);
					
				}
	        	stmt = c.createStatement();
	        	rs = stmt.executeQuery("select * from query");
	        	while(rs.next()){
					Query tmpQuery = new Query();
					tmpQuery.setQuery(rs.getString("query"));
					tmpQuery.setQID(rs.getString("QID"));
					tmpQuery.setAnswer(rs.getString("answer"));
					tmpQuery.setAssignedUID(rs.getString("assignedUID"));
					tmpQuery.setPublishFlag(rs.getInt("publishFlag"));
					tmpQuery.setEmailUser(rs.getString("email_user"));
					for(Category cat : categories){
						if(cat.getCatName().equals(rs.getString("category")))
							tmpQuery.setCategory(cat);
					}
					for(subCategory subcat : subCategories){
						if(subcat.getSubCatName().equals(rs.getString("subCategory")))
							tmpQuery.setSubCategory(subcat);					
					}
					queries.add(tmpQuery);
					
	        	}	
			
				
				
				getServletContext().setAttribute("categories", categories);
				getServletContext().setAttribute("subCategories", subCategories);
				getServletContext().setAttribute("queries", queries);
		
				c.close();
				
				formCategory = categories;
				formSubCategory = subCategories;
				request.getRequestDispatcher("FAQ.jsp").forward(
							request,response );
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
			
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("WEB-INF/answerForm.jsp").forward(
				req,resp);
	}


	
}
