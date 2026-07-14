package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Project {

    @Builder.Default
    private String name = "Test";
    @Builder.Default
    private String description = "";
}
