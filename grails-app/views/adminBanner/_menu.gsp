<a data-dropdown="drop-${bannerInstance?.id}" class=" dropdown button secondary right round" href="#">

    <i class="fi-wrench"></i>
</a>

<ul id="drop-${bannerInstance?.id}" data-dropdown-content class="f-dropdown">


    <li>
        <g:link controller="adminBanner" action="edit"
                params="[id: bannerInstance?.id]">
            ${message(code: 'admin.bannerEdit.label')}
        </g:link>
    </li>
    <li>
        <g:link controller="adminBanner" action="delete"
                params="[id: bannerInstance?.id]">
            ${message(code: 'admin.uploadLogo.delete')}
        </g:link>

    </li>

</ul>