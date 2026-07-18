package api.models.results;

import lombok.*;


@Data
@Builder
public class TestRunResultRq {

    private Integer status_id;

    private String comment;

}
