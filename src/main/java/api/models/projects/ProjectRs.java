package api.models.projects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class ProjectRs {

    private Integer id;

    private String name;

    private String announcement;

    @SerializedName("suite_mode")
    private Integer suiteMode;

    @SerializedName("is_completed")
    private Boolean completed;

}
