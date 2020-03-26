<%
    session=request.getSession();
    String name=(String)session.getAttribute("username");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <body style="background: url(ownerdash.jpg); background-size: 100% 100%;">
        <h2><center><font face="bookman old style" color="red"><b><i><u>Paying Guest Portal</u></i></b></font></center></h2>
        <hr>
        <h2><font face="algerian" color="iron">Welcome <%=name%></font></h2>
        <hr>
        
        <a href="registerProperty.jsp">Register-Property</a><br>
        <a href="ViewOwnProperty">View-Own-Property</a></br>
        <a href="ViewOwnPropertyforRemove">Remove-Property</a></br>
        <a href="ViewOwnPropertyforStatus">Change-Property-Details</a></br>        
        <a href="ProfileUpdate.jsp">Update-Profile</a></br>
        <a href="ShowInboxOfOwner.jsp">Inbox</a></br>
        <a href="KillSession">Logout</a>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><hr>
        <marquee behavior="scroll" scrollamount="9" bgcolor="black" align="bottom"><font color=white face="bookman old style">Note:- <%=name%>, For the verification of your registered property details, you must upload the corresponding IVRS number of electricity bill along with Board region. You can also complain to admin by admin@gmail.com if any indicipline occurs in this portal.</font></marquee>
        <hr>
    </body>
    
</html>
