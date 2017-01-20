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

	var lisTitle = /(^.+?)\d(\d)?([:,]\d\d)?((am)|(AM)|(Am)|(pm)|(PM)|(Pm))/;
	var lisDate = /(Date:.)((([0-9])|([0-2][0-9])|([3][0-1]))\-(Jan|JAN|Feb|FEB|MAR|Mar|APR|Apr|MAY|May|JUN|Jun|JUL|Jul|AUG|Aug|SEP|Sep|OCT|Oct|NOV|Nov|DEC|Dec)\-\d{4})|(T\+[0-9]+)/;
	var lisPage = /(?:Page:.\d)/;

    /*function pdfToPlainText(pdfData) {
        PDFJS.disableWorker = true;
        var pdf = PDFJS.getDocument(pdfData);
        pdf.then(getPages);
    }
    function getPages(pdf) {
        for (var i = 0; i < pdf.numPages; i++) {
            pdf.getPage(i + 1).then(getPageText);
        }
    }
    function getPageText(page) {
        page.getTextContent().then(function(textContent) {
            textContent.forEach(function(o) {
                $("#pdf").append(o.str + '</br>');
            });
        });
    }*/

	function matchAlternateAndWord(str, alt1, alt2, word) {
		var match_alt_word = "(?i)(?:\b"+alt1+"|"+alt2+"\b)(.*?)(?:"+word+")";
		var match;
		var matches = [];

		if (!match_alt_word.test(str)) {
			console.debug("matchAlternateAndWord no match: " + alt1 + "," + alt2 + "," + word);
			return;
		}

		console.debug("matchAlternateAndWord: " + alt1 + "," + alt2 + "," + word + " successful.");

		while (match = match_alt_word.exec(str)) {
			var index = 0;
			matches.push(match[index++]);
		}

		return matches;
    }

	function matchRepeatedNonWordChar(str, ch) {
		var match_repeated_nonword_char = "(?:"+ch+")+";
		var match;
		var matches = [];

		if (!match_repeated_nonword_char.test(str)) {
			console.debug("matchRepeatedNonWordChar no match: " + ch);
			return;
		}

        console.debug("matchRepeatedNonWordChar: " + ch + " successful.");

		while (match = match_repeated_nonword_char.exec(str)) {
			var index = 0;
			matches.push(match[index++]);
		}

		return matches;
    }

	function matchBetweenWords(str, word1, word2) {
		var match_between_words = new RegExp("(?i)(?:"+word1+")(.*?)(?:"+word2+")");
		var match;

		if (!match_between_words.test(str)) {
			console.debug("matchBetweenWords no match: " + word1 + " and " + word2);
			return;
		} else if (match = match_between_words.exec(str)) {
			console.debug("matchBetweenWords: " + word1 + " and " + word2 + " successful.");
		}

		return match;

    }
	function matchFirstAfterWord(str, word) {
		var match_first_after_word = new RegExp("(?i)(word)(\s\w+)");
		var match;

		if (!match_first_after_word.test(str)) {
			console.debug("matchFirsAfterWord no match for: " + word);
			return;
		} else if (match = match_first_after_word.exec(str)) {
			console.debug("matchFirstAfterWord: " + word + " successful.");
		} else {
			throw new Error("matchFirstAfterWord: " + word + " invalid match");
		}

		return match;
    }

	function matchUpToWord(str, word) {
		var match_up_to_word = new RegExp("(?i).*("+word+")");
		var match;

		if (!match_up_to_word.test(str)) {
			console.debug("matchUpToWord no match for: " + word);
			return;
		} else if (match = match_up_to_word.exec(str)) {
			console.debug("matchUpToWord: " + word + " successful.")
		} else {
			throw new Error("matchUpToWord: " + word + " invalid match.")
		}

		return match;
	}

    function getMatches(string, regex, index) {
        index || (index = 1); // default to the first capturing group
        var matches = [];
        var match;
        while (match = regex.exec(string)) {
            matches.push(match[index]);
        }
        return matches;
    }

    function getMatch(string, regex) {
        var match;

        if (!regex.test(string)) {
        	console.debug("match no match for: " + regex);
        	return;
		} else if (match = regex.exec(string)) {
        	console.debug("match: " + regex + " successful.")
        } else {
        	throw new Error("match: " + regex + " invalid match.");
		}
        return match;
    }

    function CSVToArray( strData, strDelimiter ){ //TODO needs attention on var creation

        strDelimiter = (strDelimiter || ",");

        var objPattern = new RegExp(
            (
                "(\\" + strDelimiter + "|\\r?\\n|\\r|^)" +

                "(?:\"([^\"]*(?:\"\"[^\"]*)*)\"|" +

                "([^\"\\" + strDelimiter + "\\r\\n]*))"
            ),
            "gi"
        );

        var arrData = [[]];

        var arrMatches = null;

        while (arrMatches = objPattern.exec( strData )){

            var strMatchedDelimiter = arrMatches[ 1 ];

            if (
                strMatchedDelimiter.length &&
                (strMatchedDelimiter != strDelimiter)
            ){
                arrData.push( [] );
            }

            if (arrMatches[ 2 ]){

                var strMatchedValue = arrMatches[ 2 ].replace(
                    new RegExp( "\"\"", "g" ),
                    "\""
                );
            } else {
                strMatchedValue = arrMatches[ 3 ];
            }

            arrData[ arrData.length - 1 ].push( strMatchedValue );
        }

        return( arrData );
    }

	(function() {

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

        function cleanString(str) {
			var outstr = "";
			for (var i=0; i<str.length; i++) {
				if (str.charCodeAt(i) < 127 && str.charCodeAt(i) > 31) {
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

                            // ascii = cleanString(ascii);

                            var mime = data.gw_rpts_mime+'<br>';
                            var name = data.gw_rpts_object_name+'<br>';
                            var seq  = data.gw_rpts_sequence;
                            var seqId = '#'+seq;

                            var _data = "";
							var sdom = "";
							var run = true;

							if (run) {
                                _data = '<div id="'+seq+'">'+ascii.substr(0, 150)+'</div>';
                                // _data = '<div id="'+seq+'">'+ascii+'</div>';
                                sdom += '<script>';
                                sdom += '$("'+seqId+'").click(function() {$(this).replaceWith("';

                                switch (data.gw_rpts_mime) {
                                    case 'lis' :
                                        console.debug("Name: " + name);
                                        console.debug("Mime: " + mime);
                                        console.debug("Seq: " + seq);
                                        console.debug("Data: " + _data);

                                        // sdom += $('#textinput').val(ascii);
										sdom += '<a href="javascript:; id="showall">'+ascii+'</a>'

                                        // potential use for PDF Blob data
                                        /*sdom += doc.addHTML($('#content'), 0, 0, function () {
                                            var blob = doc.output("blob");
                                            window.open(URL.createObjectURL(blob));
                                        });*/

                                        break;
                                    case 'pdf' :
                                        console.debug("Name: " + name);
                                        console.debug("Mime: " + mime);
                                        console.debug("Data: " + _data);
                                        console.debug("Seq: " + seq);
                                        sdom += $('#textinput').val(text);

                                        break;
                                    case 'log' :
                                        console.debug("Name: " + name);
                                        console.debug("Mime: " + mime);
                                        console.debug("Data: " + _data);
                                        console.debug("Seq: " + seq);
                                        sdom += $('#textinput').val(ascii);

                                        break;
                                    case 'txt' :
                                        console.debug("Name: " + name);
                                        console.debug("Mime: " + mime);
                                        console.debug("Data: " + _data);
                                        console.debug("Seq: " + seq);
                                        sdom += $('#textinput').val(ascii);

                                        break;
                                    case 'csv' :
                                        console.debug("Name: " + name);
                                        console.debug("Mime: " + mime);
                                        console.debug("Data: " + _data);
                                        console.debug("Seq: " + seq);

                                        sdom += '<div>' + ascii + '</div>';
                                        break;
                                }

                                sdom += '")})';
                                // sdom += '")';
                                sdom += '</script>';

                                out.push(
                                    name +
                                    mime +
                                    _data +
                                    sdom
                                );
                            }
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
