<#import "blocks/general.ftlh" as g>
<#import "blocks/login.ftlh" as l>

<@g.page>
Login page
Add new coworker
${message?ifExists}
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Phone: <input type="text" name="phone"/> </label></div>
    <div><label> Email: <input type="email" name="email"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Sign In"/></div>
</form>
</@g.page>