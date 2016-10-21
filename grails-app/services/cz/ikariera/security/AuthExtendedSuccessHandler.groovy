package cz.ikariera.security

import grails.plugin.springsecurity.SpringSecurityUtils
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


public class AuthExtendedSuccessHandler
extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    protected String determineTargetUrl(HttpServletRequest request,
                                        HttpServletResponse response) {



        boolean hasAdmin = SpringSecurityUtils.ifAllGranted("ROLE_ADMIN");
        boolean hasCompany = SpringSecurityUtils.ifAllGranted("ROLE_COMPANY");
        boolean hasStudent = SpringSecurityUtils.ifAllGranted("ROLE_STUDENT");

        if (hasAdmin) {
            return adminUrl;

        } else if (hasCompany) {
            return companyUrl;

        } else if (hasStudent) {
            return studentUrl;

        } else {
            return super.determineTargetUrl(request, response);
        }
    }

    private String studentUrl;
    private String adminUrl;
    private String companyUrl;

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }


    public void setStudentUrl(String studentUrl) {
        this.studentUrl = studentUrl;
    }

    public void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }
}
