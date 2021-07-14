package ua.org.code.personneldepartment.exception.base;

import lombok.Getter;
import lombok.Setter;
import ua.org.code.personneldepartment.exception.model.FieldErrorModel;

@Getter
@Setter
public class RestException extends RuntimeException {

    private FieldErrorModel fieldError;

    public RestException(FieldErrorModel fieldError){
        this.fieldError = fieldError;
    }

}
