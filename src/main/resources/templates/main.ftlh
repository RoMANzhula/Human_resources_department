<#import "blocks/general.ftlh" as g>

<@g.page>

<div class="container text-center">
    <h3>"Good Big Company Family"</h3>
</div>

<div class="container text-center">
    <div class="mb-2"><h4>Add new employee</h4></div>
    <form method="post">
        <div>
            <input type="text" name="firstName" placeholder="Input firs name" />
            <input type="text" name="secondName" placeholder="Input second name" />
            <input type="text" name="lastName" placeholder="Input last name" />
            <input type="text" name="phone" placeholder="Input phone" />
            <input type="email" name="email" placeholder="Input email" />

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add employee</button>
        </div>
    </form>
</div>
<div class="ms-4 mt-2">
    <form method="get" action="/main">
        <#if lastNameFilter??>
            <input type="text" name="lastNameFilter" value="${lastNameFilter}" />
        <#else>
            Last name not found
        </#if>
        <button type="submit">Find by last name</button>
    </form>
</div>

<div class="container text-center">
<#--    <button type="button" onclick="window.location.href='/employee'">List of employees</button>-->
    <label><a href="/employee">List of employees</a></label>
</div>
<div class="container text-center">
    <table class="table table-success table-sm">
        <thead>
        <tr>
            <th>Id employee</th>
            <th>First name</th>
            <th>Second name</th>
            <th>Last name</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody class="">
        <#if employees??>
            <#list employees as employee>
                <tr> <!-- string -->
                        <td>${employee.id}</td>
                        <td>${employee.firstName}</td> <!-- column -->
                        <td>${employee.secondName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.phone}</td>
                        <td>${employee.email}</td>
                </tr>
            <#else>
                No employees
            </#list>
        </#if>
        </tbody>
    </table>
</div>

</@g.page>