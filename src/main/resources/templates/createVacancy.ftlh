<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h2>Create New Vacancy</h2>
        </div>

        <form action="/vacancies/create" method="post">
            <div class="form-group">
                <label for="profession">Profession:</label>
                <input type="text" class="form-control" id="profession" name="profession" required />
            </div>
            <div class="form-group">
                <label for="profLevel">Prof level:</label>
                <input type="text" class="form-control" id="profLevel" name="profLevel" required />
            </div>
            <div class="form-group">
                <label for="englishLevel">English level:</label>
                <input type="text" class="form-control" id="englishLevel" name="englishLevel" required />
            </div>
            <div class="form-group">
                <label for="vacancyDescription">Vacancy description:</label>
                <textarea class="form-control" id="vacancyDescription" name="vacancyDescription" required></textarea>
            </div>
            <div class="form-group">
                <label for="skillsDescription">Skills:</label>
                <textarea class="form-control" id="skillsDescription" name="skillsDescription" required></textarea>
            </div>
            <div class="form-group">
                <label for="dateOfStartJob">Date of starting:</label>
                <input type="datetime-local" class="form-control" id="dateOfStartJob" name="dateOfStartJob" required>
            </div>
            <div class="form-group">
                <label for="project">Project</label>
                <div class="d-flex flex-wrap">
                    <#list allProjects as vacancyProject>
                        <div class="form-check mr-3 mb-2">
                            <input type="radio" class="form-check-input" id="project_${vacancyProject.id}" name="selectedProject"
                                   value="${vacancyProject.id}">
                            <label class="form-check-label" for="project_${vacancyProject.id}">${vacancyProject.name} <#sep>; </label>
                        </div>
                    </#list>
                </div>
            </div>

            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Add vacancy</button>
            </div>
        </form>
    </div>

</@g.page>