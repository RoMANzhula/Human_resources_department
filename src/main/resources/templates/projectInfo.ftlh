<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <h2>Project Information</h2>

    <p>
        Project Name: ${project.name}<br>
        Creation Date: ${project.getDateOfRegistration()?string("dd/MM/yyyy")}<br>
        Project Description: ${project.description}<br>
        Project Status: <#if !project.active??>No active<#else>In progress</#if><br>
        Project Customer: ${project.customer}<br>
        Project Deadline: ${project.deadline}<br>
        Project Direction: ${project.projectDirection}<br>
        Responsible for the Project: ${project.author}<br>
        Contract document: <a href="${path}/img/${project.fileContractName}" target="_blank">Open doc</a>


    </p>

</@g.page>