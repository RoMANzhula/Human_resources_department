<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="container">
        <div class="container text-center">
            <h2>Meetings</h2>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Ident</th>
                <th>Topic</th>
                <th>Date of Event</th>
                <th>Author</th>
                <th>Speakers</th>
                <th>Link</th>
                <#assign showActions = false>
                <#list meetings as meeting>
                    <#if meeting.authorOfMeeting.id == currentUserId>
                        <#assign showActions = true>
                    </#if>
                </#list>
                <#if showActions>
                    <th>Actions</th>
                </#if>



            </tr>
            </thead>
            <tbody>
            <#list meetings as meeting>
                <tr>
                    <td>${meeting.id}</td>
                    <td>${meeting.topic}</td>
                    <td>${meeting.dateOfEvent}</td>
                    <td>${meeting.authorOfMeeting.getUsername()}</td>
                    <td>
                        <#list meeting.speakers as speaker>
                            ${speaker.username}<#sep>,
                        </#list>
                    </td>
                    <td>
                        <#assign linkAlreadyPrinted = false>
                        <#list meeting.staff as member>
                            <#if (member.id == currentUserId) && !linkAlreadyPrinted>
                                <a href="${meeting.link}">${meeting.link}</a>
                                <#assign linkAlreadyPrinted = true>
                            </#if>
                        </#list>
                    </td>
                    <td>
                        <#if meeting.authorOfMeeting.id == currentUserId>
                            <a href="/meetings/edit/${meeting.id}">Edit</a>,
                            <a href="/meetings/${meeting.id}">Info</a>
                        </#if>
                    </td>



                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@g.page>