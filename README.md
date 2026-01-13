# KnowHub - Backend

KnowHub API là hệ thống backend mạnh mẽ cung cấp dịch vụ cho nền tảng kết nối cộng đồng và chia sẻ kiến thức. Dự án được phát triển theo kiến trúc Clean Architecture giúp hệ thống dễ dàng mở rộng và bảo trì.

> [!NOTE]  
> Dự án hiện đang trong quá trình phát triển (In Progress).

## 🚀 Công nghệ sử dụng

- **Framework:** [Spring Boot 3.5+](https://spring.io/projects/spring-boot)
- **Language:** Java 17
- **Database:** SQL Server
- **Security:** Spring Security & JWT (JJWT)
- **ORM:** Spring Data JPA
- **Object Mapping:** [ModelMapper](https://modelmapper.org/)
- **Media Storage:** [Cloudinary](https://cloudinary.com/)
- **Build Tool:** Maven

## ✨ Tính năng hiện tại

- **CQRS Pattern:** Phân tách luồng đọc (Query) và ghi (Command) thông qua Command/Query Bus.
- **Authentication:** Hệ thống bảo mật dựa trên JWT.
- **Media Management:** Tải lên và quản lý hình ảnh/video thông qua Cloudinary tích hợp.
- **Post & Feed:** Xử lý logic bài viết, thẻ (tags) và quản lý phương tiện đính kèm.
- **User Profile:** Quản lý thông tin người dùng, ảnh đại diện và ảnh bìa.
- **Clean Architecture:** Cấu trúc phân lớp rõ ràng (Domain, Application, Infrastructure, Presentation).

## 🛠 Phát triển dự án (Local)

1. **Yêu cầu hệ thống:**
   - Java 17
   - SQL Server
   - Maven

2. **Cấu hình môi trường:**
   Tạo file `.env` hoặc cấu hình trong `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=knowhub
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   # Cloudinary Config (Nếu có)
   CLOUDINARY_URL=your_cloudinary_url
   ```

3. **Chạy ứng dụng:**
   ```bash
   mvn spring-boot:run
   ```
   API sẽ chạy tại: [http://localhost:8080](http://localhost:8080)

## 📁 Cấu trúc thư mục chính

- `src/main/java/com/spring/knowhub/domain`: Chứa logic nghiệp vụ cốt lõi, entity và repository interfaces.
- `src/main/java/com/spring/knowhub/application`: Chứa các use cases, Commands, Queries và logic điều phối.
- `src/main/java/com/spring/knowhub/infrastructure`: Cấu hình hệ thống, triển khai repository (JPA), Security và các dịch vụ bên thứ ba (Cloudinary).
- `src/main/java/com/spring/knowhub/presentation`: Chứa REST Controllers, Request/Response DTOs và xử lý Exception API.

---
© 2026 KnowHub Team.
