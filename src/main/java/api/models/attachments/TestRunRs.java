package api.models.attachments;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class TestRunRs {

    private Integer id;

    private String name;

    private String description;

    @SerializedName("suite_id")
    private Integer suiteId;

    @SerializedName("project_id")
    private Integer projectId;

    @SerializedName("include_all")
    private Boolean includeAll;

    @SerializedName("is_completed")
    private Boolean completed;

    private transient Integer createdCaseId;

    private transient Integer createdSectionId;
}
