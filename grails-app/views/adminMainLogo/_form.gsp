<div class="form-field">

    <label for="endDate">
        <g:message code="main.logo.endDate"/>
    </label>

    <div class="empty-input ${hasErrors(bean: logosInstance, field: 'path', 'fail')}">
        <g:datePicker name="endDate" value="${new Date()}"
                      precision="day" default="${new Date().plus(7)}"/>
    </div>

</div>

<div class="form-field">

    <div class="empty-input">
        <g:if test="${logosInstance?.file}">

            <img src="${resource(dir: logosInstance?.directory + "/orig", file: logosInstance?.file, absolute: true)}"/>

        </g:if>

    </div>

</div>


<!-- file -->
<div class="form-field">

    <label for="myFile">
        <g:message code="banner.file.label"/>
    </label>


    <div class="empty-input ${hasErrors(bean: logosInstance, field: 'path', 'fail')}">
        <input type="file" name="myFile" id="myFile"/>

    </div>

</div>


