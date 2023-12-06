<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>List of Skills</h2>
    </div>

    <div class="container mt-4">
        <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
            <div class="col">
                <div class="d-grid gap-2">
                    <button class="btn btn-dark btn-lg btn-sm" type="button" data-bs-toggle="collapse" data-bs-target="#collapseWidthExample"
                            aria-expanded="false" aria-controls="collapseWidthExample">
                        Create New Skill
                    </button>
                </div>
            </div>

            <div class="col">
                <form action="/skills/search" method="get">
                    <div class="input-group input-group-sm">
                        <input type="text" name="filterName" class="form-control" placeholder="Search Skill">
                        <button type="submit" class="btn btn-secondary">Search</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="collapse collapse-horizontal" id="collapseWidthExample">
            <div class="card card-body" style="width: 800px;">
                <form action="/skills/create" method="post">
                    <div class="input-group">
                        <span class="input-group-text" id="basic-addon2">Input Skill</span>
                        <input type="text" class="form-control" name="name" placeholder="Be sure to enter the skill." aria-describedby="basic-addon2" required="" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button type="submit" class="btn btn-primary">Create Skill</button>
                    </div>
                </form>
            </div>
        </div>

        <#if allSkills??>
            <div class="container mt-3">
                <div class="row">
                    <#list allSkills as skill>
                        <div class="col-md-3">
                            <ul class="list-group">
                                <li class="list-group-item">${skill.name}</li>
                            </ul>
                        </div>
                    </#list>
                </div>
            </div>
        <#else>
            <p class="mt-3">Not found</p>
        </#if>
    </div>

</@g.page>
