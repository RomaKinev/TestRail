package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class User {

    @Builder.Default
    private String fullName = "John Doe";
    @Builder.Default
    private String email = "johndoe@testrailmail.com";
}