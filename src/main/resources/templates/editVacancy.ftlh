<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <div class="container text-center">
            <h2>Edit vacancy: ${vacancy.id}</h2>
        </div>

        <form action="/vacancies/${vacancy.id}/edit" method="post">
            <div class="form-group">
                <label for="profession">Profession:</label>
                <input type="text" class="form-control" id="profession" name="profession" required value="${vacancy.profession}" />
            </div>
            <div class="form-group">
                <label for="profLevel">Prof level:</label>
                <input type="text" class="form-control" id="profLevel" name="profLevel" required value="${vacancy.profLevel}" />
            </div>
            <div class="form-group">
                <label for="englishLevel">English level:</label>
                <input type="text" class="form-control" id="englishLevel" name="englishLevel" required value="${vacancy.englishLevel}" />
            </div>
            <div class="form-group">
                <label for="vacancyDescription">Vacancy description:</label>
                <textarea class="form-control" id="vacancyDescription" name="vacancyDescription" required>${vacancy.vacancyDescription}</textarea>
            </div>
            <div class="form-group">
                <label for="skillsDescription">Skills:</label>
                <textarea class="form-control" id="skillsDescription" name="skillsDescription" required>${vacancy.skillsDescription}</textarea>
            </div>
            <div class="form-group">
                <label for="dateOfStartJob">Date of starting:</label>
                <input type="datetime-local" class="form-control" id="dateOfStartJob" name="dateOfStartJob" required value="${vacancy.dateOfStartJob}">
            </div>
            <div class="form-group">
                <label for="project">Project</label>
                <div class="d-flex flex-wrap">
                    <#list allProjects as vacancyProject>
                        <div class="form-check mr-3 mb-2">
                            <input type="radio" class="form-check-input" id="project_${vacancyProject.id}" name="selectedProject"
                                   value="${vacancyProject.id}" <#if vacancy.vacancyProject?exists && vacancy.vacancyProject.id == vacancyProject.id>checked</#if>>
                            <label class="form-check-label" for="project_${vacancyProject.id}">${vacancyProject.name}<#sep>  ; </label>
                        </div>
                    </#list>
                </div>
            </div>
            <div class="form-group">
                <label for="isActive">Status (yes/no):</label>
                <input type="text" name="isActive" value="${vacancy.isActive()?string('yes', 'no')}" />
            </div>

            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Save Changes and go to vacancies</button>
            </div>
        </form>
    </div>

</@g.page>