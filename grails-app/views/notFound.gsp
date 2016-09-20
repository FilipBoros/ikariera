<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="page.not.found.title"/></title>
    <meta name="layout" content="main"/>

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <div class="error-container">
            <h1><g:message code="page.not.found" /> </h1>
            <div style="display: block; margin: auto">
                <g:img style="height: 300px; vertical-align: top" dir="/images/ikariera" file="404.jpg"/>

                <div style="width: 200px; display: inline-block;">
                    <h2 style="margin-top: 90px; margin-left: -90px;">Looking other page you are, young jedi.</h2>
                    <p style="font-size: small;margin-left: -90px"><g:message code="page.not.found.label"/>

                    <g:link controller="index" >
                        <g:message code="page.not.homelink"/>
                    </g:link>

                    </p>
                </div>
            </div>


        </div>

    </div>
</div>

</body>
</html>