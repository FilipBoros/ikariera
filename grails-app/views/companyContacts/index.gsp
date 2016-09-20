<%@ page import="cz.ikariera.company.Services" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="company">
</head>

<body>

<div class="row">

    <br>

    <div class="large-9 columns">

        <h1><g:message code="company.public.contacts" /> </h1>

    </div>

    <div class="large-3 columns">
        <br />

    <g:link controller="companyContacts" action="create" class="button right">
        <g:message code="create" />
    </g:link>


    </div>


</div>


<div class="row">

    <div class="large-12 columns">

        <hr>
    </div>


</div>


        <div class="row">

            <div class="large-8 columns">

%{--
String name

    String telephone

    String email
    String detailText

    String positionInCompany
--}%

        <table class="table">
            <thead>
            <tr>
                <th>${message(code : 'company.contacts.name')}</th>
                <th>${message(code : 'company.contacts.name.label')}</th>



                <th>${message(code: 'companyEmails.action.label')}</th>
            </tr>
            </thead>
            <tbody>
            <g:if test="${!contactsList}">
                <tr>
                    <td colspan="8"><g:message code="company.no.public.contacts.inforamtion" /> </td>
                </tr>
                </tbody>
                </table>
            </g:if>
            <g:else>
                <g:each in="${contactsList}" status="i" var="contact">
                    <tr>
                        <td>${contact.name} </td>
                        <td>  ${contact.telephone} <br />
                        ${contact.email}</td>

                        <td>

                            <g:render template="dropDownMenu" model="[contactInstance: contact]"/>

                        </td>
                    </tr>
                </g:each>
                </tbody>
                </table>

                <g:paginateFoundation total="${contactsList?.size()}" params="${params}"
                                      maxsteps="4"
                                      next="${message(code: "list.paginate.next")}"
                                      prev="${message(code: "list.paginate.prev")}"/>
            </g:else>


    </div>

    <div class="large-4 columns">
        <div class="panel">
            <h4><g:message code="company.public.contacts" /> </h4>
            <p>
                <g:message code="company.public.contacts.information" />

            </p>
        </div>




    </div>



</div>


<br/>
<br/>

</body>
</html>




