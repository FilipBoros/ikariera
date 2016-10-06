<script>
    $(document).ready(function(){
        var divs = $(".only150");
        for(var i=0; i<divs.length; i++){
            var element = divs.eq(i);
            element.text(element.text().substring(0,400))
        }
    });





</script>


<h3><g:message code="articles.h2.public.list.label"/></h3>
<hr>

<ul style="font-size: 85%; margin: 0; list-style: none;">
    <g:each in="${listArticles}"
            status="i" var="jobOfferInstance">

        <li >

            <g:link style="color: #008CBA; " class="actual-position-header" controller="articles" action="detail"
                    id="${jobOfferInstance.id}">

                <h8>
                    ${jobOfferInstance.header}
                </h8>
            </g:link>

            <br/>


                    <h5 style="font-weight: 300; font-size: 90%; text-align: justify" >
                        <div class="only150" style="overflow: hidden; text-overflow: ellipsis">
                            ${raw(jobOfferInstance?.bodyText)}
                        </div>
                    </h5>


        </li>

    </g:each>

</ul>

