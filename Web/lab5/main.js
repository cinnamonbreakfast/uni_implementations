var table = document.querySelector('#table');
var row1 = document.querySelector('#table tr:first-child')
var row2 = document.querySelector('#table tr:nth-child(1)')
var row3 = document.querySelector('#table tr:last-child')

var matrix = [];
for(var i=0; i<3; i++) {
    matrix[i] = new Array(3);
}

var free_cells = 9;

var cells = document.querySelectorAll('#table td');

var players_turn = true;

for(const cell of cells){
    cell.addEventListener('click', () => {
        if(players_turn == false)
        {
            alert("Not your turn!");
        }
        else 
        {
            if(cell.classList.contains('tac'))
            {
                alert("Already placed here!");
            }
            else if(cell.classList.contains('tic'))
            {
                alert("Cannot place over opponent.");
            }
            else {
                cell.classList.add('tac');
                cell.innerHTML = 'X';
                players_turn = false;
                free_cells --;
                placeInMatrix(cell);
                if(verifyWinHuman()) {
                    alert("You won!");
                    return true;
                }
                computerPlace();
            }
        }
    })
}

function verifyWinHuman()
{
    if(matrix[0][0] == 1 && matrix[0][1] == 1 && matrix[0][2] == 1) return true;
    if(matrix[1][0] == 1 && matrix[1][1] == 1 && matrix[1][2] == 1) return true;
    if(matrix[2][0] == 1 && matrix[2][1] == 1 && matrix[2][2] == 1) return true;
    if(matrix[0][0] == 1 && matrix[1][0] == 1 && matrix[2][0] == 1) return true;
    if(matrix[0][1] == 1 && matrix[1][1] == 1 && matrix[2][1] == 1) return true;
    if(matrix[0][2] == 1 && matrix[1][2] == 1 && matrix[2][2] == 1) return true;
    if(matrix[0][0] == 1 && matrix[1][1] == 1 && matrix[2][2] == 1) return true;
    if(matrix[0][2] == 1 && matrix[1][1] == 1 && matrix[2][0] == 1) return true;
}


function verifyWinPC()
{
    if(matrix[0][0] == 2 && matrix[0][1] == 2 && matrix[0][2] == 2) return true;
    if(matrix[1][0] == 2 && matrix[1][1] == 2 && matrix[1][2] == 2) return true;
    if(matrix[2][0] == 2 && matrix[2][1] == 2 && matrix[2][2] == 2) return true;
    if(matrix[0][0] == 2 && matrix[1][0] == 2 && matrix[2][0] == 2) return true;
    if(matrix[0][1] == 2 && matrix[1][1] == 2 && matrix[2][1] == 2) return true;
    if(matrix[0][2] == 2 && matrix[1][2] == 2 && matrix[2][2] == 2) return true;
    if(matrix[0][0] == 2 && matrix[1][1] == 2 && matrix[2][2] == 2) return true;
    if(matrix[0][2] == 2 && matrix[1][1] == 2 && matrix[2][0] == 2) return true;
}

function placeInMatrix(elem)
{
    if(elem.classList.contains("c0_0"))
        matrix[0][0] = 1;
    else if(elem.classList.contains("c0_1"))
        matrix[0][1] = 1;
    else if(elem.classList.contains("c0_2"))
        matrix[0][2] = 1;
    else if(elem.classList.contains("c1_0"))
        matrix[1][0] = 1;
    else if(elem.classList.contains("c1_1"))
        matrix[1][1] = 1;
    else if(elem.classList.contains("c1_2"))
        matrix[1][2] = 1;
    else if(elem.classList.contains("c2_0"))
        matrix[2][0] = 1;
    else if(elem.classList.contains("c2_1"))
        matrix[2][1] = 1;
    else if(elem.classList.contains("c2_2"))
        matrix[2][2] = 1;
    else
        alert("Something's messed up.");
}

function computerPlace()
{
    if(free_cells == 0)
    {
        alert("Round drawn!");
        return false;
    }
    x = Math.floor(Math.random() * 3) + 1;
    y = Math.floor(Math.random() * 3) + 1;

    console.log(x, y);

    query1 = '#table tr:nth-child('+x.toString()+')';
    query2 = 'td:nth-child('+y.toString()+')';

    row = document.querySelector(query1);
    cell = row.querySelector(query2);

    if(cell.classList.contains('tic') || cell.classList.contains('tac'))
    {
        computerPlace();
    }
    else {
        cell.classList.add('tic');
        cell.innerHTML = 'O';
        players_turn = true;
        matrix[x-1][y-1] = 2;
        free_cells--;
        if(verifyWinPC())
        {
            alert("you lost!");
            players_turn = false;
        }
    }
}

function game_status()
{
    // 0 - ok, 1 - win, 2 - lose
    for(const row in matrix)
    {
        s = 0;
        last = 0;
        for(const cell in row)
        {

        }
    }
}