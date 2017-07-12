package FAQ.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import FAQ.model.connectToDB;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Login() {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	doPost(req, resp);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass = new String();
		String userid = request.getParameter("userid");
		String userpass = request.getParameter("password");
		try{
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(userpass.getBytes(),0,userpass.length());
			pass = new BigInteger(1,m.digest()).toString(16);
		}catch(Exception e){	

		}
		connectToDB ctd = new connectToDB();
        Connection c = (Connection)ctd.getC();
		try {
				
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery( "select * from user where UID=\"" + userid + "\" and password=\"" + pass +"\"");
	        
	        
			//check username a password and if correct,redirect to Members
			if(rs.next()) {
				if(rs.getInt("admin")==1){
					request.getSession().setAttribute("user",userid);
					response.sendRedirect("admin?q=Home");
				}
				
				else if(rs.getInt("admin") == 0){
					request.getSession().setAttribute("user",userid);
					response.sendRedirect("sme?q=home&UID=" + userid);
				}
			}
			
			//otherwise redirect to  login form
			else{
				response.sendRedirect("Login.jsp");
			}
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		
	
		
	}

}
