function(settings, json) {
    <g:if test="${attr.initComplete}">
        %{--Call initComplete function provided in attributes.--}%
        (${attr.initComplete})(settings, json);
    </g:if>
    $("#${tableDefinition.name}HeadingLoading").toggle(false);
    var numRows = this.api().page.info().recordsTotal;
    $('.${tableDefinition.name}Count').html(numRows);

    %{--Update the column search input widths.--}%
    <g:if test="${tableDefinition.columnSearching}">
        adjust${tableDefinition.name}ColumnSearchWidths();
    </g:if>
}