<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container text-center">
        <h2>Create a New Project</h2>
    </div>

    <div class="ms-3 me-3 mt-3">
            <form method="post" action="/project/create" enctype="multipart/form-data">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input topic</span>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           value="<#if project??>${project.name!""}</#if>" name="name"
                           placeholder="Be sure to enter the topic of the project.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input description</span>
                    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="description"
                              placeholder="Be sure to enter the text of the description."
                              value="<#if project??>${project.description!""}</#if>"></textarea>
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input budget</span>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           value="<#if project??>${project.budget!""}</#if>" name="budget"
                           placeholder="Be sure to enter the budget of the project.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">This project is active</span>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           value="${project.isActive()?string('yes', 'no')}" name="isActive"
                           placeholder="Be sure to enter yes/no if the project active or no.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input customer</span>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           value="<#if project??>${project.customer!""}</#if>" name="customer"
                           placeholder="Be sure to enter the budget of the project.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input deadline</span>
                    <input type="datetime-local" class="form-control" id="exampleFormControlInput1"
                           value="<#if project??>${project.deadline!""}</#if>" name="deadline"
                           placeholder="Be sure to enter the deadline of the project.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">Input project direction</span>
                    <input type="text" class="form-control" id="exampleFormControlInput1"
                           value="<#if project??>${project.projectDirection!""}</#if>" name="projectDirection"
                           placeholder="Be sure to enter the direction of the project.">
                </div>

                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon2">Loading document</span>
                    <input type="file" name="file" class="form-control" />
                </div>

                <div class="container text-center">
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <input type="hidden" name="id" value="<#if project?has_content && project.id?has_content>${project.id?c}</#if>" />
                    <button type="submit" class="btn btn-outline-danger">Save project</button>
                </div>
            </form>
    </div>


</@g.page>