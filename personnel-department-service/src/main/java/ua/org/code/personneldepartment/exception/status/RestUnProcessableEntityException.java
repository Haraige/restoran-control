package ua.org.code.personneldepartment.exception.status;

import ua.org.code.personneldepartment.exception.base.RestException;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;

import java.util.List;

public class RestUnProcessableEntityException extends RestException {

    public RestUnProcessableEntityException(List<FieldErrorModel> fieldErrors) {
        super(fieldErrors);
    }
    public RestUnProcessableEntityException(FieldErrorModel ... fieldErrors) {
        super(fieldErrors);
    }
}
