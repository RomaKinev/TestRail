package api.models.sections;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class SectionRs {

    @SerializedName("id")
    public Integer id;

    @SerializedName("suite_id")
    public Integer suiteId;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("parent_id")
    public Integer parentId;

}