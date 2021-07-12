package ua.org.code.personaldepartment.exception.status;

import ua.org.code.personaldepartment.exception.base.RestException;
import ua.org.code.personaldepartment.exception.model.FieldErrorModel;

import java.util.List;

public class RestBadRequestException extends RestException {

    public RestBadRequestException(FieldErrorModel... fieldErrors) {
        super(fieldErrors);
    }

    public RestBadRequestException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
}
