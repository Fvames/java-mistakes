package dev.fvames.apidesign.apiasyncsyncmode;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SyncQueryUploadTaskRequest {
    private final String taskId;
}
