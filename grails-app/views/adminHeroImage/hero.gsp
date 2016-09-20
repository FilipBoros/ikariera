html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>


<div class="row">


    <div class="large-12 columns">

        <h1><g:message code="ikariera.hero.header"/></h1>

        <div class="panel">
            <p>



                <g:message code="ikariera.hero.label"/> <br/>



            </p>
        </div>


        <g:uploadForm action="uploadHero">


            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/file" model="[
                            label: message(code: 'default.file.label'),
                            name: 'file',
                            field: 'file',
                            placeholder: '',
                            inputInstance: pictureMediaInstance,
                            errorMessage: message(code: 'general.mandatory.atribute')]"/>
                </div>
            </div>


            <input type="submit"  class="button" value="${message(code: 'default.upload.label')}"/>

        </g:uploadForm>




    </div>
</div>

</body>
</html>