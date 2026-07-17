package api.models.runs;

import api.models.attachments.TestRunRs;
import lombok.Data;

import java.util.List;


@Data
public class RunsRs {

    private Integer size;

    private List<TestRunRs> runs;

}
