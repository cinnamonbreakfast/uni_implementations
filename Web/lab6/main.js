
var down = false;

$(document).ready(function()
{

    $('#board').on('mousedown', function(e)
    {
        $(this).find('.close').on('click', function()
        {
            $(this).parent().parent().hide();
        })
        
        var offs = $(this).offset();
        x = e.pageX - offs.left;
        y = e.pageY - offs.top;

        down = true; // ------------------------------

        $(document).on('mouseup', closeDragElement);
        
        $(document).on(
            'mousemove',
            (e) => (
                elementDrag(
                    e,
                    $(this),
                    x,
                    y))
        )
    })
})

function elementDrag(e, elem, x, y) {
    // console.log(elem, x, y);
    if(down)
    {
        elem.css('top', e.pageY - y);
        elem.css('left', e.pageX - x);

        //console.log(e.pageX, e.pageY);
    }

    return;
}

function closeDragElement() {
    down = false; // ------------------------------
    $(document).on('mouseup', null);
    $(document).on('mousemove', null);
}