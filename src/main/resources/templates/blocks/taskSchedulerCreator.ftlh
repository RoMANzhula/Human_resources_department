
<p xmlns="http://www.w3.org/1999/html">
    <div class="d-grid gap-2 col-5 mx-auto">
        <button class="btn btn-dark btn-lg btn-sm" type="button" data-bs-toggle="collapse" data-bs-target="#collapseWidthExample"
                aria-expanded="false" aria-controls="collapseWidthExample">
            Create New Task
        </button>
    </div>
</p>

<div class="d-grid gap-2 col-5 mx-auto">
<div style="min-height: 5px;" data-bs-theme="dark">
    <div class="collapse collapse-horizontal <#if task??>show</#if>" id="collapseWidthExample">
        <div class="card card-body" style="width: 800px;">
            <form method="post">
                <div class="input-group was-validated mb-3">
                    <span class="input-group-text" id="basic-addon2">Input topic</span>
                    <input type="text" class="form-control"
                           value="<#if task??>${task.taskTopic}</#if>" name="taskTopic"
                           placeholder="Be sure to enter the topic of the task."
                           aria-describedby="basic-addon2" required="" />
                </div>

                <div class="input-group was-validated mb-4 mt-4">
                    <span class="input-group-text" id="basic-addon1">Input text_</span>
                    <textarea class="form-control" aria-label="With textarea"
                              value="<#if task??>${task.taskText}</#if>" name="taskText"
                              placeholder="Be sure to enter the text of the task."
                              aria-describedby="basic-addon1" required="" ></textarea>
                </div>

                <div class="input-group">
                    <span class="input-group-text" id="basic-addon2">Input deadline</span>
                    <input type="datetime-local" class="form-control"
                           value="<#if task??>${task.deadline}</#if>" name="deadline"
                           placeholder="Be sure to enter the deadline of the task."
                           aria-describedby="basic-addon2" required="" />
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="id" value="<#if task??>${task.id?c}</#if>" />
                    <button type="submit" class="btn btn-outline-danger">Save task</button>
                </div>

            </form>
        </div>
    </div>
</div>
</div>