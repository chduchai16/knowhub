# KnowHub - Backend

KnowHub là hệ thống backend cho nền tảng mạng xã hội chia sẻ kiến thức. Dự án được xây dựng theo kiến trúc Clean Architecture kết hợp CQRS pattern, đảm bảo phân tách rõ ràng giữa các tầng và dễ dàng mở rộng.

Trạng thái: Tạm dừng phát triển.

---

## Công nghệ sử dụng

- Java 17
- Spring Boot 3.5
- Spring Security + JWT (JJWT)
- Spring Data JPA + Hibernate
- SQL Server
- Cloudinary (lưu trữ media)
- WebSocket + STOMP (nhắn tin thời gian thực)
- SSE - Server-Sent Events (thông báo thời gian thực)
- ModelMapper
- Maven

---

## Kiến trúc

Dự án áp dụng Clean Architecture với 4 tầng chính:

```
Presentation --> Application --> Domain <-- Infrastructure
```

- Domain: nghiệp vụ cốt lõi, không phụ thuộc framework
- Application: điều phối use case qua Command/Query Bus (CQRS)
- Infrastructure: JPA entity, repository impl, security, Cloudinary
- Presentation: REST controller, WebSocket, SSE, DTO mapper

### CQRS

Mọi thao tác ghi đi qua CommandBus, mọi thao tác đọc đi qua QueryBus. Mỗi handler chỉ xử lý một command hoặc query duy nhất.

### Event-Driven

Sau khi xử lý command, handler publish event (ví dụ: NotificationCreatedEvent). Listener nhận event và xử lý bất đồng bộ, ví dụ đẩy thông báo realtime qua SSE.

### Exception theo tầng

Mỗi tầng có exception riêng biệt:
- Domain: kiểm tra tính hợp lệ của nghiệp vụ
- Infrastructure: lỗi truy cập database, external service
- Application: lỗi điều phối use case
- Presentation: lỗi mapping response

---

## Tính năng đã xây dựng

### Xác thực
- Đăng ký tài khoản
- Đăng nhập trả về JWT token

### Quản lý người dùng
- Xem profile người dùng
- Cập nhật thông tin cá nhân
- Upload avatar, ảnh bìa
- Follow / Unfollow người dùng

### Phân quyền
- Role và Permission
- Quản lý role (tạo, cập nhật, tìm kiếm, gán permission)
- Mỗi user chỉ có một role

### Bài viết
- Tạo, cập nhật, xóa bài viết
- Đính kèm media (ảnh, video) từ Cloudinary
- Gắn tag cho bài viết
- Newsfeed (bài viết công khai theo thứ tự thời gian)
- Đếm số lượt like và comment trên mỗi bài viết

### Bình luận
- Tạo comment gốc (root comment)
- Reply comment theo chuỗi (rootId + parentId)
- Đếm số lượng reply cho mỗi comment gốc

### Like
- Like / Unlike bài viết
- Kiểm tra trạng thái đã like của người dùng hiện tại

### Thông báo
- Các loại thông báo: LIKE, COMMENT, REPLY, FOLLOW
- Lưu vào database cho người dùng offline
- Đẩy thông báo thời gian thực qua SSE khi người dùng online
- Đánh dấu đã đọc

### Nhắn tin
- Gửi tin nhắn trực tiếp giữa hai người dùng qua WebSocket + STOMP
- Đính kèm media trong tin nhắn
- Lịch sử hội thoại (inbox list)
- Nhận tin nhắn thời gian thực qua /user/queue/messages

### Media
- Upload ảnh và video lên Cloudinary
- Gán media cho bài viết hoặc tin nhắn
- Hỗ trợ upload nhiều file cùng lúc

### Báo cáo (Report)
- Báo cáo bài viết hoặc người dùng vi phạm

---

## Cấu trúc thư mục

```
src/main/java/com/spring/knowhub/
│
├── domain/
│   ├── models/           # Domain model: User, Post, Comment, Message, Notification...
│   ├── repositories/     # Repository interface (port)
│   ├── enums/            # Enum dùng chung
│   ├── exceptions/       # Domain exception theo từng aggregate
│   ├── constants/        # Hằng số domain
│   ├── specifications/   # Specification pattern cho query
│   └── security/         # Interface TokenProvider
│
├── application/
│   ├── buses/            # CommandBus, QueryBus
│   ├── commands/         # Command + CommandHandler (auth, user, post, comment, message...)
│   ├── queries/          # Query + QueryHandler
│   ├── validators/       # Validate business rule trước khi xử lý
│   ├── events/           # Domain event và handler
│   ├── exceptions/       # Application exception theo từng aggregate
│   └── ports/            # Application port interface
│
├── infrastructure/
│   ├── entities/         # JPA entity ánh xạ database
│   ├── repositories/
│   │   ├── jpas/         # Spring Data JPA interface
│   │   └── impls/        # Repository implementation
│   ├── mappers/          # Entity <-> Domain model mapper
│   ├── security/         # JWT filter, UserDetailsService, SecurityConfig
│   ├── cloudinary/       # Cloudinary upload service
│   ├── realtime/         # SSE connection registry
│   ├── configurations/   # Bean config (ModelMapper, CORS...)
│   └── exceptions/       # Infrastructure exception theo từng aggregate
│
└── presentation/
    ├── controllers/      # REST controller (auth, user, post, comment, message, notification...)
    ├── websocket/        # WebSocket controller (ChatWsController)
    ├── sse/              # SSE controller và registry
    ├── requests/         # Request DTO
    ├── response/         # Response DTO
    ├── mappers/          # Domain model <-> Response DTO mapper
    ├── advices/          # GlobalExceptionHandler
    └── exceptions/       # Presentation exception theo từng aggregate
```

---

## Chạy dự án

Yêu cầu:
- Java 17+
- SQL Server
- Maven 3.6+

Cấu hình kết nối database, Cloudinary và JWT secret trong `src/main/resources/application.properties`.

```bash
mvn clean install
mvn spring-boot:run
```

API chạy tại: http://localhost:8080

---

## API chính

### Auth
- POST /api/auth/register
- POST /api/auth/login

### User
- GET /api/users/{id}
- PUT /api/users/{id}
- PUT /api/users/{id}/avatar
- POST /api/users/{id}/follow
- DELETE /api/users/{id}/unfollow

### Role & Permission
- POST /api/roles
- GET /api/roles
- PUT /api/roles/{id}
- GET /api/permissions

### Post
- GET /api/posts/newsfeed
- GET /api/posts/{id}
- POST /api/posts
- PUT /api/posts/{id}
- DELETE /api/posts/{id}
- POST /api/posts/{id}/like
- DELETE /api/posts/{id}/unlike

### Comment
- GET /api/posts/{postId}/comments
- GET /api/comments/{rootId}/replies
- POST /api/comments

### Message
- GET /api/messages/inbox
- GET /api/messages/conversation/{partnerId}
- WebSocket: SEND /app/chat.send

### Notification
- GET /api/notifications
- PUT /api/notifications/{id}/read
- GET /api/notifications/stream (SSE)

### Media
- POST /api/media/upload

---

## Ghi chú

Dự án được xây dựng với mục tiêu học tập và thực hành kiến trúc phần mềm theo DDD + Clean Architecture + CQRS. Một số tính năng vẫn còn ở dạng prototype hoặc chưa hoàn thiện toàn bộ edge case.
