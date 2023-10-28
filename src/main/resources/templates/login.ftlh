<#import "blocks/general.ftlh" as g>
<#import "blocks/login.ftlh" as l>

<@g.page>

${message?ifExists}
<@l.login "/login" false />

</@g.page>