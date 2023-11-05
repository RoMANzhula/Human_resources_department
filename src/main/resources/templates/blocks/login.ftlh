<#include "security.ftlh">

<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username"/> </label></div>
        <div><label> Password: <input type="password" name="password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <#if isRegisterForm>
            <div><label> Phone: <input type="text" name="phone"/> </label></div>
            <div><label> Email: <input type="email" name="email"/> </label></div>
            <div><label> Special Code: <input type="text" name="secretCode"/> </label></div>
            <div class="g-recaptcha" data-sitekey="6Len5PkoAAAAAN39WA1nZO5Rf4bhIKbvor5Cn5bp"></div>
            <#if reCaptchaError??>
                <div class="alert alert-danger" role="alert">
                    ${reCaptchaError}
                </div>
            </#if>
        </#if>

        <div class="container text-center" data-bs-theme="dark">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-dark col-2 mt-4"><#if isRegisterForm>Create<#else>Sign_in</#if></button>
        </div>

    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-light"
                style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                type="submit"><#if user??>Sign_Out<#else>Log_in</#if></button>
    </form>
</#macro>