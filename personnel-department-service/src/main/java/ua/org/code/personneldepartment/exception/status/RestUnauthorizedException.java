package ua.org.code.personneldepartment.exception.status;

import ua.org.code.personneldepartment.exception.base.RestException;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;

import java.util.List;

public class RestUnauthorizedException extends RestException {

    public RestUnauthorizedException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
    public RestUnauthorizedException(FieldErrorModel ... fieldErrors) {
        super(fieldErrors);
    }
}
