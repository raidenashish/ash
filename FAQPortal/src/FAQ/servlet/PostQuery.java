package FAQ.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import FAQ.model.Mailer;
import FAQ.model.connectToDB;
import FAQ.model.subCategory;

@WebServlet("/PostQuery")
public class PostQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostQuery() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			connectToDB ctd = new connectToDB();
	        Connection c = (Connection)ctd.getC();
	        ArrayList<Category> categories = new ArrayList<Category>();
			ArrayList<subCategory> subCategories = new ArrayList<subCategory>();
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
	        
	        request.getRequestDispatcher("PostQuery.jsp").forward(request, response);;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getParameter("question");
		String email = request.getParameter("email");
		String subCategory;
		String category = null;
		int CID;
		
		
		if(request.getParameter("assignedSUB") == null){
			subCategory = null;
		}
		else{
			subCategory = request.getParameter("assignedSUB");
		}
		
			CID  = Integer.parseInt(request.getParameter("assignCID"));
		
		
		
		//Put into DB
		
		try {
				connectToDB ctd = new connectToDB();
				Connection c = (Connection)ctd.getC();
	            //Get category name
				if(CID != 0){
					PreparedStatement cat = c.prepareStatement("select categoryName from category where CID = ?");
					cat.setInt(1, CID);
					ResultSet rscat = cat.executeQuery();
					rscat.next();
					category = rscat.getString("categoryName");
				}	
				
	            PreparedStatement update = c.prepareStatement("insert into query(query,category,subCategory,email_user) values(?,?,?,?)");
	            
	            update.setString(1, query);
	            update.setString(2, category);
	            update.setString(3,subCategory);
	            update.setString(4, email);
	           	
	            
	            update.executeUpdate();			
	            System.out.println("HEREEWDASD");
	            if(subCategory != null && category != null){
	            	Mailer m = new Mailer();
	            	
	            	//Common Email to communicate with user (only gmail allowed)
	            	
	            	PreparedStatement ps = c.prepareStatement("select email from user u, subcategory sc, category c where sc.name = ? and c.CID=sc.CID and c.categoryName = ? and sc.UID = u.UID");
	            	ps.setString(1, subCategory);
	            	ps.setString(2, category);
	            	ResultSet rs = ps.executeQuery();
	            	if(rs.next()) {
		            	String to = rs.getString("email");
		            	String sub = "Unanswered Query";
		            	String msg = query;
		            	m.send(m.getQueryEmail(), m.getQueryEmailPassword(), to, sub, msg);
		            	
		            	//Assign to SME
		            	ps = c.prepareStatement("select * from subcategory where CID=? and name=?");
		            	ps.setInt(1, CID);
		            	ps.setString(2, subCategory);   	
		            	rs = ps.executeQuery();
		            	rs.next();
		            	String assignedUID = rs.getString("UID");
		            	if(assignedUID != null) {
		            	ps = c.prepareStatement("update query set assignedUID=? where query=?");
		            	ps.setString(1, assignedUID);
		            	ps.setString(2, query);
		            	ps.executeUpdate();
	            	}
	            	}
	            }
	            
	            
	            c.close();
	           
	            request.getRequestDispatcher("Home.jsp").forward(
	            		request,response );
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			}
	}

}
