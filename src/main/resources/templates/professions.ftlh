<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>Professions and Counts</h2>

        <table class="table table-striped table-sm">
            <tr>
                <th>Profession</th>
                <th>Count</th>
                <th>Coworkers</th>
            </tr>
            <#list professionCounts as profession, count>
                <tr>
                    <td>${profession}</td>
                    <td>${count}</td>
                    <td>
                        <#list coworkersByRole[profession] as coworker>
                            <a href="/coworker/profile/${coworker.id}">
                                ${coworker.firstName} ${coworker.lastName}<#sep>;
                            </a>
                        </#list>
                    </td>
                </tr>
            </#list>
        </table>

        <p>Total Employees:
            <#assign totalEmployees = 0>
            <#list professionCounts?values as count>
                <#assign totalEmployees = totalEmployees + count>
            </#list>
            ${totalEmployees}
        </p>

<#--        <h2>Coworkers by Role</h2>-->

<#--        <#list coworkersByRole as role, coworkers>-->
<#--            <h3>${role}</h3>-->
<#--            <ul>-->
<#--                <#list coworkers as coworker>-->
<#--                    <li><a href="/coworker/profile/${coworker.id}">${coworker.firstName} ${coworker.lastName}</a></li>-->
<#--                </#list>-->
<#--            </ul>-->
<#--        </#list>-->

    </div>

</@g.page>
