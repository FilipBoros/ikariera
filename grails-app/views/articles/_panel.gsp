<div class="panel detailInfo">

    <i class="fi-calendar"></i>
    <h5>
        ${message(code: 'dateOfPublishing')}

    </h5>

    <p>
        <g:daysToNow date="${articleInstance?.datePublished}"/>
    </p>

    <hr>

    <i class="fi-torso-business"></i>
    <h5>
        ${message(code: 'company')}

    </h5>

    <p>
       ${articleInstance?.company?.companyName}
    </p>

    <hr>
    <i class="fi-marker"></i>


        <h5>
            <g:message code="company.address.label"/>
        </h5>

        <p>
            ${articleInstance?.company?.companyName}
            <br>
            ${articleInstance?.company?.companyStreet}
            <br>
            ${articleInstance?.company?.companyZip}, ${articleInstance?.company?.companyCity}
            <br>
            ${articleInstance?.company?.companyCountry}
        </p>

</div>