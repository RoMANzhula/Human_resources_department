<#assign
    allowed = SPRING_SECURITY_CONTEXT??
>

<#if allowed>
    <#assign
        user = SPRING_SECURITY_CONTEXT.authentication.principal
        name = user.getUsername()
        isHR_Manager = user.isHR_Manager()
    >

<#else>
    <#assign
        name = "forbidden"
        isHR_Manager = false
    >
</#if>