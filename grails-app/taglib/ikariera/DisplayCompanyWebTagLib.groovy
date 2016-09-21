package ikariera


class DisplayCompanyWebTagLib {


    def displayWeb = {
        attrs ->
            def web=""
            if(attrs?.web == null){
                out << ''
                return
            }
            if(attrs?.web?.startsWith('http://') == true){
                web= attrs.web
            }else{
                web='http://' + attrs.web
            }

            out << '<a href='+web+' target="_blank" >'+web+'</a>'
    }

}
