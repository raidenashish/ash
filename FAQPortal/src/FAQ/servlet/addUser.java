package FAQ.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.Category;
import FAQ.model.connectToDB;
import FAQ.model.subCategory;

import java.security.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.math.*;
@WebServlet("/addUser")
public class addUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	public addUser() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			if(request.getParameter("q").equals("add")){
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
			          request.getRequestDispatcher("WEB-INF/addUser.jsp").forward(request, response);
				} catch (SQLException e) {
					 e.printStackTrace();
					}
				
			}
			if(request.getParameter("option").equals("delete")){
				String UID = request.getParameter("q");
				try{
					connectToDB ctd = new connectToDB();
			        Connection c = (Connection)ctd.getC();
					PreparedStatement delete = c.prepareStatement("delete from user where UID = ?");
					delete.setString(1, UID);
					delete.executeUpdate();
					
					delete = c.prepareStatement("update subcategory set UID = ? where UID= ?");
					delete.setString(1, null);
					delete.setString(2, UID);
					delete.executeUpdate();
					response.sendRedirect("FetchUsers");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			if(request.getParameter("q").equals("addUser")){
				String name = request.getParameter("name");
				String UID = request.getParameter("UID");
				String email = request.getParameter("email");
				String department = request.getParameter("department");
				String phoneNo = request.getParameter("phoneNo");
				String pass = request.getParameter("password");
				String CID = request.getParameter("assignCID");
				String subCatName = request.getParameter("assignedSUB");
				
				int admin = Integer.parseInt(request.getParameter("admin"));
				try{
					MessageDigest m = MessageDigest.getInstance("MD5");
					m.update(pass.getBytes(),0,pass.length());
					pass = new BigInteger(1,m.digest()).toString(16);
				}catch(Exception e){	
	
				}
				try {
					connectToDB ctd = new connectToDB();
			        Connection c = (Connection)ctd.getC();
					PreparedStatement add = c.prepareStatement("insert into user(UID,name,email,admin,department,phoneNo,password) values(?,?,?,?,?,?,?)");
					add.setString(1, UID);
					add.setString(2, name);
					add.setString(3, email);
					add.setInt(4, admin);
					add.setString(5, department);
					add.setString(6, phoneNo);
					add.setString(7, pass);
					add.executeUpdate();
					PreparedStatement update = c.prepareStatement("UPDATE subcategory SET UID = ? where CID = ? and name = ?");
					System.out.println(UID + CID + subCatName);
					update.setString(1, UID);
	            	update.setString(2, CID);
	            	update.setString(3, subCatName);
	            	update.executeUpdate();		
					response.sendRedirect("FetchUsers");
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}

}
