/**
 *   Copyright 2012 Senman s.r.o.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */



$("span.favorite").click( function () {

  //  e.preventDefault();
    var adv = "";
    var exp = 36; /* +/-1 month */
    var id = $(this).attr("AdvertisementID");
    var user = $(".user-login-state").attr("userWatchDogId")



    if(user!= null && user != "") {

        var watchDogString = "ikarieraWatchDog_" + user;

        var myCookie = $.cookie(watchDogString);


    if (myCookie == null || myCookie == "") {
        adv = id;
        $.cookie(watchDogString, adv, { expires: exp, path: '/' });
        favoriteAdvertisement($(this), "");

    } else {
        var myString = new String(myCookie);
        var myArray = myString.split('_');
        adv = "";
        if (myArray.indexOf(id) == -1) {
            myArray.push(id);
            adv = myArray.join('_');
            $.cookie(watchDogString, adv, { expires: exp, path: '/' });
            favoriteAdvertisement($(this), "");
        } else {
            var pos = myArray.indexOf(id);

            if (myArray.length - 1 == pos) {
                myArray.pop();
            }
            else {
                myArray[pos] = myArray.pop();
            }


            if (myArray.length > 0) {

                adv = myArray.join('_');
                $.cookie(watchDogString, adv, { expires: exp, path: '/' });
                deleteFavoriteAdvertisement($(this), "");
            }
            else {
                $.cookie(watchDogString, null, { expires: exp, path: '/' });
                deleteFavoriteAdvertisement($(this), "");

            }


        }


    }



    refreashTotal($(".watchDogTotalNumber") );
    } else {



       $( "#jquery_dialog_student").dialog({
                height: 180,
                width: 450,
                buttons: {
                    Ok: function() { // textový popisek tlačítka bude "ok"
                        $(this).dialog("close");
                    }
                },
                title: $( "#jquery_dialog_student").attr("dialogTitle"),
                resizable: false
        });

    }


});





    function favoriteAdvertisement(select, effect) {


        select.removeClass("silver").addClass("blue") ;

    //    incTotalNumber(1)

       // select.parent().addClass("delete");
       // select.html("<span>Odebrat sledování</span>")
        //   select.removeClass("favourieAdvIcon");
    }

    function deleteFavoriteAdvertisement(select, effect) {
        select.removeClass("blue").addClass("silver") ;

       // incTotalNumber(-1)
    }


function incTotalNumber (incBy){
    var total = $("#watchDogTotalNumber").val();
    if(total =="")
        total = 0;

    total += incBy;
    $("#watchDogTotalNumber").val(total);


}


function refreashTotal(selector){


    var user = $(".user-login-state").attr("userWatchDogId")

    if(user!= null && user != ""){

        var watchDogString = "ikarieraWatchDog_" + user;
        var ikarieraWatchDogTotal  = "ikarieraWatchDogTotal_" + user;

    var myCookie = $.cookie(watchDogString);
    var total = 0;
    var exp = 36; /* 1 month */
    if (!(myCookie == "" || myCookie == null)) {
        var myString = new String(myCookie);
        var myArray = myString.split('_');
        total =myArray.length

    }

    $.cookie(ikarieraWatchDogTotal, total, { expires: exp, path: '/' });

    selector.text(total);

    }

}



$(".watchDogTotalNumber").each(function () {
    refreashTotal(   $(this)  )  ;
});




$("span.favorite").each(function () {
    var user = $(".user-login-state").attr("userWatchDogId")

    if(user!= null && user != ""){

        var watchDogString = "ikarieraWatchDog_" + user;


    var id = $(this).attr("AdvertisementID");

    var myCookie = $.cookie(watchDogString);

    if (!(myCookie == "" || myCookie == null)) {



        var myString = new String(myCookie);
        var myArray = myString.split('_');

        if (myArray.indexOf(id) > -1)
            favoriteAdvertisement($(this), "");

    }






    }
});














