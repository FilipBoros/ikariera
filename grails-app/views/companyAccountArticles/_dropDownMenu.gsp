<a data-dropdown="drop-${articleInstance.id}" class="button dropdown secondary round" href="#">

    <i class="fi-wrench"></i>

</a>



<ul id="drop-${articleInstance.id}" data-dropdown-content class="f-dropdown">

    <g:if test="${!articleInstance?.willExpire}">

        <li>
            <g:link controller="companyAccountArticles" action="show" target="_blank"
                    params="[id: articleInstance?.id]">
                ${message(code: 'companyAccountUser.quick.show')}
            </g:link>
        </li>



        <li>
            <g:link controller="companyAccountArticles" action="edit"
                    params="[id: articleInstance?.id]">
                ${message(code: 'companyAccountUser.editJobOffer.label')}
            </g:link>
        </li>


        <li>
            <g:link controller="companyAccountArticles" action="publish"
                    class="confirmIt"
                    params="[id: articleInstance?.id]"
                    dialogMsg="${message(code: 'system.publishJobOffer.text')}">
                ${message(code: 'companyAccountUser.publicJobOffer.label')}
            </g:link>
        </li>


        <li>

            <g:link controller="companyAccountArticles" action="delete"
                    class="confirmIt"
                    params="[id: articleInstance?.id]"
                    dialogMsg="${message(code: 'system.deleteJobOffer.text')}">
                ${message(code: 'companyAccountUser.deleteJobOffer.label')}
            </g:link>

        </li>

    </g:if>
    <g:else>

        <li>
            <g:link controller="companyAccountArticles" action="show" target="_blank"
                    params="[id: articleInstance?.id]">
                ${message(code: 'companyAccountUser.quick.show')}
            </g:link>
        </li>


        <li>
            <g:link controller="companyAccountArticles" action="expire"
                    class="confirmIt"
                    params="[id: articleInstance?.id]"
                    dialogMsg="${message(code: 'system.nowExpire.warning')}">
                ${message(code: 'companyAccountUser.nowExpire.label')}
            </g:link>
        </li>
    </g:else>

</ul>

