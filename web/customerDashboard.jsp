<%
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body style="background: url(forcustdas.jpg); background-size: 100% 100%;">
        <h2><center><font face="bookman old style" color="red"><b><i><u>Paying Guest Portal</u></i></b></font></center></h2>
        <hr><font face="algerian" color="blue"><h3>Welcome <%=name%></h3></font>
        <hr>
        
        <a href="ShowAllPropertyForCustomer"><font face="bookman old style" color="iron">View-All-Property</font></a><br>
        <a href="SearchForProperty.jsp"><font face="bookman old style" color="iron">Search-For-Property</font></a><br>
        <a href="ProfileUpdateForCust.jsp"><font face="bookman old style" color="iron">Update-Profile</font></a><br>
        <a href="ShowInboxOfCustomer.jsp"><font face="bookman old style" color="iron">Inbox</font></a><br>
        <a href="KillSession"><font face="bookman old style" color="iron">Logout</font></a>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><hr>
    <marquee behavior="alternate" bgcolor="black" align="bottom"><font color=white face="bookman old style">Note:- <%=name%>, you can complaint about any misbehave occurrence in this portal to mail- admin@123gmail.com </font></marquee>
    <hr>
    </body>
</html>
