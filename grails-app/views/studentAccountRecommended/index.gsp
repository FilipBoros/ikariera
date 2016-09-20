<%@ page import="cz.ikariera.company.JobOffer" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="student">
    <title><g:message code="title.joboffer.search"/></title>
</head>

<body>

<div class="panel">

    <div class="row">
        <div class="large-12 columns">

            <h1>

                ${message(code: 'jobOffer.recomended.JobOffer.label')}

            </h1>

        </div>

    </div>

</div>

<style>


tr {
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    height: 6em;
}

tr.first_row {
    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    height: 3em;
}

div.jobOfferItem {

    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    padding: 20px;
    min-height: 30px;
}

div.jobOfferItem.topPos {

    background-color: #FAFAFA;

}


</style>



<div class="row ">

    <div class="large-12 columns">


        <g:if test="${!jobOfferInstanceList}">

            ${message(code: 'search.noEntries.label')}

        </g:if>
        <g:else>

            <g:each in="${jobOfferInstanceList}" status="i" var="jobOfferInstance">


                <g:render template="/jobOffer/table" model="[jobOfferInstance : jobOfferInstance]" />
             </g:each>




            <br>
            <br>
            <g:paginateFoundation total="${jobOfferInstanceTotal}" params="${params}"
                                  maxsteps="4"
                                  next="${message(code: "list.paginate.next")}"
                                  prev="${message(code: "list.paginate.prev")}"/>
        </g:else>

    </div>

</div>

</body>
</html>
