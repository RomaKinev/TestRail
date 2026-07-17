package api.models.results;

import lombok.*;


@Data
@Builder
public class ResultRq {

    private Integer status_id;

    private String comment;

}
