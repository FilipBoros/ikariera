/**
 * Created by cernydav on 4/7/14.
 */
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




 var universityChildCount = $("#universities-table-list").attr("universityChildCount");
 var languageChildCount = $("#languages-table-list").attr("languageChildCount");
 var experienceChildCount = $("#experiences-table-list").attr("experienceChildCount");
 var certificateChildCount = $("#certificates-table-list").attr("certificateChildCount");



$(document).ready(function () {

    $(".studentUniversitySelect").on("change", function () {
        if ($(this).val() == "1") {
            $(this).parent().parent().find('.studentOtherUniversityInput').show();
            // alert("got");

        } else {

            $(this).parent().parent().find('.studentOtherUniversityInput').hide();
            //alert("got nothing");

        }


    });


    $(":input").focus(function () {
        if ($(this).parent().hasClass('fail')) {
            $(this).parent().parent().find(".error").show();
            $(this).parent().parent().find(".help").hide();
        } else {
            $(this).parent().parent().find(".error").hide();
            $(this).parent().parent().find(".help").show();

        }


    });

    $(":input").blur(function () {


        $(this).parent().parent().find(".help").hide();

        $(this).parent().parent().find(".error").hide();


    });




    $("#add-new-university").click(function (e) {

        e.preventDefault();

        var arraySelectInputs = new Array( "\\.id", "\\.titleAwarded", "\\.type", "\\.deleted", "\\.new", "\\.university\\.id", "\\.studyCategory\\.id", "\\.specialization", "\\.endingYear_year", "\\.endingYear", "\\.otherUniversity");
        var arrayInputs = new Array( ".id", ".titleAwarded", ".type", ".deleted", ".new", ".university.id", ".studyCategory.id", ".specialization", ".endingYear_year", ".endingYear", ".otherUniversity");

        addTableListRow(universityChildCount, "educations", "#university-row-clone ", "#universities-table-list", arrayInputs, arraySelectInputs);
        universityChildCount++;

    });




    $("#add-new-language").click(function (e) {

        e.preventDefault();

        var arraySelectInputs = new Array("\\.id", "\\.type", "\\.deleted", "\\.new", "\\.languageType\\.id", "\\.level\\.id");
        var arrayInputs = new Array(".id", ".type", ".deleted", ".new", ".languageType.id", ".level.id");

        addTableListRow(languageChildCount, "languages", "#language-row-clone   tr:first", "#languages-table-list", arrayInputs, arraySelectInputs);

        languageChildCount++;


    });


    $("#add-new-experience").click(function (e) {

        e.preventDefault();

        var arraySelectInputs = new Array("\\.id", "\\.type", "\\.deleted", "\\.new", "\\.periodEnd", "\\.periodStart", "\\.occupation", "\\.activities", "\\.employer");
        var arrayInputs = new Array(".id", ".type", ".deleted", ".new", ".periodEnd", ".periodStart", ".occupation", ".activities", ".employer");

        addTableListRow(experienceChildCount, "experiences", "#experience-row-clone tr:first", "#experiences-table-list", arrayInputs, arraySelectInputs);

        experienceChildCount++;


    });


    $("#add-new-certificate").click(function (e) {

        e.preventDefault();

        var arraySelectInputs = new Array("\\.id", "\\.type", "\\.deleted", "\\.new", "\\.name", "\\.description", "\\.level");
        var arrayInputs = new Array(".id", ".type", ".deleted", ".new", ".name", ".description", ".level");

        addTableListRow(certificateChildCount, "certificates", "#certificate-row-clone tr:first", "#certificates-table-list", arrayInputs, arraySelectInputs);

        certificateChildCount++;


    });


    function addTableListRow(childCount, prefix, path2Object2Clone, appendTo, arrayInputs, arraySelectInputs) {

        var clone;
        var htmlId;

        var securePreFix = "\\[_clone\\]";

        clone = $(path2Object2Clone).clone();

        htmlId = prefix + '[' + childCount + ']';

        clone.find('.exclude-from-clone').remove();

        for (var key in arraySelectInputs) {
            //    + prefix + securePreFix +


            clone.find(":input[name$=" + securePreFix + arraySelectInputs[key] + "]").attr('id', htmlId + arrayInputs[key]).attr('name', htmlId + arrayInputs[key]);

        }


        clone.attr('id', prefix + childCount);
        $(appendTo).append(clone);
        clone.show();
        // phoneInput.focus();

    }





//bind click event on delete buttons using jquery on
    $('.tabled-list-form').on('click', '.del-cloned-table-row a', function (e) {
        e.preventDefault();
        //find the parent div
        var prnt = $(this).parents(".cloned-table-row");

        prnt.remove();

        //find the deleted hidden input
       // var delInput = prnt.find("input[id$=deleted]");
        //check if this is still not persisted
      //  var newValue = prnt.find("input[id$=new]").attr('value');
        //if it is new then i can safely remove from dom
      //  var type = prnt.find("input[id$=type]").attr('value');

      /*  if (newValue == 'true') {
            // prnt.remove();

            delInput.attr('value', 'true');
            //hide the div
            prnt.hide();
        */
            /* if(type== "university") {
             universityChildCount -= 1;
             }
             else if(type== "language") {
             languageChildCount -= 1;
             }
             else if(type== "experience") {
             experienceChildCount -= 1;
             }
             else if(type== "knowledge") {
             knowledgeChildCount -= 1;
             }*/
      /*
        } else {
            //set the deletedFlag to true
            delInput.attr('value', 'true');
            //hide the div
            prnt.hide();
        } */
    });


});