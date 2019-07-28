function refreshLoop() {
    globalStatistics();
    setTimeout("refreshLoop();", 5000);
}

function hello() {
    $.get('/hello').done(function(data) {
//    $.get("stub/hello.json").done(function(data) {
                      statistics();
                    })
                    .fail(function(xhr, status, error) {
                $('#alert-message').html("Oops! Error at hello: " + JSON.stringify(xhr.responseText));
                      $('.alert').show();
                    });
}

function statistics() {
    $.getJSON('/statistics', {}, function(json) {
//    $.getJSON('stub/statistics.json', {}, function(json) {
        $('#hello-user').html("You played " + json.length + " rounds.");
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
                $('#alert-message').html("Oops! Error at statistics: " + JSON.stringify(xhr.responseText));
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
    globalStatistics();
}

function globalStatistics() {
    $.getJSON('/global-statistics', {}, function(json) {
//    $.getJSON('stub/global-statistics.json', {}, function(json) {
        $('#global-statistics').html("Total rounds played: " + json.roundsPlayed +
            ",<br/> wins for 1 players: " + json.winsForFirstPlayers +
            ",<br/> wins for 2 players: " + json.winsForSecondPlayers +
            ",<br/> draws: " + json.draws
            );
    })            .fail(function (xhr, status, error) {
                $('#alert-message').html("Oops! Error at global-statistics: status = " + status + ", message = " + error);
                      $('.alert').show();
                  });;
}



