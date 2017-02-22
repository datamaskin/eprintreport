// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better 
// to create separate JavaScript files as needed.
//
//= require jquery
//= require_tree .
//= require_self

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);

    String.prototype.splice = function(
        index,
        howManyToDelete,
        stringToInsert /* [, ... N-1, N] */
    ){
        var characterArray = this.split( "" );

        Array.prototype.splice.apply(
            characterArray,
            arguments
        );

        return(
            characterArray.join( "" )
        );
    };

    function runExecApp(filename) {
        console.debug("Running application for: " + filename)

        return $.ajax({
            url: "/EprintReport/gwrptsExecApp",
            data: {
                fileName: filename
            }
        }).success(function( filename ) {
            if ( console && console.log ) {
                console.debug( "runExecApp success filename:", filename);
            }
        }).fail(function (filename) {
            if (console && console.log) {
                console.debug("runExecApp failed for filename:", filename);
            }
        }).error(function (xhr, ajaxOptions, thrownError){
            if(xhr.status==404) {
                alert(thrownError);
            }
        }).complete(function (filename) {
            console.debug("runExecApp complete filename:", filename);
        });
    }

    function writeBlob(filename) {

    	console.debug(filename);

        return $.ajax({
            url: "/EprintReport/gwrptsWriteBlob",
            data: {
                filename: filename
            }
        }).success(function( filename ) {
            if ( console && console.log ) {
                console.debug( "writeBlob success filename:", filename);
            }
        }).fail(function (filename) {
            if (console && console.log) {
                console.debug("Blob write failed for filename:", filename);
            }
        }).error(function (xhr, ajaxOptions, thrownError){
            if(xhr.status==404) {
                alert(thrownError);
            }
        }).complete(function (filename) {
            // console.debug("Doc complete wblob filename:", filename.responseText);
            getDoc(filename.responseText);
        });
    }

    function getDoc(filename) {
    	console.debug("getDoc:", filename);
        var mime = filename.split('.');

        return $.ajax({
            url: "/EprintReport/gwrptsFileUpload",
            data: {
            	fileName: filename
            },
			method: 'post'
        }).success(function( data ) {
            if ( console && console.log ) {
                // console.debug( "Doc success data:", data.responseText, filename);
            }
        }).fail(function (filename) {
            if (console && console.log) {
                console.debug("Doc upload failed.", filename);
            }
        }).error(function (xhr, ajaxOptions, thrownError){
            if(xhr.status==404) {
                alert(thrownError);
            }
        }).complete(function (data) {
            // console.debug("Doc complete data: ", data.responseText, filename);
            if (mime[1].toLowerCase() != 'xls')
                showDoc(data, filename);
            else
                saveXls(data, filename);
        });
    }

    function saveXls(data, fileName) {

        download(data.responseText, fileName, "application/vnd.ms-excel");

        return false;
    }

    function showDoc(data, fileName)
    {
    	// console.debug("showDoc:", fileName, data.responseText);

        var mime = fileName.split('.');

        var datauri;

        var win;

        switch (mime[1].toLowerCase()) {
            case 'pdf' : datauri = 'data:application/pdf;base64,' + data.responseText;
                $("<div>Display "+fileName+" in new window<br/>Or save file?</div>").dialog({
                    modal: true,
                    buttons: {
                        "Ok": function() {
                            win = window.open("", fileName, "width=1024,height=768,resizable=yes,scrollbars=yes,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,copyhistory=no,dependent=yes");
                            win.document.location.href = datauri;
                            $(this).dialog("close");
                        },
                        "Save": function() {
                            download(datauri, fileName, "application/pdf");
                            $(this).dialog("close");
                        }
                    }
                });

                break;
            case 'lis' :
            case 'txt' :
            case 'log' : datauri = 'data:text/plain,' 	+ data.responseText;
                $("<div>Display "+fileName+" in new window?</div>").dialog({
                    modal: true,
                    buttons: {
                        "Ok": function() {
                            win = window.open("", fileName, "width=1024,height=768,resizable=yes,scrollbars=yes,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,copyhistory=no,dependent=yes");
                            win.document.write('<html><head><title>Text</title><link rel="stylesheet" type="text/css" href="styles.css"></head><body>');
                            win.document.write('<pre>'+data.responseText+'</pre>');
                            win.document.write('</body></html>');
                            $(this).dialog("close");
                        },
                        "Cancel": function() {
                            $(this).dialog("close");
                        }
                    }
                });

                break;
            case 'csv' : datauri = 'data:text/html,' 	+ data.responseText;
                $("<div>Display "+fileName+" in new window<br/>Or save file?</div>").dialog({
                    modal: true,
                    buttons: {
                        "Ok": function() {
                            win = window.open("", fileName, "width=1024,height=768,resizable=yes,scrollbars=yes,toolbar=yes,location=yes,directories=yes,status=yes,menubar=yes,copyhistory=no,dependent=yes");
                            win.document.location.href = datauri;
                            $(this).dialog("close");
                        },
                        "Save": function() {
                            getDoc(mime[0]+".xls");
                            $(this).dialog("close");
                        }
                    }
                });

                break;
        }

    }

    (function() {

		function getChild ( row ) {
			var container = $('<div>Loading...</div>');

			var blobprom = $.ajax( {
				url: '/EprintReport/gwrptsSNB',
				data: {
					name: row.data()[1]
				},
				success: function ( json ) {
					if ( json.length === 0 ) {
						container.html( 'No data found' );
					}
					else {

						var out = [];
						var filename;
						for ( var i=0; i<json.length ;i++ ) {
							var data = json[i];

                            var mime = data['GW_RPTS_MIME'];
                            var name = data['GW_RPTS_OBJECT_NAME'];
                            var seq = data['GW_RPTS_SEQUENCE'];
                            var created = data['GW_RPTS_CREATE_DATE'];
                            var _data = "";
							var sdom = "";

							switch (data['GW_RPTS_MIME']) {
								case 'lis' :
									/*console.debug("Name: " + name);
									console.debug("Mime: " + mime);
									console.debug("Seq: " + seq);*/
									// console.debug("Data: " + _data);
									var period = '.';
                                    filename = seq+period+mime;
                                    filename = filename.trim();
                                    var lmime = '"'+mime+'"';
                                    _data = '<a id="'+seq+'" title="'+filename+'" href="#" onclick="writeBlob(\''+encodeURIComponent(filename)+'\')">'+created+'</a>';
                                    _data += '<div id="dialog"></div>';
                                    sdom += '<script>';
                                    sdom += '</script>';

									break;
								case 'pdf' :
									/*console.debug("Name: " + name);
									console.debug("Mime: " + mime);
									console.debug("Seq: " + seq);
                                    console.debug("Data: " + _data);*/
                                    period = '.';
                                    filename = seq+period+mime;
                                    filename = filename.trim();
                                    _data = '<a id="'+seq+'" title="'+filename+'" href="#" onclick="writeBlob(\''+encodeURIComponent(filename)+'\')">'+created+'</a>';

                                    sdom += '<script type="application/javascript">';
                                    sdom += '</script>';

									break;
								case 'log' :
									/*console.debug("Name: " + name);
									console.debug("Mime: " + mime);
									console.debug("Seq: " + seq);*/
                                    // console.debug("Data: " + _data);
                                    period = '.';
                                    filename = seq+period+mime;
                                    filename = filename.trim();
                                    _data = '<a id="'+seq+'" title="'+filename+'" href="#" onclick="writeBlob(\''+encodeURIComponent(filename)+'\')">'+created+'</a>';

									break;

								case 'txt' :
									/*console.debug("Name: " + name);
									console.debug("Mime: " + mime);
									console.debug("Seq: " + seq);*/
                                    // console.debug("Data: " + _data);
                                    period = '.';
                                    filename = seq+period+mime;
                                    filename = filename.trim();
                                    _data = '<a id="'+seq+'" title="'+filename+'" href="#" onclick="writeBlob(\''+encodeURIComponent(filename)+'\')">'+created+'</a>';

									break;
                                case 'csv' : //TODO - need to employ the Apache Commons HSSF/POI API for the Excel CSV files.
									/*console.debug("Name: " + name);
									console.debug("Mime: " + mime);
									console.debug("Seq: " + seq);*/
                                    // console.debug("Data: " + _data);

                                    period = '.';
                                    filename = seq+period+mime;
                                    filename = filename.trim();
                                    _data = '<a id="'+seq+'" title="'+filename+'" href="#" onclick="writeBlob(\''+encodeURIComponent(filename)+'\')">'+created+'</a>';
									break;
							}

							out.push(
								_data +
								sdom
							);
                        }

						container.html(
							out.join('<div></div>')
						);
					}
				},
				error: function ( json ) {
					container.html( 'Failed to load child content: '+row.data()[1] );
				}
			} );

			return container[0];
		}

		$(document).ready( function () {
			$('#EprintTable').on('click', 'tbody td.details-control', function () {
				var table = $('#EprintTable').DataTable();
				var tr = $(this).closest('tr');
				var row = table.row( tr );

				if ( row.child.isShown() ) {
					// This row is already open - close it
					row.child.hide();
					tr.removeClass('shown');
				}
				else {
					// Open this row
					row.child( getChild( row ) ).show();
					tr.addClass('shown');
				}
			} );
		} );
	})();

}
