package kr.co.onlybooks.openapi.springdoc.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kr.co.onlybooks.openapi.springdoc.dto.UserIn;
import kr.co.onlybooks.openapi.springdoc.dto.UserOut;
import org.springframework.http.ResponseEntity;

public interface UserResource {

    @RequestBody(content = @Content(
            examples = {
                    @ExampleObject(name = "이순심",
                            value =
                                    """
                                    {
                                        "email": "abc@example.com",
                                        "password": "newPassWord01",
                                        "firstName": "순심",
                                        "lastName": "이",
                                        "birthDate": "1544-11-03"
                                    }
                                    """
                    ),
                    @ExampleObject(name = "강감춘",
                            value =
                                    """
                                    {
                                        "email": "xyz@example.com",
                                        "password": "newPassWord02",
                                        "firstName": "감춘",
                                        "lastName": "강",
                                        "birthDate": "941-07-08"
                                    }
                                    """
                    )
            }
    ))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "이순심",
                                            value =
                                                    """
                                                    {
                                                        "id": "76272713-50b0-4370-a8a9-097055911855",
                                                        "email": "abc@example.com",
                                                        "fullName": "순심 이",
                                                        "birthDate": "1544-11-03"
                                                    }
                                                    """
                                    ),
                                    @ExampleObject(name = "강감춘",
                                            value =
                                                    """
                                                    {
                                                        "id": "2fd167c5-65ca-4462-9791-cd9a45da4963",
                                                        "email": "xyz@example.com",
                                                        "fullName": "감춘 강",
                                                        "birthDate": "941-07-08"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            @ApiResponse(responseCode = "404", description = "사용자가 존재하지 않음",
                    content = @Content
            )
    })
    ResponseEntity<UserOut> updateUser(String id,
                                       UserIn userIn);
}
