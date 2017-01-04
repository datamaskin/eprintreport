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

		function cleanString(str) {
			var outstr = "";
			for (var i=0; i<str.length; i++) {
				if (str.charCodeAt(i) <= 127 && str.charCodeAt(i) > 31) {
					outstr += str.charAt(i);
				}
			}
			return outstr;
		}

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
						var out = [];

						for ( var i=0; i<json.length ;i++ ) {
							var data = json[i];

							var text = '';
							var ascii = '';

							for ( var j=0; j<data.gw_rpts_blob.length ;j++ ) {
								text += String.fromCharCode( data.gw_rpts_blob[j] );
							}

                            ascii = hex2ascii(text);
							ascii = cleanString(ascii);

                            /*out.push(
                                'Name: '+data.gw_rpts_object_name+'<br>'+
                                'Mime: '+data.gw_rpts_mime+'<br>'+
                                'Sequence: '+data.gw_rpts_sequence+'<br>'+
                                'Data: '+ascii.substr(0, 150)+'...'
                                // 'Data: ' + ascii
                            );*/

                            var name = '"<ul>'+data.gw_rpts_object_name+'</ul>"';
                            var mime = '"<li>'+data.gw_rpts_mime+'</li>"';
                            var seq  = data.gw_rpts_sequence;
                            var seqId = '"#'+seq+'"';

                            var _data = '<div id="'+seq+'">'+ascii.substr(0, 200)+'</div>';

                            var sdom = '<script>';
                            sdom += '$('+seqId+').click(function() {$(this).replaceWith(';


                            switch (data.gw_rpts_mime) {
                                case 'lis' : 	console.debug("Name: " + name);
                                				console.debug("Mime: " + mime);
                                				console.debug("Seq: " + seq);
											 	console.debug("Data: " + _data);
												sdom += '"<p>'+ascii+'</p>"';
												sdom += ')})';
												sdom += '</script>';

                                    break;
                                case 'pdf' : 	console.debug("Name: " + name);
                                    			console.debug("Mime: " + mime);
                                    			console.debug("Data: " + _data);
                                    			console.debug("Seq: " + seq);
												sdom += '"<p>'+ascii+'</p>"';
												sdom += ')})';
												sdom += '</script>';

                                    break;
                                case 'log' : 	console.debug("Name: " + name);
                                    			console.debug("Mime: " + mime);
                                    			console.debug("Data: " + _data);
                                    			console.debug("Seq: " + seq);
												sdom += '"<p>'+ascii+'</p>"';
												sdom += ')})';
												sdom += '</script>';

                                    break;
                                case 'txt' : 	console.debug("Name: " + name);
                                    			console.debug("Mime: " + mime);
                                    			console.debug("Data: " + _data);
                                    			console.debug("Seq: " + seq);
												sdom += '"<p>'+ascii+'</p>"';
												sdom += ')})';
												sdom += '</script>';

                                    break;
                                case 'csv' : 	console.debug("Name: " + name);
                                    			console.debug("Mime: " + mime);
                                    			console.debug("Data: " + _data);
                                    			console.debug("Seq: " + seq);
												sdom += '"<p>'+ascii+'</p>"';
												sdom += ')})';
												sdom += '</script>';
                                    break;
                            }
                            out.push(
                                name +
                                mime +
                                _data +
								sdom
                            );
                        }

						container.html(
							out.join('<br><br>')
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
