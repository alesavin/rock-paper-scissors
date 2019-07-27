//function refreshLoop() {
//    refresh();
////    alert("232");
//    setTimeout("refreshLoop();", 30000);
//}

function hello() {
//    $.get('/hello').done(function(data) {
    $.get("stub/hello.json").done(function(data) {
                      $('#hello-user').html("Hello, " + data + "!");
                    })
                    .fail(function(xhr, status, error) {
                $('#alert-message').html("Oops! Error at hello: status = " + status + ", message = " + error);
                      $('.alert').show();
                    });
}

function statistics() {
//    $.getJSON('/statistics', {}, function(json) {
    $.getJSON('stub/statistics.json', {}, function(json) {
        $('#hello-user').html("Played " + json.length + " rounds.");
        $('#statistics').html('');
        for(var item in json) {
            $('#statistics').append("<tr><td>" +
                json[item].choicePlayer1 +
                "</td><td>" +
                json[item].choicePlayer2 +
                "</td><td>" +
                json[item].outcome +
                 "</td></tr>");
        }
    })            .fail(function (xhr, status, error) {
                $('#alert-message').html("Oops! Error at statistics: status = " + status + ", message = " + error);
                      $('.alert').show();
                  });;
}

function play() {
    $.ajax({
        method: "POST",
        url: "/play",
        data: JSON.stringify({"strategyIdPlayer1": "random", "strategyIdPlayer2": "static:ROCK"}),
        contentType:"application/json; charset=utf-8",
        dataType: 'json'
    }).done(
            function (data) {
                refresh();
            })
            .fail(function (xhr, status, error) {
                $('#alert-message').html("Oops! Error at play: " + JSON.stringify(xhr.responseText));
                $('.alert').show();
            });
}

function restart() {
        $.ajax({
            method: "DELETE",
            url: "/play"
        }).done(function(data) {
                        refresh();
                    })
                    .fail(function(xhr, status, error) {
                      $('#alert-message').html("Oops! Error at restart: " + JSON.stringify(xhr.responseText));
                      $('.alert').show();
                    });
}

function refresh() {
    statistics();
}


