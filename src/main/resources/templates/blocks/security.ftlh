<#assign
    allowed = SPRING_SECURITY_CONTEXT??
>

<#if allowed>
    <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isHR_Manager = user.isHR_Manager()
        isProjectManager = user.isProjectManager()
        currentUserId = user.getId()
        isAdmin = user.isAdmin()
    >

<#else>
    <#assign
        name = "forbidden"
        isHR_Manager = false
        isProjectManager = false
        isAdmin = false
        currentUserId = -1
    >
</#if>