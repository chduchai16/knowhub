package com.spring.knowhub.domain.models.media;

import com.spring.knowhub.domain.enums.media.MediaStatus;
import com.spring.knowhub.domain.enums.media.MediaType;
import com.spring.knowhub.domain.enums.media.OwnerType;
import com.spring.knowhub.domain.models.BaseModel;

public class Media extends BaseModel {
    private Long id ;
    private String publicId ;
    private String url ;
    private String originalName ;
    private String format ;
    private Long size ;
    private MediaType type ;
    private String folder ;
    private Long ownerId ;
    private OwnerType ownerType ;
    private MediaStatus status ;

    public Media(Long id , String publicId, String url, String originalName, String format, Long size, MediaType type, String folder, Long ownerId, OwnerType ownerType , MediaStatus status) {
        this.publicId = publicId;
        this.url = url;
        this.originalName = originalName;
        this.format = format;
        this.size = size;
        this.type = type;
        this.folder = folder;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.id = id ;
        this.status = status ;
    }

    public Media(){} ;

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    public MediaStatus getStatus() {
        return status;
    }

    public void setStatus(MediaStatus status) {
        this.status = status;
    }
}
