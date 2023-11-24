<#import "blocks/general.ftlh" as g>
<#include "blocks/security.ftlh">

<@g.page>

    <div class="container">
        <h2>Create Meeting</h2>
        <form action="/meetings/create" method="post">
            <div class="form-group">
                <label for="topic">Topic:</label>
                <input type="text" class="form-control" id="topic" name="topic" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea class="form-control" id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="dateOfEvent">Date of Event:</label>
                <input type="datetime-local" class="form-control" id="dateOfEvent" name="dateOfEvent" required>
            </div>



            <div class="mt-3">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-secondary">Create Meeting</button>
            </div>
        </form>
    </div>
</@g.page>
