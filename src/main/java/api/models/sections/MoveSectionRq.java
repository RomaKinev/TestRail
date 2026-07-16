package api.models.sections;

import lombok.*;


@Data
@Builder
public class MoveSectionRq {

    private Integer parent_id;

    private Integer after_id;

}