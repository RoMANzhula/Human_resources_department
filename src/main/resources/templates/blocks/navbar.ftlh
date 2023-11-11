<#import "login.ftlh" as l>
<#include "security.ftlh">

<#--<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">-->
<nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Company Name</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/">Home</a>
                </li>
                <#if isHR_Manager>
                    <li class="nav-item">
                        <a class="nav-link" href="/main">Add Employee</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/employee">All Employees</a>
                    </li>
                </#if>
                <#if currentUserId lt 0>
                    <li class="nav-item">
                        <a class="nav-link" href="/registration">Registration</a>
                    </li>
                <#elseif isAdmin || isHR_Manager>
                    <li class="nav-item">
                        <a class="nav-link" href="/coworker">All coworkers</a>
                    </li>
                </#if>
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/messages">All messages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/coworker-messages-list/${currentUserId?c}">My messages</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/new-message">New message</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/scheduler">Scheduler</a>
                    </li>
                </#if>


<#--                <li class="nav-item dropdown">-->
<#--                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">-->
<#--                        Dropdown-->
<#--                    </a>-->
<#--                    <ul class="dropdown-menu">-->
<#--                        <li><a class="dropdown-item" href="#">Action</a></li>-->
<#--                        <li><a class="dropdown-item" href="#">Another action</a></li>-->
<#--                        <li><hr class="dropdown-divider"></li>-->
<#--                        <li><a class="dropdown-item" href="#">Something else here</a></li>-->
<#--                    </ul>-->
<#--                </li>-->
<#--                <li class="nav-item">-->
<#--                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>-->
<#--                </li>-->
            </ul>
<#--            <form class="d-flex" role="search">-->
<#--                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">-->
<#--                <button class="btn btn-outline-success" type="submit">Search</button>-->
<#--            </form>-->
        </div>
    </div>

    <div class="navbar-text me-2">
        <#if user??>
            ${name}
        <#else>
            Login
        </#if>
    </div>

    <div class="me-2">
        <@l.logout />
    </div>
</nav>
