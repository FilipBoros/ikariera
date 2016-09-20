

    <div class="row">
        <div class="large-3 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'firstName',
                    field: 'firstName',
                    placeholder: message(code: 'user.firstName.label'),
                    value: params?.firstName]"/>
        </div>


        <div class="large-3 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'lastName',
                    field: 'lastName',
                    placeholder: message(code: 'user.lastName.label'),
                    value: params?.lastName]"/>
        </div>


        <div class="large-3 columns">
            <g:render template="/layouts/_fields/input" model="[

                    name: 'username',
                    field: 'username',
                    placeholder: message(code: 'user.username.label'),
                    value: params?.username]"/>
        </div>


        <div class="large-3 columns">
            <button>${message(code: 'constantModerator.search.label')}</button>
        </div>

    </div>
