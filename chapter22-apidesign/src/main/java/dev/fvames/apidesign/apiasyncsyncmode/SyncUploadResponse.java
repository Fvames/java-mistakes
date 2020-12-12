package dev.fvames.apidesign.apiasyncsyncmode;

import lombok.Data;

@Data
public class SyncUploadResponse {
    private String downloadUrl;
    private String thumbnailDownloadUrl;
}
