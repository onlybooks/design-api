package kr.co.onlybooks.openapi.springdoc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.onlybooks.openapi.springdoc.model.User;

import java.time.LocalDate;

public record UserOut(

        @Schema(description = "사용자 식별자")
        String id,

        @Schema(description = "이메일 주소")
        String email,

        @Schema(description = "전체 이름")
        String fullName,

        @Schema(description = "생년월일 yyyy-MM-dd")
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
