<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <h2>Project Information</h2>

    <p>
        Project Name: ${project.name}<br>
        Creation Date: ${project.getDateOfRegistration()?string("dd/MM/yyyy")}
        <!-- Add other project details as needed -->
    </p>

</@g.page>