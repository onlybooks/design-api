package kr.co.onlybooks.openapi.springdoc.dto;

import kr.co.onlybooks.openapi.springdoc.model.User;

import java.time.LocalDate;

public record UserIn(
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDate birthDate
) {
    public User toEntity(String id) {
        return new User(
                id,
                email,
                password,
                firstName,
                lastName,
                birthDate
        );
    }
}
