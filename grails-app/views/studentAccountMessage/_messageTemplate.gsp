
<div class="row">

    <h4 class="subheader">${msg?.sender}</h4>

    <div class="large-8 columns">

        <br>

        <p>

            <g:stripHtmlTags text="${msg?.message}"/>

        </p>

    </div>




    <div class="large-4 columns">
        <div class="right">
            <g:formatDate date="${msg?.sendDate}" dateStyle="SHORT" type="datetime"/>
        </div>
    </div>
    <hr>
</div>
