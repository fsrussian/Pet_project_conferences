<#import "parts/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by conference description">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Conference
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="conferenceName" placeholder="Enter conference name" />
            </div>
            <div class="form-group">
                <label for="start">Start date:</label>

                <input type="date" id="start" name="conferenceDate"
                       value="2020-09-22"
                       min="2020-10-10" max="2021-10-10">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="text" placeholder="Description">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="fullDescription" placeholder="Full description">
            </div>


            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose picture</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add conference</button>
            </div>
        </form>
    </div>
</div>

</a>
<div class="card-columns">
    <#list conferences as conference>
    <div class="card my-3">
        <#if conference.fileName??>
        <img src="/images/${conference.fileName}" class="card-img-top" width="400" height="400">
    </#if>
    <div class="m-2">
        <center>
            <p>
                <b>The name of the conference:</b>
                <br/>
                ${conference.conferenceName}
            </p>
        <span> Organizer of Conference: ${conference.authorName}</span>
        </center>
    </div>
    <div class="card-footer text-muted">
        <center>
            <i>
                <b>Theme of the conference:</b>
                <br/>
                ${conference.text}
                <br/>
                <b>Date of the conference: </b>
                ${conference.dateget()}
            </i>
        <br/>
        <a href="/conferences/${conference.id}">Full description</a>
            <br/>
            <#if registr==conference.id>You have successfully registered for the conference: ${conference.id}
            <#else>
            <form method="post" >
                 <input type="hidden" name="_csrf" value="${_csrf.token}" />
                 <input type="hidden" name="idconference" value="${conference.id}" />
                 <div class="form-group">
                        <button type="submit" class="btn btn-primary">Registration for the conference</button>
                 </div>
            </form>
            </#if>
        </center>
    </div>
</div>


</#list>
</div>
</@c.page>



