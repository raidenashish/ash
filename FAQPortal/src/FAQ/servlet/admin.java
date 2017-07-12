package FAQ.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.Category;
import FAQ.model.Query;
import FAQ.model.connectToDB;
import FAQ.model.subCategory;

@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
		String url = request.getParameter("q");
		
		
		
		
		// Admin to have Query Power
		if(url.equals("Home")){
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
				 
				request.getRequestDispatcher("/WEB-INF/adminHome.jsp").forward(request,response );
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// For admin to have category power
		else if(url.equals("category")){
			ArrayList<Category> categories = new ArrayList<Category>();
			ArrayList<subCategory> subCategories = new ArrayList<subCategory>();
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
		          getServletContext().setAttribute("categories", categories);
		          getServletContext().setAttribute("subCategories", subCategories);
		          c.close();
		           
		          request.getRequestDispatcher("/WEB-INF/category.jsp").forward(
		        		  request,response );
			} catch (SQLException e) {
				 e.printStackTrace();
				}
	}
		if(url.equals("sme")){
			
			request.getRequestDispatcher("/WEB-INF/smelist.jsp").forward(
					request,response );
		}

		if(url.equals("category")){
			request.getRequestDispatcher("/WEB-INF/category.jsp").forward(
					request,response );
		}
		else{
			request.getRequestDispatcher("/WEB-INF/adminHome.jsp").forward(
					request,response );
			}
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
