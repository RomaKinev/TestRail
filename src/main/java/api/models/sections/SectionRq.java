package api.models.sections;

import lombok.*;


@Data
@Builder
public class SectionRq {

    private Integer parent_id;

    private String name;

    private String description;

}