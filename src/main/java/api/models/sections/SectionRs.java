package api.models.sections;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class SectionRs {

    private Integer id;

    @SerializedName("suite_id")
    private Integer suitId;

    private String name;

    private String description;

    @SerializedName("parent_id")
    private Integer parentId;

}