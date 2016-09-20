<g:render template="/email/style/style"/>

<div style=" width: 800px;">

    <h2>
        <g:message code="company.new.registration.text"/>
    </h2>


    ${companyInstance?.companyID}
    <br/>
    ${companyInstance?.companyName}
    <br/>
    ${companyInstance?.companyStreet}
    <br/>
    ${companyInstance?.companyCity}
    <br/>
    ${companyInstance?.companyCountry}
    <br/>
    ${companyInstance?.companyZip}
    <br/>
    ${companyInstance?.companyWeb}
    <br/>
    ${companyInstance?.companyCharacteristic}

    <br/>
    <g:render template="/email/contact"/>

</div>

