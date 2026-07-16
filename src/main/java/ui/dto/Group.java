package ui.dto;

import lombok.*;

@AllArgsConstructor
@Data
@Builder
public class Group {

    @Builder.Default
    private String title = "Test Group Title";

}