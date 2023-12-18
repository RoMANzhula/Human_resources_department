<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>Vacancy Details</h2>
    </div>

    <div class="container mt-3">
        <p><strong>Id:</strong> ${vacancy.id}</p>
        <p><strong>Date of Create:</strong> ${vacancy.dateOfCreate?string('dd.MM.yyyy HH:mm:ss')}</p>
        <p><strong>Author:</strong> ${vacancy.authorVacancy.username}</p>
        <p><strong>Topic:</strong> ${vacancy.profession}</p>
        <p><strong>Description:</strong> ${vacancy.authorVacancy.username}</p>
        <p><strong>Description:</strong> ${vacancy.vacancyDescription}</p>
        <p>
            <strong>Project:</strong>
            <a href="/project/project_info/${vacancy.vacancyProject.id}">${vacancy.vacancyProject.name}</a>
        </p>
        <p><strong>Date of starting:</strong> ${vacancy.dateOfStartJob}</p>
        <p><strong>Skills:</strong> ${vacancy.skillsDescription}</p>
    </div>


</@g.page>