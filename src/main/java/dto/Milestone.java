package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class Milestone {

    @Builder.Default
    private String name = "Test Milestone";
    @Builder.Default
    private String description = "";

}