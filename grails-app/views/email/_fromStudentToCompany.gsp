<g:render template="/email/style/style"/>

<div style=" width: 800px;">
    Dobrý den,
    <br>

    máte nového zájemce o vaší nabídku práce:
    <a href="http://www.ikariera.cz/nabidky-prace/${msg.jobOfferId}">
        ${msg.jobOffer.positionName}
    </a>
    ${ msg.studentName + " " + msg.studentLastName}
    <br>
    <br>
    <p>

        <g:stripHtmlTags text="${msg.replyText}"/>


    </p>
    Odpovědět můžete po přihlášeni na portálu.

    <br/>
    <g:render template="/email/contact"/>
</div>