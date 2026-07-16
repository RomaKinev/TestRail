package api.models.attachments;

import com.google.gson.annotations.SerializedName;
import lombok.Data;


@Data
public class AttachmentUploadRs {

    @SerializedName("attachment_id")
    private String attachmentId;

}