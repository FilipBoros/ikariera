<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <div class="large-12 columns">

        <br/>

        <h2>${articleInstance?.header}</h2>



        <g:link mapping="${message(code: 'company.link')}" id="${articleInstance?.company?.id}">
            <h3 style="font-weight: 300">${articleInstance?.company?.companyName}</h3>

        </g:link>

    </div>

    <hr>

</div>


<div class="row">

    <div class="large-8 columns">

        <div class="row">

            <div class="large-12 columns">
                <h4>
                    <g:if test="${articleInstance.leadingText}">

                        <g:stripHtmlTags text="${articleInstance?.leadingText}"/>
                    </g:if>
                </h4>

            </div>
        </div>


        <div class="row">

            <div class="large-12 columns">

                <p>
                    ${raw(articleInstance?.bodyText)}
                </p>
            </div>

        </div>

    </div>

    <div class="large-4 columns">

        <g:render template="/articles/panel" model="[articleInstance: articleInstance]"/>

    </div>

</div>


<br>

</body>
</html>




