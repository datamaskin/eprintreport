package edu.tamu.banner.eprintreport

import com.cedarsoftware.util.Converter
import com.google.gson.Gson
import edu.tamu.compassreport.CsvXls
import edu.tamu.compassreport.SystemCommandProcessor
import edu.tamu.compassreport.ToHtml
import grails.converters.JSON
import org.apache.commons.io.IOUtils
import org.apache.commons.io.monitor.FileEntry
import org.apache.commons.lang.SystemUtils
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GwRptsController {

    def compassReportsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        respond GwRpts.list(params), model:[gwRptsInstanceCount: GwRpts.count()]
    }

    def show(GwRpts gwRptsInstance) {
        BigDecimal bigint = new BigDecimal(21)

        byte[] b = compassReportsService.getGwRptsBlobPDFBytes(bigint)

        /*response.setHeader("Expires","0")
        response.setHeader("Content-disposition", "attachment; filename=file.pdf")
        response.setContentType("application/pdf")
        response.setContentLength(b.length)
        response.getOutputStream().write(b)
        response.getOutputStream().flush()*/
        respond gwRptsInstance

    }

    def create() {
        respond new GwRpts(params)
    }

    def gwrptsToJSON(final String report) {
        response.contentType = "application/json"
        def gwrpts = GwRpts.findAllByGwRptsObjectName(report)
        def result = [gwRptsInstance: gwrpts]
//        render "Params: report = $report"
        render result as JSON
//        def result = compassReportsService.getCompassReportsAsJSON('GURPDED')
    }

    def gwrptsBlobToJSON(int seq) { //http://localhost:8080/EprintReport/gwrptsBlobToJSON?seq=21

        BigDecimal bigint = new BigDecimal(seq)

//        byte[] b = compassReportsService.getGwRptsBlobPDFBytes(bigint)

        byte[] b = compassReportsService.readBlobToByte(bigint)
        response.setHeader("Expires","0")
        response.setHeader("Content-disposition", "attachment; filename=file.pdf")
        response.setContentType("application/pdf")
        response.setContentLength(b.length)
        response.getOutputStream().write(b, 0, b.length)
        response.getOutputStream()
        response.getOutputStream().flush()
        response.setStatus(200)
//        response.getOutputStream().close()
        /*def encoded = b.encodeHex()
        encoded*/


    }

    def gwrptsSeqNameBlob(final String name) { //http://localhost:8080/EprintReport/gwrptsSNB?name=tgrfeed
        log.debug "gwrptsSeqNameBlob: ${name}"

        def gwrpts = compassReportsService.getCompassReportsAsJSON(name)
        render gwrpts
    }

    def gwrptsBlob(final BigDecimal seq) {
        def gwrpts = compassReportsService.getGwRptsBlobAsJSON(seq)
        render gwrpts
    }

    byte[] gwrptsBlobAsByte(final BigDecimal seq) {
        byte[] gwrptsbytes = compassReportsService.getGwRptsBlobPDFBytes(seq)
        gwrptsbytes
    }

    byte[] gwrptsBlobBytes(final BigDecimal seq) {
        byte[] gwrptsblobbytes = compassReportsService.getGwRptsBlobBytes(seq);
        gwrptsblobbytes
    }

    byte[] getReadBlob(final BigDecimal seq) {
        byte[] blob = compassReportsService.readBlobToByte(seq)
        blob
    }

    def gwrptsReportInfoJSON(final String name) {
        def reports = compassReportsService.getReportInfoJSON(name)
        render reports
    }

    def writeBlob(String filename) {

        def tok = filename.tokenize(".")
        def name = tok[0]
        def ext = tok[1]

//        WriteBlob writeBlob = new WriteBlob()

        def total = compassReportsService.writeBlobToFile(filename)

        if (total > 0)
            render filename+"."+total
        else
            render filename

    }

    def upload(String fileName) { //gwrptsFileUpload @controller

        log.debug "upload: ${fileName}"

        def (name, mime, ndx) = fileName.tokenize('.')

        def location = "/"+grailsApplication.config.EprintReport.file.storage.location

        def paths = servletContext.getResourcePaths(location)
        def extdig = ~/^.+\.\d+$/
        def filepath = new LinkedHashMap(paths.size())
        def newpath = new ArrayList(paths.size())
        def index = 0
        filepath.put(index, name+"."+mime)
        newpath.add(name+"."+mime)

        for (path in paths) {
            if (extdig.matcher(path).matches()) {
                def len = path.split("/").length
                def str = path.split("/")[len-1]
                len = str.split("\\.").length
                def idx = str.split("\\.")[len-1]
                index = (int)Converter.convert(idx, Integer.class)
                newpath.add(str)
                filepath.put(index, str)
            }
        }

        paths.each {
            println "path ${it}"
        }

        newpath.each {
            println "newpath ${it}"
        }

        filepath.eachWithIndex { fileitem, i ->
//            println "Key: ${fileitem.key} has value: ${fileitem.value}"
            newpath.set((int)Converter.convert(fileitem.key, Integer.class), fileitem.value)
        }

        def realpath = servletContext.getRealPath(location)

        def input = null

        switch (mime.toLowerCase()) {
            case 'pdf' :
                        input = servletContext.getResourceAsStream(location + "/" + fileName).bytes.encodeAsBase64()
                break
            case 'lis' :
            case 'txt' :
            case 'log' :

//                        input = newpath
                        /*Gson gson = new Gson()
                        input = gson.toJson(newpath)*/
//                        InputStream inputStream = new FileInputStream(realpath+"/"+fileName)
                        input = servletContext.getResourceAsStream(location + "/" + fileName).getText('UTF-8')
//                        def file = new java.io.File(realpath + "/" + fileName)
//                        response.setContentType("APPLICATION/OCTET-STREAM")
//                        response.setHeader("Content-Disposition", "Attachment;Filename=\"${fileName}\"")
//                        file.withInputStream { response.outputStream << it }

//                        download(fileName, input)
                        /*BigDecimal seq = (BigDecimal)Converter.convert(name, BigDecimal.class)
                        GwRpts gwRpts = GwRpts.findByGwRptsSequence(seq)
                        input = gwRpts*/

                break
            case 'csv' :
                        input = servletContext.getResourceAsStream(location + "/" + fileName).getText('UTF-8')
                        CsvXls.csvToXLS(input, name, realpath)
                        ToHtml toHtml = ToHtml.create(realpath+"/"+name+".xls", new PrintWriter(new FileWriter(realpath+"/"+name+".html")))
                        toHtml.setCompleteHTML(true)
                        toHtml.printPage()
                        input = servletContext.getResourceAsStream(location + "/" + name+".html").getText('UTF-8')
//                        InputStream inputStream = new FileInputStream(realpath+"/"+fileName)
//                        input = inputStream.getText('UTF-8')
                break
            case 'xls' :
                        InputStream inputStream = new FileInputStream(realpath+"/"+fileName)
                        input = inputStream.getBytes().encodeAsBase64()
                break
        }
        render input
    }

    def saveZIP(String filename) {
//        render(template: "_downloader", filename)
        render(file: new File("/Users/datamaskinaggie/dev/grails/eis/eprintreport/web-app/WEB-INF/files/"+filename), fileName: filename, contentType: "text/plain", view: "downloader")
    }

    def execApp(String fileName) {

        /*Gson gson = new Gson()
        def filenames =  gson.fromJson(fileName, String[].class)
        def fn = filenames.toString()
        println fn*/
        def temppath =  "/"+grailsApplication.config.EprintReport.file.storage.location
        def cmdpath = grailsApplication.config.EprintReport.command.path
        def winpath = grailsApplication.config.EprintReport.windows.path

        SystemCommandProcessor scp = new SystemCommandProcessor()
        log.debug "execApp: ${fileName}"

        def paths = servletContext.getResourcePaths(temppath)
        paths.each {
            log.debug "path: ${it}"
            println "path ${it}"
        }

        def realpath = servletContext.getRealPath(temppath)

//        def (name, mime) = fileName.tokenize('.')

        File[] appFiles = null

        if (SystemUtils.IS_OS_MAC_OSX) {
            scp.setCommandDir(cmdpath)
            appFiles = new File(cmdpath).listFiles({ File file ->
                [
                        "echo"
                ].any { file.name.endsWith(it) }
            } as FileFilter)
        } else if (SystemUtils.IS_OS_WINDOWS_7) {
            scp.setCommandDir(winpath)
            appFiles = new File(winpath).listFiles({ File file ->
                [
                        "echo"
                ].any { file.name.endsWith(it) }
            })
        } else if (SystemUtils.IS_OS_SOLARIS || SystemUtils.IS_OS_SUN_OS || SystemUtils.IS_OS_LINUX) {
            scp.setCommandDir(cmdpath)
            appFiles = new File(cmdpath).listFiles({ File file ->
                [
                        "echo"
                ].any { file.name.endsWith(it) }
            } as FileFilter)
        } else {
            throw new Exception("Unknown OS...")
        }

        scp.setTimeoutInSeconds(120000)
        scp.commands.add(appFiles[0].name)
        scp.setCommands(scp.commands)
        scp.processContent(realpath+"/"+fileName)
//        scp.processContent(realpath+"/599.txt.zip")

        scp
    }

    def saveUpload(){
        String fileLabel = params.fileLabel
        MultipartFile uploadedFile = null

        String fileName=""
        try{
            if (request instanceof MultipartHttpServletRequest){
                //Get the file's name from request
                fileName = request.getFileNames()[0]
                //Get a reference to the uploaded file.
                uploadedFile = request.getFile(fileName)

                println "File name : ${uploadedFile.originalFilename}"
                println "File size : ${uploadedFile.size}"
                println "File label :"+ ${fileLabel}
                //appending file extension. In my requirement, user can modify original file name providing fileLabel. So, if user provides fileLabel, we //should save file with that name.
                fileLabel +=".${uploadedFile.originalFilename.split("\\.")[-1]}"

            }
            if (uploadedFile.empty) {
                flash.error = g.message(code: uploadedFile.size, default:'Empty cannot be uploaded') // put in uploadedFile.size instead of blank: ''
                redirect(action: 'show',id: user.id)
                return
            }
            //get uploaded file's inputStream
            InputStream inputStream = uploadedFile.inputStream
            //get the file storage location
//            def fileTobeStoredInDirPath = grailsApplication.config.myapp.file.storage.location
            def fileTobeStoredInDirPath = grailsApplication.config.EprintReport.file.storage.location
            //create a new file with fileLabel
            File file = new File(fileTobeStoredInDirPath, fileLabel)
            //This support both overriding and creating new file
            //If two of these fails, that means got some internal issue. May be new file creation permissions issue
            if (file.exists() || file.createNewFile()) {

                //to close the fileOutStream, opening it using withOutStream closure
                file.withOutputStream{fos->
                    IOUtils.copyLarge(inputStream, fos)
                }
                FileEntry fileEntry = new FileEntry()
                fileEntry.createdUser = session.user.id
                fileEntry.fileLabel = fileLabel
                fileEntry.save(flush:true)
                flash.success = g.message(code: null, default:'File uploaded successfully')
                redirect(action: 'show',id: user.id)
            }else{
                throw new RuntimeException("error while creating  ${file} at ${fileTobeStoredInDirPath}")
            }
        }
        catch (Exception e){
            flash.error = g.message(code: null, default: 'File upload failed due to internal errors. Please try again')
//            redirect(action: 'show',id: user.id)
            redirect(action: 'notFound')
        }
    }

    @Transactional
    def save(GwRpts gwRptsInstance) {
        if (gwRptsInstance == null) {
            notFound()
            return
        }

        if (gwRptsInstance.hasErrors()) {
            respond gwRptsInstance.errors, view:'create'
            return
        }

        gwRptsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect gwRptsInstance
            }
            '*' { respond gwRptsInstance, [status: CREATED] }
        }
    }

    def edit(GwRpts gwRptsInstance) {
        respond gwRptsInstance
    }

    @Transactional
    def update(GwRpts gwRptsInstance) {
        if (gwRptsInstance == null) {
            notFound()
            return
        }

        if (gwRptsInstance.hasErrors()) {
            respond gwRptsInstance.errors, view:'edit'
            return
        }

        gwRptsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect gwRptsInstance
            }
            '*'{ respond gwRptsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GwRpts gwRptsInstance) {

        if (gwRptsInstance == null) {
            notFound()
            return
        }

        gwRptsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GwRpts.label', default: 'GwRpts'), gwRptsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gwRpts.label', default: 'GwRpts'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
