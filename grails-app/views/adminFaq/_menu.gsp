<a data-dropdown="drop-${faqInstance.id}" class=" dropdown button secondary right round" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${faqInstance.id}" data-dropdown-content class="f-dropdown">

    <li>

        <g:link controller="adminFaq" action="show" params="[id: faqInstance.id]">
            <g:message code="admin.faq.show.label" />
        </g:link>
    </li>
    <li>
        <g:link controller="adminFaq" action="edit" params="[id: faqInstance.id]">
            <g:message code="edit" />
        </g:link>
    </li> <li>
    <g:link controller="adminFaq" action="delete" params="[id: faqInstance.id]">
        <g:message code="delete" />
    </g:link>

</li>

</ul>
%{--</div>--}%