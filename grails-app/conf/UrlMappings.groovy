class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

//        "/EprintReport"(resources: "gwRptsDef")
        "/"(view:"/index")
//        "/"(view:"index")
//        "/"(controller: "gwRptsDef", action: "index")
//        "/"(controller: "gwRptsDef", action: "getReportDefJSON")
//        "/"(controller: "gwRptsDef", action: "getReportDefJSON_H2")
        "500"(view:'/error')
        "/gwRptsDefJSON_H2" {
            controller = "gwRptsDef"
            action = "gwrptsReportDefJSON_H2"
        }
        /*"/gwRpts" {
            controller = "gwRpts"
            action = "gwrptsToJSON"
        }*/
        "/gwrptsSNB" {
            controller = "gwRpts"
            action = "gwrptsSeqNameBlob"
        }
        /*"/gwrptsBlob" {
            controller = "gwRpts"
            action = "gwrptsBlobAsByte"
        }
        "/gwrptsBlobToJSON" {
            controller = "gwRpts"
            action = "gwrptsBlobToJSON"
        }
        "/gwrptsWriteBlob" {
            controller = "gwRpts"
            action = "writeBlob"
        }
        "/gwrptsFileUpload" {
            controller = "gwRpts"
            action = "upload"
        }
        "/gwrptsExecApp" {
            controller = "gwRpts"
            action = "execApp"
        }
        "/saveZIP" {
            controller = "gwRpts"
            action = "saveZIP"
        }
        "/reportInfoJSON" {
            controller = "gwRpts"
            action = "getReportInfoJSON"
        }
        "/reportDefJSON" {
            controller = "gwRptsDef"
            action = "getReportDefJSON"
        }*/
        "/showPeople" {
            controller = "Dt"
            action = "showPeople"
        }
	}
}
