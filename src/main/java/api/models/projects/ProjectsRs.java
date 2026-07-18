package api.models.projects;

import lombok.Data;

import java.util.List;


@Data
public class ProjectsRs {

    private Integer offset;

    private Integer limit;

    private Integer size;

    private List<ProjectRs> projects;

}
