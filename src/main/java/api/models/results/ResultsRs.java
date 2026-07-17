package api.models.results;

import lombok.Data;

import java.util.List;


@Data
public class ResultsRs {

    private Integer size;

    private List<ResultRs> results;

}
