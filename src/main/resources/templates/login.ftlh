<#import "blocks/general.ftlh" as g>
<#import "blocks/login.ftlh" as l>

<@g.page>
Login page
<@l.login "/login" />
<a href="/registration">Add new HR-manager</a>
</@g.page>