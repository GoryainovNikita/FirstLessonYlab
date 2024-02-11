<%--
  Created by IntelliJ IDEA.
  User: gorya
  Date: 19.05.2023
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserStorage</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
        $(document).ready(function (){
            $("#myButton").click(function (){
                $.ajax({
                    type: "GET",
                    url: "http://localhost:8080/testServlet/user",
                    success: function (msg){
                        var resultDiv = $("#result");
                        resultDiv.html("Result : " + JSON.stringify(msg));
                    }
                })
            });
            $("#myButton2").click(function (){
                var login=$("#user-name").val();
                var userPassword=$("#user-password").val();
                $.ajax({
                    url: "http://localhost:8080/testServlet/user",
                    type: "POST",
                    data: jQuery.param({userName : login, password : userPassword})
                })
            });
        });

    </script>
</head>
<body>
    <div class="input-field">
        <label for="user-name">Name</label>
        <input type="text" id="user-name">
    </div>

    <div class="input-field">
        <label for="user-password">Password</label>
        <input type="text" id="user-password">
    </div>
    </br>
    <button id="myButton2" type="button">AddUser</button>
    </br>
    </br>
    <div id="result"></div>

    <button id="myButton" type="button">GetUsers</button>
</body>
</html>
