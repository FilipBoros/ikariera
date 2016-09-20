<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>
<div class="row">

    <br/>


    <div class="large-8 columns">

        <h1>${feedback?.subject}</h1>

    </div><br/>
    <div class="large-4 columns">
        <div class="headerNavigationRight right">${feedback?.dateCreated}</div>

    </div>
</div>

<div class="row">
    <div class="large-8 columns">

        <br>

        <p>
            ${feedback?.text}
        </p>

    </div>
</div>
</body>
</html>