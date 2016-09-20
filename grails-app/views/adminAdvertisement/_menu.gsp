<a data-dropdown="drop-${advertisementInstance?.id}" class=" dropdown button secondary" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${advertisementInstance?.id}" data-dropdown-content class="f-dropdown">
    <li>
    <g:link controller="adminAdvertisement" action="edit"
            params="[id: advertisementInstance.id]">
        ${message(code: 'admin.advertisement.edit')}
    </g:link>
    </li>
    <g:if test="${!advertisementInstance.dateExpire}">
        <li><g:link controller="adminAdvertisement" action="publish"
                params="[id: advertisementInstance.id]">
            ${message(code: 'admin.advertisement.publish')}
        </g:link></li>
    </g:if>
    <g:else>
        <li><g:link controller="adminAdvertisement" action="depublish"
                params="[id: advertisementInstance.id]">
            ${message(code: 'admin.advertisement.destroy')}
        </g:link></li>
    </g:else>

</ul>