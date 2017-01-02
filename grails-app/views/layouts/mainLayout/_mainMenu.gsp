<style>

.top-bar-section li:not(.has-form) a:not(.button) {

    padding: 0 20px;
}

</style>

<div class="contain-to-grid  ">

    <nav class="top-bar" data-topbar>
        <ul class="title-area">
            <li class="namel">
                <h1>

                    <a class="home show-for-medium" href="${createLink(uri: '/')}">
                        <asset:image src="ikariera/apple-touch-icon.png" alt="iKariera" style="height: 70px; border-width: 0px; padding-bottom: 15px;"/>
                        %{--<img style="height: 70px"
                             src="${resource(dir: 'images/ikariera', file: 'apple-touch-icon.png')}" alt="iKariera"
                             style="border-width: 0px; margin-bottom: 10px;"/>--}%
                    </a>


                    <a class="home show-for-large-up" href="${createLink(uri: '/')}">
                        <asset:image src="ikariera/ikariera-special.png" alt="iKariera" style="height: 70px; border-width: 0px; padding-bottom: 15px;"/>
                        %{--<img style="height: 70px"
                             src="${resource(dir: 'images/ikariera', file: 'ikariera-special.png')}" alt="iKariera"
                             style="border-width: 0px; margin-bottom: 10px;"/>--}%
                    </a>


                    <a class="home show-for-small" href="${createLink(uri: '/')}">
                        <asset:image src="ikariera/ikariera-special.png" alt="iKariera" style="height: 70px; border-width: 0px; padding-bottom: 15px;"/>
                        %{--<img style="height: 70px"
                             src="${resource(dir: 'images/ikariera', file: 'ikariera-special.png')}" alt="iKariera"
                             style="border-width: 0px; margin-bottom: 10px;"/>--}%
                    </a>

                </h1>
            </li>


            <li class="toggle-topbar menu-icon">
                <a href="#">Menu</a>
            </li>
        </ul>

        <section
                class="top-bar-section">

            <!-- Left Nav Section -->
            <ul class="right">
                <li>
                    <g:link mapping="${message(code: 'joboffers.link')}">
                        ${message(code: 'jobOffer.jobOffers.label')}
                    </g:link>
                </li>

                <li>
                    <g:link mapping="${message(code: 'companies.link')}">
                        <g:message code="company.companies.label"/>
                    </g:link>
                </li>



                <li>
                    <g:link mapping="${message(code: 'arcticles.link')}">
                        ${message(code: 'company.articles.label')}
                    </g:link>
                </li>

%{--
                <li>

                    <a href="http://katalog.ikariera.cz">

                        <g:message code="ikariera.catalogue.label"/>

                    </a>

                </li>
--}%


            <li>
                %{--This is so far just a proof of concept--}%
                <g:if test="${grailsApplication.config.language.equals('cz')}">
                    <a href="http://veletrhy.ikariera.cz" target="_blank">
                        Veletrhy
                    </a>
                </g:if>
                <g:elseif test="${grailsApplication.config.language.equals('sk')}">
                    <a href="http://iaeste.sk" target="_blank">
                        Iaeste Slovensko
                    </a>
                </g:elseif>

            </li>



                <style>

                .top-bar-section ul li:hover > a.button.alert {

                    color: white;

                }

                .top-bar-section li:not(.has-form) a:not(.button) {

                    padding-left: 15px;
                    padding-right: 15px;
                }

                .top-bar-button {
                    margin-top: 15px

                }
                </style>


                <g:render template="/layouts/sharedLayout/userLoginState"/>
                %{--<li>   <g:link controller="login" action="auth" style="margin-top: 15px " class="button radius alert top-bar-button" >Login</g:link>  </li>--}%

            </ul>

        </section>
    </nav>

</div>


