package com.spring.knowhub.presentation.controllers.media;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.media.CreateMediaCommand;
import com.spring.knowhub.application.commands.media.CreateMediasCommand;
import com.spring.knowhub.application.commands.user.user.UpdateUserCommand;
import com.spring.knowhub.application.queries.user.user.GetUserByIdQuery;
import com.spring.knowhub.domain.enums.MediaFolder;
import com.spring.knowhub.domain.models.user.User;
import com.spring.knowhub.infrastructure.cloudinary.CloudinaryService;
import com.spring.knowhub.infrastructure.cloudinary.CloudinaryUploadResponse;
import com.spring.knowhub.infrastructure.cloudinary.MediaFolderBuilder;
import com.spring.knowhub.infrastructure.security.CustomUserDetails;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/medias")
@RequiredArgsConstructor
public class MediaController {

        private final CommandBus commandBus;
        private final QueryBus queryBus;
        private final CloudinaryService cloudinaryService;

        // upload avatar (ghi đè file cũ, không lưu vào bảng Media)
        @PostMapping("/avatar")
        public ResponseEntity<ApiResponse<?>> uploadAvatar(
                        @RequestParam("file") MultipartFile file,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolderBuilder.build(MediaFolder.USER, userDetails.getUserId());
                CloudinaryUploadResponse upload = cloudinaryService.uploadWithOverwrite(file, folder, "avatar");
                String avatarUrl = upload.getUrl();

                User user = queryBus.execute(new GetUserByIdQuery(userDetails.getUserId()));

                commandBus.execute(
                                new UpdateUserCommand(
                                                user.getId(),
                                                user.getFullName(),
                                                user.getBio(),
                                                avatarUrl,
                                                user.getBackgroundUrl(),
                                                user.getRole().getId(),
                                                user.getGender(),
                                                user.getDateOfBirth()));
                return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Tải lên avatar thành công", avatarUrl));
        }

        // upload background (ghi đè file cũ, không lưu vào bảng Media)
        @PostMapping("/background")
        public ResponseEntity<ApiResponse<?>> uploadBackground(
                        @RequestParam("file") MultipartFile file,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolderBuilder.build(MediaFolder.USER, userDetails.getUserId());
                CloudinaryUploadResponse upload = cloudinaryService.uploadWithOverwrite(file, folder, "background");
                String backgroundUrl = upload.getUrl();

                User user = queryBus.execute(new GetUserByIdQuery(userDetails.getUserId()));

                commandBus.execute(
                                new UpdateUserCommand(
                                                user.getId(),
                                                user.getFullName(),
                                                user.getBio(),
                                                user.getAvatarUrl(),
                                                backgroundUrl,
                                                user.getRole().getId(),
                                                user.getGender(),
                                                user.getDateOfBirth()));

                return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Tải lên background thành công", backgroundUrl));
        }

        // upload cho bài viết
        @PostMapping("/posts/{postId}")
        public ResponseEntity<ApiResponse<?>> uploadPostMedia(
                        @RequestParam("file") MultipartFile file,
                        @PathVariable Long postId,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolder.POST.path() + "/" + postId;
                CloudinaryUploadResponse upload = cloudinaryService.upload(file, folder);
                Long mediaId = commandBus.execute(
                                new CreateMediaCommand(
                                                upload.getPublicId(),
                                                upload.getUrl(),
                                                file.getOriginalFilename(),
                                                upload.getFormat(),
                                                upload.getBytes(),
                                                upload.getResourceType().toUpperCase(),
                                                folder));
                return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Post media uploaded successfully", mediaId));
        }

        // upload nhiều cho bài viết
        @PostMapping("/posts/{postId}/batch")
        public ResponseEntity<ApiResponse<?>> uploadPostMedias(
                        @RequestParam("files") List<MultipartFile> files,
                        @PathVariable Long postId,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolder.POST.path() + "/" + postId;
                List<CreateMediaCommand> commands = files.stream()
                                .map(file -> {
                                        CloudinaryUploadResponse upload = cloudinaryService.upload(file, folder);
                                        return new CreateMediaCommand(
                                                        upload.getPublicId(),
                                                        upload.getUrl(),
                                                        file.getOriginalFilename(),
                                                        upload.getFormat(),
                                                        upload.getBytes(),
                                                        upload.getResourceType().toUpperCase(),
                                                        folder);
                                })
                                .toList();
                List<Long> mediaIds = commandBus.execute(new CreateMediasCommand(commands));
                return ResponseEntity
                                .ok(new ApiResponse<>("SUCCESS", "Tải lên file cho bài đăng thành công", mediaIds));
        }

        // upload cho comment
        @PostMapping("/comments/{commentId}")
        public ResponseEntity<ApiResponse<?>> uploadCommentMedia(
                        @RequestParam("file") MultipartFile file,
                        @PathVariable Long commentId,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolder.COMMENT.path() + "/" + commentId;
                CloudinaryUploadResponse upload = cloudinaryService.upload(file, folder);
                Long mediaId = commandBus.execute(
                                new CreateMediaCommand(
                                                upload.getPublicId(),
                                                upload.getUrl(),
                                                file.getOriginalFilename(),
                                                upload.getFormat(),
                                                upload.getBytes(),
                                                upload.getResourceType().toUpperCase(),
                                                folder));
                return ResponseEntity
                                .ok(new ApiResponse<>("SUCCESS", "Tải lên file cho bình luận thành công", mediaId));
        }

        // upload tạm (dùng khi chưa có postId/commentId)
        @PostMapping("/temp")
        public ResponseEntity<ApiResponse<?>> uploadTempMedia(
                        @RequestParam("file") MultipartFile file,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolder.TEMP.path() + "/" + userDetails.getUserId();
                CloudinaryUploadResponse upload = cloudinaryService.upload(file, folder);
                Long mediaId = commandBus.execute(
                                new CreateMediaCommand(
                                                upload.getPublicId(),
                                                upload.getUrl(),
                                                file.getOriginalFilename(),
                                                upload.getFormat(),
                                                upload.getBytes(),
                                                upload.getResourceType().toUpperCase(),
                                                folder));
                return ResponseEntity.ok(new ApiResponse<>("SUCCESS", "Tải lên file tạm thời thành công", mediaId));
        }

        // upload nhiều file tạm
        @PostMapping("/temp/batch")
        public ResponseEntity<ApiResponse<?>> uploadTempMedias(
                        @RequestParam("files") List<MultipartFile> files,
                        @AuthenticationPrincipal CustomUserDetails userDetails) {
                String folder = MediaFolder.TEMP.path() + "/" + userDetails.getUserId();
                List<CreateMediaCommand> commands = files.stream()
                                .map(file -> {
                                        CloudinaryUploadResponse upload = cloudinaryService.upload(file, folder);
                                        return new CreateMediaCommand(
                                                        upload.getPublicId(),
                                                        upload.getUrl(),
                                                        file.getOriginalFilename(),
                                                        upload.getFormat(),
                                                        upload.getBytes(),
                                                        upload.getResourceType().toUpperCase(),
                                                        folder);
                                })
                                .toList();
                List<Long> mediaIds = commandBus.execute(new CreateMediasCommand(commands));
                return ResponseEntity
                                .ok(new ApiResponse<>("SUCCESS", "Tải lên nhiều file tạm thời thành công", mediaIds));
        }
}
