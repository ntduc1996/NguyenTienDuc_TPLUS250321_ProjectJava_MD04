USE project_module04;
CREATE TABLE admin
(
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE student
(
    student_id   INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    student_dob  DATE         NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    sex          BIT          NOT NULL,
    phone        VARCHAR(20),
    password     VARCHAR(255) NOT NULL,
    create_at    DATE         NOT NULL

);
CREATE TABLE course
(
    course_id         INT AUTO_INCREMENT PRIMARY KEY,
    course_name       VARCHAR(100) NOT NULL,
    course_duration   INT          NOT NULL,
    course_instructor VARCHAR(100) NOT NULL,
    create_at         DATE         NOT NULL
);
CREATE TABLE enrollment
(
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id    INT,
    FOREIGN KEY (student_id) REFERENCES student (student_id),
    course_id     INT,
    FOREIGN KEY (course_id) REFERENCES course (course_id),
    register_at   DATETIME                                     DEFAULT CURRENT_TIMESTAMP,
    status        ENUM ('WAITING','DENIED','CANCEL','CONFIRM') DEFAULT 'WAITING'
);
INSERT INTO admin (username, password)
VALUES ('admin01', 'adminpass123'),
       ('admin02', 'securepass456');

INSERT INTO student (student_name, student_dob, email, sex, phone, password, create_at)
VALUES ('Nguyen Van A', '2000-05-15', 'a.nguyen@example.com', 1, '0901234567', 'pass123', '2025-01-10'),
       ('Tran Thi B', '2001-08-20', 'b.tran@example.com', 0, '0912345678', 'pass456', '2025-02-12'),
       ('Le Van C', '1999-11-05', 'c.le@example.com', 1, '0933456789', 'pass789', '2025-03-05'),
       ('Pham Thi D', '2002-03-22', 'd.pham@example.com', 0, '0978123456', 'pass234', '2025-04-01'),
       ('Hoang Van E', '1998-07-09', 'e.hoang@example.com', 1, '0967567890', 'pass345', '2025-04-10'),
       ('Do Thi F', '2003-11-18', 'f.do@example.com', 0, '0922345567', 'passabc', '2025-04-15'),
       ('Nguyen Van G', '2001-06-11', 'g.nguyen@example.com', 1, '0911223344', 'passg1', '2025-05-01'),
       ('Tran Thi H', '2000-10-25', 'h.tran@example.com', 0, '0933112211', 'passh2', '2025-05-03'),
       ('Le Van I', '1997-12-30', 'i.le@example.com', 1, '0955667788', 'passi3', '2025-05-05'),
       ('Pham Thi J', '1999-08-08', 'j.pham@example.com', 0, '0966332211', 'passj4', '2025-05-07'),
       ('Hoang Van K', '2000-09-15', 'k.hoang@example.com', 1, '0977551122', 'passk5', '2025-05-10'),
       ('Do Thi L', '1998-03-17', 'l.do@example.com', 0, '0988223344', 'passl6', '2025-05-12'),
       ('Nguyen Van M', '2002-01-01', 'm.nguyen@example.com', 1, '0909090909', 'passm7', '2025-05-14'),
       ('Tran Thi N', '2001-05-05', 'n.tran@example.com', 0, '0918181818', 'passn8', '2025-05-16'),
       ('Le Van O', '1996-06-06', 'o.le@example.com', 1, '0927272727', 'passo9', '2025-05-18'),
       ('Pham Thi P', '1997-07-07', 'p.pham@example.com', 0, '0936363636', 'passp10', '2025-05-20'),
       ('Hoang Van Q', '1995-08-08', 'q.hoang@example.com', 1, '0945454545', 'passq11', '2025-05-22'),
       ('Do Thi R', '2004-09-09', 'r.do@example.com', 0, '0954545454', 'passr12', '2025-05-24');

INSERT INTO course (course_name, course_duration, course_instructor, create_at)
VALUES ('Lập trình Java cơ bản', 45, 'Thầy Nguyễn Văn A', '2025-07-01'),
       ('Thiết kế đồ họa với Photoshop', 30, 'Cô Trần Thị B', '2025-07-02'),
       ('Kỹ năng giao tiếp hiệu quả', 20, 'Thầy Lê Văn C', '2025-07-03'),
       ('Phân tích dữ liệu với Excel', 25, 'Cô Phạm Thị D', '2025-07-04'),
       ('Lập trình Web với HTML/CSS/JS', 60, 'Thầy Hoàng Văn E', '2025-07-05'),
       ('Tiếng Anh giao tiếp nâng cao', 40, 'Cô Đỗ Thị F', '2025-07-06'),
       ('Quản lý dự án với Agile/Scrum', 35, 'Thầy Vũ Quốc G', '2025-07-07'),
       ('Chứng chỉ kế toán tổng hợp', 50, 'Cô Nguyễn Thị H', '2025-07-08'),
       ('Lập trình Python cho người mới bắt đầu', 45, 'Thầy Trần Văn I', '2025-07-09'),
       ('Digital Marketing cơ bản', 30, 'Cô Lưu Thị K', '2025-07-10'),
       ('Thiết kế giao diện người dùng (UI/UX)', 40, 'Thầy Bùi Minh L', '2025-07-11'),
       ('Kỹ năng viết CV & phỏng vấn xin việc', 15, 'Cô Mai Thị M', '2025-07-12'),
       ('An ninh mạng và bảo mật thông tin', 55, 'Thầy Hà Văn N', '2025-07-13'),
       ('Lập trình di động với Android', 50, 'Thầy Phan Quốc P', '2025-07-14'),
       ('Kỹ năng làm việc nhóm', 20, 'Cô Tạ Minh Q', '2025-07-15');


INSERT INTO enrollment (student_id, course_id, register_at, status)
VALUES (1, 1, '2025-06-01 09:00:00', 'CONFIRM'),
       (2, 2, '2025-06-02 10:00:00', 'WAITING'),
       (3, 3, '2025-06-03 11:00:00', 'DENIED'),
       (4, 4, '2025-06-04 12:00:00', 'CONFIRM'),
       (5, 5, '2025-06-05 13:00:00', 'CANCEL'),
       (6, 6, '2025-06-06 14:00:00', 'WAITING'),
       (7, 7, '2025-06-07 15:00:00', 'CONFIRM'),
       (8, 8, '2025-06-08 16:00:00', 'DENIED'),
       (9, 9, '2025-06-09 17:00:00', 'WAITING'),
       (10, 10, '2025-06-10 18:00:00', 'CONFIRM'),
       (11, 11, '2025-06-11 09:30:00', 'WAITING'),
       (12, 12, '2025-06-12 10:30:00', 'CANCEL'),
       (13, 13, '2025-06-13 11:30:00', 'CONFIRM'),
       (14, 14, '2025-06-14 12:30:00', 'DENIED'),
       (15, 15, '2025-06-15 13:30:00', 'WAITING'),
       (16, 1, '2025-06-16 14:30:00', 'CONFIRM'),
       (17, 2, '2025-06-17 15:30:00', 'WAITING'),
       (18, 3, '2025-06-18 16:30:00', 'CONFIRM'),
       (1, 4, '2025-06-19 17:30:00', 'WAITING'),
       (2, 5, '2025-06-20 09:00:00', 'CONFIRM'),
       (3, 6, '2025-06-21 10:00:00', 'CANCEL'),
       (4, 7, '2025-06-22 11:00:00', 'DENIED'),
       (5, 8, '2025-06-23 12:00:00', 'CONFIRM'),
       (6, 9, '2025-06-24 13:00:00', 'WAITING');

DELIMITER &&

CREATE PROCEDURE login_admin(username_in VARCHAR(50), password_in VARCHAR(255))
BEGIN
    SELECT admin_id, username
    FROM admin
    WHERE username = username_in
      AND password = password_in;
END;

CREATE PROCEDURE login_student(
    IN email_in VARCHAR(100),
    IN password_in VARCHAR(255),
    OUT id_out INT
)
BEGIN
    SELECT student_id INTO id_out
    FROM student
    WHERE email = email_in
      AND password = password_in;
END;
# hiển thị danh sách course
CREATE PROCEDURE find_all_course()
BEGIN
    SELECT course_id,
           course_name,
           course_duration,
           course_instructor,
           create_at
    FROM course;
END;
# thêm mới course
CREATE PROCEDURE add_new_course(name_in VARCHAR(100), duration_in INT, instructor_in VARCHAR(100), created_in DATE)
BEGIN
    INSERT INTO course(course_name, course_duration, course_instructor, create_at)
    VALUES (name_in, duration_in, instructor_in, created_in);
END;

# Tìm course theo id
CREATE PROCEDURE find_course_by_id(id_in INT)
BEGIN
    SELECT course_name, course_duration, course_instructor, create_at
    FROM course
    WHERE course_id = id_in;
END;
# Cập nhật course
CREATE PROCEDURE update_course(id_in INT, name_in VARCHAR(100), duration_in INT, instructor_in VARCHAR(100),
                               creat_date DATE)
BEGIN
    UPDATE course
    SET course_name       = name_in,
        course_duration   = duration_in,
        course_instructor = instructor_in
    WHERE course_id = id_in;
END;

# Xóa course
CREATE PROCEDURE delete_course(id_in INT)
BEGIN
    DELETE
    FROM course
    WHERE course_id = id_in;
END;
# tìm course theo tên
CREATE PROCEDURE find_course_by_name(name_in VARCHAR(100))
BEGIN
    SELECT course_id, course_name, course_duration, course_instructor, create_at
    FROM course
    WHERE course_name LIKE CONCAT('%', name_in, '%');
END;
# Kiểm tra tên course đã tồn tại
CREATE PROCEDURE course_name_exist(name_in VARCHAR(100), OUT exist INT)
BEGIN
    SET exist = (SELECT COUNT(c.course_id) FROM course c WHERE course_name = name_in);
END;
# Phân trang và tìm kiếm course
-- CREATE PROCEDURE get_course_total_pages_by_name(
--     IN name_in VARCHAR(100),
--     IN data_of_page INT,
--     OUT pages INT
-- )
-- BEGIN
--     DECLARE cnt_course_pages INT;
--
-- SELECT COUNT(course_id)
-- INTO cnt_course_pages
-- FROM course
-- WHERE course_name LIKE CONCAT('%', IFNULL(name_in, ''), '%');
--
-- SET pages = CEIL(cnt_course_pages / data_of_page);
-- END;
--
-- CREATE PROCEDURE find_course_by_name(
--     IN name_in VARCHAR(100),
--     IN page INT,
--     IN data_of_page INT
-- )
-- BEGIN
--     DECLARE index_data INT;
--     SET index_data = (page - 1) * data_of_page;
--
-- SELECT course_id,
--        course_name,
--        course_duration,
--        course_instructor,
--        create_at
-- FROM course
-- WHERE course_name LIKE CONCAT('%', IFNULL(name_in, ''), '%')
--     LIMIT data_of_page OFFSET index_data;
-- END;

# Bảng student
# Hiển thị danh sách sinh viên
CREATE PROCEDURE show_all_student()
BEGIN
    SELECT student_id,
           student_name,
           student_dob,
           email,
           sex,
           phone,
           password,
           create_at
    FROM student;
END;

# kiểm tra email đã tồn tại
CREATE PROCEDURE is_email_exist(email_in VARCHAR(100), OUT exist INT)
BEGIN
    SET exist = (SELECT COUNT(student_id)
                 FROM student
                 WHERE email = email_in);

END;

# Thêm sinh viên
CREATE PROCEDURE add_new_student(name_in VARCHAR(100),
                                 dob_in DATE,
                                 email_in VARCHAR(100),
                                 sex_in BIT,
                                 phone_in VARCHAR(20),
                                 password_in VARCHAR(255),
                                 create_in DATE)
BEGIN
    INSERT INTO student(student_name, student_dob, email, sex, phone, password, create_at)
    VALUES (name_in, dob_in, email_in, sex_in, phone_in, password_in, create_in);
END;

# chỉnh sửa thông tin sinh viên
CREATE PROCEDURE update_student(id_in INT,
                                name_in VARCHAR(100),
                                dob_in DATE,
                                email_in VARCHAR(100),
                                sex_in BIT,
                                phone_in VARCHAR(20),
                                password_in VARCHAR(255))
BEGIN
    UPDATE student
    SET student_name = name_in,
        student_dob  = dob_in,
        email        = email_in,
        sex          = sex_in,
        phone=phone_in,
        password     = password_in
    WHERE student_id = id_in;

END;
# Lấy thông tin sinh viên theo id
CREATE PROCEDURE find_student_by_id(id_in INT)
BEGIN
    SELECT student_name, student_dob, email, sex, phone, password, create_at FROM student WHERE student_id = id_in;
END;

# tìm kiếm thông tin sinh viên theo id hoặc email hoặc họ tên
CREATE PROCEDURE search_student_advanced(
    IN search_type VARCHAR(20),
    IN keyword VARCHAR(100)
)
BEGIN
    IF search_type = 'id' THEN
        SELECT * FROM student WHERE CAST(student_id AS CHAR) LIKE CONCAT('%', keyword, '%');
    ELSEIF search_type = 'name' THEN
        SELECT * FROM student WHERE student_name LIKE CONCAT('%', keyword, '%');
    ELSEIF search_type = 'email' THEN
        SELECT * FROM student WHERE email LIKE CONCAT('%', keyword, '%');
    END IF;
END;

# xóa sinh viên
CREATE PROCEDURE delelte_student(id_in INT)
BEGIN
    DELETE FROM student WHERE student_id = id_in;
END;

# Lấy danh sách khóa học từ bảng student
# CREATE PROCEDURE show_all_enrollment()
# BEGIN
#     SELECT  c.course_name, s.student_name,e.register_at,e.status FROM enrollment e
#         INNER JOIN course c ON c.course_id = e.course_id
#         INNER JOIN student s ON e.student_id = s.student_id
#     WHERE status NOT LIKE 'CANCEL';
# END ;

# Đăng ký khóa học (có bao gồm kiểm tra)
CREATE PROCEDURE register_course(s_id INT, c_id INT, OUT message VARCHAR(100))
BEGIN
    IF EXISTS (SELECT 1
               FROM enrollment
               WHERE student_id = s_id
                 AND course_id = c_id) THEN

        SET message = 'Học viên đã đăng ký khóa học này.';
    ELSE
        INSERT INTO enrollment(student_id, course_id, status)
        VALUES (s_id, c_id, 'WAITING');
        SET message = 'Đăng ký khóa học thành công.';
    END IF;
END;

# Xem khóa học đã đăng ký bởi học viên
CREATE PROCEDURE get_enrolled_courses_by_student(IN student_id INT)
BEGIN
    SELECT c.course_id,
           c.course_name,
           c.course_instructor,
           e.register_at,
           e.status
    FROM enrollment e
             JOIN course c ON e.course_id = c.course_id
    WHERE e.student_id = student_id;
END;

DELIMITER &&

CALL get_enrolled_courses_by_student(2);