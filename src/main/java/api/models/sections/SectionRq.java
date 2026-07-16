package api.models.sections;

import com.google.gson.annotations.SerializedName;
import lombok.*;


@Data
@Builder
public class SectionRq {

    @SerializedName("parent_id")
    public Integer parent_id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

}