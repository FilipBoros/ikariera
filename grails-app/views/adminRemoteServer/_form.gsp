<div class="row">
    <div class="large-12 columns">
        <label for="name">
            Name *
        </label>
        <div class="input ${hasErrors(bean: remoteInstance, field: 'name', 'fail')} required">
            <g:textField name="name" maxlength="100" value="${remoteInstance?.name}"/>
        </div>
    </div>
</div>
<div class="row">

    <div class="large-12 columns">

        <g:render template="/layouts/_fields/select" model="[
                optionKey    : 'id',
                label        : message(code: 'jobOffer.positionCountry.label') + '*',
                placeholder  : '',
                value        : remoteInstance?.country?.id,
                field        : 'country',
                name         : 'country',
                from         : cz.ikariera.admin.Country.list(sort: 'posOrder')]"/>

    </div>
</div>
<div class="row">
    <div class="large-12 columns">
        <label for="token">
            Key *
        </label>
        <div class="input ${hasErrors(bean: remoteInstance, field: 'token', 'fail')} required">
            <g:textField name="token" maxlength="100" value="${remoteInstance?.token}"/>
        </div>
    </div>
</div>
<div class="row">
    <div class="large-12 columns">
        <label for="serverURL">
            Server URI *
        </label>
        <div class="input ${hasErrors(bean: remoteInstance, field: 'serverURL', 'fail')} required">
            <g:textField name="serverURL" maxlength="100" value="${remoteInstance?.serverURL}"/>
        </div>
    </div>
</div>
