<html>
<head>
    <meta name="layout" content="administrator">
</head>

<body>


<div class="row">


    <div class="large-12 columns">

        <h1>Upload Hero obrázek</h1>

        <div class="panel">
            <p>

                Aplikace není schopná zmenšit obrzáky větší než 1000px na 1000px. Obří fotografie prosím nejdříve zmenšete.           <br/>

                Aplikace má z zatim neznámých příčin problém s některými specifickými znaky v názvech. Jedná so hlavně o pomlčky "-".    <br/>

                Minimální výška nebo šířka pro logo je 130pxx130px   <br/>

                Rozměry hero banneru jsou (š x v) 970 x 236 px. Lze používat i užší nebo jinak široké banery. Je jen potřeba dodržet u všech obrázků pro hero baner stejnou velikost.      <br/>

                Aplikace neumí pracovat s obrázky, které mají průhledné pozadí. Nahrávejte obrázky v barvě pozadí.

            </p>
        </div>


        <g:uploadForm action="uploadHero">


            <div class="row">
                <div class="large-12 columns">
                    <g:render template="/layouts/_fields/file" model="[
                            label: 'Obrazek',
                            name: 'file',
                            field: 'file',
                            placeholder: '',
                            inputInstance: pictureMediaInstance,
                            errorMessage: message(code: 'general.mandatory.atribute')]"/>
                </div>
            </div>


            <input type="submit"  class="button"/>

        </g:uploadForm>




    </div>
</div>

</body>
</html>