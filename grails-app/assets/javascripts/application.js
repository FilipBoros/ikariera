
(function ($) {
    $(".datepicker").datepicker({ dateFormat: 'yy-mm-dd' });

    $("table.selectable-row tbody tr").click(function () {
        window.document.location = $(this).attr("link");
    });


    $(".ikariera-show").click(function () {

        var a = $(this).attr("data-ikariera-show");


        $("." + a).show();

    });



})(jQuery);


$(document).ready(function () {

    $(".chosen-select").chosen();
    $(".automaticAjaxLoad").each(function () {


        var url = $(this).attr("ajaxLoadingUrl");

        if (url != "") {
            $(this).load(url);
        }
    });
});


$(document).ready(function(){
    $(".doc1").hide(0);
    $(".hide1").click(function(){
        if($(".doc1").is(":visible")){
            $(".doc1").hide(1000);
        }
        if($(".doc1").is(":hidden")){
            $(".doc1").show(1000);
        }
    });

});









/*
 showHotPositionForm($("#hotPosition"));


 function showHotPositionForm(selector) {
 if (selector.is(":checked")) {

 $("#hotPositionHidden").show();

 } else {


 $("#hotPositionHidden").hide();

 }
 }
 */

