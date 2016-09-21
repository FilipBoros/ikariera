$('.getContactDetail').click(function(e) {
    e.preventDefault();

    var userId = $(this).attr("userId") ;
    var advertId = $(this).attr("advertId") ;

    var toChange = $(this).parent() ;//.find(".contact-detail");

    var data =  {
        userId: userId,
        advertId:advertId
    };

    jQuery.ajax({
        url: $(this).attr("href"),
        type: "POST",
        data: data,
        dataType: 'json',

        success: function(result) {

            toChange.text(result.data) ;


        }


    });


});


$('.getContactDetailNotLogged').click(function(e) {
    e.preventDefault();

    $( "#jquery_dialog_register").dialog({
        height: 180,
        width: 450,
        buttons: {
            Ok: function() {
                $(this).dialog("close");
            }
        },
        title: $( "#jquery_dialog_register").attr("dialogTitle"),
        resizable: false
    });


});