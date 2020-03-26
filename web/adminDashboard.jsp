
<%
    session=request.getSession();
    String name=(String)session.getAttribute("userid");
    if(name==null){
        response.sendRedirect("index.jsp");
    }
%>

<html>
    <script>
        function checkf1(){
            var v1=document.getElementById("textf1").value;
            if(v1.length===0){
                alert("Please enter email of Customer to Search");
                return false;
            }else{
                return true;
            }
        }
        function checkf2(){
            var v2=document.getElementById("textf2").value;
            if(v2.length===0){
                alert("Please enter name of Customer to Search");
                return false;
            }else{
                return true;
            }
        }
        function checkf3(){
            var v3=document.getElementById("textf3").value;
            if(v3.length===0){
                alert("Please enter email of Owner to Search");
                return false;
            }else{
                return true;
            }
        }
        function checkf4(){
            var v4=document.getElementById("textf4").value;
            if(v4.length===0){
                alert("Please enter name of Owner to Search");
                return false;
            }else{
                return true;
            }
        }
    </script>
    <body style="background: url(sweet-home-weddings-night.jpg); background-size: 100% 100%;">
        <h2><center><font face="bookman old style" color="red"><u><i>Paying Guest Portal</i></u></font></center></h2>
        <h3><font face=algerian color="green"><center>Welcome Admin</center></font></h3>
        <hr>
    <center>
        <font face="bookman old style" color="yellow"><strong>
            &emsp;&emsp;&emsp; <font color=red>Search-Customer</font> <br>&emsp;&emsp;&emsp;<form action="searchCustomerbyEmail" method="post" onsubmit="return checkf1()"> By-Email<input type="email" name="emailofcustomer" id="textf1"/><input type="submit" value="Search"/></form>&emsp;or&emsp;<form action="searchCustomerbyName" method="post" onsubmit="return checkf2()">By-Name <input type="text" name="nameofcustomer" id="textf2"/><input type="submit" value="Search"/></form><br><br>
        <hr></strong></font><font face="bookman old style" color="iron"><strong>
            <font color=iron style="background-color: black; color: deeppink;">Search------Owner</font> <br>&emsp;&emsp;&emsp;<form action="searchOwnerbyEmail" method="post" onsubmit="return checkf3()"> <font style="background-color: black; color: greenyellow;">By-Email</font><input type="email" name="emailofowner" id="textf3"/><input type="submit" value="Search"/></form>&emsp;<font style="background-color: black; color: greenyellow;">or</font>&emsp;<form action="searchOwnerbyName" method="post" onsubmit="return checkf4()"><font style="background-color: black; color: greenyellow;">By-Name</font> <input type="text" name="nameofowner" id="textf4"/><input type="submit" value="Search"/></form><br><br>
        <hr>
        </strong></font>
        <a href="BlockCustomer"><font face="algerian" color="yellow">Block-Customers</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;
        <a href="BlockOwner"><font face="algerian" color="yellow">Block-Owners</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;
        <a href="BlockProperty"><font face="algerian" color="yellow">Block-Property</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;<br><hr><br>
        <a href="ShowUnactivatedOwners"><font face="bookman old style" color="white">View-Unactivated-Owners</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;
        <a href="ShowUnactivatedCustomers"><font face="bookman old style" color="white">View-Unactivated-Customers</font></a><br><hr><br>
        <a href="ShowActivatedOwners"><font face="bookman old style" color="red">View-Activated-Owners</font></a>&emsp;&emsp;&emsp;&emsp;&emsp;
        <a href="ShowActivatedCustomers"><font face="bookman old style" color="red">View-Activated-Customers</font></a><br><hr><br>
        <a href="ShowAllPropertyUnverified"><font face="Lucida Handwriting" color="white">Unverified-Property</font></a>&emsp;&emsp;&emsp;
        <a href="ShowAllProperty"><font face="Lucida Handwriting" color="white">Verified-Property</font></a><br><hr><br>
        <a href="KillSession"><font face="Lucida Handwriting" color="blue">Logout</font></a>
    </center>
        <hr>
    </body>
</html>
