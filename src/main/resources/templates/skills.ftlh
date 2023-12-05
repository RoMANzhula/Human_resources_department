<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>List of Skills</h2>
    </div>

    <form action="/skills/create" method="post">
        <label for="name">Skill Name:</label>
        <input type="text" id="name" name="name" class="form-control mb-2" required>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Create Skill</button>
    </form>

    <form action="/skills/search" method="get">
        <label for="searchName">Search Skills:</label>
        <input type="text" id="searchName" name="name" class="form-control mb-2">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary">Search</button>
    </form>

    <ul class="list-group mt-3">
        <#if allSkills??>
            <#list allSkills as skill>
                <li class="list-group-item">${skill.name}</li>
            </#list>
        <#else>
            Not found
        </#if>
    </ul>

</@g.page>