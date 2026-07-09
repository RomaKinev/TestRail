package api.models.sections;

import com.google.gson.annotations.*;
import lombok.*;


@Data
@Builder
public class MoveSectionRq {

    @Expose
    @SerializedName("parent_id")
    private Integer parentId;

    @Expose
    @SerializedName("after_id")
    private Integer afterId;

}