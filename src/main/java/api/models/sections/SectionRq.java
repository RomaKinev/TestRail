package api.models.sections;

import com.google.gson.annotations.*;
import lombok.*;


@Data
@Builder
public class SectionRq {

    @Expose
    @SerializedName("parent_id")
    private Integer parentId;

    @Expose
    private String name;

    @Expose
    @SerializedName("description")
    private String description;

}