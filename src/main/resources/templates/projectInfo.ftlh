<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <h2>Project Information</h2>
                <p>
                    <strong>Project Name:</strong> ${project.name}<br>
                    <strong>Creation Date:</strong> ${project.getDateOfRegistration()?string("dd/MM/yyyy")}<br>
                    <strong>Project Description:</strong> ${project.description}<br>
                    <strong>Project Status:</strong> ${project.active?string("In progress", "No active")}<br>
                    <strong>Project Customer:</strong> ${project.customer}<br>
                    <strong>Project Deadline:</strong> ${project.deadline}<br>
                    <strong>Project Budget:</strong> ${project.budget}<br>
                    <strong>Project Direction:</strong> ${project.projectDirection}<br>
                    <strong>Responsible for the Project:</strong> ${project.author.username}<br>
                    <strong>Contract document:</strong>
                    <#if project.fileContractName??>
                        <a href="${path}/img/${project.fileContractName}" target="_blank">Open doc</a>
                    <#else>
                        No document.
                    </#if><br>

                    <a href="${path}/project/${project.id}/create-team" class="btn btn-primary">Create Team</a>
                    <a href="${path}/project/${project.id}/show-team" class="btn btn-secondary">Show Team</a>
                </p>
            </div>
        </div>
    </div>
</@g.page>
