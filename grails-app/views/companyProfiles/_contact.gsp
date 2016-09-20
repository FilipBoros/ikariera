<div class="row">

    <div class="large-4 columns">

        <div class="panel detailInfo">

            <i class="fi-marker"></i>
            <h5>
                ${message(code: 'companyDetail.companyAddress.label')}
            </h5>


            <p>

                ${companyInstance?.companyStreet}
                <br/>
                ${companyInstance?.companyZip}, ${companyInstance?.companyCity}
                <br/>
                ${companyInstance?.companyCountry}
                <br/>
                ${message(code: companyInstance?.locality?.i18NameFull)}
            </p>


            <p>${message(code: 'companyDetail.companyID.label')}


            ${companyInstance?.companyID}
                <br/>
                ${message(code: 'companyDetail.companyVatID.label')}



                ${companyInstance?.companyVatID}
            </p>

            <hr/>

            <g:if test="${companyInstance?.companyWeb}">
                <h5>
                    ${message(code: 'companyDetail.companyWeb.label')}
                </h5>

                <p>
                    <g:displayWeb web="${companyInstance?.companyWeb}"/>
                    %{--                <g:link absolute="true" url="${companyInstance?.companyWeb}"
                                            target="_blank">${companyInstance?.companyWeb}</g:link>--}%

                </p>
            </g:if>
        </div>

    </div>

    %{--
       <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d163930.96841337875!2d14.465623899999992!3d50.05966964999999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x470b939c0970798b%3A0x400af0f66164090!2sPraha!5e0!3m2!1scs!2scz!4v1415098528487"
                width="600" height="450" frameborder="0" style="border:0"></iframe>
    --}%

    <div class="large-8 columns">

        <iframe
                width="600"
                height="450"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCVqejzDi9ERpzTAtQ4vrk6o2vPdCvlY9U
    &q=${companyInstance?.companyStreet}+${companyInstance?.companyCity}+ ${companyInstance?.companyCountry}">
        </iframe>

    </div>

</div>


<br>

<br>