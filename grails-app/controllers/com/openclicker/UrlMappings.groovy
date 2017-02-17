package com.openclicker

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

    "/"(controller: 'dashboard', action: 'index')


//        "/api/users"(resource: User)

//        "/"(controller: 'dashboard', action: 'index')

//        group("/") {
//
//        }

//        group("/admin") {
//            "/$action?/$id?(.${format})?"(controller: 'dashboard')
////            "/$action?/$id?(.${format})?"(controller: 'dashboard')
//        }

//        group("/") {
//            "/$action?/$id?(.${format})?"(controller: 'dashboard')
//        }

//        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
