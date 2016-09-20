<div class="row">
    <div class="large-12 columns">

        <label for="${name?name:field}" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

            ${label}

            <g:select optionKey="${optionKey}"
                      optionValue="${{ message(code: it.i18NameFull) }}"

                      value="${value}"

                      data-placeholder="${placeholder?:' '}"

                      class="chosen-select"

                      name="${name?name:field}"
                      from="${from}"
                      multiple="true"/>

        </label>

        <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">${errorMessage}</small>

    </div>
</div>


