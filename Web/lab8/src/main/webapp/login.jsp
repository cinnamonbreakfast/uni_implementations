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
        <form action="LoginServlet" id="login_action" submit="login(event)">
            <input type="text" name="username" id="username" placeholder="User"/>
            <input type="text" name="mother" id="mother" placeholder="mother"/>
            <input type="text" name="father" id="father" placeholder="father"/>
            <input type="submit" value="Login"/>
        </form>
    </div>

    <script src='jquery.js'></script>
    <script src='jquery.cookie.js'></script>
    <script src='main.js'></script>

</body>
</html>