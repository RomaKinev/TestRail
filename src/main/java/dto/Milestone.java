package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class Milestone {

    @Builder.Default
    private String title = "Test Milestone";
    @Builder.Default
    private String description = "Test Milestone description";

}