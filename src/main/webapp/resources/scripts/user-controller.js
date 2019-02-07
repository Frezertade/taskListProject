$(function () {

    $('#saveUser').click(function (evt) {
        evt.preventDefault();
        const parametrs = getInputValues(["firstName", "lastName", "email", "phone", "location"]);
        $.ajax("user", {
            'type': "post",
            'data': parametrs
        }).done(displaySavedData);
        clearTask();
    });

    $('#btnAddUser').click(function (evt) {
        evt.preventDefault();
        $('#creteUser').removeClass('not');
        $('#taskCreation').addClass('not');
        $('#ManageTeam').addClass('not');
    });

    function getInputValues(parName) {
        let parameters = {};
        $.each(parName, idx => {
            parameters[parName[idx]] = $('input[name=' + parName[idx] + ']').val();
        });
        return parameters;
    }

    function displaySavedData(users) {
        viewUser(users)
    }

    function viewUser(users) {
        const tableUser = $('#tblUser tbody');
        tableUser.empty();
        $.each(users, function (idx, user) {
            $("#userRow").tmpl(user).appendTo(tableUser);
        });
    }

    function loadUser() {
        $.ajax("user", {
            "type": "get",
            dataType: "json"
        }).done(viewUser);
    }

    loadUser();


    function clearTask() {
        $(taskPage).find('#userForm').fromObject({});
    }

    $('#clearUser').click(clearTask);
});