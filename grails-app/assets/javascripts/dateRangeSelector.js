$(function() {
    $( "#dateFrom" ).datepicker({

        changeMonth: true,
        changeYear: true,
        maxDate: +1,
        firstDay: 1,
        dateFormat: 'dd.mm.yy',
        showOn: "button",
        buttonImage: "../images/calendar.png",
        buttonImageOnly: true,

        onSelect: function( selectedDate ) {
            $( "#dateTo" ).datepicker( "option", "minDate", selectedDate);
        }
    });
    $( "#dateTo" ).datepicker({
        changeMonth: true,
        changeYear: true,
        maxDate: +1,
        firstDay: 1,
        dateFormat: 'dd.mm.yy',
        showOn: "button",
        buttonImage: "../images/calendar.png",
        buttonImageOnly: true,
        onSelect: function( selectedDate ) {
            $( "#dateFrom" ).datepicker( "option", "maxDate", selectedDate);
        }
    });
});