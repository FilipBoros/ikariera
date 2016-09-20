<g:if test="${mediaType == 'hero'}">
    <img src="${createLink(controller: 'media', action: 'getHeroMedia', id: filename )}"/>
</g:if>

<g:elseif test="${mediaType == 'logo'}">
    <img src="${createLink(controller: 'media', action: 'getMedia', id: filename )}"/>
</g:elseif>