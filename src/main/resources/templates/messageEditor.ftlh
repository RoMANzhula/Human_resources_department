<#import "blocks/general.ftlh" as g>

<@g.page>
    Message Editor

    <div class="ms-3 me-3 mt-3">
        <form method="post" action="/coworker-messages" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label">Input topic</label>
                <input type="text" class="form-control" id="exampleFormControlInput1"
                       value="<#if message??>${message.topic}</#if>" name="topic"
                       placeholder="Be sure to enter the topic of the message.">
            </div>
            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label">Input text</label>
                <textarea class="form-control" id="exampleFormControlTextarea1" name="text"
                          placeholder="Be sure to enter the text of the message.">
                    <#if message??>${message.text}</#if>
                </textarea>
            </div>

            <div class="input-group mb-3 mt-5">
                <input type="file" name="file" class="form-control" id="inputGroupFile04"
                       aria-describedby="inputGroupFileAddon04" aria-label="Upload" />
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input type="hidden" name="messageId" value="<#if message??>${message.id?c}</#if>" />
                <button type="submit" class="btn btn-outline-danger">Save message</button>
            </div>
        </form>
    </div>
</@g.page>
