
window.main = {
    
    titleSize : 20,
    mmTitleSize : 7.6, // can be changed to fit the need
    textSize : 12,
    mmTextSize : 4.5,
    pageTopMargin : 20,

    previewPdf : function() {
        var text = $("#textinput")[0].value;

        var preview = $("#preview")[0];
        if ((text != null) && (preview != null)) {
            // create and feed pdf
            var doc = new jsPDF('p', 'mm', 'letter');
            var textLines = doc.setFont('Courier','').setFontSize(this.textSize).splitTextToSize(text, 190)
            doc.text(20, this.pageTopMargin + (textLines.length * this.mmTitleSize), textLines);

            var pdfOutput = doc.output('datauristring');

            // clean preview and add pdf
            preview.src = pdfOutput;
        }
    }
    
};