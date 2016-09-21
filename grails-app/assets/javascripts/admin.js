$('.ajaxCall').click(function(e) {
    e.preventDefault();

    var col = $(this).attr("tableAffectCol") ;
    var colName = $(this).attr("tableAffectColName") ;
    var clicked = $(this);
    var hidden =  $(this).parent().find("a:hidden");
    var msg =  $(this).attr("dialogMsg");
    var okBtn =  $(this).attr("okBtn");
    var cancelBtn =  $(this).attr("cancelBtn");
    var message2 = $( ".giveMessage" );


 if($(this).hasClass("confirmIt")){
//    var targetUrl = $(this).attr("href");               //Get link for further use
//    var msg2 =  $(this).attr("dialogMsg");

        $("#confirm-dialog").dialog({
            buttons : {
                Ok : function() {
            jqueryAjaxTableCall(clicked, col, colName, hidden );
                    $(this).dialog("close");
        },
                Cancel : function() {
            $(this).dialog("close");

        }
    }
}   );


$("#confirm-dialog").dialog("open");
     message2.html( msg ) ;
} else{

    jqueryAjaxTableCall(clicked, col, colName, hidden );

}

});


function jqueryAjaxTableCall(clicked, col, colName, hidden ){

    jQuery.ajax({
        url: clicked.attr("href"),
        type: "GET",
        dataType: 'json',
        //  dataType: 'jsonp',
        success: function(result) {

            //    var obj = jQuery.parseJSON( result);
            ///   alert(result) ;
            if(col=="all"){
                clicked.closest("tr").remove();
            }   else{
            clicked.closest("tr").find('td[colactive="'+ col +'"]').each(function (index) {
                if(result.data == "A"){
                    $(this).css('color', 'black');
                }else{
                    $(this).css('color', 'red');
                }

                $(this).text(result.data) ;


                hidden.show();
                clicked.hide();


            });

            //name column
                clicked.closest("tr").find('td[colactive="'+ colName +'"]').each(function (index) {
                    if(result.data == "A"){
                        $(this).css('color', 'black');
                    }else{
                        $(this).css('color', 'red');
                    }
                    var text = $(this).text();
                    $(this).text(text) ;


                    hidden.show();
                    clicked.hide();


                });

            }
        }


    });

    $('[data-toggle="dropdown"]').parent().removeClass('open') ;
    return false;

}
