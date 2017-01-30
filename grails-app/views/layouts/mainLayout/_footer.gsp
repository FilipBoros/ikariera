<div class="row">
    <div class="small-6 medium-6 large-5 columns">

        <g:if test="${grailsApplication.config.language.equals('cz')}">
            <asset:image src="ikariera/iaeste-bile.png"/>
        </g:if>
        <g:elseif test="${grailsApplication.config.language.equals('sk')}">
            <asset:image src="ikariera_sk/iaeste-bile.png"/>
        </g:elseif>
        <br/>     <br/>


        <p style="max-width: 330px">

            <g:message code="ikariera.owner.label"/>
            <br>
            <a href="http://www.iaeste.${grailsApplication.config.language}"><g:message code="iaesteCR.label"/></a>.
            <br/>

        </p>

        <br/>

        <br/>

    </div>

    <div class="small-6 medium-6   large-3 columns">


        <br>
        <h4><g:message code="footer.contact"/></h4>
        <ul class="no-bullet">
            <li>Email: <a href="mailto:${contactInstance?.contactEmail}">info@ikariera.${grailsApplication.config.language}</a></li>

            <li>Web:  <a href="${contactInstance?.contactWeb}"><g:message code="iaesteCR.label"/></a></li>

            <li>Youtube:  <a href="${contactInstance?.youtube}" target="_blank"><g:message code="youtube.label"/></a></li>

            <li>Linkedin:  <a href="${contactInstance?.linkedin}" target="_blank"><g:message code="linkedin.label"/></a></li>

            %{--<li>Twitter:  <a href="https://twitter.com/iaesteczech" target="_blank">@iaesteczech</a></li>--}%

        </ul>

        <p>
            <strong><g:message code="iaesteCR.label"/></strong>      <br>
            <g:message code="about.street.label"/><br>
            <g:message code="about.zip"/> <g:message code="about.city"/>    <br>
            <g:message code="about.country"/>

        </p>
    </div>


    <div class="show-for-large-up large-3 columns">

        <br>
        <h4><g:message code="footer.like.us"/></h4>
        <iframe src="//www.facebook.com/plugins/like.php?href=https%3A%2F%2Ffacebook.com%2Fikariera&amp;width=220&amp;layout=button_count&amp;action=like&amp;show_faces=false&amp;share=false&amp;height=21"
                scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:220px; height:21px;"
                allowTransparency="true"></iframe>


        <br>    <br>

        <iframe src="//www.facebook.com/plugins/facepile.php?app_id&amp;href=http%3A%2F%2Ffacebook.com%2Fikariera&amp;action&amp;width=220&amp;height&amp;max_rows=3&amp;colorscheme=dark&amp;size=medium&amp;show_count=true"
                scrolling="no" frameborder="0" style="border:none; overflow:hidden; width:220px;"
                allowTransparency="true"></iframe>

    </div>
</div>




%{--
<div class="row">

    <div class="large-6 columns">
        <p>
            <g:message code="manual.language.selector"/> :

            <g:link params="[lang: 'cs_CZ', f : request.forwardURI]" controller="index" action="fakeAction" ><g:message code="language.czech"/></g:link>

            <g:link params="[lang: 'en_US', f : request.forwardURI]" controller="index" action="fakeAction" ><g:message code="language.english"/></g:link>

        <p>
    </div>

    <div class="large-6 columns">
        <p style="text-align: right"><g:message code="copyright.label"/>

        2007 - <g:formatDate format="yyyy" date="${new Date()}"/>

        <p>
    </div>
</div>--}%
