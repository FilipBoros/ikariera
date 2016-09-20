<g:if test="${flash.message}">
    <div class="row">
        <div class="large-12 columns">

            <div data-alert class="alert-box">

                <div class="info-container" role="status">${flash.message}</div>

                <a href="#" class="close">&times;</a></div>

        </div>

    </div>
</g:if>



<g:if test="${flash.error}">
    <div class="row">
        <div class="large-12 columns">

            <div data-alert class="alert-box alert">

                <div class="error-container" role="status">${flash.error}</div>
                <a href="#" class="close">&times;</a></div>

        </div>

    </div>
</g:if>

%{--<g:hasErrors>

    <div class="error-container" style="display:block;">

    <div class="row">
        <div class="large-12 columns">

            <div data-alert class="alert-box alert">

                <g:eachError>

                    <div class="error-container" role="status"><g:message error="${it}"/></div>
                </g:eachError>

                <a href="#" class="close">&times;</a></div>

        </div>

    </div>
    </div>
</g:hasErrors>--}%



