package FAQ.servlet;
//select u.UID, c.CID, s.name, u.name, c.categoryName from user u,subcategory s, category c where u.UID=s.UID and s.CID=c.CID;
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
import FAQ.model.SME;
import FAQ.model.connectToDB;
import FAQ.model.subCategory;
@WebServlet("/UserForm")
public class UserForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
    public UserForm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		//Get Expert List
		
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
		}
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		if(user != null){
			String UID = request.getParameter("q");
			System.out.println(UID);
			ArrayList<SME> sme = (ArrayList<SME>)getServletContext().getAttribute("sme");
			for(SME s : sme){
				if(s.getUID().equals(UID)){
					SME tmpSME = new SME();
					tmpSME.setAdminFlag(s.getAdminFlag());
					tmpSME.setDepartment(s.getDepartment());
					tmpSME.setEmail(s.getEmail());
					tmpSME.setName(s.getName());
					tmpSME.setPassword(s.getPassword());
					tmpSME.setPhoneNo(s.getPhoneNo());
					tmpSME.setUID(UID);
					getServletContext().setAttribute("tmpSME", tmpSME);
				}
			}
			
			request.getRequestDispatcher("/WEB-INF/UserForm.jsp").forward(
					request,response );

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			String parameter = request.getParameter("q");
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
	            
	            if(parameter.equals("editUser")){
	            	String name = request.getParameter("name");
	            	String email = request.getParameter("email");
	            	String department= request.getParameter("department");
	            	String phoneNo = request.getParameter("phoneNo");
					int admin = Integer.parseInt(request.getParameter("admin"));
					int CID = Integer.parseInt(request.getParameter("assignCID"));
					String UID = request.getParameter("UID");
					String subcategory = request.getParameter("assignedSUB");
					System.out.println(subcategory);
					String deleteSub = "Not filled";
					if(request.getParameter("deleteSUB") != null){
						deleteSub = request.getParameter("deleteSUB");
					}
					int deleteCID = Integer.parseInt(request.getParameter("deleteCID"));
					//Same queries in different form will still be added to DB
					PreparedStatement ps;
					if(CID != 0){
			         ps = c.prepareStatement("update user SET name = ? ,email = ?,department = ?,phoneNo = ?,admin = ? where UID = ?");
			         ps.setString(1,name);
			         ps.setString(2, email);
			         ps.setString(3, department);
			         ps.setString(4,phoneNo);
			         ps.setInt(5,admin);
			         ps.setString(6,UID);
			         ps.executeUpdate();
			         ps = c.prepareStatement("UPDATE subcategory SET UID=? where CID=? and name=?"); 
			         ps.setString(1,UID);
			         ps.setInt(2,CID);
			         ps.setString(3,subcategory);
			         ps.executeUpdate();
			         
			         //Reassign queries to new SME
			         
			         ps = c.prepareStatement("select * from category where CID=?");
			         ps.setInt(1, CID);
			         rs = ps.executeQuery();
			         rs.next();
			         String category = rs.getString("categoryName");
			         
			         ps = c.prepareStatement("update query set assignedUID=? where subcategory=? and category=?");
			         ps.setString(1, UID);
			         ps.setString(2, subcategory);
			         ps.setString(3, category);
			         ps.executeUpdate();
					}
					
			         if(!deleteSub.equals("Not filled") || deleteCID !=0){
			        	 ps = c.prepareStatement("update subcategory set UID = ? where name = ? and CID = ?");
			        	 ps.setString(1, null);
			        	 ps.setString(2, deleteSub);
			        	 ps.setInt(3, deleteCID);
			        	 ps.executeUpdate();
			         }
			         
			         c.close();
			         
			         response.sendRedirect("admin?q=sme");
			         
				
	            	}
	            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
				
			}
		}
	}

}
