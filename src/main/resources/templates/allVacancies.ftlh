<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h1>All Vacancies</h1>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Profession</th>
                    <th>Prof Level</th>
                    <th>Date of start job</th>
                    <th>Actions</th>



                </tr>
                </thead>
                <tbody>
                <#list allVacancies as vacancy>
                    <tr>
                        <td>${vacancy.id}</td>
                        <td>${vacancy.profession}</td>
                        <td>${vacancy.profLevel}</td>
                        <td>${vacancy.dateOfStartJob}</td>
                        <td>
                            <#if vacancy.authorVacancy.id == currentUserId>
                                <a href="/meetings/${vacancy.id}/edit">Edit</a>,
                            </#if>
                            <a href="/meetings/${vacancy.id}">Info</a>
                        </td>



                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

</@g.page>