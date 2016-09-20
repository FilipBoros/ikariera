


%{--
<g:render template="/studentAccount/userPersonalsNoPass" model="['userInstance': userInstance, 'studentInstance': userInstance?.studentAccount]" />




<g:render template="/layouts/formLayout/elements/studentOrganisation" model="['studentInstance': userInstance?.studentAccount]" />
--}%




<g:render template="/studentAccountPersonalDetails/form" model="['userInstance': userInstance]" />
%{--<g:render template="/studentAccountPersonalDetails/form" model="['studentInstance': userInstance?.studentAccount]" />--}%



