package edu.oswego.cs.lakerpolling.services

import grails.web.servlet.mvc.GrailsParameterMap
import org.springframework.http.HttpStatus

/**
 * Precondition checking service.
 */
class PrecheckService {

    /**
     * Class containing data about checks.
     */
    class CheckResults {

        private boolean success = true
        private int error
        private String message

        boolean getSuccess() {
            return success
        }

        int getError() {
            return error
        }

        String getMessage() {
            return message
        }

    }

    /**
     * Checks that all given parameters exist in params map. Stops upon the first failure.
     * @param paramsMap - The parameters map for the request.
     * @param parameters - The required parameters.
     * @param results - An optional parameter. Failure results will be recorded in this object.
     * @return A results object containing check results.
     */
    CheckResults require(GrailsParameterMap paramsMap, List<String> parameters, CheckResults results = new CheckResults()) {
        if(parameters.size() > 0) {

            for(String param in parameters) {
                if(paramsMap[param] == null) {
                    results.with {
                        success = false
                        error = HttpStatus.BAD_REQUEST.value()
                        message = "Required parameter $param is missing"
                    }
                    break
                }
            }

        }
        results
    }

}
