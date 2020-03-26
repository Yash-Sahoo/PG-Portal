<%
    session=request.getSession();
    
%>
<html>
    <script>
        function checkAll(){
            var b1=checkemail();
            var b2=checkpassword();
            var b3=checkname();
            var b4=hno();
            var b5=street();
            var b6=mobileNo();
            return (b1 && b2 && b3 && b4 && b5 && b6);
        }
        function checkemail(){
            var email=document.getElementById("mail").value;
            if(email.length!==0){
                return true;
            }else{
                alert("Enter email id");
                return false;
            }
        }
        function checkpassword(){
            var pass=document.getElementById("pw").value;
            if(pass.length>=3){
                return true;
            }else{
                alert("password must be atleast 3 characters");
                return false;
            }
        }
        function checkname(){
            var nam=document.getElementById("nam").value;
            if(nam.length===0){
                alert("Enter Your Name");
                return false;
            }else{
                return true;
            }
        }
        function hno(){
            var hn=document.getElementById("hn").value;
            if(hn.length===0){
                alert("Enter Your House Number!");
                return false;
            }else{
                return true;
            }
        }
        function street(){
            var str=document.getElementById("street").value;
            if(str.length===0){
                alert("Enter Your Street Details!");
                return false;
            }else{
                return true;
            }
        }
        function mobileNo(){
            var mob=document.getElementById("mno").value;
            if(isNaN(mob)){
                alert("Mobile Number must be Numeric Value");
                return false;
            }else{
                if(mob.length<10){
                    alert("Please enter a valid Mobile Number");
                    return false;
                }else{
                    return true;
                }
            }
        }
    </script>
    <body  style="background: url(regiimage.jpg); background-size: 100% 100%;">
    <center><h3><font face="Lucida Handwriting" color="red"><i><u>Paying Guest Portal</u></i></font></h3></center>
    <hr>
    <br>
    <font align="right">
        <h3>&emsp;&emsp;&emsp;&emsp;<font face="bookman old style" color="Orange">Registration Form</font></h3>
    <form action="SaveUser" method="post" onsubmit="return checkAll()">
        <pre>
           <font face="bookman old style" color="yellow"> Email ID </font> <input type="email" id="mail" name="email"/><br>
           <font face="bookman old style" color="yellow"> Password </font><input type="password" id="pw" name="password"/><br>
           <font face="bookman old style" color="yellow"> Name     </font> <input type="text" id="nam" name="name"/><br>
           <font face="bookman old style" color="yellow"> User Type </font> <select name="utype">
                                                                            <option>customer</option>
                                                                            <option>owner</option>
                                                                            </select><br>
           <font face="bookman old style" color="yellow"> H.No.    </font> <input type="text" id="hn" name="hno"/><br>
           <font face="bookman old style" color="yellow"> Street   </font> <input type="text" id="street" name="street"/><br>
           <font face="bookman old style" color="yellow"> City     </font> <select name="city">
                                                                                <option>indore</option>
                                                                                <option>bhopal</option>
                                                                                <option>mumbai</option>
                                                                                <option>vadodara</option>
                                                                                <option>dehradun</option>
                                                                            </select>

           <font face="bookman old style" color="yellow"> State    </font> <select name="state">
                                                                            <option>M.P.</option>
                                                                            <option>U.P.</option>
                                                                            <option>M.H.</option>
                                                                            <option>H.P.</option>
                                                                            <option>Gujarat</option>
                                                                           </select><br>

           <font face="bookman old style" color="yellow"> Country  </font> <select name="country">
                                                                            <option>India</option>
                                                                            </select><br>
           <font face="bookman old style" color="yellow"> Mobile No </font> <input type="text" id="mno" name="mobile"/><br>
            
                <input type="submit" value="Register"/>
        </pre>
    </form>
        </font>
    <hr>
    <center>
    <a href="index.jsp"><font face="bookman old style" color="white">Home</font></a>
    </center>
    </body>
</html>
