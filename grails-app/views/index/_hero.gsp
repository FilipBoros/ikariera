<style type="text/css">
ul.nav {
    list-style: none;
    position: relative;
    top: 210px;
    z-index: 15;
}

ul.nav li.orbit-prev {
    float: left;
    margin: 0 0 0 40px;
}

ul.nav liorbit-next {
    float: right;
    margin: 0 50px 0 0;
}

ul.nav li a {
    display: block;
    width: 80px;
    height: 80px;
    text-indent: -9999px;
}

img.orbit-prev {
    height: 80px;
    margin-top: -22px;
    position: absolute;
    top: 50%;
    width: 80px;
    z-index: 100;
}

img.orbit-next {
    height: 80px;
    margin-top: -22px;
    position: absolute;
    top: 50%;
    width: 80px;
    right: 0px;
    z-index: 100;
}

div#slideshow ul#slides {
    list-style: none;
}

div#slideshow ul#slides li {
    margin: 0 0 20px 0;
}

    /* rotator in-page placement */
div.senman_rotator {
    position: relative;
    height: 100px; /* TODO change it to relatives */
    width: 100px;
    display: block;
    overflow: hidden;
    padding: 10px 0;

}

div.senman_rotator ul {
    margin: 0px;
    padding: 0px;
}

div.senman_rotator ul li {
    float: left;
    position: absolute;
    list-style: none;
    display: none;

}

div.senman_rotator ul li img {
    border-radius: 7px;

    /*background: #FFF;*/
}

div.senman_rotator ul li.show {
    /*z-index:500;*/
}

@media screen and (max-width: 999px) {

    div.senman_rotator {
        display: none;
    }
}

.senman_rotator .dark-stripe .caption {

    color: white;
    font-size: 16px;
    line-height: 16px;

    margin: 15px;

}

.senman_rotator .dark-stripe {
    height: 50px;
    left: 0;
    bottom: 0px;
    position: absolute;
    width: 100%;
}

    /* hero code */

.hero {
    height: 500px;
    /* box-shadow: 0 1px 1px white; */
    z-index: 1;
    position: relative;
    display: block;
    background-color: #000;

}

.top-menu .top-bar {

    margin-bottom: 0px;

}

.search-area h1, .search-area h2, .search-area h3, .search-area h4 {
    color: #FFFFFF;
    line-height: 1.0;
}

.hero h1, .hero h2, .hero a {
    text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.6);
}

.blob-bg {
    background: url("https://a0.muscache.com/airbnb/static/landing_pages/home_v2/homepage-sprite-54617e9d9dbd7f5e375a5667bb7a393f.png") no-repeat scroll 0 0 transparent;
    display: none;
    height: 180px;
    left: 50%;
    margin-left: -510px;
    margin-top: -25px;
    position: absolute;
    width: 600px;
    z-index: -1;
}

.slideshow li {
    background: none repeat scroll 0 0 #000000;
    height: 100%;
    overflow: hidden;
    position: absolute;
    width: 100%;
    z-index: 2;
}

.slideshow {
    margin: 0;
    list-style: none outside none;
    padding-left: 0;
}

.slideshow li > img {
    left: 50%;
    margin-left: -800px;
    position: relative;
    display: block;
    height: 700px;
    width: 1600px;
    max-width: none;
    vertical-align: middle;
    border: 0 none;
    z-index: 3;
}

.search-area {
    height: 300px;
    padding-top: 160px;
    position: absolute;
    top: 0;
    width: 100%;
    z-index: 90;
}

.search-area form {
    background: none repeat scroll 0 0 rgba(57, 60, 61, 0.8);
    border: 1px solid rgba(0, 0, 0, 0.8);
    border-radius: 3px 3px 3px 3px;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
    float: left;
    line-height: 1.2;
    padding: 4px;
}

.row-space-6 {
    margin-bottom: 37.5px;
}

.descriptions {

    width: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    /*margin-top: -22px;*/

    height: 55px;
    position: absolute;
    bottom: 0px;
    z-index: 20;
}

.hero_description {

    position: absolute;
    right: 20%;

    margin: 10px;
    /*font-weight: bold;*/
    color: white;
    text-align: right;
    text-shadow: 1px 1px #333;

}

.hero_description a {

    color: white;
    text-shadow: 1px 1px #333;
}

.hero_description a:hover,
.hero_description a:active {

    color: #efefef;
    text-decoration: underline;
    text-shadow: 1px 1px #333;
}


.hero h1 {

  /*  background: none repeat scroll 0 0 rgba(0, 0, 0, 0.8);
    width:  860px; */
    margin:0;
    padding: 8px 0;

}

.hero h1 span.lead {


   /*     background: none repeat scroll 0 0 rgba(0, 0, 0, 0.8);
   background: #008CBA;
   */ font-weight: 700;
    padding-left: 5px;
    padding-right: 5px;

    font-size: 120%;

    font-family: "Open Sans", "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
     /*
    text-shadow: -3px 3px 5px #000;
*/
}

.heroBackground  {
    background: none repeat scroll 0 0  rgba(0, 0, 0, 0.8);
}

.hero h4 {

  /*  margin-top: 14px;*/
   /* background: none repeat scroll 0 0  rgba(0, 0, 0, 0.8);
   */ /*/width:  860px;*/
    margin:0;
    padding: 8px 0;
}

.hero h4 span.sub-lead {

       /*  background: none repeat scroll 0 0  rgba(0, 0, 0, 0.8);
    background: #008CBA;
   */ padding-left: 4px;
    padding-right: 4px;

    padding-bottom: 4px;

    /*text-shadow: 1px 1px 2px #000;
     */
}

</style>


<div class="hero show-for-large-up">

    %{--    <img class="orbit-prev" src="${resource(dir: 'images', file: 'Arrows-Left-circular-icon.png')}"/>
        <img class="orbit-next" src="${resource(dir: 'images', file: 'Arrows-Right-circular-icon.png')}"/>--}%




    <ul class="slideshow">
    %{-- getting 5 lasts job offer --}%

        <g:each in="${heroImageArrayList}" var="hero">

            <g:set var="heroLink"
                   value="${createLink(absolute: true, controller: 'media', action: 'getHeroMedia', id: hero?.imageLink)}"/>

            <li style="display: none;

            background: center url('${heroLink}') repeat-x ;  ">

            </li>
        </g:each>

    </ul>


    <div class="search-area">
        <div class="row">
            <div class="large-12 columns heroBackground">
                <div id="blob-bg" style="opacity: 1; display: block;"></div>

                <h1 class="text-special">
                    <span class="lead">
                        <g:message code="hero.find.your.job"/>
                    </span>
                </h1>


                <h4>
                    <span class="sub-lead">
                        <g:message code="topSearchBanner.women.text"/>
                    </span>
                </h4>

                <g:render template="/index/searchForm"/>
            </div>

        </div>

    </div>


    <div class=" descriptions">

        <g:each in="${jobOfferList}" var="jobOffer">
            <div class="hero_description " style="display: none">



                <span style="margin-right: 15px;  ">
                <g:link mapping="${message(code: 'joboffer.link')}" id="${jobOffer.id}">
                        <strong>${jobOffer?.positionName}</strong>
                    </g:link>
                </span>

                ${jobOffer?.company?.companyName}
                <br>

                <div style="font-size: 12px; margin-top: 7px; color: #dadada">
                    <g:each in="${jobOffer.jobCategories}" var="jobCategory">
                        ${jobCategory.name} /
                    </g:each>

                    <g:message code="${jobOffer?.positionLocality?.i18NameFull}"/>
                </div>
            </div>
        </g:each>

    </div>

</div>

<script>
    //slideshow
    /*
     $(document).ready(function () {
     $('.orbit-next').hide();
     $('.orbit-prev').hide();
     $('.hero').mouseover(function () {
     $('.orbit-next').fadeIn("slow");
     $('.orbit-prev').fadeIn("slow");
     });
     $('.hero').mouseleave(function () {
     $('.orbit-next').fadeOut("slow");
     $('.orbit-prev').fadeOut("slow");
     });
     });
     $('.myContainer').on({
     mouseenter: function () {
     $('.myContainer .slider-nav').show();
     },
     mouseleave: function () {
     $('.myContainer .slider-nav').hide();
     }
     }); */
    //end of slideshow


    //global variables
    rotator_elements_descriptions = $(".hero div.descriptions div.hero_description");
    rotator_elements = $(".hero ul li");
    rotator_delay = 10000;
    rotator_delay_out = 600;
    rotator_delay_in = 1000;

    rotator_text_delay1 = 8400;
    rotator_text_delay2 = 1600;
    rotator_text_delay_out = 600;
    rotator_text_delay_in = 1000;

    rotator_iterator = 1;
    senman_next_hop = true;

    rotator_elements.first().show().fadeIn(rotator_delay_in);
    rotator_elements_descriptions.first().fadeIn(rotator_text_delay_in);

    $.rotator_loop = function rotator_loop() {

        rotator_elements.siblings()
                .delay(rotator_delay)
                .fadeOut(rotator_delay_out)
                .eq(rotator_iterator)
                .fadeIn(rotator_delay_in, function () {
                    rotator_iterator != rotator_elements.length - 1 ?
                            rotator_iterator++
                            :
                            rotator_iterator = 0;
                    if (senman_next_hop) {
                        rotator_loop()
                    }
                });

        rotator_elements_descriptions.siblings()
                .delay(rotator_text_delay1)
                .fadeOut(rotator_text_delay_out)
                .delay(rotator_text_delay2)
                .eq(rotator_iterator)
                .fadeIn(rotator_text_delay_in);
    }
    $.rotator_loop()

    //  click events on arrows
    /*
     $("img.orbit-prev").click(function () {
     //alert( "handler for .click() called." );
     function rotator_prev() {

     rotator_elements.siblings()
     .delay(0)
     .fadeOut(rotator_delay_out)
     .eq(rotator_iterator)
     .fadeIn(rotator_delay_in, function () {
     rotator_iterator != 0 ?
     rotator_iterator--
     :
     rotator_iterator = rotator_elements.length - 1;

     });
     rotator_elements_descriptions.siblings()
     .delay(rotator_delay)
     .fadeOut(rotator_delay_out)
     .eq(rotator_iterator)
     .fadeIn(rotator_delay_in);
     }

     rotator_elements.siblings().clearqueue();
     rotator_elements.siblings().stop();
     rotator_elements.siblings().dequeue();
     rotator_elements_descriptions.siblings().clearqueue();
     rotator_elements_descriptions.siblings().stop();
     rotator_elements_descriptions.siblings().dequeue();
     rotator_prev()
     //$.rotator_loop()

     senman_next_hop = false;
     settimeout(function () {
     // do something after 5 seconds
     senman_next_hop = true;
     $.rotator_loop();
     }, 6000);

     });
     $("img.orbit-next").click(function () {
     //alert( "handler for .click() called." );

     function rotator_next() {

     rotator_elements.siblings()
     .delay(0)
     .fadeOut(rotator_delay_out)
     .eq(rotator_iterator)
     .fadeIn(rotator_delay_in, function () {
     rotator_iterator != rotator_elements.length - 1 ?
     rotator_iterator++
     :
     rotator_iterator = 0;
     });
     rotator_elements_descriptions.siblings()
     .delay(rotator_delay)
     .fadeout(rotator_delay_out)
     .eq(rotator_iterator)
     .fadein(rotator_delay_in);
     }

     rotator_elements.clearqueue();
     rotator_elements.siblings().stop();
     rotator_elements.siblings().dequeue();
     rotator_elements_descriptions.siblings().clearqueue();
     rotator_elements_descriptions.siblings().stop();
     rotator_elements_descriptions.siblings().dequeue();
     rotator_next()
     //rotator_elements.siblings().resume();

     senman_next_hop = false;
     settimeout(function () {
     // do something after 6 seconds
     senman_next_hop = true;
     $.rotator_loop();
     }, 6000);

     });              */

</script>
