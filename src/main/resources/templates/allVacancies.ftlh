<#import "blocks/general.ftlh" as g>

<@g.page>

    <body>

    <h1>All Vacancies</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Profession</th>
            <th>Prof Level</th>
            <th>English Level</th>



        </tr>
        </thead>
        <tbody>
        <#list allVacancies as vacancy>
            <tr>
                <td>${vacancy.id}</td>
                <td>${vacancy.profession}</td>
                <td>${vacancy.profLevel}</td>
                <td>${vacancy.englishLevel}</td>



            </tr>
        </#list>
        </tbody>
    </table>

    </body>

</@g.page>