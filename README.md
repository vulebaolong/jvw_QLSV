# 📚 Quản Lý Sinh Viên (QLSV)

## 🚀 Giới thiệu
Ứng dụng **Quản Lý Sinh Viên (QLSV)** là phần mềm desktop được xây dựng bằng **Java Swing** và **MySQL** để hỗ trợ quản lý sinh viên, giáo viên, lớp học, môn học và điểm số.

Ứng dụng gồm hai phần chính:
1. **Hệ thống quản trị** dành cho **Admin**.
2. **Hệ thống dành cho giáo viên và sinh viên**.

---

## 🛠️ Công nghệ sử dụng
- **Java Swing** – Giao diện người dùng
- **MySQL** – Hệ quản trị cơ sở dữ liệu
- **Maven** – Quản lý thư viện
- **JDBC** – Kết nối với cơ sở dữ liệu
- **FlatLaf** – Giao diện dark mode
- **JCalendar** – Chọn ngày tháng
- **BCrypt** – Bảo mật mật khẩu

---

## 📌 Các chức năng chính

### 🔐 1. Đăng nhập / Đăng ký
- **Đăng nhập** bằng email và mật khẩu.
- Hỗ trợ **"Ghi nhớ đăng nhập"** để lưu **userId** vào **Preferences**.
- Hỗ trợ **phím Enter** để đăng nhập nhanh.
- **Đăng ký** tài khoản với vai trò **Sinh viên hoặc Giáo viên**.
- **Mã hóa mật khẩu** bằng **BCrypt**.

### 👤 2. Quản lý tài khoản
- **Admin** có thể tạo tài khoản **Giảng viên, Sinh viên**.
- **Sinh viên** có thể cập nhật thông tin cá nhân.
- **Giảng viên** có thể cập nhật thông tin cá nhân.

### 🎓 3. Quản lý sinh viên
- Thêm, sửa, xóa sinh viên.
- **Tìm kiếm** sinh viên theo **tên, email, số điện thoại**.
- **Chỉnh sửa trực tiếp trên bảng (JTable)** và cập nhật vào database.
- **Chức năng đổi lớp học** bằng cách mở popup và chọn lớp mới.
- **Xóa sinh viên** bằng nút **Delete** trên bảng.

### 🏫 4. Quản lý giáo viên
- Thêm, sửa, xóa giáo viên.
- **Gán môn học** cho giảng viên.
- **Giảng viên có thể đăng ký môn học để giảng dạy**.

### 🏛️ 5. Quản lý lớp học
- Danh sách tất cả các lớp học.
- **Sắp xếp lớp học theo ngày tạo**.
- Xem danh sách sinh viên trong lớp.
- **Giáo viên có thể đăng ký lớp dạy**.

### 📖 6. Quản lý môn học
- **Danh sách môn học** và số tín chỉ.
- **Sinh viên có thể đăng ký môn học**.
- **Giảng viên có thể đăng ký môn học để giảng dạy**.
- **Lọc danh sách môn học chưa đăng ký**.

### 🔄 7. Hệ thống phân quyền
- **Admin** có quyền quản lý tất cả dữ liệu.
- **Giảng viên** chỉ có thể xem, đăng ký và chấm điểm lớp học của mình.
- **Sinh viên** chỉ có thể đăng ký môn học và xem điểm.

### 🛠️ 8. Cấu trúc ứng dụng
- **Sử dụng CardLayout** để chuyển đổi giữa các màn hình.
- **Có Header cố định trên cùng**.
- **Chia bố cục theo layout client và layout auth**.

### 🔔 9. Hiển thị thông báo (Toast)
- **Hiệu ứng trượt lên và biến mất** giống Web.
- **Hiển thị ở vị trí Top-Right, Top-Left, Bottom-Right, Bottom-Left**.
- **Tự động xóa sau một khoảng thời gian**.

### 🔗 10. Cơ sở dữ liệu
- **Tự động kiểm tra và khởi tạo database** nếu chưa tồn tại.
- **Tự động tạo bảng từ file `schema.sql` nếu chưa có**.
- **Import dữ liệu mẫu nếu database rỗng**.

---

## 🔧 Cách chạy ứng dụng
### 🏗️ **Build project**
```sh
mvn clean install
```

### 🚀 **Chạy ứng dụng**
```sh
java -jar target/QLSV-1.0-SNAPSHOT.jar
```


📢 **Ứng dụng đang trong quá trình phát triển!** 🚀

