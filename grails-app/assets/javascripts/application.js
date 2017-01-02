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

	(function() {

        function hex2ascii(hexx) {

        	if (hexx.length % 2 == 1)
        		hexx = '0'+hexx;

            hexx = hexx.toString();//force conversion

            var str = '';
            for (var i = 0; i < hexx.length; i += 2)
                str += String.fromCharCode(parseInt(hexx.substr(i, 2), 16));
            return str;
        }

		function getChild ( row ) {
			var container = $('<div>Loading...</div>');

			$.ajax( {
				url: '/EprintReport/gwrptsSNB',
				data: {
					name: row.data()[1]
				},
				success: function ( json ) {
					if ( json.length === 0 ) {
						container.html( 'No data found' );
					}
					else {
						var data = json[0];

						var text = '';
						var ascii = '';

						for ( var i=0, ien=data.gw_rpts_blob.length ; i<ien ; i++ ) {
							text += String.fromCharCode( data.gw_rpts_blob[i] );
						}

						ascii = hex2ascii(text);

						container.html(
							'Name: '+data.gw_rpts_object_name+'<br>'+
							'Mime: '+data.gw_rpts_mime+'<br>'+
							'Sequence: '+data.gw_rpts_sequence+'<br>'+
							// 'Data: '+text.substr(0, 30)+'...'
							'Data: ' + ascii
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
