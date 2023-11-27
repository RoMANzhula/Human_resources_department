<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <h2>Meeting Details</h2>
        <p>Topic: ${meeting.topic}</p>
        <p>Description: ${meeting.description}</p>
        <p>Date of Event: ${meeting.dateOfEvent}</p>
        <p>Speaker(s):
            <#list meeting.speakers as speaker>
                ${speaker.username}<#sep>,
            </#list>
        </p>
        <p>Link: <a href="${meeting.link}">${meeting.link}</a></p>

        <h3>Select Coworkers</h3>
        <form action="/meetings/details/${meeting.id}/addCoworkers" method="post">
            <div class="form-group">
                <label for="coworkers">Coworkers</label>
                <div>
                    <#list allUsers as user>
                        <input type="checkbox" class="form-check-input" id="coworker_${user.id}" name="coworkers"
                               value="${user.id}">
                        <label class="form-check-label" for="coworker_${user.id}">${user.username}</label>
                    </#list>
                </div>
            </div>

            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Save Meeting</button>
            </div>
        </form>

        <#assign errorMessage = errorMessage!''>
    </div>

</@g.page>
