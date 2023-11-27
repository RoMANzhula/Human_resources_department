<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <h2>Create Meeting</h2>
        <form action="/meetings/save" method="post">
            <div class="form-group">
                <label for="topic">Topic:</label>
                <input type="text" class="form-control" id="topic" name="topic" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="dateOfEvent">Date of Event:</label>
                <input type="datetime-local" class="form-control" id="dateOfEvent" name="dateOfEvent" required>
            </div>

            <div class="form-group">
                <label for="project">Project</label>
                <div>
                    <#list allProjects as project>
                        <input type="checkbox" class="form-check-input" id="project_${project.id}" name="selectedProjects"
                               value="${project.id}">
                        <label class="form-check-label" for="project_${project.id}">${project.name}</label>
                    </#list>
                </div>
            </div>

            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Add Coworkers</button>
            </div>
        </form>
    </div>

</@g.page>
