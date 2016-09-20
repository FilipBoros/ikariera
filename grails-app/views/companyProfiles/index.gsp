<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <title><g:message code="title.companies"/></title>
</head>

<body>

<div class="panel">
    <div class="row">



        <div class="large-12 columns">

            <h1><g:message code="company.companies.label"/></h1>


            <br>

            <g:render template="search"/>

        </div>

    </div>

</div>


<style>



div.companyProfileItem {

    border-bottom: 1px solid rgba(0, 0, 0, 0.05);
    padding: 20px;
    min-height: 30px;
}

div.companyProfileItem.topCompany {

    background-color: #FAFAFA;

}


</style>


<div class="row">

    <div class="large-12 columns">
        <br>

        <g:if test="${!companyInstanceList}">

            ${message(code: 'search.noEntries.label')}

        </g:if>
        <g:else>
            <g:each in="${companyInstanceList}" status="i" var="companyInstance">

                <div class="companyProfileItem ">

                    <div class="row ">

                        <div class="large-8 columns">

                            <g:link mapping="${message(code: 'companies.link')}" id="${companyInstance?.id}">
                                <h3 style="font-weight: 300">${companyInstance?.companyName}</h3>


                            </g:link>





                            <div style=" color: #666; font-size: 80%">


                            <g:each in="${companyInstance?.mainJobCategories?.sort { it.posOrder }  }" status="ii"
                                    var="category">
                                <g:if test="${ii < 5}">

                                    ${message(code: category?.i18NameFull)}<g:if test="${ii < companyInstance?.mainJobCategories?.size() - 1 && ii<4}">,</g:if>



                                </g:if>
                            </g:each>

                            </div>


                        </div>


                        <div class="large-4 columns ">

                            <div style="text-align: right; color: #666; font-size: 80%">
                                ${companyInstance?.companyStreet}   <br/>
                                ${companyInstance?.companyCity}    <br/>
                                ${companyInstance?.companyCountry}  <br/>
                                ${message(code: companyInstance?.locality?.i18NameFull)}
                            </div>

                        </div>

                    </div>

                </div>
            </g:each> %{--celkem  ${companyInstanceListTotal}--}%




            <br>
            <br>

            <g:paginateFoundation total="${companyInstanceListTotal}" params="${params}"
                                  maxsteps="4"
                                  next="${message(code: "list.paginate.next")}"
                                  prev="${message(code: "list.paginate.prev")}"/>

        </g:else>

    </div>

</div>

</body>
</html>
