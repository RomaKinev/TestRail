package api.models.attachments;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class TestCaseRs {

    private Integer id;

    private String title;

    @SerializedName("section_id")
    private Integer sectionId;

    @SerializedName("suite_id")
    private Integer suiteId;

    @SerializedName("template_id")
    private Integer templateId;

    @SerializedName("type_id")
    private Integer typeId;

    @SerializedName("priority_id")
    private Integer priorityId;

    private transient Integer createdSectionId;
}
