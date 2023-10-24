<html>
<head>
    <style>
        .centered-text {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="centered-text">
    <h3>"Good Big Company Family"</h3>
</div>

<div>
    <div>Add new employee</div>
    <form method="post" action="main">
        <div>
            <input type="text" name="firstName" placeholder="Input firs name."/>
            <input type="text" name="secondName" placeholder="Input second name."/>
            <input type="text" name="lastName" placeholder="Input last name."/>
            <input type="text" name="phone" placeholder="Input phone."/>
            <input type="text" name="additionalPhone" placeholder="Input additional phone."/>

            <button type="submit">Add employee</button>
        </div>
    </form>
</div>

<div>
    <form method="post" action="filter">
        <input type="text" name="lastName" />
        <button type="submit">Find by last name</button>
    </form>
</div>

<div class="centered-text">List of employee</div>
<div>
    <table>
        <thead>
        <tr>
            <th>Id employee</th>
            <th>First name</th>
            <th>Second name</th>
            <th>Last name</th>
            <th>Phone</th>
            <th>Additional phone</th>
        </tr>
        </thead>
        <tbody class="centered-text">
        <#list employees as employee>
            <tr> <!-- string -->
                    <td>${employee.id}</td>
                    <td>${employee.firstName}</td> <!-- column -->
                    <td>${employee.secondName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.phone}</td>
                    <td>${employee.additionalPhone}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>

</html>