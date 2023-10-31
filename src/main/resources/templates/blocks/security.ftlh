<#assign
    allowed = SPRING_SECURITY_CONTEXT??
>

<#if allowed>
    <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isHR_Manager = user.isHR_Manager()
        currentUserId = user.getId()
    >

<#else>
    <#assign
        name = "forbidden"
        isHR_Manager = false
        currentUserId = -1
    >
</#if>