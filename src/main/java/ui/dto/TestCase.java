package ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class TestCase {

    @Builder.Default
    private String title = "Test Case";
    @Builder.Default
    private String section = "";
    @Builder.Default
    private String template = "";
    @Builder.Default
    private String type = "";
    @Builder.Default
    private String priority = "";
}
