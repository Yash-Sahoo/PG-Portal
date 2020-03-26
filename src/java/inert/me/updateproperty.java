package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateproperty extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        //reads the request
        PrintWriter out=response.getWriter();
        
        String ftype=request.getParameter("ftyp");
        String fdesc=request.getParameter("fdes");
        String rent=request.getParameter("ren");
        String ctype=request.getParameter("ctyp");
        String hno=request.getParameter("hnoo");
        String street=request.getParameter("stree");
        String city=request.getParameter("cityy");
        String state=request.getParameter("statee");
        String country=request.getParameter("countr");
        String pcode=request.getParameter("pcode");
        try{
        
            Connection con=Util.connect();
            String qr1="update property set ftype=?,fdesc=?,rent=?,ctype=?,hno=?,street=?,city=?,state=?,country=? where pcode=?";
            PreparedStatement ps=con.prepareStatement(qr1);
            
            
            ps.setString(1, ftype);
            ps.setString(2, fdesc);
            ps.setString(3, rent);
            ps.setString(4, ctype);
            ps.setString(5, hno);
            ps.setString(6, street);
            ps.setString(7, city);
            ps.setString(8, state);
            ps.setString(9, country);
            ps.setInt(10, Integer.parseInt(pcode));
            
            ps.executeUpdate();
            ps.close();
            con.close();
            
            response.sendRedirect("ViewOwnPropertyforStatus");
            
        }catch(Exception e){
            e.printStackTrace();
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
