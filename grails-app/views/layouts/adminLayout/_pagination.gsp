
<div class="pagination">

    <g:message code="default.table.list.pagination.text.part1"/>
    <span class="red">${displayedResults}

    </span>
    <g:message code="default.table.list.pagination.text.part2"/>
    <span class="red">${instanceTotal}</span>
    <g:message code="default.table.list.pagination.text.part3"/>



    <div class="pagination-right-navigation">
        <g:paginate total="${instanceTotal}" params="${params}"
                    maxsteps="4"
                    next="${message(code: "list.paginate.next")}"
                    prev="${message(code: "list.paginate.prev")}"/>
    </div>

</div>