package com.spring.knowhub.presentation.controllers.post;

import com.spring.knowhub.application.buses.CommandBus;
import com.spring.knowhub.application.buses.QueryBus;
import com.spring.knowhub.application.commands.post.tag.CreateTagCommand;
import com.spring.knowhub.application.commands.post.tag.DeleteTagCommand;
import com.spring.knowhub.application.commands.post.tag.UpdateTagCommand;
import com.spring.knowhub.application.queries.post.tag.GetTagByIdQuery;
import com.spring.knowhub.application.queries.post.tag.GetTagPagedQuery;
import com.spring.knowhub.domain.models.post.Tag;
import com.spring.knowhub.presentation.requests.post.tag.CreateTagRequest;
import com.spring.knowhub.presentation.requests.post.tag.UpdateTagRequest;
import com.spring.knowhub.presentation.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
        private final CommandBus commandBus;
        private final QueryBus queryBus;

        @GetMapping
        public ResponseEntity<ApiResponse<?>> getPagedTags(
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int limit) {
                log.info("GET /api/tags - page={}, limit={}", page, limit);

                PageRequest pageable = PageRequest.of(page, limit);

                var tagsPage = queryBus.execute(
                                new GetTagPagedQuery(pageable));

                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Lấy danh sách tag phân trang thành công",
                                                tagsPage));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> getTagById(
                        @PathVariable Long id) {
                log.info("GET /api/tags/{}", id);

                GetTagByIdQuery query = new GetTagByIdQuery(id);
                Tag tag = queryBus.execute(query);
                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Lấy tag thành công",
                                                tag));
        }

        @PostMapping
        public ResponseEntity<ApiResponse<?>> createTag(
                @RequestBody CreateTagRequest request
        ) {
                log.info("POST /api/tags - name={}", request.getName());

                Long tagId = commandBus.execute(
                                new CreateTagCommand(
                                                request.getName()));

                return ResponseEntity.status(201).body(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Tạo tag thành công",
                                                tagId));
        }

        @PutMapping
        public ResponseEntity<ApiResponse<?>> updateTag(
                        @RequestBody UpdateTagRequest request
        ) {
                log.info("PUT /api/tags - id={}, name={}", request.getId(), request.getName());
                Long tagId = commandBus.execute(
                                new UpdateTagCommand(
                                                request.getId(),
                                                request.getName()));
                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Cập nhật tag thành công",
                                                tagId));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse<?>> deleteTag(@PathVariable Long id) {
                log.info("DELETE /api/tags/{}", id);
                commandBus.execute(
                        new DeleteTagCommand(id)
                );
                return ResponseEntity.ok(
                                new ApiResponse<>(
                                                "SUCCESS",
                                                "Xóa tag thành công",
                                                null));
        }
}
