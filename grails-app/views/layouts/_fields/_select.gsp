<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}

            <g:select optionKey="${optionKey}"
                      optionValue="${{ message(code: it.i18NameFull) }}"

                      value="${value}"

                      placeholder="${placeholder}"
                      name="${name?name:field}"

                      from="${from}"
                      />

        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>


%{--


<div class="form-field">
    <label for="addressCountry.id"><g:message code="student.addressCountry.label"/></label>

    <div class="select ${hasErrors(bean: studentInstance, field: 'addressCountry', 'fail')} requiered">
        <g:select name="addressCountry.id"
                  id="addressCountry.id"
                  from="${cz.ikariera.admin?.Country?.list(sort: "posOrder")}"
                  optionKey="id"
                  optionValue="${{ message(code: "Country.name.label." + it.i18Name) }}"
                  value="${studentInstance?.addressCountry?.id ?: 1079}"/>
    </div>
</div>

--}%

