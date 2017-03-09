package edu.oswego.cs.lakerpolling.util

import org.springframework.http.HttpStatus


class QueryResult<T> {

    boolean success = true
    int errorCode = HttpStatus.OK.value()
    String message = HttpStatus.OK.reasonPhrase
    T data = null

}
