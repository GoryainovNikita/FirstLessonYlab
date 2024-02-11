
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script>
        $(document).ready(function (){
            $("#myButton2").click(function (){
                var firstName = $("#user-first-name").val();
                var secondName = $("#user-second-name").val();
                var login=$("#user-login").val();
                var password=$("#user-password").val();
                $.ajax({
                    url: "http://localhost:8080/ylab/registration",
                    type: "POST",
                    data: jQuery.param({firstName : firstName, secondName : secondName, login : login, password : password})
                })
            });
        });

    </script>
</head>
<body>

<div class="input-field">
    <label for="user-first-name">Имя</label>
    <input type="text" id="user-first-name">
</div>
<div class="input-field">
    <label for="user-second-name">Фамилия</label>
    <input type="text" id="user-second-name">
</div>
<div class="input-field">
    <label for="user-login">Логин</label>
    <input type="text" id="user-login">
</div>
<div class="input-field">
    <label for="user-password">Пароль</label>
    <input type="text" id="user-password">
</div>
</br>
<button id="myButton2" type="button">Зарегистрироваться</button>
</br>

</body>
</html>
