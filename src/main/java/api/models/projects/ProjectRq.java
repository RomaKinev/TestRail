package api.models.projects;

import lombok.*;


@Data
@Builder
public class ProjectRq {

    private String name;

    private String announcement;

    private Boolean show_announcement;

    private Integer suite_mode;

}
