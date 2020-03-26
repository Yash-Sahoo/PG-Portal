/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
public class sendMessagebyCust extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
    session=request.getSession();
    String email=(String)session.getAttribute("userid");
    try{
        
        int maxpcod=0;
        try{
            Connection con=inert.me.Util.connect();
            String sql="select MAX(pcode) from messages";
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            rs.next();
            maxpcod=rs.getInt(1);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        int pcode=Integer.parseInt(request.getParameter("pcode"));
        
        if(pcode<=maxpcod){
        
        Connection con=inert.me.Util.connect();
        String sql="insert into messages(sender,receipt,message,mdate,pcode) values(?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, email);
        String to=request.getParameter("to");
        String message=request.getParameter("msg");
        java.util.Date dt=new java.util.Date();
        java.sql.Date sqldate=new java.sql.Date(dt.getTime());
        
        
        ps.setString(2, to);
        ps.setString(3, message);
        ps.setDate(4, sqldate);
        ps.setInt(5, pcode);
        ps.executeUpdate();
        ps.close();
        con.close();
        out.println("<html>");
        out.println("<body>");
        out.println("<h2><center><font color=red><i>Paying Guest Portal</i></font></center></h2>");
        out.println("<hr><br>");
        out.println("<h3>Message Sent successfully!!!</h3>");
        out.println("<br><hr><br><a href=customerDashboard.jsp>Customer-Dashboard</a>");
        out.println("</body>");
        out.println("</html>");
        }else{
            out.println("<html>");
            out.println("<body>");
            out.println("<h2><center><font color=red><i>Paying Guest Portal</i></font></center></h2>");
            out.println("<br><hr><br>");
            out.println("<h3>Invalid Property Code!!!</h3>");
            out.println("<br><hr><br><a href=customerDashboard.jsp>Customer-Dashboard</a>");
            out.println("</body>");
            out.println("</html>");
        }
        }catch(Exception e){
            out.println("<html>");
            out.println("<body>");
            out.println("<h2><center><font color=red><i>Paying Guest Portal</i></font></center></h2>");
            out.println("<br><hr><br>");
            out.println("<h3>Invalid email! Not available!</h3>");
            out.println("<br><hr><br><a href=customerDashboard.jsp>Customer-Dashboard</a>");
            out.println("</body>");
            out.println("</html>");
            
            
        }
        
        
        
        
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
