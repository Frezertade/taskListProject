$(function () {

$('#saveUser').click(function (evt) {
    evt.preventDefault();
    const parametrs = getInputValues(["firstName","lastName","email","phone","location"]);
    $.ajax("user",{
      'type':"post",
      'data':parametrs
    }).done(displaySavedData);
});

$('#btnAddUser').click(function(evt) {
        evt.preventDefault();
        $('#creteUser').removeClass('not');
});
function getInputValues(parName) {
    let parameters={};
    $.each(parName, idx=>{
        parameters[parName[idx]]=$('input[name='+parName[idx]+']').val();
    });
    return parameters;
}

function displaySavedData(users){
    console.log(users);
}
});