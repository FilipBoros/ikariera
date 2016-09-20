<li style="width: 120px; height: 160px; overflow: hidden; padding-top: 5px">
    <div style="height: 120px">
        <g:link class="th" action="show" id="${pictureMediaInstance?.id}">

            <g:render template="mediaTypePicture" model="[mediaType: pictureMediaInstance?.mediaType , filename:pictureMediaInstance?.newFilename ]"   />



        </g:link>
    </div>

    <div style="font-size: 10px; text-align: center; overflow: hidden"><strong>${fieldValue(bean: pictureMediaInstance, field: "name")}</strong>   <br/>
        ${pictureMediaInstance?.fileSize}

    </div>

</li>