<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    session=request.getSession();
    String email=(String)session.getAttribute("userid");
    try{
    Connection con=inert.me.Util.connect();
    String sql="select * from messages where sender=? order by msgid desc";
    PreparedStatement ps=con.prepareStatement(sql);
    ps.setString(1,email);
    ResultSet rs=ps.executeQuery();
    boolean b=(rs.next());
    if(b==false){
        out.println("<html>");
        out.println("<body>");
        out.println("<h2><center><font color=red><u><i>Paying Guest Portal</i></u></font></center></h2>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<br>");
        out.println("<h3>No Messages Found</h3>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<br>");
        out.println("<a href=ownerDashboard.jsp>Owner-Dashboard</a><br>");
        out.println("</body>");
        out.println("</html>");
    }
    else{
            rs.beforeFirst();
            
%>
<html>
    <body>
        <h2><center><font face="bookman old style" color="green"><u><i>Paying Guest Portal</i></u></font></center></h2>
        <h3><font color=red>Inbox</font></h3>
        <hr>
        <a href="mailCompose.jsp">Compose-Mail</a><br>
        <a href="ShowInboxOfOwner.jsp">Inbox</a>
        <hr>
        <table border="2" cellspacing="2" cellpadding="2">
            <thead>
                <tr>
                    <th>To</th>
                    <th>Messages</th>
                    <th>Property Code</th>
                    <th>Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    while(rs.next()){
                        String too=rs.getString("receipt");
                        String msg=rs.getString("message");
                        int pcode=rs.getInt("pcode");
                        java.sql.Date dt=rs.getDate("mdate");
                    
                %>
                <tr>
                    <td><%=too%></td>
                    <td><%=msg%></td>
                    <td><%=pcode%></td>
                    <td><%=dt%></td>
                    <td><a href='deleteMsgByOwner1?id=<%=rs.getInt("msgid")%>'>remove</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            <hr>
            <a href="ownerDashboard.jsp">Owner-Dashboard</a>
        
    </body>
</html>
<%
        }
    }catch(Exception e){
        out.println(e);
    }
%>
