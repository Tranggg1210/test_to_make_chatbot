# project-base-spring
I. Luồng hoạt động
  Bước 1: Định nghĩa entity, liên kết bảng...
  
  Bước 2: Tạo các repository cho các entity
  
  Bước 3: Tạo parameter, body cho từng endpoint
  
  Bước 4: Tạo class input cho endpoint, được map từ parameter
  
  Bước 5: Tạo class output cho endpoint, hứng output của service
  
  Bước 6: Tạo class service, xử lý logic tại đây. Đầu vào là class input, đầu ra là class output
  
  Bước 7. Tạo class controller, nhận parameter, body... Biến đổi parameter -> input => service => output -> return output

II. Lưu ý để dễ bảo trì
  - Tạo class constant định nghĩa các quá trị String, number để tránh hardcode
  
  - Các message định nghĩa trong file application để tránh phải sửa nhiều chỗ
  
  - Tạo các annotation validate param
  
  - Đoạn nào xử lý logic nhiều class dùng lại thì cho vào util
  
  - Dùng map struct để map các class

III. Link hướng dẫn sử dụng base

  https://youtu.be/_ngYqZNSo18?si=NNubadKzkr6ltdVe
