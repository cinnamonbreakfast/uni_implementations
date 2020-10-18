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

    <div id="puzzle_row">
        <div class="pieces" id="free_pieces">
            <div class="piece piece_1"></div>
            <div class="piece piece_2"></div>
            <div class="piece piece_3"></div>
            <div class="piece piece_4"></div>
            <div class="piece piece_5"></div>
            <div class="piece piece_6"></div>
            <div class="piece piece_7"></div>
            <div class="piece piece_8"></div>
            <div class="piece piece_9"></div>
        </div>
    </div>
    
    <div id="board">
        <table id="table" class="pieces target">
            <tr>
                <td class="target_slot" id="cell_1"></td>
                <td class="target_slot" id="cell_2"></td>
                <td class="target_slot" id="cell_3"></td>
            </tr>
            <tr>
                <td class="target_slot" id="cell_4"></td>
                <td class="target_slot" id="cell_5"></td>
                <td class="target_slot" id="cell_6"></td>
            </tr>
            <tr>
                <td class="target_slot" id="cell_7"></td>
                <td class="target_slot" id="cell_8"></td>
                <td class="target_slot" id="cell_9"></td>
            </tr>
        </table>

        <div id="left_moves">Left: </div>
        <div id="new_game">New Game</div>
        <div id="logout" onClick="location.href='/web/Logout'">Logout</div>
    </div>

    <div id="temp">

    </div>


    <script src='jquery.js'></script>
    <script src='jquery.cookie.js'></script>
    <script src='main.js'></script>

    <script>
        if($.cookie('username') == null || $.cookie('username') == '')
        {
            location.href = 'login.jsp';
        }
    </script>
</body>
</html>