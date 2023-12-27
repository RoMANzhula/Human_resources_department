<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center mt-2">
            <h2>Edit Meeting</h2>
        </div>
        <form action="/meetings/edit/${meeting.id}" method="post">
            <div class="form-group">
                <label for="topic">Topic:</label>
                <input type="text" class="form-control" id="topic" name="topic" value="${meeting.topic}" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required>${meeting.description}</textarea>
            </div>
            <div class="form-group">
                <label for="dateOfEvent">Date of Event:</label>
                <input type="datetime-local" class="form-control" id="dateOfEvent" name="dateOfEvent" value="${meeting.dateOfEvent}" required>
            </div>
            <div class="form-group">
                <label for="link">Link:</label>
                <input type="text" class="form-control" id="link" name="link" value="${meeting.link}" required>
            </div>

            <div class="form-group">
                <label for="speakers">Speaker(s)</label>
                <div>
                    <#list allSpeakers as user>
                        <input type="checkbox" class="form-check-input" id="user_${user.id}" name="selectedUsers"
                               value="${user.id}"<#if meeting.speakers?seq_contains(user)> checked</#if>>
                        <label class="form-check-label" for="user_${user.id}">${user.username}</label>
                    </#list>
                </div>
            </div>

            <div class="form-group">
                <label for="project">Project(s)</label>
                <div>
                    <#list allProjects as project>
                        <input type="checkbox" class="form-check-input" id="project_${project.id}" name="selectedProjects"
                               value="${project.id}"<#if meeting.projects?seq_contains(project)> checked</#if>>
                        <label class="form-check-label" for="project_${project.id}">${project.name}</label>
                    </#list>
                </div>
            </div>

            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Save Changes and go to Coworkers</button>
            </div>
        </form>
    </div>

</@g.page>
