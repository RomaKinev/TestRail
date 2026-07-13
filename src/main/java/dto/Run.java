package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Run {

    @Builder.Default
    private String name = "Test Run";
    @Builder.Default
    private String description = "";
}
