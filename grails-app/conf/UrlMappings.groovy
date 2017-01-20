class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/EprintReport"(resources: "gwRptsDef")
//        "/"(view:"index")
        "/"(controller: "gwRptsDef", action: "index")
        "500"(view:'/error')

        "/gwRpts" {
            controller = "gwRpts"
            action = "gwrptsToJSON"
        }
        "/gwrptsSNB" {
            controller = "gwRpts"
            action = "gwrptsSeqNameBlob"
        }
        "/gwrptsBlob" {
            controller = "gwRpts"
            action = "gwrptsBlobAsByte"
        }
        "/gwrptsWriteBlob" {
            controller = "gwRpts"
            action = "gwrptsWriteBlobFile"
        }
	}
}
