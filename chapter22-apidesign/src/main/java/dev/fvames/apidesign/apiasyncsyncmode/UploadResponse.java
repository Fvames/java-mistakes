package dev.fvames.apidesign.apiasyncsyncmode;

import lombok.Data;

@Data
public class UploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
