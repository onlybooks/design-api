package kr.co.onlybooks.openapi.springdoc.dto;

import kr.co.onlybooks.openapi.springdoc.model.User;

import java.time.LocalDate;

public record UserOut(
        String id,
        String email,
        String fullName,
        LocalDate birthDate
) {
    public static UserOut fromEntity(User user) {
        return new UserOut(
                user.id(),
                user.email(),
                user.firstName() + " " + user.lastName(),
                user.birthDate()
        );
    }
}
