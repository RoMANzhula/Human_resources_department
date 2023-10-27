<#import "blocks/general.ftlh" as g>
<#import "blocks/login.ftlh" as l>

<@g.page>
    <div class="container text-center mb-3 mt-3">
        <h4>Greeting deer friend! And welcome in our HR Department</h4>
    </div>
${message?ifExists}
<form action="/registration" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><label> Phone: <input type="text" name="phone"/> </label></div>
    <div><label> Email: <input type="email" name="email"/> </label></div>
    <div><label> Special Code: <input type="text" name="inviteCode"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Sign In"/></div>
</form>
</@g.page>