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
            <input type="text" name="lastName" value="${employee.firstName}" />


            <div class="mb-2 mt-2">
                <#list roles as role>
                    <label>
                        <input type="checkbox" name="${role}"
                        ${employee.roles?seq_contains(role)?string("checked", "")} />${role}<#sep>,
                    </label>
                </#list>
            </div>
            <input type="hidden" name="employeeId" value="${employee.id}" />
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

    <div>
        <h>Recruiter: </h>
        <strong>${employee.getRecruiterName()}</strong>
    </div>
</@g.page>
