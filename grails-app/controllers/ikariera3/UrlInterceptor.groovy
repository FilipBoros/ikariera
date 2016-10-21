package ikariera3

import grails.compiler.GrailsCompileStatic
import org.springframework.web.servlet.support.RequestContextUtils

@GrailsCompileStatic
class UrlInterceptor {

    UrlInterceptor(){
        match(controllerName: '*',actionName:'*')
    }

        boolean before() {

            def locale = RequestContextUtils.getLocaleResolver(request)

            if (params.lang == null && locale == null) {

                // this is for redirection
                locale = new Locale("cs", "CZ")
                RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale)
                //redirect(controller: params.controller, action: params.action, params: ["lang": request.locale.language.toString()] + params)
            }
            return true;
        }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
