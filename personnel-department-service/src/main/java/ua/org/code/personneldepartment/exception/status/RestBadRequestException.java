package ua.org.code.personneldepartment.exception.status;

import ua.org.code.personneldepartment.exception.base.RestException;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;

public class RestBadRequestException extends RestException {

    public RestBadRequestException(FieldErrorModel fieldError) {
        super(fieldError);
    }
}
