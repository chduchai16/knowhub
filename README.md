# KnowHub - Backend

KnowHub API là hệ thống backend mạnh mẽ cung cấp dịch vụ cho nền tảng kết nối cộng đồng và chia sẻ kiến thức. Dự án được phát triển theo kiến trúc Clean Architecture với CQRS pattern, giúp hệ thống dễ dàng mở rộng và bảo trì.

> [!NOTE]  
> Dự án hiện đang trong quá trình phát triển (In Progress).

## Công nghệ sử dụng

- **Framework:** [Spring Boot 3.5+](https://spring.io/projects/spring-boot)
- **Language:** Java 17
- **Database:** SQL Server
- **Security:** Spring Security & JWT (JJWT)
- **ORM:** Spring Data JPA
- **Object Mapping:** [ModelMapper](https://modelmapper.org/)
- **Media Storage:** [Cloudinary](https://cloudinary.com/)
- **Real-time:** Server-Sent Events (SSE)
- **Build Tool:** Maven

## Tính năng hiện tại

### Core Features
- **CQRS Pattern:** Phân tách luồng đọc (Query) và ghi (Command) thông qua Command/Query Bus
- **Clean Architecture:** Cấu trúc phân lớp rõ ràng (Domain, Application, Infrastructure, Presentation)
- **Event-Driven:** Spring Event System cho loose coupling và extensibility

### Authentication & Security
- **JWT Authentication:** Hệ thống bảo mật dựa trên JWT tokens
- **SSE Token Support:** Hỗ trợ authentication qua query parameter cho SSE connections
- **Username/Email Validation:** Kiểm tra duplicate và format validation

### Social Features
- **Post Management:** Tạo, chỉnh sửa, xóa bài viết với tags và media attachments
- **Comment System:** 
  - Nested comments với `rootId` và `parentId`
  - Reply threading cho conversations
  - Đếm số lượng replies cho mỗi root comment
- **Reactions:** Like/Unlike bài viết
- **User Follow:** Follow/Unfollow người dùng khác
- **User Profile:** Quản lý thông tin cá nhân, avatar và cover photo

### Notification System
- **Real-time Notifications:** SSE (Server-Sent Events) cho instant updates
- **Notification Types:** 
  - `LIKE` - Khi ai đó thích bài viết
  - `COMMENT` - Khi ai đó comment bài viết
  - `REPLY` - Khi ai đó reply comment
  - `FOLLOW` - Khi ai đó follow bạn
- **Rich Notification Data:**
  - Actor information (username, avatar)
  - Reference tracking (postId, commentId)
  - Read/unread status
  - Timestamps
- **Offline Support:** Notifications được lưu trong DB cho users offline

### Media Management
- **Cloudinary Integration:** Upload và quản lý images/videos
- **Multi-file Upload:** Hỗ trợ upload nhiều files cùng lúc
- **File Size Limits:** Configurable max file size (default: 100MB)

## Phát triển dự án (Local)

### 1. Yêu cầu hệ thống
- Java 17+
- SQL Server 2012+
- Maven 3.6+

### 2. Cấu hình môi trường

Cấu hình database, Cloudinary, và file upload limits trong `src/main/resources/application.properties`

### 3. Chạy ứng dụng

```bash
mvn clean install
mvn spring-boot:run
```

API sẽ chạy tại: [http://localhost:8080](http://localhost:8080)


## Cấu trúc dự án

```
src/main/java/com/spring/knowhub/
│
├── domain/                          # Domain Layer - Business Logic Core
│   ├── models/                      # Domain Models (Entities)
│   │   ├── user/                    # User, Role, UserFollow
│   │   ├── post/                    # Post, PostLike, PostMedia, Tag
│   │   ├── comment/                 # Comment (with rootId, parentId)
│   │   └── notification/            # Notification, UserNotification
│   ├── repositories/                # Repository Interfaces
│   ├── enums/                       # Domain Enums (NotificationType, UserStatus, etc.)
│   ├── exceptions/                  # Domain Exceptions
│   └── security/                    # Security Interfaces (TokenProvider)
│
├── application/                     # Application Layer - Use Cases
│   ├── commands/                    # Write Operations (CQRS)
│   │   ├── auth/                    # Register, Login
│   │   ├── user/                    # CreateUser, UpdateUser, UserFollow
│   │   ├── post/                    # CreatePost, UpdatePost, PostLike
│   │   └── comment/                 # CreateComment (with rootId logic)
│   ├── queries/                     # Read Operations (CQRS)
│   │   ├── user/                    # GetUserById, GetUserProfile
│   │   ├── post/                    # GetPostById, GetPostsByUserId
│   │   ├── comment/                 # GetCommentsByPostId, GetCommentsByRootId
│   │   └── notification/            # GetNotificationsByUserId
│   ├── buses/                       # Command/Query Bus Implementation
│   ├── validators/                  # Business Validation Logic
│   ├── events/                      # Domain Events
│   │   ├── NotificationCreatedEvent.java
│   │   └── handlers/
│   │       └── PushNotificationRealtimeHandler.java  # SSE Event Handler
│   └── listeners/                   # Event Listeners
│
├── infrastructure/                  # Infrastructure Layer - External Concerns
│   ├── entities/                    # JPA Entities (Database Mapping)
│   │   ├── user/                    # UserEntity
│   │   ├── post/                    # PostEntity, PostMediaEntity
│   │   ├── comment/                 # CommentEntity (rootId column)
│   │   └── notification/            # NotificationEntity (postId column)
│   ├── repositories/
│   │   ├── jpas/                    # JPA Repository Interfaces
│   │   │   └── comment/
│   │   │       └── JpaCommentRepository.java  # findByPostId, findByRootId, countByRootId
│   │   └── impls/                   # Repository Implementations
│   ├── mappers/                     # Entity ↔ Domain Model Mappers
│   │   ├── user/                    # UserMapper
│   │   ├── post/                    # PostMapper
│   │   ├── comment/                 # CommentMapper
│   │   └── notification/            # NotificationMapper
│   ├── security/                    # Security Implementation
│   │   ├── jwt/
│   │   │   ├── JwtAuthenticationFilter.java  # Token from header/query param
│   │   │   └── JwtTokenProvider.java
│   │   ├── SpringSecurityConfig.java
│   │   └── UserDetailsServiceImpl.java
│   └── services/                    # External Services
│       └── cloudinary/              # Cloudinary Integration
│
└── presentation/                    # Presentation Layer - API & DTOs
    ├── controllers/                 # REST Controllers
    │   ├── auth/                    # AuthController
    │   ├── user/                    # UserController
    │   ├── post/                    # PostController
    │   ├── comment/                 # CommentController
    │   └── notification/            # NotificationController
    ├── sse/                         # Server-Sent Events
    │   ├── NotificationSseController.java  # /api/notifications/stream
    │   ├── SseConnectionRegistry.java      # Manage SSE connections
    │   └── payload/
    │       └── NotificationRealtimePayload.java
    ├── request/                     # Request DTOs
    ├── response/                    # Response DTOs
    │   ├── comment/
    │   │   └── CommentResponse.java         # includes replyQuantity
    │   └── notification/
    │       └── NotificationResponse.java    # includes postId, actor info
    ├── mappers/                     # DTO Mappers
    │   ├── comment/
    │   │   └── CommentResponseMapper.java   # calculates replyQuantity
    │   └── notification/
    │       └── NotificationResponseMapper.java
    └── exceptions/                  # Global Exception Handlers
```

## Key Architectural Patterns

### CQRS (Command Query Responsibility Segregation)
- **Commands:** Modify state (Create, Update, Delete)
- **Queries:** Read state (Get, List, Search)
- **Bus Pattern:** Centralized routing for commands/queries

### Event-Driven Architecture
- Command Handlers publish events
- Event Listeners handle asynchronously
- Loose coupling between components


### Clean Architecture Layers
```
Presentation → Application → Domain ← Infrastructure
     ↓              ↓           ↑            ↑
   DTOs        Use Cases    Entities    JPA/External
```

## API Endpoints

### Authentication
- `POST /api/auth/register` - Đăng ký tài khoản
- `POST /api/auth/login` - Đăng nhập

### Users
- `GET /api/users/{id}` - Lấy thông tin user
- `PUT /api/users/{id}` - Cập nhật thông tin
- `POST /api/users/{id}/follow` - Follow user
- `DELETE /api/users/{id}/unfollow` - Unfollow user

### Posts
- `GET /api/posts` - Lấy danh sách posts
- `POST /api/posts` - Tạo post mới
- `PUT /api/posts/{id}` - Cập nhật post
- `DELETE /api/posts/{id}` - Xóa post
- `POST /api/posts/{id}/like` - Like post
- `DELETE /api/posts/{id}/unlike` - Unlike post

### Comments
- `GET /api/posts/{postId}/comments` - Lấy root comments của post
- `GET /api/comments/{rootId}/replies` - Lấy replies của comment
- `POST /api/comments` - Tạo comment/reply mới

### Notifications
- `GET /api/notifications` - Lấy danh sách notifications
- `PUT /api/notifications/{id}/read` - Đánh dấu đã đọc
- `GET /api/notifications/stream` - SSE endpoint (real-time)
© 2026 KnowHub Team.
