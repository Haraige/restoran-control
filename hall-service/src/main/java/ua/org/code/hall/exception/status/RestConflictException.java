package ua.org.code.hall.exception.status;


import ua.org.code.hall.exception.base.RestException;
import ua.org.code.hall.exception.model.FieldErrorModel;

import java.util.List;

public class RestConflictException extends RestException {

    public RestConflictException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
    public RestConflictException(FieldErrorModel ... fieldErrors) {
        super(fieldErrors);
    }
}
