package api.models.attachments;

import lombok.*;

import java.util.List;


@Data
@Builder
public class TestRunRq {

    private Integer suite_id;

    private String name;

    private String description;

    private Integer milestone_id;

    private Integer assignedto_id;

    private Boolean include_all;

    private List<Integer> case_ids;

    private String refs;
}
