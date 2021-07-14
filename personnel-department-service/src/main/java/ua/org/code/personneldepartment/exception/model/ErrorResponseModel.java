package ua.org.code.personneldepartment.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.org.code.personneldepartment.exception.base.RestException;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseModel {

    private List<FieldErrorModel> fieldErrors;

    public ErrorResponseModel(RestException e) {
        this.fieldErrors = e.getFieldErrors();
    }
}
