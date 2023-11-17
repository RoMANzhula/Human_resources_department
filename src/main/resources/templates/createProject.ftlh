<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <h2>Create a New Project</h2>

    <div class="ms-3 me-3 mt-3">
        <form method="post" action="/project/create" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input topic</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.name}</#if>" name="name"
                       placeholder="Be sure to enter the topic of the project.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Input description</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
                          value="<#if project??>${project.description}</#if>" name="description"
                          placeholder="Be sure to enter the text of the description."></textarea>
            </div>

            <div class="input-group mb-3 mt-5" >
                <input type="file" name="fileContractName" class="form-control" id="inputGroupFile04"
                       aria-describedby="inputGroupFileAddon04" aria-label="Upload" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if project??>${project.id?c}</#if>" />
                <button type="submit" class="btn btn-outline-danger">Save message</button>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input budget</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.budget}</#if>" name="budget"
                       placeholder="Be sure to enter the budget of the project.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Make choice</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="${project.isActive()?string('yes', 'no')}" name="isActive"
                       placeholder="Be sure to enter yes/no if the project active or no.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input customer</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.customer}</#if>" name="customer"
                       placeholder="Be sure to enter the budget of the project.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input deadline</label>
                <input type="datetime-local" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.deadline}</#if>" name="deadline"
                       placeholder="Be sure to enter the deadline of the project.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input project direction</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.projectDirection}</#if>" name="projectDirection"
                       placeholder="Be sure to enter the direction of the project.">
            </div>

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input project responsible for the project</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if project??>${project.author}</#if>" name="author"
                       placeholder="Be sure to enter the responsible for the project.">
            </div>

            <div class="mb-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="id" value="<#if project??>${project.id?c}</#if>" />
                <button type="submit" class="btn btn-outline-danger">Save project</button>
            </div>
        </form>
    </div>


<#--    <form action="/project/create" method="post">-->
<#--        <label for="name">Project Name:</label>-->
<#--        <input type="text" id="name" name="name" required><br>-->

<#--        <label for="description">Description:</label>-->
<#--        <textarea id="description" name="description" required></textarea><br>-->

<#--        <label for="fileContractName">File Contract Name:</label>-->
<#--        <input type="text" id="fileContractName" name="fileContractName" required><br>-->

<#--        <label for="budget">Budget:</label>-->
<#--        <input type="number" id="budget" name="budget" required><br>-->

<#--        <label for="isActive">Active:</label>-->
<#--        <input type="checkbox" id="isActive" name="isActive"><br>-->

<#--        <label for="customer">Customer:</label>-->
<#--        <input type="text" id="customer" name="customer" required><br>-->

<#--        <label for="deadline">Deadline:</label>-->
<#--        <input type="datetime-local" id="deadline" name="deadline" required><br>-->

<#--        <label for="projectDirection">Project Direction:</label>-->
<#--        <input type="text" id="projectDirection" name="projectDirection" required><br>-->

<#--        <label for="author">Author:</label>-->
<#--        <input type="text" id="author" name="author" required><br>-->

<#--        <button type="submit">Create Project</button>-->
<#--    </form>-->

</@g.page>