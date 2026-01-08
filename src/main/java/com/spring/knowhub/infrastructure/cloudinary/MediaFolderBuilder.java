package com.spring.knowhub.infrastructure.cloudinary;

import com.spring.knowhub.domain.enums.media.MediaFolder;

public class MediaFolderBuilder {
    public static String build(
            MediaFolder folder,
            Long ownerId
    ) {
        return folder.path() + "/" + ownerId;
    }
}
