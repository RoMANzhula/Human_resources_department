<#import "blocks/general.ftlh" as g>
<#import "blocks/login.ftlh" as l>

<@g.page>
    <div class="container text-center mb-3 mt-3">
        <h4>Greeting deer friend! And welcome in our Big Family!</h4>
    </div>
${message?ifExists}

<@l.login "/registration" true />

</@g.page>