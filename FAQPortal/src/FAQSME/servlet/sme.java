package FAQSME.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import FAQ.model.Category;
import FAQ.model.Query;
import FAQ.model.connectToDB;
import FAQ.model.subCategory;


@WebServlet("/sme")
public class sme extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public sme() {
       }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			String q = request.getParameter("q");
			String UID = request.getParameter("UID");
			connectToDB ctd = new connectToDB();
			Connection c = (Connection)ctd.getC();
			ArrayList<Category> categories = new ArrayList<Category>();
			ArrayList<subCategory> subCategories = new ArrayList<subCategory>();
			ArrayList<Query> smeQueries = new ArrayList<Query>();
			try {
				
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
		        PreparedStatement ps = c.prepareStatement("select * from query where assignedUID=?");
		        ps.setString(1, UID);
		       	rs = ps.executeQuery();
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
				smeQueries.add(tmpQuery);
						
		        }	
		       
		       	getServletContext().setAttribute("categories", categories);
				getServletContext().setAttribute("subCategories", subCategories);
				getServletContext().setAttribute("smeQueries", smeQueries);	
				getServletContext().setAttribute("smeUID", UID);	
				c.close();
			
				ctd.closeConnection(c);
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
				if(q.equals("home"))
					request.getRequestDispatcher("WEB-INF/SMEpages/smeHome.jsp").forward(request, response);
			
			
			
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
