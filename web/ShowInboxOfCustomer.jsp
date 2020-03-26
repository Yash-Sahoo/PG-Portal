<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%
    session=request.getSession();
    String email=(String)session.getAttribute("userid");
    Connection con=inert.me.Util.connect();
    String sql="select * from messages where receipt=? order by msgid desc";
    PreparedStatement ps=con.prepareStatement(sql);
    ps.setString(1,email);
    ResultSet rs=ps.executeQuery();
    boolean b=(rs.next());
    if(b==false){
        out.println("<html>");
        out.println("<body>");
        out.println("<h2><center><font color=red><u><i>Paying Guest Portal</i></u></font></center></h2>");
        out.println("<br>");
        out.println("<h2><center>Inbox</center></h2>");
        out.println("<hr>");
        out.println("<a href='mailcomposebycust.jsp'>Compose-Mail</a><br>");
        out.println("<a href='MessageSentByCustomer.jsp'>Message-Sent</a>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<br>");
        out.println("<h3>No Messages Found</h3>");
        out.println("<br>");
        out.println("<hr>");
        out.println("<br>");
        out.println("<a href=customerDashboard.jsp>Customer-Dashboard</a><br>");
        out.println("</body>");
        out.println("</html>");
    }
    else{
            rs.beforeFirst();
            
%>
<html>
    <body>
        <h2><center><font size="6" face="bookman old style" color="green"><u><i>Paying Guest Portal</i></u></font></center></h2>
        <h3><font size="6" color=red>Inbox</font></h3>
        <hr>
        <a href="mailcomposebycust.jsp">Compose-Mail</a><br>
        <a href="MessageSentByCustomer.jsp">Message-Sent</a>
        <hr>
        <table border="2" cellspacing="2" cellpadding="2">
            <thead>
                <tr>
                    <th width="250">From</th>
                    <th width="550">Messages</th>
                    <th width="100">Property Code</th>
                    <th width="100">Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    while(rs.next()){
                        String from=rs.getString("sender");
                        String msg=rs.getString("message");
                        int pcode=rs.getInt("pcode");
                        java.sql.Date dt=rs.getDate("mdate");
                    
                %>
                <tr>
                    <td><%=from%></td>
                    <td><%=msg%></td>
                    <td><%=pcode%></td>
                    <td><%=dt%></td>
                    <td><a href='replyMsgByCustomer.jsp?id=<%=from%>&pcode=<%=pcode%>'>reply</td>
                    <td><a href='deleteMsgByCustomer?id=<%=rs.getInt("msgid")%>'>remove</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
            <hr>
            <a href="customerDashboard.jsp">Customer-Dashboard</a>
        
    </body>
</html>
<%
    }
%>
