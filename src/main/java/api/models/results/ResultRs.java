package api.models.results;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class ResultRs {

    private Integer id;

    @SerializedName("test_id")
    private Integer testId;

    @SerializedName("status_id")
    private Integer statusId;

    private String comment;

}
