<#import "blocks/general.ftlh" as g>

<@g.page>
    <div class="container text-center">
        <h3>List of all employees</h3>
    </div>

    <div class="ms-4 mt-2">
        <form method="get" action="/employee">
            <#if lastNameFilter??>
                <input type="text" name="lastNameFilter" value="${lastNameFilter}" />
            <#else>
                Last name not found
            </#if>
            <button type="submit">Find by last name</button>
        </form>
    </div>

    <div class="container text-center">
    <table class="table table-striped-columns">
        <thead>
            <tr>
                <th>Date of registration</th>
                <th>Active</th>
                <th>First name</th>
                <th>Second name</th>
                <th>Last name</th>
                <th>Profession</th>
                <th>Go to profile</th>
            </tr>
        </thead>
        <tbody>
            <#list employees as employee>
                <tr>
                    <td>${employee.getDateOfRegistration()}</td>
                    <td>${employee.isActive()?string('yes', 'no')}</td>
                    <td>${employee.getFirstName()}</td>
                    <td>${employee.getSecondName()}</td>
                    <td>${employee.getLastName()}</td>
                    <td><#list employee.employeeRoles as role>${role}<#sep>, </#list></td>
                    <td><a href="/employee/${employee.id?c}">profile_page</a></td>
                </tr>
            </#list>
        </tbody>
    </table>
    </div>

    <div>
        <button class="ms-3" type="button" onclick="window.location.href='/main'">Go to create employee</button>
    </div>

</@g.page>