package kr.co.onlybooks.openapi.springdoc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.co.onlybooks.openapi.springdoc.model.User;

import java.time.LocalDate;

public record UserIn(

        @Schema(description = "이메일 주소")
        @NotNull
        @Email
        @Size(max = 50)
        String email,

        @Schema(description = "비밀번호")
        @NotNull
        @Size(min = 8, max = 20)
        String password,

        @Schema(description = "이름")
        @NotNull
        @Size(max = 30)
        String firstName,

        @Schema(description = "성")
        @NotNull
        @Size(max = 30)
        String lastName,

        @Schema(description = "생년월일 yyyy-MM-dd")
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
