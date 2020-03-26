<%
    String v1="",v2="",v3="";
    Cookie ck[]=request.getCookies();
    if(ck!=null){
    for(Cookie c:ck){
        String s=c.getName();
        if(s.equals("id")){
            v1=c.getValue();
        }else if(s.equals("pwd")){
            v2=c.getValue();
        }else if(s.equals("utype")){
            v3=c.getValue();
        }
    }
    }
%>
<html>
    <script>
        function checkAll(){
            var a1=checkemail();
            var a2=checkpassword();
            return a1 && a2;
        }
        function checkpassword(){
            var var2=document.getElementById("t2").value;
            if(var2.length>=3){
                return true;
            }else{
                alert("password must contain atleast 3 characters");
                return false;
            }
        }
        function checkemail(){
            var var1=document.getElementById("t1").value;
            if(var1.length===0){
                alert("Email cannot be blank");
                return false;
            }else{
                return true;
            }
        }
    </script>
    <style>
        h4 {background-color: white; width:200px; height: 25px;}
        
    </style>
    <body style="background: url(home-sweet-home.jpg); background-size: 100% 100%;">
    <center><h2><font face="algerian" color="red"><u>Paying Guest Portal</u></font></h2></center>
    
    <hr>
    
    <center> <font color="white"><b> <h3>&emsp;Login Form </h3></b></font>
    <form action="VerifyUser" method="post" onsubmit="return checkAll()">
        
        <pre>
        <font color="white">
    Email ID  <input placeholder="Email" type="email" id="t1" name="email" value="<%=v1%>"/><br>
    Password  <input placeholder="Password" type="password" id="t2" name="password" value="<%=v2%>"/><br>
  User Type <select name="utype" value="<%=v3%>">
              <option>customer</option>
              <option>owner</option>
              <option>admin</option>
            </select>
            </font><font color="red"><b>
               <h4>Save Password <input type="checkbox" name="save" value="yes"/></h4><br>
           </b></font>
                <input type="submit" value="Login"/>&emsp;&emsp;&emsp;&emsp;<a href=""><b><font color=white >Forget-Password</font></b></a>
        </pre>
                     
        </form>  </center>  
    <hr>
    <nav>
        <center><a href="registration.jsp" title="Sign-up"><b><font color="white">New-Registration</font></b></a></center>
    </nav>
    <hr>
    <center><h3><a href=home.jsp><font color=white>Home</font></a></h3></center>
  <!--  <img src="IMG_20171025_215813634[265].jpg" align="right" title="PG-Portal-Developer-Yash Kumar Sahoo" height="110" width="150"></img> -->
    </body>
    <marquee behavior="alternate" direction="up"><h3><font face="bookman old style" color="yellow"><i><h3><strong>Welcome to paying guest portal.Search homes here...</strong></h3></i></font></marquee>
    
</html>
