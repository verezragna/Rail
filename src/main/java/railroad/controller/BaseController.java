package railroad.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import railroad.Exceptions.ApiException;
import railroad.Exceptions.InternalServerErrorException;
import railroad.Responses.BaseResponse;
import railroad.Responses.FailedResponse;
import railroad.Responses.SuccessResponse;

public class BaseController {
    ResponseEntity createSuccessResponse() throws JsonProcessingException {
        return new ResponseEntity<BaseResponse>(new BaseResponse(true), HttpStatus.OK);
    }

    ResponseEntity createSuccessResponse(Object object) throws JsonProcessingException {
        return new ResponseEntity<SuccessResponse>(new SuccessResponse(object), HttpStatus.OK);
    }

    ResponseEntity createFailedResponse(ApiException ex) throws JsonProcessingException {
        return new ResponseEntity<FailedResponse>(new FailedResponse(ex), HttpStatus.OK);
    }

    ResponseEntity createInternalErrorResponse(Exception ex) {
        return new ResponseEntity<FailedResponse>(new FailedResponse(new InternalServerErrorException(ex)), HttpStatus.OK);
    }
}
