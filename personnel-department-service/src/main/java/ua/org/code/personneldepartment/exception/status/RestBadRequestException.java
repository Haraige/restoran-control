package ua.org.code.personneldepartment.exception.status;


import ua.org.code.personneldepartment.exception.base.RestException;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;

import java.util.List;

public class RestBadRequestException extends RestException {

    public RestBadRequestException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
    public RestBadRequestException(FieldErrorModel ... fieldErrors) {
        super(fieldErrors);
    }
}
