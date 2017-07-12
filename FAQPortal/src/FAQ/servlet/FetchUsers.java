package FAQ.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import FAQ.model.*;
@WebServlet("/FetchUsers")
public class FetchUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FetchUsers() {
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
		String user = (String)request.getSession().getAttribute("user");
		if(user != null){
		ArrayList<SME> sme = new ArrayList<SME>();
		ArrayList<Admin> admin = new ArrayList<Admin>();
 		try {
 				connectToDB ctd = new connectToDB();
 				Connection c = (Connection)ctd.getC();
 				Statement stmt = c.createStatement();
		        ResultSet rs = stmt.executeQuery( "select * from user" );
				
				
				while(rs.next()){
					if(rs.getString("admin").equals("0")){
						SME tmpSME = new SME();
						tmpSME.setName(rs.getString("name"));
						tmpSME.setEmail(rs.getString("email"));
						tmpSME.setUID(rs.getString("UID"));
						tmpSME.setDepartment(rs.getString("department"));
						tmpSME.setPhoneNo(rs.getString("phoneNo"));
						tmpSME.setPassword(rs.getString("password"));
						sme.add(tmpSME);
					}
					else{
						Admin tmpSME = new Admin();
						tmpSME.setName(rs.getString("name"));
						tmpSME.setEmail(rs.getString("email"));
						tmpSME.setUID(rs.getString("UID"));
						tmpSME.setDepartment(rs.getString("department"));
						tmpSME.setPhoneNo(rs.getString("phoneNo"));
						tmpSME.setPassword(rs.getString("password"));
						admin.add(tmpSME);
					}
				}
				
				
				
				getServletContext().setAttribute("sme", sme);
				getServletContext().setAttribute("admin",admin);
				c.close();
				
				request.getRequestDispatcher("/WEB-INF/smelist.jsp").forward(
						request,response );
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
