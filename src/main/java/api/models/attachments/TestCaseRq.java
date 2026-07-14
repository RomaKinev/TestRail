package api.models.attachments;

import lombok.*;


@Data
@Builder
public class TestCaseRq {

    private String title;

    private Integer template_id;

    private Integer type_id;

    private Integer priority_id;

    private String estimate;

    private Integer milestone_id;

    private String refs;
}
