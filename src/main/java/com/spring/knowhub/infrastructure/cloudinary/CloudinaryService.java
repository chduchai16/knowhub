package com.spring.knowhub.infrastructure.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.spring.knowhub.infrastructure.exceptions.media.MediaRepositoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    // Upload file lên Cloudinary (unique filename - cho post, comment)
    public CloudinaryUploadResponse upload(
            MultipartFile file,
            String folder) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "resource_type", "auto",
                            "use_filename", true,
                            "unique_filename", true));
            return new CloudinaryUploadResponse(
                    (String) uploadResult.get("public_id"),
                    (String) uploadResult.get("secure_url"),
                    (String) uploadResult.get("format"),
                    ((Number) uploadResult.get("bytes")).longValue(),
                    (String) uploadResult.get("resource_type"));

        } catch (IOException e) {
            throw MediaRepositoryException.uploadFailed(e.getMessage());
        }
    }

    // Upload với tên cố định và ghi đè (cho avatar, background)
    public CloudinaryUploadResponse uploadWithOverwrite(
            MultipartFile file,
            String folder,
            String fileName) {
        try {
            Map uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", folder,
                            "public_id", fileName,
                            "overwrite", true,
                            "resource_type", "auto"));
            return new CloudinaryUploadResponse(
                    (String) uploadResult.get("public_id"),
                    (String) uploadResult.get("secure_url"),
                    (String) uploadResult.get("format"),
                    ((Number) uploadResult.get("bytes")).longValue(),
                    (String) uploadResult.get("resource_type"));

        } catch (IOException e) {
            throw MediaRepositoryException.uploadFailed(e.getMessage());
        }
    }

    // Move file từ folder này sang folder khác (rename)
    public CloudinaryMoveResponse moveMedia(
            String fromPublicId,
            String toFolder,
            String resourceType) {
        try {
            // Lấy tên file từ publicId (vd: temp/123/image_abc -> image_abc)
            String fileName = fromPublicId.substring(fromPublicId.lastIndexOf("/") + 1);
            String toPublicId = toFolder + "/" + fileName;

            Map result = cloudinary.uploader().rename(
                    fromPublicId,
                    toPublicId,
                    ObjectUtils.asMap(
                            "resource_type", resourceType.toLowerCase(),
                            "overwrite", true));

            return new CloudinaryMoveResponse(
                    (String) result.get("public_id"),
                    (String) result.get("secure_url"));

        } catch (Exception e) {
            throw MediaRepositoryException.moveFailed(e.getMessage());
        }
    }

    public void deleteMedia(String publicId, String resourceType) {
        try {
            cloudinary.uploader().destroy(
                    publicId,
                    ObjectUtils.asMap("resource_type", resourceType.toLowerCase()));
        } catch (Exception e) {
            throw MediaRepositoryException.deleteFailed(e.getMessage());
        }
    }
}
