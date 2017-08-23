<g:form name="filterForm"
        controller="jobOffer"
        action="index"
        method="get">

    <div>
        <div style="width: 250px; float: left">
            %{--Hidden multiselects --}%

            <g:textField name="${fullTextSearch}" disabled="${disabled}" placeholder="${placeholder}" required="required"
                         maxlength="${maxLength ?: 255}" value="${fullTextSearch ?: "Zadajte novu "}" style="height: ${height}px; padding: ${padding}em"/>


        </div>

        <div style="width: 100px; float: left">
            <g:submitButton name="${message( code: 'button.search')}" class="button expand  searchButton"/>
        </div>
    </div>

    <g:hiddenField name="max" value="${params.max}"/>
    <g:hiddenField name="menuState" id="menuState" value="${params.menuState}"/>

</g:form>


