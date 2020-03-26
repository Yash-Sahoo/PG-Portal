package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyUser extends HttpServlet {

    Connection con;
    ResultSet rs;
    PreparedStatement ps;
    public void init(){
        try {
            con=inert.me.Util.connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void destroy(){
        try{
            con.close();
            ps.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {

        //reads the request
        String email=request.getParameter("email");
        String utype=request.getParameter("utype");
        String password=request.getParameter("password");
        String save=request.getParameter("save");
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        try {
            //process the request
           
            ServletConfig config=getServletConfig();
            String admin_email=config.getInitParameter("admin-email");
            String admin_password=config.getInitParameter("admin-password");
            
            
            if(utype.equals("admin")){
                if(email.equals(admin_email) && password.equals(admin_password)){
                    if(save!=null){
                        Cookie c1=new Cookie("id",email);
                        Cookie c2=new Cookie("pwd",password);
                        Cookie c3=new Cookie("utype",utype);
                        c1.setMaxAge(60*60*24*7);
                        c2.setMaxAge(60*60*24*7);
                        c3.setMaxAge(60*60*24*7);
                        response.addCookie(c1);
                        response.addCookie(c2);
                        response.addCookie(c3);
                    }
                    session.setAttribute("userid", email);
                        response.sendRedirect("adminDashboard.jsp");      
                }else{
                    out.println("Invalid User ID or Password for Admin!!!");
                }
            }else{
                String sq1="select * from users where email=? and utype=? and password=? and status='activated'";
                ps=con.prepareStatement(sq1);
                ps.setString(1, email);
                ps.setString(2, utype);
                ps.setString(3, password);
                rs=ps.executeQuery();
                boolean found=rs.next();
                if(found){
                    Cookie c1=new Cookie("id",email);
                        Cookie c2=new Cookie("pwd",password);
                        Cookie c3=new Cookie("utype",utype);
                        c1.setMaxAge(60*60*24*7);
                        c2.setMaxAge(60*60*24*7);
                        c3.setMaxAge(60*60*24*7);
                        response.addCookie(c1);
                        response.addCookie(c2);
                        response.addCookie(c3);
                    session.setMaxInactiveInterval(60*60*24);
                    session.setAttribute("userid", email);
                    session.setAttribute("username", rs.getString("name"));
                    
                    if(utype.equals("owner")){
                        response.sendRedirect("ownerDashboard.jsp");
                    }
                    else{
                        response.sendRedirect("customerDashboard.jsp");
                    }
                }else{
                    
                    out.println("<html><body><center><h2>Paying Guest Portal</h2></center><hr><h3>No such Credentials found or yet to be verified by Admin!!!</h3>");
                    out.println("<h4>Try again !!!</h4>");
                    out.println("<br><hr>");
                    out.println("<a href=index.jsp>Home</a>");
                    out.println("</body></html>");
                }
            }
            
        } catch (Exception e) {
            out.println(e);
        }
        //provide the response


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerifyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VerifyUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
