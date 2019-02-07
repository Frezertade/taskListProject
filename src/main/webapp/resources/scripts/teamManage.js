//
$(document).ready(function () {
    fetchTeamData();
});

//Build the drop down table of teams by getting data from the Teams from back end
function fetchTeamData() {
    function displayUser(data) {
        let each;
        each = $.each(data, function (index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#sel').append('<option value="' + value.teamId + '">' + value.name + '</option>')

        });
        return each;
    }

    $.ajax("http://localhost:8080/task/teams", {
        "type": "get",
        dataType:"json"
    }).done(displayUser);

    $('#sel').change(function () {
       // add user to the selected team !
        $.ajax(
            "http://localhost:8080/task/addUserTeam", {
                "type": "post",
                "data": {
                    "teamId":this.options[this.selectedIndex].value
                }
            }
        // ).done($('#msg').text('You are aded to ' + this.options[this.selectedIndex].text));
        ).done($('#msg').text('You are aded to ' + this.options[this.selectedIndex].text));
    });
}

$(function() {
    $("#CreatTeam").click(updateGuests);
});

function updateGuests() {
    let teamName = $("#teamName").val();
    $.ajax("creatTeam", {
        "type": "post",
        "data": {
            "teamName": teamName,
        }
    }).done(tasksController.loadServerTasks(data));
}

