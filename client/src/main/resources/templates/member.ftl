<!DOCTYPE html>
<head>
    <meta charset="utf-8"/>
    <title>member test</title>
</head>
<body>

    <form id="login">
        <p>login</p>
        <input type="text" name="username">
        <input type="text" name="password">
        <input type="button">
    </form>

    <form id="member">
        <p>get member info</p>
        <input type="button">
    </form>

    <p id="result"></p>

    <script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script>
    var token = "";

    $("#login input[type='button']").click(function() {
        $.ajax({
            url: "http://localhost:9000/login",
            type: "GET",
            data: {
                username: $("input[name='username']").val(),
                password: $("input[name='password']").val(),
                grant_type: "password",
                client_id: "foo",
                scope: "read"
            }
        }).done(function(data) {
            alert(data);
            token = data;
        });
    });

    $("#member input[type='button']").click(function() {
        $.ajax({
            url: "http://localhost:9000/member",
            type: "GET",
            data: {}
        }).done(function(data) {
            $("#result").html(data);
            token = data;
        });
    });
</script>
</body>