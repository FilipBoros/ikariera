<!doctype html>
<html>
<head>
    <meta name="layout" content="main">

</head>

<body>

<div class="row">

    <br/>

    <div class="large-12 columns">

        <h1>${message(code: "catalogue.label")}</h1>





        <g:each in="${catalogueList}" status="i" var="catalogueInstance">
            <div class="archive-instance">
                <img src="${resource(dir: "catalogue/" + catalogueInstance?.directory, file: catalogueInstance?.file,
                        absolute: true)}" width="200px" height="262px"/>

                <div class="archive-instance-data">
                    <table>
                        <tbody>

                        <tr>
                            <td colspan="2">
                                <h2>
                                    ${catalogueInstance?.name}
                                </h2>
                            </td>
                        </tr>

                        <tr>
                            <td class="first">${message(code: "catalogue.archive.published")}</td>
                            <td>
                                <g:formatDate format="yyyy" date="${catalogueInstance?.date}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="first">${message(code: "catalogue.archive.coordinator")}</td>
                            <td>
                                <g:if test="${catalogueInstance?.coordinator}">
                                    ${catalogueInstance?.coordinator}
                                </g:if>
                                <g:else>
                                    ${message(code: "catalogue.archive.noCoordinator")}
                                </g:else>
                            </td>
                        </tr>
                        <tr>
                            <td class="first">${message(code: "catalogue.archive.link")}</td>
                            <td>
                                <g:if test="${catalogueInstance?.link}">
                                    <g:link url="${catalogueInstance?.link}"
                                            absolute="true">${catalogueInstance?.link}</g:link>
                                </g:if>
                                <g:else>
                                    ${message(code: "catalogue.archive.nolink")}
                                </g:else>
                            </td>
                        </tr>
                        <tr>
                            <td class="first">${message(code: "catalogue.archive.description")}</td>
                            <td>
                                <g:if test="${catalogueInstance?.description}">
                                    ${catalogueInstance?.description}
                                </g:if>
                                <g:else>
                                    ${message(code: "catalogue.archive.noInfo")}
                                </g:else>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </g:each>
    </div>
</div>



</body>
</html>