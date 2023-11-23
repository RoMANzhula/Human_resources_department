<#import "blocks/general.ftlh" as g>

<@g.page>

    <div class="container text-center">
        <h2>Error Page</h2>
    </div>

    <div class="container mt-3">
        <p>An error occurred while processing your request.</p>
        <#if errorMessage??>
            <p>${errorMessage}</p>
        <#else>
            Error message is missing.
        </#if>
    </div>

</@g.page>