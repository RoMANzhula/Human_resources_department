<#import "blocks/general.ftlh" as g>

<@g.page>
    <div class="container text-center">
        <h3>Profile of ${employee.getFirstName()}_${employee.getSecondName()}_${employee.getLastName()}</h3>
    </div>

    <form action="/employee" method="post" enctype="multipart/form-data">
        <div class="container text-center mb-2">
            <input type="file" name="filePhoto" />
            <input type="text" name="isActive" value="${employee.isActive()?string('yes', 'no')}" />
            <input type="text" name="firstName" value="${employee.firstName}" />
            <input type="text" name="secondName" value="${employee.secondName}" />
            <input type="text" name="lastName" value="${employee.lastName}" />


            <div class="mb-2 mt-2">
                <#list roles as role>
                    <label>
                        <input type="checkbox" name="${role}"
                        ${employee.employeeRoles?seq_contains(role)?string("checked", "")} />${role}<#sep>,
                    </label>
                </#list>
            </div>
            <input type="hidden" name="employeeId" value="${employee.id?c}" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Save changed information</button>
        </div>
    </form>

    <div>
        <div>
            <#if employee.filePhoto??>
                <img src="/img/${employee.filePhoto}"  class="img-thumbnail">
            </#if>
        </div>
    </div>

    <div class="ms-3">
        <h>Recruiter: </h>
        <strong>${employee.getRecruiterName()}</strong>
        <strong>--${employee.getDateOfRegistration()}</strong>
        <div>
            <strong>secret code:
                <#if employee.secretCodeForRole??>
                    ${employee.secretCodeForRole}
                <#else>
                    No Secret Code
                </#if></strong>
        </div>
    </div>

    <#if employee.employeeRoles?size!=0>
        <form action="/employee/sendActivationCode" method="post">
            <div class="ms-3">
                <input type="hidden" name="employeeId" value="${employee.id?c}" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit">Send Activation Code</button>
            </div>
        </form>
    </#if>


</@g.page>
