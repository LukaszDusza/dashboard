package com.dfsp.dashboard.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalFile {

    private String name;
    private String creationTime;
    private String lastModified;
    private Long size;
    private String downloadUri;
    private String fileDeleteUri;
    private String fileType;
}
