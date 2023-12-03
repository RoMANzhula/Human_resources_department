<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>Meeting Details</h2>
    </div>

    <div class="container mt-3">
        <p><strong>Topic:</strong> ${meeting.topic}</p>
        <p><strong>Description:</strong> ${meeting.description}</p>
        <p><strong>Link:</strong> <a href="${meeting.link}">${meeting.link}</a></p>
        <p><strong>Date of Registration:</strong> ${meeting.dateOfRegistration}</p>
        <p><strong>Author of Meeting:</strong> ${meeting.authorOfMeeting.username}</p>
        <p><strong>Date of Event:</strong> ${meeting.dateOfEvent}</p>

        <p><strong>Speakers:</strong></p>
        <ul>
            <#list meeting.speakers as speaker>
                <li>${speaker.username}</li>
            </#list>
        </ul>

        <p><strong>Staff:</strong></p>
        <ul>
            <#list meeting.staff as staffMember>
                <li>${staffMember.username}</li>
            </#list>
        </ul>

        <p><strong>Projects:</strong></p>
        <ul>
            <#list meeting.projects as project>
                <li>${project.name}</li>
            </#list>
        </ul>
    </div>


</@g.page>