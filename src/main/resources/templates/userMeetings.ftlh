<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h2>My meetings</h2>
        </div>
        <table class="table table-dark table-sm">
            <thead>
            <tr>
                <th>Ident</th>
                <th>Topic</th>
                <th>Date of Event</th>
                <th>Author</th>
                <th>Speakers</th>
                <th>Link</th>



            </tr>
            </thead>
            <tbody>
            <#list userMeetings as meeting>
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
                    <td><a href="${meeting.link}">${meeting.link}</a></td>



                </tr>
            </#list>
            </tbody>
        </table>
    </div>

</@g.page>