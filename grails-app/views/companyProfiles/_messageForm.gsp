<g:uploadForm controller="studentAccountMessage" action="sendMessageToCompany">
    <g:hiddenField name="mail" value="${sec.loggedInUserInfo(field: 'username')}"/>

    %{--<g:hiddenField name="companyId" value="${jobOfferInstance?.company?.id}"/>--}%
    <g:hiddenField name="id" value="${companyInstance?.id}"/>

                   <h3> ${companyInstance?.companyName} </h3>


    <sec:ifLoggedIn>

        <div class="row">
            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.firstName.label'),
                        field      : 'firstName',
                        value      : sec.loggedInUserInfo(field: 'firstName'),
                        name       : 'firstName',
                        placeholder: '',
                        disabled   : true,
                        maxLength  : 150]"/>

            </div>

            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.lastName.label'),
                        field      : 'lastName',
                        value      : sec.loggedInUserInfo(field: 'lastName'),
                        name       : 'lastName',
                        placeholder: '',
                        disabled   : true,
                        maxLength  : 150]"/>

            </div>
            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.username.label'),
                        field      : 'contactMail',
                        value      : sec.loggedInUserInfo(field: 'username'),
                        required   : 'true',
                        name       : 'contactMail',
                        placeholder: 'email',
                        disabled   : true,
                        maxLength  : 150]"/>

            </div>
        </div>

    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>

        <div class="row">
            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.firstName.label'),
                        field      : 'firstName',
                        required   : 'true',
                        name       : 'firstName',
                        placeholder: '',

                        maxLength  : 150]"/>
            </div>

            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.lastName.label'),
                        field      : 'lastName',

                        name       : 'lastName',
                        placeholder: '',

                        maxLength  : 150]"/>

            </div>

            <div class="large-4 columns">

                <g:render template="/layouts/_fields/input" model="[
                        label      : message(code: 'user.username.label'),
                        field      : 'contactMail',
                        required   : 'true',
                        name       : 'contactMail',
                        placeholder: 'email',

                        maxLength  : 150]"/>

            </div>
        </div>

    </sec:ifNotLoggedIn>






    <g:render template="/layouts/_fields/textArea" model="[
            label      : message(code: 'message.body'),
            field      : 'text',
            name       : 'text',
            placeholder: '',
            required   : 'true',
            maxLength  : 4000,
            height     : '400px',
    ]"/>


%{--
    <sec:ifLoggedIn>
        <g:checkBox name="cv"/>

        <g:message code="student.cv.attachcv"  />
        <br />

    </sec:ifLoggedIn>
    <sec:ifNotLoggedIn>


        <g:message code="student.uploadCv.label"  />
        <input type="file" name="myFile"/>


    </sec:ifNotLoggedIn>
--}%
    <a class="close-reveal-modal">&#215;</a>

    <button class="button"><g:message code="message.company.send"/></button>

</g:uploadForm>