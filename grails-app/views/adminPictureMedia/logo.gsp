<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>


<div class="row">


    <div class="large-12 columns">

        <h1>${message(code: 'admin.company.uploadLogo')}</h1>

        <div class="panel">
            <p>



                ${message(code: 'logo.max', args: [150, 150])}




            </p>
        </div>


        <g:uploadForm action="uploadLogo">




            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/file" model="[
                            label: message(code: 'logo.small'),
                            name: 'logo',
                            field: 'logo',
                            placeholder: '',
                            inputInstance: pictureMediaInstance,
                            errorMessage: message(code: 'general.mandatory.atribute')]"/>
                </div>
            </div>



            <input type="submit"  class="button"/>

        </g:uploadForm>




    </div>
</div>

</body>
</html>