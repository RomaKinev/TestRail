package api.models.cases;

import api.models.attachments.TestCaseRs;
import lombok.Data;

import java.util.List;


@Data
public class TestCasesRs {

    private Integer size;

    private List<TestCaseRs> cases;

}
