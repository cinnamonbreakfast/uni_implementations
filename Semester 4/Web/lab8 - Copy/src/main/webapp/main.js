var selectedPiece = null;
var moves = 9;
var matrix = [];

function newGame()
{
    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/web/NewGame',
        data: {
            username: $.cookie('username'),
        },
        success : function(data) { loadGame(); } });
}

function loadGame()
{
    $('.target_slot').html("");
    $.ajax({
        method: "POST",
        url: 'http://localhost:8080/web/GameSave',
        data: {
            username: $.cookie('username'),
        },
        success : function(data) {
            console.log(data);

            if(data == "Not logged in")
            {
                location.href = 'login.jsp';
                return;
            }

            if(data == "end")
            {
                alert("No more moves left. Start a new game.");
                return;
            }

            var dataObj = JSON.parse(data);
            let placed = [];
            console.log(data);
            jQuery.each(dataObj, function(i, val) {
                if(i == "moves")
                {
                    $('#left_moves').html("Moves: "+val);
                } else
                if(val != 0)
                {
                    $('#'+i).html('<div class="piece piece_'+val+'"></div>');
                    placed.push(val);
                }
            });
            let i = 1;
            console.log(placed);
            $('#free_pieces').html('');
            for(i = 1; i < 10; i++)
            {
                if(!placed.includes(i))
                {
                    $('#free_pieces').append('<div class="piece piece_'+i+'"></div>');
                }
            }

            $('.pieces .piece').css({'background-image':'url("./img/1.jpg")'});
            $('.piece').click(function(){
                pieceClick($(this));
            });
        }
    });
}

function piece_id(piece)
{
    console.log(piece.slice(12));
    return parseInt(piece.slice(12));
}

function getTableMatrix()
{
    var slots = []
    $('.target_slot').each(function(){
        if($(this).find('.piece').length > 0)
        {
            slots.push(piece_id($($(this).find('.piece')).attr('class')))
        } else {
            slots.push(0);
        }
    })

    console.log(slots);
}

function decrementMoves()
{
    moves--;

    $('#left_moves').html("moves: "+moves);

    if(moves == 0)
    {
        // check for win
    }
    if(moves < 0)
    {
        alert("No more moves allowed");
        return false;
    }
    getTableMatrix();
}

function pieceClick(piece) {
    if(moves <= 0)
    {
        alert("No more moves allowed");
        return false;
    }

    if(selectedPiece == null)
    {
        if(piece.find(".active").length > 0)
        {
            $(".piece").html("");
            piece.html("");
            selectedPiece = null;
        } else {
            selectedPiece = piece;
            $(".piece").html("");
            piece.append('<div class="active"></div>');
        }
    } else {
        if(piece.attr('class') == $(selectedPiece).attr('class'))
        {
            $(".piece").html("");
            piece.html("");
            selectedPiece = null;

            return false;
        }
        return false;
    }

    console.log("==============PIECE");
}

$(document).ready(function()
{
    if($.cookie('username') != null && $.cookie('username') != '')
    {
        loadGame();
    }

    $('#new_game').click(function () {
        newGame();
    });

    $('#left_moves').html("moves: "+moves);

    $('#temp').click(function(){
        getTableMatrix();
    })

    $('.piece').click(function(){
        pieceClick($(this));
    });

    $('.target_slot').click(function(){
        console.log($(this));

        // if(moves <= 0)
        // {
        //     alert("No more moves allowed");
        //     return false;
        // }
        //
        // if(selectedPiece == null)
        // {
        //     if($(this).find('.piece').length > 0)
        //     {
        //         return false;
        //     } else {
        //         alert("No piece selected");
        //     }
        // } else {
        //     if($(this).find('.piece').length > 0)
        //     {
        //         if(selectedPiece.attr('class') == $(this).find('.piece').attr('class'))
        //         {
        //             return false;
        //         } else {
        //             if(selectedPiece.parent().hasClass('target_slot'))
        //             {
        //                 $('#temp').append($(this).find('.piece'));
        //                 parentOfSelected = selectedPiece.parent();
        //                 $(this).append(selectedPiece);
        //                 selectedPiece.html("");
        //                 selectedPiece = null;
        //                 parentOfSelected.append($('#temp').find('.piece'));
        //                 decrementMoves()
        //             } else {
        //                 $('#free_pieces').append($(this).find('.piece')[0]);
        //                 $(this).append(selectedPiece);
        //                 selectedPiece.html("");
        //                 selectedPiece = null;
        //                 decrementMoves()
        //             }
        //         }
        //
        //     } else {
        //         $(this).append(selectedPiece);
        //         decrementMoves()
        //         selectedPiece.html("");
        //         selectedPiece = null;
        //     }
        // }

        if(selectedPiece == null)
        {
            alert("No piece selected");
            return false;
        } else {
            if($(selectedPiece).parent().attr('id') == $(this).attr('id'))
            {
                return;
            }
            $.ajax({
                method: "POST",
                url: 'http://localhost:8080/web/MovePiece',
                data: {
                    username: $.cookie('username'),
                    piece: piece_id(selectedPiece.attr('class')),
                    target: $(this).attr('id'),
                    parent: selectedPiece.parent().attr('id'),
                },
                success : function(data) {
                    if(data == "end")
                    {
                        alert("No more moves left. Start a new game.");
                        return;
                    }

                    loadGame();

                    $('.pieces .piece').css({'background-image':'url("./img/1.jpg")'});
                    $('.piece').click(function(){
                        pieceClick($(this));
                    });
                }
            });
            selectedPiece = null;
        }

        console.log("==============TARGET");
    });

    $('#login_action').submit(function(e){
        event.preventDefault();

        $.ajax({
            method: "POST",
            url: $(this).attr('action'),
            data: {
                username: $('#username').val(),
                password: $('#password').val()
            },
            success : function(data) {
                if(data == "login_bad") {
                    alert("Wrong user or pass");
                } else {
                    location.href = 'index.jsp';
                }
            }
        })
    })
});