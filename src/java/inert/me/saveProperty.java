package inert.me;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class saveProperty extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session=request.getSession();
        String id=(String)session.getAttribute("userid");
        if(id==null){
            response.sendRedirect("index.jsp");
        }
        //reads the request
        PrintWriter out=response.getWriter();
        
        String email=(String)session.getAttribute("userid");
        
        String ptype="",ftype="",fdesc="",rent="",ctype="",
                hno="",street="",city="",state="",country="",ivrs="",board="";
        byte imgdata[]=null;
        
        DiskFileItemFactory factory=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(factory);
        try{
            List<FileItem> items=upload.parseRequest(new ServletRequestContext(request));
            for(FileItem item:items){
                String name=item.getFieldName();
                if(name.equals("ptype")){
                    ptype=item.getString();
                }else if(name.equals("ftype")){
                    ftype=item.getString();
                }else if(name.equals("fdesc")){
                    fdesc=item.getString();
                }else if(name.equals("rent")){
                    rent=item.getString();
                }else if(name.equals("ctype")){
                    ctype=item.getString();
                }else if(name.equals("hno")){
                    hno=item.getString();
                }else if(name.equals("street")){
                    street=item.getString();
                }else if(name.equals("city")){
                    city=item.getString();
                }else if(name.equals("state")){
                    state=item.getString();
                }else if(name.equals("country")){
                    country=item.getString();
                }else if(name.equals("pimg")){
                    imgdata=item.get();
                }else if(name.equals("ivrs")){
                    ivrs=item.getString();
                }else if(name.equals("board")){
                    board=item.getString();
                }
            }
        
            Connection con=Util.connect();
            String qr1="insert into property(email,ptype,ftype,fdesc,rent,ctype,hno,street,city,state,country,status,photo,ivrs,board) values(?,?,?,?,?,?,?,?,?,?,?,'vacant',?,?,?)";
            PreparedStatement ps=con.prepareStatement(qr1);
            ps.setString(1, email);
            ps.setString(2, ptype);
            ps.setString(3, ftype);
            ps.setString(4, fdesc);
            ps.setInt(5, Integer.parseInt(rent));
            ps.setString(6, ctype);
            ps.setString(7, hno);
            ps.setString(8, street);
            ps.setString(9, city);
            ps.setString(10, state);
            ps.setString(11, country);
            ps.setBytes(12,imgdata);
            ps.setString(13,ivrs);
            ps.setString(14,board);
            ps.executeUpdate();
            ps.close();
            con.close();
            
            out.println("<html>");
            out.println("<body>");
            out.println("<h1><center>Paying Guest Portal</center></h1>");
            out.println("<hr>");
            out.println("<h2>Property Submitted Successfully!!!</h2>");
            out.println("<hr>");
            out.println("<h2>Wait until admin verify your Property detail via IVRS...</h2>");
            out.println("<hr>");
            out.println("<a href=registerProperty.jsp>Submit-Another</a><br>");
            out.println("<a href=ownerDashboard.jsp>Owner-Home</a>");
            out.println("</body>");
            out.println("</html>");
            
            
            
        }catch(Exception e){
            out.println(e);
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
