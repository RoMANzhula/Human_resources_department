<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="container">
        <h2>Meetings</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Topic</th>
                <th>Date of Event</th>



            </tr>
            </thead>
            <tbody>
            <#list meetings as meeting>
                <tr>
                    <td>${meeting.topic}</td>
                    <td>${meeting.dateOfEvent}</td>



                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@g.page>