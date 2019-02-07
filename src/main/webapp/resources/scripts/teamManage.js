
//Build the drop down table of teams by getting data from the Teams from back end
function fetchTeamData() {
    function displayTeamData(data) {
        $('#sel').empty();
        let each;
        each = $.each(data, function (index, value) {
            // APPEND OR INSERT DATA TO SELECT ELEMENT.
            $('#sel').append('<option value="' + value.teamId + '">' + value.name + '</option>')

        });
        return each;
    }

    $.ajax("teams", {
        "type": "get",
        dataType:"json"
    }).done(displayTeamData);

    $('#sel').change(function () {
       // add user to the selected team !
        $.ajax(
            "addUserTeam", {
                "type": "post",
                "data": {
                    "teamId":this.options[this.selectedIndex].value
                }
            }
        ).done($('#msg').text('You are aded to ' + this.options[this.selectedIndex].text));
    });
}

$(function() {

    $.ajax("teams", {
        "type": "get",
        dataType: "json"

    }).done(loadTeam);
    function loadTeam(teams) {
        fetchTeamData();
        const tableTeam = $('#tblTeam tbody');
        tableTeam.empty();
        $.each(teams, function (idx, team) {
            $("#teamRow").tmpl(team).appendTo(tableTeam);
        });
    }
    $("#creatTeam").click(function (){
        console.log("this is click");
        let teamName = $('input[name=teamName]').val();
        console.log('this is team name '+ teamName);
        $.ajax("createTeam", {
            "type": "post",
            "data": {
                "teamName": teamName,
            }
        }).done(loadTeam);
    });
});





