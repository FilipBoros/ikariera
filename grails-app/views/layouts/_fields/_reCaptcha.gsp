<div id="recaptcha_widget" style="display:none">

    <div class="row">
        <div class="large-6 columns">

            <label for="recaptcha_response_field" class="${hasErrors(bean: inputInstance, field: field, 'error')}">

                ${label}


                <input id="recaptcha_response_field" placeholder="${placeholder}" name="recaptcha_response_field"
                       type="text" autocomplete="off"/>
            </label>

            <small class="hide ${hasErrors(bean: inputInstance, field: field, 'error')}">
                <g:message code="incorrect.answer"/>
            </small>

            <div>
                <a href="javascript:Recaptcha.reload()"><g:message code="get.another.captcha"/></a>
            </div>

        </div>

        <div class="large-6 columns">
            <div id="recaptcha_image" style="width:300px;height:57px;"></div>



            %{--            <div class="recaptcha_only_if_image">
                            <a href="javascript:Recaptcha.switch_type('audio')">Get an audio CAPTCHA</a>
                        </div>
                        <div>
                            <a href="javascript:Recaptcha.showhelp()">Help</a>
                        </div>--}%

        </div>
    </div>

</div>

<recaptcha:ifEnabled>
    <recaptcha:recaptcha theme="custom" custom_theme_widget="recaptcha_widget"/>
</recaptcha:ifEnabled>

