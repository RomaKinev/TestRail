package api.models.results;

import lombok.Data;

import java.util.List;


@Data
public class TestRunResultsRs {

    private Integer size;

    private List<TestRunResultRs> results;

}
