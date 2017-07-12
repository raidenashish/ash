package FAQ.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import java.sql.*;

import FAQ.model.connectToDB;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			request.getRequestDispatcher("/WEB-INF/changePassword.jsp").forward(request,response );
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
			String UID = request.getParameter("username");
			String password = request.getParameter("password");
			String updatedPassword = null;
			
			try{
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.update(password.getBytes(),0,password.length());
				updatedPassword = new BigInteger(1,m.digest()).toString(16);
				
				connectToDB ctd = new connectToDB();
				Connection c = (Connection) ctd.getC();
				
				PreparedStatement ps = c.prepareStatement("update user set password=? where UID=?");
				ps.setString(1, updatedPassword);
				ps.setString(2, UID);
				ps.executeUpdate();
				
				c.close();
				
				response.sendRedirect("admin?q=Home");
			}
			catch(Exception e){	
				e.printStackTrace();
			}
			
			
			
		}
	}

}
