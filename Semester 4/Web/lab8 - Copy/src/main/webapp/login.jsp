<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Startup ML Puzzle</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='main.css'>
</head>
<body>
    <div id="login_form">
        <form action="LoginServlet" id="login_action">
            <input type="text" name="username" id="username" placeholder="Username"/>
            <input type="password" name="password" id="password" placeholder="Password"/>
            <input type="submit" value="Login"/>
        </form>
    </div>

    <script src='jquery.js'></script>
    <script src='jquery.cookie.js'></script>
    <script src='main.js'></script>

    <script>
        if($.cookie('username') != null && $.cookie('username') != "")
        {
            location.href = 'index.jsp';
        }
    </script>

</body>
</html>