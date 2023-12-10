package kr.co.onlybooks.openapi.springdoc.model;

import java.time.LocalDate;

public record User(
        String id,
        String email,
        String password,
        String firstName,
        String lastName,
        LocalDate birthDate
) {
}
