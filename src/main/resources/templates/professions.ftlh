<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>Professions and Counts</h2>

        <table class="table table-striped table-sm">
            <tr>
                <th>Profession</th>
                <th>Count</th>
            </tr>
            <#list professionCounts as profession, count>
                <tr>
                    <td>${profession}</td>
                    <td>${count}</td>
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
    </div>

</@g.page>