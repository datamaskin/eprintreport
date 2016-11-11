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
	}
}
