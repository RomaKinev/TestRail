package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@AllArgsConstructor
@Data
@Builder
public class Group {

    @Builder.Default
    private String title = "Test Group Title";

}