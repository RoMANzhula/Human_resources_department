<#include "security.ftlh">

<#macro login path isRegisterForm>

    <div class="container text-center mt-3">
        <form action="${path}" method="post" class="needs-validation mx-auto col-6" novalidate>

            <div class="row mb-3">
                <label for="username" class="col-sm-4 col-form-label">User Name:</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="username" name="username" required/>
                </div>
            </div>

            <div class="row mb-3">
                <label for="password" class="col-sm-4 col-form-label">Password:</label>
                <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" name="password" required/>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <#if isRegisterForm>
                <div class="row mb-3">
                    <label for="phone" class="col-sm-4 col-form-label">Phone:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="phone" name="phone" required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="email" class="col-sm-4 col-form-label">Email:</label>
                    <div class="col-sm-8">
                        <input type="email" class="form-control" id="email" name="email" required/>
                    </div>
                </div>

                <div class="row mb-3">
                    <label for="secretCode" class="col-sm-4 col-form-label">Special Code:</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="secretCode" name="secretCode" required/>
                    </div>
                </div>

                <div class="mb-3 text-center">
                    <div class="g-recaptcha" data-sitekey="6Len5PkoAAAAAN39WA1nZO5Rf4bhIKbvor5Cn5bp"></div>
                    <#if reCaptchaError??>
                        <div class="alert alert-danger" role="alert">
                            ${reCaptchaError}
                        </div>
                    </#if>
                </div>
            </#if>

            <div class="container text-center">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-dark col-6 mt-4"><#if isRegisterForm>Create<#else>Sign_in</#if></button>
            </div>

        </form>
    </div>

</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-light"
                style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"
                type="submit"><#if user??>Sign_Out<#else>Log_in</#if></button>
    </form>
</#macro>
