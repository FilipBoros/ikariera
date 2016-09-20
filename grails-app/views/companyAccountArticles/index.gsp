<!doctype html>
<html>
<head>
    <meta name="layout" content="company">

</head>

<body>

<div class="row">

    <br>

    <div class="large-9 columns">

        <h1>${message(code: 'companyAccount.articles.h2.list.label')}</h1>

    </div>


    <div class="large-3 columns">
        <br>
        <g:link controller="companyAccountArticles" action="create"
                class="button right">${message(code: 'companyAccountUser.addArticle.label')}</g:link>

    </div>
</div>


<div class="row">

    <div class="large-12 columns">

        <hr>


        <table class="table">
            <thead>
            <tr>
                %{--    <th>#</th>--}%
                <g:sortableColumn property="header" title="${message(code: 'companyAccount.articleHeader.label')}"/>
                <g:sortableColumn property="dateCreated" title="${message(code: 'companyAccount.dateCreated.label')}"/>
                <g:sortableColumn property="datePublished"
                                  title="${message(code: 'companyAccount.datePublished.label')}"/>
                <g:sortableColumn property="willExpire" title="${message(code: 'companyAccount.willExpire.label')}"/>
                %{--<g:sortableColumn property="username" title="${message(code: 'companyAccount.username.label')}" />--}%
                <g:sortableColumn property="credits" title="${message(code: 'companyAccount.credits.label')}"/>

                <th style="text-align: center;">${message(code: 'jobOffer.printed.label')}"</th>
                <th>${message(code: 'company.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!articleInstanceList}">
                <tr>
                    <td colspan="6">${message(code: 'companyAccount.articles.noEntries.label')}</td>
                </tr>

            </g:if>
            <g:else>
                <g:each in="${articleInstanceList}" status="i" var="articleInstance">
                    <tr>
                        %{-- <td>${i+1}</td>--}%
                        <td>${articleInstance?.header}</td>
                        <td><g:formatDate format="dd.MM.yyyy" date="${articleInstance?.dateCreated}"/></td>
                        <td><g:formatDate format="dd.MM.yyyy" date="${articleInstance?.datePublished}"/></td>
                        <td><g:formatDate format="dd.MM.yyyy" date="${articleInstance?.willExpire}"/></td>
                        %{--<td>${fieldValue(bean: jobInstance, field: "companyAccountUser.user.username")}</td>--}%
                        <td style="text-align: center;">${articleInstance?.credits}</td>
                        <td style="text-align: center;">${articleInstance?.counter} / ${articleInstance?.contactCounter}</td>
                        <td>
                            <g:render template="dropDownMenu" model="[articleInstance: articleInstance]"/>

                        </td>
                    </tr>
                </g:each>

            </g:else>
            </tbody>
        </table>

        <g:paginateFoundation total="${articleInstanceListTotal}"/>

    </div>
</div>

</body>
</html>




