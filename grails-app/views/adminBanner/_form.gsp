<label for="priority">

    <g:message code="fileupload.add.files"/>

</label>

<g:render template="/layouts/_fields/file" model="[name: 'file']"/>

<div class="form-field">
    <label for="priority">

        <g:message code="banner.priority.label"/>

    </label>

    <g:select class="chosen-select"
              name="priority"

              value="${bannerInstance?.priority}"

              from="${[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]}"/>
</div>
<br />
<div class="row">

    <div class="large-12 columns">


        <label for="link" class="${hasErrors(bean: bannerInstance, field: 'dateCreated', 'error')}">

            %{--<g:message code="banner.urlLink.label"/>--}%URL ("http://example.com")

                <input type='text' required="" value="${bannerInstance?.link}"  name='link' placeholder="${message(code: 'company.url.label')}"/>

    </label>

    <small class="hide ${hasErrors(bean: bannerInstance, field: 'link', 'error')}">
        <g:message code="banner.wrong.date"/>
    </small>

    </div>
</div>
    <div class="row">

        <div class="large-12 columns">

            <label for="dateCreated" class="${hasErrors(bean: bannerInstance, field: 'dateCreated', 'error')}">

                <g:message code="banner.start.date"/>

                <g:datePicker name="dateCreated" precision="day" default="${new Date()}"
                              value="${bannerInstance?.dateCreated}"/>

            </label>

            <small class="hide ${hasErrors(bean: bannerInstance, field: 'dateCreated', 'error')}">
                <g:message code="banner.wrong.date"/>

            </small>

        </div>
    </div>

    <div class="row">

        <div class="large-12 columns">

            <label for="expirationDate" class="${hasErrors(bean: bannerInstance, field: 'expirationDate', 'error')}">

                <g:message code="banner.end.date"/>

                <g:datePicker name="expirationDate" precision="day" default="${new Date() + 31}"
                              value="${bannerInstance?.expirationDate}"/>

            </label>

            <small class="hide ${hasErrors(bean: bannerInstance, field: 'expirationDate', 'error')}">
                <g:message code="banner.wrong.date"/>
            </small>

        </div>
    </div>

