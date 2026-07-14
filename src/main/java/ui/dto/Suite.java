package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Suite {

    @Builder.Default
    private String name = "Test Suite";
    @Builder.Default
    private String description = "";
}