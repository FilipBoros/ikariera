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

        <h2><g:message code="admin.company.uploadBanner2"/> ${companyInstance?.companyName}</h2>

    </div>

</div>

<div class="row">

    <div class="large-12">
        <label> <g:message code="admin.banner.currentBanner"/> </label>
        <div id="banner"
             style="border-bottom: 1px solid rgba(0, 0, 0, 0.05); margin: 10px 0px 0px 0px ;height: 100px">
            %{--<img src="http://cs620718.vk.me/v620718208/81ae/gXSyKZFhdZc.jpg" style="height: 90px; width: 720px; margin: auto; display: block;"/>--}%
            <img style="height: 90px; width: 720px; display: block;" src="${createLink(controller: 'publicBanner', action: "getSomeBanner", id: bannerInstance?.id, absolute: true)}"/>
        </div>

        <g:form method="post" enctype="multipart/form-data" controller="adminBanner" action="update">
            <g:hiddenField name="id" value="${bannerInstance?.id}"/>
            <g:hiddenField name="version" value="${bannerInstance?.version}"/>

            <g:render template="/adminBanner/form"/>

            <br />

            <button name="create" class="save float">
                ${message(code: 'default.button.save.label')}
            </button>

        </g:form>
    </div>
</div>
</body>
</html>
