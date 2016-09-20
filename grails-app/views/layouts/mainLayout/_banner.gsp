<div id="ikariera-logo" role="banner" style="position: relative;">

    <a class="home" href="${createLink(uri: '/')}" >
        <img src="${resource(dir: 'images/ikariera', file: 'ikariera-small.png')}" alt="iKariera"
             style="border-width: 0px; margin-bottom: 10px;"/>
    </a>


    <div style="position: absolute; right: 0px; top:0px;" id="top_banner"
         ajax_href="${createLink(controller: "publicBanner", action: "getActiveBannerWithPosition")}">

    </div>

</div>
