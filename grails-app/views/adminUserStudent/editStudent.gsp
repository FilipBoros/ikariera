<!doctype html>
<html>
<head>
    <meta name="layout" content="administrator">

    <r:require modules="mainStyle, application, studentEducation"/>



</head>

<body>

<div class="row">

    <br/>

    <div class="large-12">

        <h2>
        <g:message args="[userInstance?.lastName, userInstance?.firstName, userInstance?.username]"
                   code="company.userChange.label"/>
        </h2>
        <hr>

    </div>

</div>

<div class="row">

    <div class="large-12">

        <g:form action="save" controller="adminUserStudent">

            <g:hiddenField name="userVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="studentVersion" value="${userInstance?.version}"/>
            <g:hiddenField name="id" value="${userInstance?.id}"/>
            <g:hiddenField name="basicInfo" value="${userInstance?.id}"/>
            <g:render template="/adminUserStudent/studentForm" model="['userInstance': userInstance]"/>

            <g:link params="[id: userInstance?.id]"
                    action="changePass">
                ${message(code: 'user.changePassword.label')}
            </g:link>
            <br>
            <br>



            <button name="create"
                    class="save long">${message(code: 'default.button.save.label')}</button>

        </g:form>

        <g:render template="/adminUserStudent/education" model="['studentInstance': userInstance?.studentAccount, 'userInstance': userInstance]"/>

        <g:render template="/adminUserStudent/languages" model="['studentInstance': userInstance?.studentAccount, 'userInstance': userInstance]"/>

        <g:render template="/adminUserStudent/certificates" model="['studentInstance': userInstance?.studentAccount, 'userInstance': userInstance]"/>

        <g:render template="/adminUserStudent/experience" model="['studentInstance': userInstance?.studentAccount, 'userInstance': userInstance]"/>

<g:form action="saveContact" controller="adminUserStudent">

    <g:hiddenField name="userVersion" value="${userInstance?.version}"/>
    <g:hiddenField name="studentVersion" value="${userInstance?.version}"/>
    <g:hiddenField name="id" value="${userInstance?.id}"/>
    <g:hiddenField name="basicInfo" value="${userInstance?.id}"/>

        <g:render template="/adminUserStudent/contactForm" model="['studentInstance': userInstance?.studentAccount, 'userInstance': userInstance]"/>

    <button name="create"
            class="save long">${message(code: 'default.button.save.label')}</button>

</g:form>
    %{--
            <table style="display: none;" id="university-row-clone">
                <g:render template='/layouts/_fields/university/university' model="['university': null, 'i': '_clone']"/>
            </table>
            <table style="display: none;" id="language-row-clone">
                <g:render template='/layouts/_fields/language/language' model="['language': null, 'i': '_clone']"/>
            </table>

            <table style="display: none;" id="experience-row-clone">
                <g:render template='/layouts/_fields/experience/experience' model="['experience': null, 'i': '_clone']"/>
            </table>


            <table style="display: none;" id="knowledge-row-clone">
                <g:render template='/layouts/_fields/certificate/certificate' model="['knowledge': null, 'i': '_clone']"/>
            </table>--}%



    </div>

</div>
</body>
</html>