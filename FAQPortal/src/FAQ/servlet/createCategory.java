package FAQ.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.connectToDB;

@WebServlet("/createCategory")
public class createCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public createCategory() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			String q = request.getParameter("q");
			String option = request.getParameter("option");
			String catName = request.getParameter("category");
			try {
				connectToDB ctd = new connectToDB();
		        Connection c = (Connection)ctd.getC();
				if(option.equals("delete")){
					PreparedStatement delete = c.prepareStatement("delete from category where CID = ?");
					delete.setString(1, q);
					delete.executeUpdate();
            
					delete = c.prepareStatement("delete from subcategory where CID = ?");
					delete.setString(1,q);
					delete.executeUpdate();
            
					delete = c.prepareStatement("update query set subCategory = ?,category =? where category = ?");
					delete.setString(1, null);
					delete.setString(2, null);
					delete.setString(3, catName);
					delete.executeUpdate();
					
					response.sendRedirect("admin?q=category");
					
				}
				
				c.close();
				 
			}catch (SQLException e) {
					e.printStackTrace();
					System.out.println(e);
				}
		}
			
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newCatName = request.getParameter("catName");
		if(!newCatName.trim().isEmpty()){
			try {
				connectToDB ctd = new connectToDB();
		        Connection c = (Connection)ctd.getC();
				PreparedStatement add = c.prepareStatement("insert into category(categoryName) values(?)");
				add.setString(1, newCatName);
				add.executeUpdate();
				c.close();
				 
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		response.sendRedirect("admin?q=category");
	}

}
