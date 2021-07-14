package ua.org.code.personneldepartment.service;

public interface PersonnelCheckForExistDataService {

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);

}
