<div class="row">
    <div class="large-12 columns">
        <label for="name">
            Name *
        </label>
        <div class="input ${hasErrors(bean: maintainMessage, field: 'name', 'fail')} required">
            <g:textField name="name" maxlength="100" value="${maintainMessage?.name}"/>
        </div>
    </div>
</div>

<div class="row">
    <div class="large-12 columns">
        <label for="text">
            Message text *
        </label>
        <div class="input ${hasErrors(bean: maintainMessage, field: 'text', 'fail')} required">
            <g:textField name="text" maxlength="100" value="${maintainMessage?.text}"/>
        </div>
    </div>
</div>

<div class="row">

    <div class="large-12 columns">


        <label for="dateBegin" class="${hasErrors(bean: maintainMessage, field: 'dateBegin', 'error')}">

            <g:message code="admin.maintenanceMessage.start.date"/>

            <g:datePicker name="dateBegin" precision="day" default="${new Date() + 31}"
                          value="${maintainMessage.dateBegin}"/>

        </label>

        <small class="hide ${hasErrors(bean: message, field: 'dateBegin', 'error')}">
            <g:message code="admin.maintenanceMessage.wrong.date"/>
        </small>

    </div>
</div>

<div class="row">

    <div class="large-12 columns">


        <label for="dateEnd" class="${hasErrors(bean: maintainMessage, field: 'dateEnd', 'error')}">

            <g:message code="admin.maintenanceMessage.end.date"/>

            <g:datePicker name="dateEnd" precision="day" default="${new Date() + 31}"
                          value="${maintainMessage.dateEnd}"/>

        </label>

        <small class="hide ${hasErrors(bean: message, field: 'dateEnd', 'error')}">
            <g:message code="admin.maintenanceMessage.wrong.date"/>
        </small>

    </div>
</div>
