<#import "blocks/general.ftlh" as g>

<@g.page>


    <div class="container text-center">
        <h2>Error Page</h2>
    </div>

    <div class="container mt-3">
        <p>Виникла помилка під час обробки вашого запиту.</p>
        <p>${errorMessage}</p>

    </div>

<#--    <div class="container text-center">-->
<#--        <h1>Sorry, you have an error!</h1>-->
<#--    </div>-->
</@g.page>