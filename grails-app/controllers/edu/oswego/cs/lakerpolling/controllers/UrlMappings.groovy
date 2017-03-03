package edu.oswego.cs.lakerpolling.controllers

class UrlMappings {

    static mappings = {
//        delete "/$controller/$id(.$format)?"(action:"delete")
//        get "/$controller(.$format)?"(action:"index")
//        get "/$controller/$id(.$format)?"(action:"show")
//        post "/$controller(.$format)?"(action:"save")
//        put "/$controller/$id(.$format)?"(action:"update")
//        patch "/$controller/$id(.$format)?"(action:"patch")

//        "/api" {
//            "/user/auth"(controller: 'application', action: 'auth', method: 'post')
//        }

        group "/api", {
            group "/user", {
                "/$accessToken"(controller: 'user', action: 'all', method : 'get')
                "/$accessToken/$id"(controller: 'user', action: 'findUser', method: 'get')
                "/auth/$idToken"(controller: 'user', action: 'auth', method : 'post')
            }

            group "/course", {
                "/$accessToken"(controller: 'course', action: 'index', method: 'get')
                "/$accessToken"(controller: 'course', action: 'create', method: 'put')
                "$accessToken"(controller: 'course', action: 'delete', method: 'delete')
            }

        }


        "/**"(controller: 'application', action: 'index')
    }
}
