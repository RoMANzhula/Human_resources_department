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
        </#if>

        <div class="container text-center" data-bs-theme="dark">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit" class="btn btn-dark col-2 mt-4"><#if isRegisterForm>Create<#else>Sign_in</#if></button>
        </div>

<#--        <#if !isRegisterForm>-->
<#--            <div class="container text-center mt-3">-->
<#--                <a class="btn btn-info" href="/registration">Registration</a>-->
<#--            </div>-->
<#--        </#if>-->
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