<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center mt-2">
        <h3>Scheduler Page</h3>
    </div>

    <#include "blocks/taskSchedulerCreator.ftlh">

    <div class="row row-cols-1 row-cols-md-3 g-4 m-3">
        <#if tasks??>
            <#list tasks as task>
                <div class="card border-success me-2" style="max-width: 18rem;">
                    <div class="card-header bg-transparent border-success">
                        <small class="text-body-secondary">
                            <p>
                                Created -- "${task.getDateOfRegistration()}
                            </p>
                        </small>
                    </div>
                    <div class="card-body text-success">
                        <h5 class="card-title">${task.taskTopic}</h5>
                        -------------------------------
                        <p class="card-text">${task.taskText}</p>
                        -------------------------------
                        <p class="card-text">${task.deadline}</p>
                    </div>
                    <div class="card-footer bg-transparent border-success">
                        <div class="mb-2 mt-2">
                            <form method="post" action="/scheduler/updateTask">
                                <input type="hidden" name="taskId" value="${task.id}" />
                                <label>
                                    <input type="checkbox" name="isCompleted" ${task.completed?string("checked", "")} /> - Task solved
                                </label>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button type="submit">Save</button>
                            </form>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            No tasks
        </#if>
    </div>

</@g.page>