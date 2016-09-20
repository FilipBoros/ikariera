<%@ page import="cz.ikariera.admin.Banner" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2><g:message code="admin.company.uploadBanner2"/> </h2>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form method="post" enctype="multipart/form-data" action="save">

            <g:render template="/adminBanner/form" model="[bannerInstance : bannerInstance]"/>

            <br />

            <button name="create" class="save float">
                ${message(code: 'default.button.save.label')}
            </button>

        </g:form>

    </div>

</div>
</body>
</html>
