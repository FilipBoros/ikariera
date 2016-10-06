

   %{-- <g:set var="projects" value="${cz.ikariera.admin.Projects?.findAll("from Projects as p where p.lang.name=:lang order by p.posOrder",[lang:session['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'].toString()], [max: 4])}"  />

    <g:each in="${projects}" status="i" var="projectsInstance">
        <g:link mapping="projectsIkariera"  >
            <h3 style="max-height: 18px;">${fieldValue(bean: projectsInstance, field: "name")}</h3>
        </g:link>

        <div style="margin-top: -5px; text-align: justify;"><g:textStripTag  param1="${projectsInstance?.text}" param2="0" param3="180" param4="true" /> </div>
    </g:each>


--}%
