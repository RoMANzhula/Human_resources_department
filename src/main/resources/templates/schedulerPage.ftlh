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
                <#if task.completed>
                    <div class="card border-danger me-2" style="max-width: 18rem;">
                <#else>
                    <div class="card border-success me-2" style="max-width: 18rem;">
                </#if>
                    <div class="card-header bg-transparent border-success">
                        <small class="text-body-secondary">
                            Created -- ${task.getDateOfRegistration()}
                        </small>
                    </div>
                    <div class="card-body text-success">
                        <h5 class="card-title">${task.taskTopic}</h5>
                        -------------------------------
                        <p class="card-text">${task.taskText}</p>
                        -------------------------------
                        <p class="card-text">${task.deadline}</p>
                    </div>
                    <div class="card-footer bg-transparent border-success container">
                        <form method="post" action="/scheduler/updateTask">
                            <div class="row">
                                <input type="hidden" name="taskId" value="${task.id}" />
                                <#if task.completed>
                                    <input type="hidden" name="isCompleted" value="false" />
                                </#if>

                                <label class="col">
                                    <input type="checkbox" name="isCompleted" ${task.completed?string("checked", "")} /> -Solved
                                </label>

                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                <button class="btn btn-outline-info col-3" type="submit"
                                        style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;">
                                    Save
                                </button>
                            </div>
                            <div>
                                <#if task.completed>
                                    <small class="text-body-secondary">
                                        <br/>Done -- ${task.dateOfCompletion?string("yyyy-MM-dd HH:mm:ss")}
                                    </small>
                                </#if>
                            </div>
                        </form>
                    </div>
                </div>
            </#list>
        <#else>
            No tasks
        </#if>
    </div>

</@g.page>