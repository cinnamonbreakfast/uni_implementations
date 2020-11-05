// $('#login_action').submit(function(e){
//
// })

function login(event) {
    event.preventDefault();

    $.ajax({
        method: "POST",
        url: $(this).attr('action'),
        data: {
            username: $('#username').val(),
            mother: $('#mother').val(),
            father: $('#father').val()
        },
        success : function(data) {
            if(data == "login_bad") {
                alert("Wrong data");
            } else {
                location.href = 'index.jsp';
            }
        }
    })
}