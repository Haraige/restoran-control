package ua.org.code.personaldepartment.exception.base;

import lombok.Getter;
import lombok.Setter;
import ua.org.code.personaldepartment.exception.model.FieldErrorModel;

import java.util.List;

@Getter
@Setter
public class RestException extends RuntimeException {

    private List<FieldErrorModel> fieldErrors;

    public RestException(FieldErrorModel ... fieldErrors){
        this.fieldErrors = List.of(fieldErrors);
    }

    public RestException(List<FieldErrorModel> fieldErrors){
        this.fieldErrors = fieldErrors;
    }

}
