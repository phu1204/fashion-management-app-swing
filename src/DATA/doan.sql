CREATE DATABASE doan CHARACTER SET utf8 COLLATE utf8_general_ci;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `khach` (
  `Idkhach` int(11) NOT NULL AUTO_INCREMENT,
  `Họ` varchar(255) NOT NULL,
  `Tên` varchar(255)  NOT NULL,
  `gioitinh` varchar(10)  NOT NULL,
  `Gmail` varchar(30)  NOT NULL,
  `SDT` varchar(255)  NOT NULL,
    primary key(Idkhach)
) ;

ALTER TABLE khach AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `nhanvien` (
  `Idnv` int(11) NOT NULL AUTO_INCREMENT,
  `Họ` varchar(50) NOT NULL,
  `Tên` varchar(10) NOT NULL,
  `Ngày Sinh` date not null,
  `Giới tính` varchar(3) NOT NULL,
  `Địa chỉ` varchar(255) ,
  `Email` varchar(255) ,
  `SDT` varchar(10) NOT NULL,
  `Lương tháng` int(11) NOT NULL,
   primary key(Idnv)

);
ALTER TABLE nhanvien AUTO_INCREMENT=1;

CREATE TABLE `giamgia` (
  `IdGgiá` VARCHAR(11) NOT NULL,
  `Tên sự kiện` varchar(255) NOT NULL,
  `Ngày bắt đầu` date NOT NULL,
  `Ngày kết thúc` date NOT NULL,
    primary key(IdGgiá)
);

CREATE TABLE IF NOT EXISTS `loai` (
  `maloai` int(11) NOT NULL AUTO_INCREMENT,
  `tenloai` varchar(50) NOT NULL,
   primary key(maloai)
);
ALTER TABLE loai AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `sanpham` (
  `masanpham` varchar(10) NOT NULL ,
  `tensanpham` varchar(50) NOT NULL,
  `nsx` varchar(50) ,
  `img` varchar(50) ,
  `soluong` int(11) NOT NULL,
  `maloai` int(11) NOT NULL,
  `dongia` int(20) NOT NULL,
   primary key(masanpham),
   foreign key(maloai) references loai(maloai)
);


CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `manhacungcap` int(11) NOT NULL AUTO_INCREMENT,
  `tennhacungcap` varchar(255),
  `diachi` varchar(255),
  `sdt` varchar(10),
   primary key(manhacungcap)
);
ALTER TABLE nhacungcap AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `phieunhaphang` (
  `maphieunhap` int(11) NOT NULL AUTO_INCREMENT,
  `manhanvien` int(11) NOT NULL,
  `manhacungcap` int(11) NOT NULL,
  `ngaynhap` date NOT NULL,
  `tongtien` int(11) NOT NULL,
   primary key(maphieunhap),
   foreign key(manhacungcap) references nhacungcap(manhacungcap),
   foreign key(manhanvien) references nhanvien(Idnv)
);
ALTER TABLE phieunhaphang AUTO_INCREMENT=1;
CREATE TABLE IF NOT EXISTS `chitietphieunhap` (

  `maphieunhap` int(11) NOT NULL,
  `masanpham` varchar(10) NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` int(11) NOT NULL,
  `thanhtien` int(11) NOT NULL,
   primary key(maphieunhap,masanpham),
   foreign key(masanpham) references sanpham(masanpham)
) ;

CREATE TABLE IF NOT EXISTS `chitietsanpham` (
  `machitietsanpham` int(11) NOT NULL AUTO_INCREMENT,
  `masanpham` varchar(10) NOT NULL,
  `size` varchar(11) NOT NULL,
  `mausac` varchar(10) ,
  `soluong` int(11),
   primary key(machitietsanpham,masanpham),
   foreign key(masanpham) references sanpham(masanpham)
);
ALTER TABLE chitietsanpham AUTO_INCREMENT=1;



CREATE TABLE `hoadon` (
  `Idhd` int(11) NOT NULL AUTO_INCREMENT,
  `Idkhach` int(11) NOT NULL,
  `Idnv` int(11) NOT NULL,
  `IdGgiá` Varchar(11) NOT NULL,
  `Ngày` date NOT NULL,
  `Tổng tiền` int(11) NOT NULL,
  `Tiền giảm` int(11),
  `Tiền trả` int(11) NOT NULL,
    primary key(Idhd),
    foreign key (IdGgiá) references giamgia(IdGgiá),
    foreign key (Idnv) references nhanvien(Idnv),
    foreign key (Idkhach) references khach(Idkhach)
);
ALTER TABLE hoadon AUTO_INCREMENT=1;

CREATE TABLE `chitietgiamgia` (
  `IdGgiá` VARCHAR(11) NOT NULL,
  `Idsp` varchar(10) NOT NULL,
  `%giảm` int(11) NOT NULL,
    
    foreign key (IdGgiá) references giamgia(IdGgiá),
    foreign key (Idsp) references sanpham(masanpham)
) ;
CREATE TABLE `dangnhap` (
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `ID_Nhanvien` int(11)  NOT NULL,
  `Type` int(11) NOT NULL,
   primary key(username,ID_Nhanvien),
   foreign key (ID_Nhanvien) references nhanvien(Idnv) 
) ;
CREATE TABLE `chitiethoadon` (
  `Idhd` int(11) NOT NULL,
  `Idsp` varchar(10) NOT NULL,
  `Số lượng` int(11) NOT NULL,
  `Đơn giá` int(11) NOT NULL,
  `mactsp` int(11) NOT NULL,
foreign key(Idhd) references hoadon(Idhd),
foreign key(mactsp) references chitietsanpham(machitietsanpham),
foreign key(Idsp) references sanpham(masanpham)
) ;




INSERT INTO nhanvien(`Họ`,`Tên`,`Ngày Sinh`,`Giới tính`,`Địa chỉ`,`Email`,`SDT`,`Lương tháng`) values
('admin','admin','2001-08-20','adm','admin','admin@gmail','0','0'),
('Nguyễn Trọng','Phú','2001-08-20','Nam','TPHCM','phu@gmail','0123456789','10000000'),
('Nguyễn Hữu Hoài','Nam','2001-08-20','Nam','TPHCM','nam@gmail','0123456789','10000000'),
('Phan Minh','Phát','2001-09-14','Nam','TPHCM','phat@gmail','0123456789','20000000'),
('Lê Trọng','Nghĩa','2001-08-20','Nam','TPHCM','nghia@gmail','0123456789','10000000'),
('admin','admin','2001-08-20','adm','admin','admin@gmail','0','0'),
('Nguyễn Văn','A','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','3000000'),
('Nguyễn Văn','B','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','4000000'),
('Nguyễn Văn','C','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','5000000'),
('Nguyễn Văn','D','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','6000000'),
('Nguyễn Thị','A','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','3000000'),
('Nguyễn Thị','B','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','4000000'),
('Nguyễn Thị','C','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','5000000'),
('Nguyễn Thị','D','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','6000000'),
('Lê Văn','A','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','3000000'),
('Lê Văn','B','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','4000000'),
('Lê Văn','C','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','5000000'),
('Lê Văn','D','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','6000000'),
('Lê Thị','A','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','3000000'),
('Lê Thị','B','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','4000000'),
('Lê Thị','C','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','5000000'),
('Lê Thị','D','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','6000000'),
('Dương Văn','A','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','3000000'),
('Dương Văn','B','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','4000000'),
('Dương Văn','C','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','5000000'),
('Dương Văn','D','2001-08-20','Nam','TPHCM','nguyen@gmail','0123456789','6000000'),
('Dương Thị','A','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','3000000'),
('Dương Thị','B','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','4000000'),
('Dương Thị','C','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','5000000'),
('Dương Thị','D','2001-08-20','Nữ','TPHCM','nguyen1@gmail','0123456789','6000000'),
('Đinh Văn','A','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','3000000'),
('Đinh Văn','B','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','4000000'),
('Đinh Văn','C','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','5000000'),
('Đinh Văn','D','2001-08-20','Nam','TPHCM','nguyen2@gmail','0123456789','6000000'),
('Đinh Thị','A','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','3000000'),
('Đinh Thị','B','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','4000000'),
('Đinh Thị','C','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','5000000'),
('Đinh Thị','D','2001-08-20','Nữ','TPHCM','nguyen21@gmail','0123456789','6000000');




INSERT INTO khach(`Họ`,`Tên`,`gioitinh`,`Gmail`,`SDT`) values
('CUSTOMER','CUSTOMER','NaN','CUSTOMER@','CUSTOMER'),
('Phạm Văn','A','Nam','pham@gmail','0123456789'),
('Phạm Văn','B','Nam','pham@gmail','0123456789'),
('Phạm Văn','C','Nam','pham@gmail','0123456789'),
('Phạm Văn','D','Nam','pham@gmail','0123456789'),
('Phạm Thị','A','Nữ','pham1@gmail','0123456789'),
('Phạm Thị','B','Nữ','pham2@gmail','0123456789'),
('Phạm Thị','C','Nữ','pham1@gmail','0123456789'),
('Phạm Thị','D','Nữ','pham1@gmail','0123456789'),
('Phan Văn','A','Nam','pham@gmail','0123456789'),
('Phan Văn','B','Nam','pham@gmail','0123456789'),
('Phan Văn','C','Nam','pham@gmail','0123456789'),
('Phan Văn','D','Nam','pham@gmail','0123456789'),
('Phan Thị','A','Nữ','pham11@gmail','0123456789'),
('Phan Thị','B','Nữ','pham21@gmail','0123456789'),
('Phan Thị','C','Nữ','pham11@gmail','0123456789'),
('Phan Thị','D','Nữ','pham11@gmail','0123456789'),
('Nguyễn Văn','A','Nam','pham@gmail','0123456789'),
('Nguyễn Văn','B','Nam','pham@gmail','0123456789'),
('Nguyễn Văn','C','Nam','pham@gmail','0123456789'),
('Nguyễn Văn','D','Nam','pham@gmail','0123456789'),
('Nguyễn Thị','A','Nữ','pham1@gmail','0123456789'),
('Nguyễn Thị','B','Nữ','pham2@gmail','0123456789'),
('Nguyễn Thị','C','Nữ','pham1@gmail','0123456789'),
('Nguyễn Thị','D','Nữ','pham1@gmail','0123456789'),
('Lý Văn','A','Nam','pham@gmail','0123456789'),
('Lý Văn','B','Nam','pham@gmail','0123456789'),
('Lý Văn','C','Nam','pham@gmail','0123456789'),
('Lý Văn','D','Nam','pham@gmail','0123456789'),
('Lý Thị','A','Nữ','pham11@gmail','0123456789'),
('Lý Thị','B','Nữ','pham21@gmail','0123456789'),
('Lý Thị','C','Nữ','pham11@gmail','0123456789'),
('Lý Thị','D','Nữ','pham11@gmail','0123456789'),
('Lưu Văn','A','Nam','pham@gmail','0123456789'),
('Lưu Văn','B','Nam','pham@gmail','0123456789'),
('Lưu Văn','C','Nam','pham@gmail','0123456789'),
('Lưu Văn','D','Nam','pham@gmail','0123456789'),
('Lưu Thị','A','Nữ','pham11@gmail','0123456789'),
('Lưu Thị','B','Nữ','pham21@gmail','0123456789'),
('Lưu Thị','C','Nữ','pham11@gmail','0123456789'),
('Lưu Thị','D','Nữ','pham11@gmail','0123456789'),
('Đinh Văn','A','Nam','pham@gmail','0123456789'),
('Đinh Văn','B','Nam','pham@gmail','0123456789'),
('Đinh Văn','C','Nam','pham@gmail','0123456789'),
('Đinh Văn','D','Nam','pham@gmail','0123456789'),
('Đinh Thị','A','Nữ','pham11@gmail','0123456789'),
('Đinh Thị','B','Nữ','pham21@gmail','0123456789'),
('Đinh Thị','C','Nữ','pham11@gmail','0123456789'),
('Đinh Thị','D','Nữ','pham11@gmail','0123456789');


INSERT INTO `dangnhap` (`username`, `password`, `ID_Nhanvien`, `Type`) VALUES
('admin','admin', '1', 1),
('phu', 'phu', '2', 1),
('phat', 'phat', '4', 1),
('nam', 'nam', '3', 1),
('nghia', 'nghia', '5', 1);

insert into loai(tenloai) values 
('T-Shirt'),('SWEATSHIRT'),('HODDIES'),('JACKETS'),('JEANS'),('PANT'),('SHORTS'),('CAPS'),('BAGS'),('SOCKS');


insert into nhacungcap(tennhacungcap,diachi,sdt) values
('PHAT FASHION COMPANY','Quận 12,TPHCM','0707061515'),
('NAMNHI STORE','Quận Bình Tân,TPHCM','0123456789'),
('PHUGAY SHOP','Quận Bình Tân,TPHCM','0123456789'),
('NGHIANGUNGOC TAP DOAN 1 THANH VIEN','American Tho City','0147852369'),
('FASHION COMPANY','Quận 12,TPHCM','0707061515'),
('STORE','Quận Bình Tân,TPHCM','0123456789'),
('SHOP','Quận Bình Tân,TPHCM','0123456789'),
('TAP DOAN 1 THANH VIEN','American Tho City','0147852369'),
('PHAT FASHION','Quận 12,TPHCM','0707061515'),
('NAMNHI','Quận Bình Tân,TPHCM','0123456789'),
('PHUGAY','Quận Bình Tân,TPHCM','0123456789'),
('NGHIANGUNGOC TN','American Tho City','0147852369'),
('PHAT  COMPANY','Quận 12,TPHCM','0707061515'),
('NAM STORE','Quận Bình Tân,TPHCM','0123456789'),
('PHU SHOP','Quận Bình Tân,TPHCM','0123456789'),
('NGHIA TAP DOAN 1 THANH VIEN','American Tho City','0147852369');

INSERT INTO `giamgia` (`IdGgiá`, `Tên sự kiện`, `Ngày bắt đầu`, `Ngày kết thúc`) VALUES
('NULL', 'NULL', '2001-01-01', '2001-01-01'),
('CT1-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 1 NĂM 2020', '2020-1-1', '2020-1-31'),
('CT2-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 2 NĂM 2020', '2020-2-1', '2020-2-29'),
('CT3-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 3 NĂM 2020', '2020-3-1', '2020-3-31'),
('CT4-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 4 NĂM 2020', '2020-4-1', '2020-4-30'),
('CT5-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 5 NĂM 2020', '2020-5-1', '2020-5-31'),
('CT6-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 6 NĂM 2020', '2020-6-1', '2020-6-30'),
('CT7-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 7 NĂM 2020', '2020-7-1', '2020-7-31'),
('CT8-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 8 NĂM 2020', '2020-8-1', '2020-8-31'),
('CT9-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 9 NĂM 2020', '2020-9-1', '2020-9-30'),
('CT10-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 10 NĂM 2020', '2020-10-1', '2020-10-31'),
('CT11-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 11 NĂM 2020', '2020-11-1', '2020-11-30'),
('CT12-2020', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 12 NĂM 2020', '2020-12-1', '2020-12-31'),
('CT1-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 1 NĂM 2021', '2021-1-1', '2021-1-31'),
('CT2-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 2 NĂM 2021', '2021-2-1', '2021-2-29'),
('CT3-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 3 NĂM 2021', '2021-3-1', '2021-3-31'),
('CT4-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 4 NĂM 2021', '2021-4-1', '2021-4-30'),
('CT5-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 5 NĂM 2021', '2021-5-1', '2021-5-31'),
('CT6-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 6 NĂM 2021', '2021-6-1', '2021-6-30'),
('CT7-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 7 NĂM 2021', '2021-7-1', '2021-7-31'),
('CT8-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 8 NĂM 2021', '2021-8-1', '2021-8-31'),
('CT9-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 9 NĂM 2021', '2021-9-1', '2021-9-30'),
('CT10-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 10 NĂM 2021', '2021-10-1', '2021-10-31'),
('CT11-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 11 NĂM 2021', '2021-11-1', '2021-11-30'),
('CT12-2021', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 12 NĂM 2021', '2021-12-1', '2021-12-31'),
('CT1-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 1 NĂM 2019', '2019-1-1', '2019-1-31'),
('CT2-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 2 NĂM 2019', '2019-2-1', '2019-2-29'),
('CT3-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 3 NĂM 2019', '2019-3-1', '2019-3-31'),
('CT4-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 4 NĂM 2019', '2019-4-1', '2019-4-30'),
('CT5-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 5 NĂM 2019', '2019-5-1', '2019-5-31'),
('CT6-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 6 NĂM 2019', '2019-6-1', '2019-6-30'),
('CT7-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 7 NĂM 2019', '2019-7-1', '2019-7-31'),
('CT8-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 8 NĂM 2019', '2019-8-1', '2019-8-31'),
('CT9-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 9 NĂM 2019', '2019-9-1', '2019-9-30'),
('CT10-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 10 NĂM 2019', '2019-10-1', '2019-10-31'),
('CT11-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 11 NĂM 2019', '2019-11-1', '2019-11-30'),
('CT12-2019', 'CHƯƠNG TRÌNH GIẢM GIÁ THÁNG 12 NĂM 2019', '2019-12-1', '2019-12-31');

INSERT	INTO sanpham VALUES 
('TS1','Áo T-SHIRT ADIDAS','ADIDAS','blank.png',50,1,1000000),
('SW1','ÁO SWEATSHIRT ADIDAS','ADIDAS','blank.png',50,2,1000000),
('HO1','ÁO HODDIES ADIDAS','ADIDAS','blank.png',50,3,1000000),
('JA1','ÁO JACKET ADIDAS','ADIDAS','blank.png',50,4,1000000),
('JE1','ÁO JEAN ADIDAS','ADIDAS','blank.png',0,5,1000000),
('PA1','QUẦN DÀI ADIDAS','ADIDAS','blank.png',50,6,1000000),
('SH1','QUẦN NGẮN ADIDAS','ADIDAS','blank.png',50,7,1000000),
('CA1','NÓN ADIDAS','ADIDAS','blank.png',50,8,1000000),
('BA1','TÚI ADIDAS','ADIDAS','blank.png',50,9,1000000),
('SO1','VỚ ADIDAS','ADIDAS','blank.png',50,10,1000000),
('TS12','Áo T-SHIRT NIKE','NIKE','blank.png',50,1,1000000),
('SW12','ÁO SWEATSHIRT NIKE','NIKE','blank.png',50,2,1000000),
('HO12','ÁO HODDIES NIKE','NIKE','blank.png',50,3,1000000),
('JA12','ÁO JACKET NIKE','NIKE','blank.png',50,4,1000000),
('JE12','ÁO JEAN NIKE','NIKE','blank.png',0,5,1000000),
('PA12','QUẦN DÀI NIKE','NIKE','blank.png',50,6,1000000),
('SH12','QUẦN NGẮN NIKE','NIKE','blank.png',50,7,1000000),
('CA12','NÓN NIKE','NIKE','blank.png',50,8,1000000),
('BA12','TÚI NIKE','NIKE','blank.png',50,9,1000000),
('SO12','VỚ NIKE','NIKE','blank.png',50,10,1000000),
('TS13','Áo T-SHIRT PUMA','PUMA','blank.png',50,1,1000000),
('SW13','ÁO SWEATSHIRT PUMA','PUMA','blank.png',50,2,1000000),
('HO13','ÁO HODDIES PUMA','PUMA','blank.png',50,3,1000000),
('JA13','ÁO JACKET PUMA','PUMA','blank.png',50,4,1000000),
('JE13','ÁO JEAN PUMA','PUMA','blank.png',0,5,1000000),
('PA13','QUẦN DÀI PUMA','PUMA','blank.png',50,6,1000000),
('SH13','QUẦN NGẮN PUMA','PUMA','blank.png',50,7,1000000),
('CA13','NÓN PUMA','PUMA','blank.png',50,8,1000000),
('BA13','TÚI PUMA','PUMA','blank.png',50,9,1000000),
('SO13','VỚ PUMA','PUMA','blank.png',50,10,1000000),
('TS14','Áo T-SHIRT GUCCI','GUCCI','blank.png',50,1,1000000),
('SW14','ÁO SWEATSHIRT GUCCI','GUCCI','blank.png',50,2,1000000),
('HO14','ÁO HODDIES GUCCI','GUCCI','blank.png',50,3,1000000),
('JA14','ÁO JACKET GUCCI','GUCCI','blank.png',50,4,1000000),
('JE14','ÁO JEAN GUCCI','GUCCI','blank.png',0,5,1000000),
('PA14','QUẦN DÀI GUCCI','GUCCI','blank.png',50,6,1000000),
('SH14','QUẦN NGẮN GUCCI','GUCCI','blank.png',50,7,1000000),
('CA14','NÓN GUCCI','GUCCI','blank.png',50,8,1000000),
('BA14','TÚI GUCCI','GUCCI','blank.png',50,9,1000000),
('SO14','VỚ GUCCI','GUCCI','blank.png',50,10,1000000),
('TS15','Áo T-SHIRT LV','LV','blank.png',50,1,1000000),
('SW15','ÁO SWEATSHIRT LV','LV','blank.png',50,2,1000000),
('HO15','ÁO HODDIES LV','LV','blank.png',50,3,1000000),
('JA15','ÁO JACKET LV','LV','blank.png',50,4,1000000),
('JE15','ÁO JEAN LV','LV','blank.png',0,5,1000000),
('PA15','QUẦN DÀI LV','LV','blank.png',50,6,1000000),
('SH15','QUẦN NGẮN LV','LV','blank.png',50,7,1000000),
('CA15','NÓN LV','LV','blank.png',50,8,1000000),
('BA15','TÚI LV','LV','blank.png',50,9,1000000),
('SO15','VỚ LV','LV','blank.png',50,10,1000000);

INSERT INTO `chitietgiamgia` (`IdGgiá`, `Idsp`, `%giảm`) VALUES
('CT1-2019', 'TS1', 10),
('CT2-2019', 'TS12', 20),
('CT3-2019', 'TS13', 30),
('CT4-2019', 'TS14', 50),
('CT5-2019', 'TS1', 10),
('CT6-2019', 'TS12', 20),
('CT7-2019', 'TS13', 30),
('CT8-2019', 'TS14', 50),
('CT1-2020', 'TS1', 10),
('CT2-2020', 'TS12', 20),
('CT3-2020', 'TS13', 30),
('CT4-2020', 'TS14', 50),
('CT5-2020', 'TS1', 10),
('CT6-2020', 'TS12', 20),
('CT7-2020', 'TS13', 30),
('CT8-2020', 'TS14', 50),
('CT1-2021', 'TS1', 10),
('CT2-2021', 'TS12', 20),
('CT3-2021', 'TS13', 30),
('CT4-2021', 'TS14', 50),
('CT5-2021', 'TS1', 10),
('CT6-2021', 'TS12', 20),
('CT7-2021', 'TS13', 30),
('CT8-2021', 'TS14', 50);

INSERT INTO `chitietsanpham` (`masanpham`, `size`, `mausac`, `soluong`) VALUES
('TS1', 'L', 'Trắng', 10),
('TS1', 'XL', 'Đen', 10),
('TS1', 'XL', 'Đỏ', 10),
('TS1', 'L', 'Xám', 10),
('TS1', 'XL', 'Vàng', 10),
('TS12', 'XL', 'Vàng', 50),
('TS13', 'XL', 'Vàng', 50),
('TS14', 'XL', 'Vàng', 50),
('TS15', 'XL', 'Vàng', 50),

('SW1', 'L', 'Trắng', 10),
('SW1', 'L', 'Đen', 10),
('SW1', 'XL', 'Đỏ', 10),
('SW1', 'XL', 'Xám', 10),
('SW1', 'L', 'Vàng', 10),
('SW12', 'XL', 'Vàng', 50),
('SW13', 'XL', 'Vàng', 50),
('SW14', 'XL', 'Vàng', 50),
('SW15', 'XL', 'Vàng', 50),

('HO1', 'XL', 'Trắng', 10),
('HO1', 'XL', 'Đen', 10),
('HO1', 'L', 'Đỏ', 10),
('HO1', 'XL', 'Xám', 10),
('HO1', 'L', 'Vàng', 10),
('HO12', 'XL', 'Vàng', 50),
('HO13', 'XL', 'Vàng', 50),
('HO14', 'XL', 'Vàng', 50),
('HO15', 'XL', 'Vàng', 50),

('JA1', 'XL', 'Trắng', 10),
('JA1', 'L', 'Đen', 10),
('JA1', 'XL', 'Đỏ', 10),
('JA1', 'L', 'Xám', 10),
('JA1', 'XL', 'Vàng', 10),
('JA12', 'XL', 'Vàng', 50),
('JA13', 'XL', 'Vàng', 50),
('JA14', 'XL', 'Vàng', 50),
('JA15', 'XL', 'Vàng', 50),

('JE1', 'XL', 'Xanh', 50),
('JE12', 'XL', 'Xanh', 50),
('JE13', 'XL', 'Xanh', 50),
('JE14', 'XL', 'Xanh', 50),
('JE15', 'XL', 'Xanh', 50),

('PA1', 'XL', 'Vàng', 50),
('PA12', 'XL', 'Vàng', 50),
('PA13', 'XL', 'Vàng', 50),
('PA14', 'XL', 'Vàng', 50),
('PA15', 'XL', 'Vàng', 50),

('CA1', 'UNISEX', 'Vàng', 50),
('CA12', 'UNISEX', 'Vàng', 50),
('CA13', 'UNISEX', 'Vàng', 50),
('CA14', 'UNISEX', 'Vàng', 50),
('CA15', 'UNISEX', 'Vàng', 50),

('BA1', 'UNISEX', 'Vàng', 50),
('BA12', 'UNISEX', 'Vàng', 50),
('BA13', 'UNISEX', 'Vàng', 50),
('BA14', 'UNISEX', 'Vàng', 50),
('BA15', 'UNISEX', 'Vàng', 50),

('SO1', 'UNISEX', 'Vàng', 50),
('SO12', 'UNISEX', 'Vàng', 50),
('SO13', 'UNISEX', 'Vàng', 50),
('SO14', 'UNISEX', 'Vàng', 50),
('SO15', 'UNISEX', 'Vàng', 50),

('SH14', 'XL', 'Vàng', 50),
('SH15', 'XL', 'Vàng', 50),
('SH1', 'XL', 'Vàng', 50),
('SH12', 'XL', 'Vàng', 50),
('SH13', 'XL', 'Vàng', 50),
('SH14', 'XL', 'Vàng', 50),
('SH15', 'XL', 'Vàng', 50),

('CA1', 'UNISEX', 'Vàng', 50),
('CA12', 'UNISEX', 'Vàng', 50),
('CA13', 'UNISEX', 'Vàng', 50),
('CA14', 'UNISEX', 'Vàng', 50),
('CA15', 'UNISEX', 'Vàng', 50),

('BA1', 'UNISEX', 'Vàng', 50),
('BA12', 'UNISEX', 'Vàng', 50),
('BA13', 'UNISEX', 'Vàng', 50),
('BA14', 'UNISEX', 'Vàng', 50),
('BA15', 'UNISEX', 'Vàng', 50),

('SO1', 'UNISEX', 'Vàng', 50),
('SO12', 'UNISEX', 'Vàng', 50),
('SO13', 'UNISEX', 'Vàng', 50),
('SO14', 'UNISEX', 'Vàng', 50),
('SO15', 'UNISEX', 'Vàng', 50);

INSERT INTO `phieunhaphang`(`manhanvien`, `manhacungcap`, `ngaynhap`, `tongtien`) VALUES 
(1,1,'2020-1-20',200000000),
(2,2,'2020-2-20',200000000),
(3,3,'2020-3-20',200000000),
(4,4,'2020-4-20',200000000),
(5,5,'2020-5-20',200000000),
(6,6,'2020-6-20',200000000),
(7,7,'2020-7-20',200000000),
(8,8,'2020-8-20',200000000),
(9,9,'2020-9-20',200000000),
(10,10,'2020-10-20',200000000);

INSERT INTO `chitietphieunhap`(`maphieunhap`, `masanpham`, `soluong`, `dongia`, `thanhtien`) VALUES 
(1,'TS1',50,800000,40000000),
(1,'TS12',50,800000,40000000),
(1,'TS13',50,800000,40000000),
(1,'TS14',50,800000,40000000),
(1,'TS15',50,800000,40000000),

(2,'SW1',50,800000,40000000),
(2,'SW12',50,800000,40000000),
(2,'SW13',50,800000,40000000),
(2,'SW14',50,800000,40000000),
(2,'SW15',50,800000,40000000),

(3,'HO1',50,800000,40000000),
(3,'HO12',50,800000,40000000),
(3,'HO13',50,800000,40000000),
(3,'HO14',50,800000,40000000),
(3,'HO15',50,800000,40000000),

(4,'JA1',50,800000,40000000),
(4,'JA12',50,800000,40000000),
(4,'JA13',50,800000,40000000),
(4,'JA14',50,800000,40000000),
(4,'JA15',50,800000,40000000),

(5,'JE1',50,800000,40000000),
(5,'JE12',50,800000,40000000),
(5,'JE13',50,800000,40000000),
(5,'JE14',50,800000,40000000),
(5,'JE15',50,800000,40000000),

(6,'PA1',50,800000,40000000),
(6,'PA12',50,800000,40000000),
(6,'PA13',50,800000,40000000),
(6,'PA14',50,800000,40000000),
(6,'PA15',50,800000,40000000),

(7,'SH1',50,800000,40000000),
(7,'SH12',50,800000,40000000),
(7,'SH13',50,800000,40000000),
(7,'SH14',50,800000,40000000),
(7,'SH15',50,800000,40000000),

(8,'CA1',50,800000,40000000),
(8,'CA12',50,800000,40000000),
(8,'CA13',50,800000,40000000),
(8,'CA14',50,800000,40000000),
(8,'CA15',50,800000,40000000),

(9,'BA1',50,800000,40000000),
(9,'BA12',50,800000,40000000),
(9,'BA13',50,800000,40000000),
(9,'BA14',50,800000,40000000),
(9,'BA15',50,800000,40000000),

(10,'SO1',50,800000,40000000),
(10,'SO12',50,800000,40000000),
(10,'SO13',50,800000,40000000),
(10,'SO14',50,800000,40000000),
(10,'SO15',50,800000,40000000);

INSERT INTO `hoadon`(`Idkhach`, `Idnv`, `IdGgiá`, `Ngày`, `Tổng tiền`, `Tiền giảm`, `Tiền trả`) VALUES 
(2,'2','NULL','2021-1-20',5000000,0,5000000),
(3,'2','NULL','2021-1-21',2000000,0,2000000),
(4,'2','NULL','2021-1-22',8000000,0,8000000),
(5,'2','NULL','2021-1-23',10000000,0,10000000),
/*thang1*/
(1,'2','NULL','2019-1-1',2000000,0,2000000),
(2,'2','NULL','2019-1-2',3000000,0,3000000),
(3,'3','NULL','2019-1-3',4000000,0,4000000),
(3,'4','NULL','2019-1-4',5000000,0,5000000),
(3,'5','NULL','2019-1-5',6000000,0,6000000),
(3,'6','NULL','2019-1-6',7000000,0,7000000),
(3,'7','NULL','2019-1-7',8000000,0,8000000),
(4,'8','NULL','2019-1-8',9000000,0,9000000),
(4,'9','NULL','2019-1-9',10000000,0,10000000),
(5,'10','NULL','2019-1-10',1000000,0,1000000),
/*thang2*/
(5,'2','NULL','2019-2-1',2000000,0,2000000),
(6,'2','NULL','2019-2-2',3000000,0,3000000),
(7,'3','NULL','2019-2-3',4000000,0,4000000),
(8,'4','NULL','2019-2-4',5000000,0,5000000),
(9,'5','NULL','2019-2-5',6000000,0,6000000),
(11,'6','NULL','2019-2-6',7000000,0,7000000),
(11,'4','NULL','2019-2-4',5000000,0,5000000),
(12,'5','NULL','2019-2-5',6000000,0,6000000),
(13,'6','NULL','2019-2-6',7000000,0,7000000),
(14,'7','NULL','2019-2-7',8000000,0,8000000),
(15,'8','NULL','2019-2-8',9000000,0,9000000),
(16,'9','NULL','2019-2-9',10000000,0,10000000),
(1,'10','NULL','2019-2-10',1000000,0,1000000),
(1,'10','NULL','2019-2-10',30000000,0,30000000),
/*thang3*/
(1,'2','NULL','2019-3-1',2000000,0,2000000),
(1,'2','NULL','2019-3-2',3000000,0,3000000),
(1,'3','NULL','2019-3-3',4000000,0,4000000),
(1,'4','NULL','2019-3-4',5000000,0,5000000),
(1,'5','NULL','2019-3-5',6000000,0,6000000),
(1,'6','NULL','2019-3-6',7000000,0,7000000),
(1,'7','NULL','2019-3-7',8000000,0,8000000),
(1,'8','NULL','2019-3-8',9000000,0,9000000),
(1,'3','NULL','2019-3-3',4000000,0,4000000),
(1,'4','NULL','2019-3-4',5000000,0,5000000),
(1,'5','NULL','2019-3-5',6000000,0,6000000),
(1,'6','NULL','2019-3-6',7000000,0,7000000),
(1,'7','NULL','2019-3-7',8000000,0,8000000),
(1,'8','NULL','2019-3-8',9000000,0,9000000),
(1,'9','NULL','2019-3-9',10000000,0,10000000),
(1,'10','NULL','2019-3-10',1000000,0,1000000),
(1,'10','NULL','2019-2-10',30000000,0,30000000),

/*thang4*/
(1,'2','NULL','2019-4-1',2000000,0,2000000),
(1,'2','NULL','2019-4-2',3000000,0,3000000),
(1,'3','NULL','2019-4-3',4000000,0,4000000),
(1,'4','NULL','2019-4-4',5000000,0,5000000),
(1,'5','NULL','2019-4-5',6000000,0,6000000),
(1,'6','NULL','2019-4-6',7000000,0,7000000),
(1,'4','NULL','2019-4-4',5000000,0,5000000),
(1,'5','NULL','2019-4-5',6000000,0,6000000),
(1,'6','NULL','2019-4-6',7000000,0,7000000),
(1,'7','NULL','2019-4-7',8000000,0,8000000),
(1,'8','NULL','2019-4-8',9000000,0,9000000),
(1,'4','NULL','2019-4-4',5000000,0,5000000),
(1,'5','NULL','2019-4-5',6000000,0,6000000),
(1,'6','NULL','2019-4-6',7000000,0,7000000),
(1,'7','NULL','2019-4-7',8000000,0,8000000),
(1,'8','NULL','2019-4-8',9000000,0,9000000),
(1,'7','NULL','2019-4-7',8000000,0,8000000),
(1,'8','NULL','2019-4-8',9000000,0,9000000),
(1,'9','NULL','2019-4-9',10000000,0,10000000),
(1,'10','NULL','2019-4-10',1000000,0,1000000),
(1,'4','NULL','2019-4-4',5000000,0,5000000),
(1,'5','NULL','2019-4-5',6000000,0,6000000),
(1,'6','NULL','2019-4-6',7000000,0,7000000),
(1,'7','NULL','2019-4-7',8000000,0,8000000),
(1,'8','NULL','2019-4-8',9000000,0,9000000),
(1,'7','NULL','2019-4-7',8000000,0,8000000),
(1,'8','NULL','2019-4-8',9000000,0,9000000),
(1,'9','NULL','2019-4-9',10000000,0,10000000),
(1,'10','NULL','2019-4-10',1000000,0,1000000),
/*thang5*/
(1,'2','NULL','2019-5-1',2000000,0,2000000),
(1,'2','NULL','2019-5-2',3000000,0,3000000),
(1,'3','NULL','2019-5-3',4000000,0,4000000),
(1,'4','NULL','2019-5-4',5000000,0,5000000),
(1,'5','NULL','2019-5-5',6000000,0,6000000),
(1,'6','NULL','2019-5-6',7000000,0,7000000),
(1,'7','NULL','2019-5-7',8000000,0,8000000),
(1,'8','NULL','2019-5-8',9000000,0,9000000),
(1,'9','NULL','2019-5-9',10000000,0,10000000),
(1,'10','NULL','2019-5-10',1000000,0,1000000),
(1,'6','NULL','2019-5-6',7000000,0,7000000),
(1,'7','NULL','2019-5-7',8000000,0,8000000),
(1,'8','NULL','2019-5-8',9000000,0,9000000),
(1,'9','NULL','2019-5-9',10000000,0,10000000),
(1,'10','NULL','2019-5-10',1000000,0,1000000),
(1,'6','NULL','2019-5-6',7000000,0,7000000),
(1,'7','NULL','2019-5-7',8000000,0,8000000),
(1,'8','NULL','2019-5-8',9000000,0,9000000),
(1,'9','NULL','2019-5-9',10000000,0,10000000),
(1,'10','NULL','2019-5-10',1000000,0,1000000),

/*thang6*/
(1,'2','NULL','2019-6-1',2000000,0,2000000),
(1,'2','NULL','2019-6-2',3000000,0,3000000),
(1,'3','NULL','2019-6-3',4000000,0,4000000),
(1,'4','NULL','2019-6-4',5000000,0,5000000),
(1,'5','NULL','2019-6-5',6000000,0,6000000),
(1,'4','NULL','2019-6-4',5000000,0,5000000),
(1,'5','NULL','2019-6-5',6000000,0,6000000),
(1,'4','NULL','2019-6-4',5000000,0,5000000),
(1,'5','NULL','2019-6-5',6000000,0,6000000),
(1,'4','NULL','2019-6-4',5000000,0,5000000),
(1,'5','NULL','2019-6-5',6000000,0,6000000),
(1,'4','NULL','2019-6-4',5000000,0,5000000),
(1,'5','NULL','2019-6-5',6000000,0,6000000),
(1,'6','NULL','2019-6-6',7000000,0,7000000),
(1,'7','NULL','2019-6-7',8000000,0,8000000),
(1,'8','NULL','2019-6-8',9000000,0,9000000),
(1,'9','NULL','2019-6-9',10000000,0,10000000),
(1,'10','NULL','2019-6-10',1000000,0,1000000),
/*thang7*/
(1,'2','NULL','2019-7-1',2000000,0,2000000),
(1,'2','NULL','2019-7-2',3000000,0,3000000),
(1,'3','NULL','2019-7-3',4000000,0,4000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'6','NULL','2019-7-6',7000000,0,7000000),
(1,'7','NULL','2019-7-7',8000000,0,8000000),
(1,'8','NULL','2019-7-8',9000000,0,9000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'6','NULL','2019-7-6',7000000,0,7000000),
(1,'7','NULL','2019-7-7',8000000,0,8000000),
(1,'8','NULL','2019-7-8',9000000,0,9000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'2','NULL','2019-7-2',3000000,0,3000000),
(1,'3','NULL','2019-7-3',4000000,0,4000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'6','NULL','2019-7-6',7000000,0,7000000),
(1,'7','NULL','2019-7-7',8000000,0,8000000),
(1,'8','NULL','2019-7-8',9000000,0,9000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'6','NULL','2019-7-6',7000000,0,7000000),
(1,'7','NULL','2019-7-7',8000000,0,8000000),
(1,'8','NULL','2019-7-8',9000000,0,9000000),
(1,'4','NULL','2019-7-4',5000000,0,5000000),
(1,'5','NULL','2019-7-5',6000000,0,6000000),
(1,'6','NULL','2019-7-6',7000000,0,7000000),
(1,'7','NULL','2019-7-7',8000000,0,8000000),
(1,'8','NULL','2019-7-8',9000000,0,9000000),
(1,'9','NULL','2019-7-9',10000000,0,10000000),
(1,'10','NULL','2019-7-10',1000000,0,1000000),
/*thang8*/
(1,'2','NULL','2019-8-1',2000000,0,2000000),
(1,'2','NULL','2019-8-2',3000000,0,3000000),
(1,'3','NULL','2019-8-3',4000000,0,4000000),
(1,'6','NULL','2019-8-6',7000000,0,7000000),
(1,'7','NULL','2019-8-7',8000000,0,8000000),
(1,'8','NULL','2019-8-8',9000000,0,9000000),
(1,'4','NULL','2019-8-4',5000000,0,5000000),
(1,'5','NULL','2019-8-5',6000000,0,6000000),
(1,'6','NULL','2019-8-6',7000000,0,7000000),
(1,'7','NULL','2019-8-7',8000000,0,8000000),
(1,'8','NULL','2019-8-8',9000000,0,9000000),
(1,'9','NULL','2019-8-9',10000000,0,10000000),
(1,'10','NULL','2019-8-10',1000000,0,1000000),
/*thang9*/
(1,'2','NULL','2019-9-1',2000000,0,2000000),
(1,'2','NULL','2019-9-2',3000000,0,3000000),
(1,'3','NULL','2019-9-3',4000000,0,4000000),
(1,'4','NULL','2019-9-4',5000000,0,5000000),
(1,'5','NULL','2019-9-5',6000000,0,6000000),
(1,'6','NULL','2019-9-6',7000000,0,7000000),
(1,'7','NULL','2019-9-7',8000000,0,8000000),
(1,'8','NULL','2019-9-8',9000000,0,9000000),
(1,'9','NULL','2019-9-9',10000000,0,10000000),
(1,'4','NULL','2019-9-4',5000000,0,5000000),
(1,'5','NULL','2019-9-5',6000000,0,6000000),
(1,'6','NULL','2019-9-6',7000000,0,7000000),
(1,'7','NULL','2019-9-7',8000000,0,8000000),
(1,'8','NULL','2019-9-8',9000000,0,9000000),
(1,'9','NULL','2019-9-9',10000000,0,10000000),
(1,'4','NULL','2019-9-4',5000000,0,5000000),
(1,'5','NULL','2019-9-5',6000000,0,6000000),
(1,'6','NULL','2019-9-6',7000000,0,7000000),
(1,'7','NULL','2019-9-7',8000000,0,8000000),
(1,'8','NULL','2019-9-8',9000000,0,9000000),
(1,'9','NULL','2019-9-9',10000000,0,10000000),
(1,'10','NULL','2019-9-10',1000000,0,1000000),
/*thang10*/
(1,'2','NULL','2019-10-1',2000000,0,2000000),
(1,'2','NULL','2019-10-2',3000000,0,3000000),
(1,'3','NULL','2019-10-3',4000000,0,4000000),
(1,'4','NULL','2019-10-4',5000000,0,5000000),
(1,'5','NULL','2019-10-5',6000000,0,6000000),
(1,'6','NULL','2019-10-6',7000000,0,7000000),
(1,'7','NULL','2019-10-7',8000000,0,8000000),
(1,'4','NULL','2019-10-4',5000000,0,5000000),
(1,'5','NULL','2019-10-5',6000000,0,6000000),
(1,'6','NULL','2019-10-6',7000000,0,7000000),
(1,'7','NULL','2019-10-7',8000000,0,8000000),
(1,'4','NULL','2019-10-4',5000000,0,5000000),
(1,'5','NULL','2019-10-5',6000000,0,6000000),
(1,'6','NULL','2019-10-6',7000000,0,7000000),
(1,'7','NULL','2019-10-7',8000000,0,8000000),
(1,'8','NULL','2019-10-8',9000000,0,9000000),
(1,'9','NULL','2019-10-9',10000000,0,10000000),
(1,'10','NULL','2019-10-10',1000000,0,1000000),
/*thang11*/
(1,'2','NULL','2019-11-1',2000000,0,2000000),
(1,'2','NULL','2019-11-2',3000000,0,3000000),
(1,'3','NULL','2019-11-3',4000000,0,4000000),
(1,'4','NULL','2019-11-4',5000000,0,5000000),
(1,'5','NULL','2019-11-5',6000000,0,6000000),
(1,'6','NULL','2019-11-6',7000000,0,7000000),
(1,'7','NULL','2019-11-7',8000000,0,8000000),
(1,'4','NULL','2019-11-4',5000000,0,5000000),
(1,'5','NULL','2019-11-5',6000000,0,6000000),
(1,'6','NULL','2019-11-6',7000000,0,7000000),
(1,'7','NULL','2019-11-7',8000000,0,8000000),
(1,'4','NULL','2019-11-4',5000000,0,5000000),
(1,'5','NULL','2019-11-5',6000000,0,6000000),
(1,'6','NULL','2019-11-6',7000000,0,7000000),
(1,'7','NULL','2019-11-7',8000000,0,8000000),
(1,'8','NULL','2019-11-8',9000000,0,9000000),
(1,'9','NULL','2019-11-9',10000000,0,10000000),
(1,'10','NULL','2019-11-10',1000000,0,1000000),
/*thang12*/
(1,'2','NULL','2019-12-1',2000000,0,2000000),
(1,'2','NULL','2019-12-2',3000000,0,3000000),
(1,'3','NULL','2019-12-3',4000000,0,4000000),
(1,'4','NULL','2019-12-4',5000000,0,5000000),
(1,'5','NULL','2019-12-5',6000000,0,6000000),
(1,'6','NULL','2019-12-6',7000000,0,7000000),
(1,'7','NULL','2019-12-7',8000000,0,8000000),
(1,'8','NULL','2019-12-8',9000000,0,9000000),
(1,'9','NULL','2019-12-9',10000000,0,10000000),
(1,'10','NULL','2019-12-10',1000000,0,1000000);



INSERT INTO `chitiethoadon` (`Idhd`, `Idsp`, `Số lượng`, `Đơn giá`, `mactsp`) VALUES
(1, 'TS1', 1, 1000000, 1),
(1, 'TS1', 2, 1000000, 2),
(1, 'TS1', 2, 1000000, 3),

(2, 'TS1', 2, 1000000, 3),
(2, 'TS1', 2, 1000000, 4),

(3, 'TS1', 2, 1000000, 2),
(3, 'TS1', 1, 1000000, 4),
(3, 'TS1', 1, 1000000, 5),
(3, 'TS1', 2, 1000000, 1),
(3, 'TS1', 2, 1000000, 2),

(4, 'TS1', 2, 1000000, 2),
(4, 'TS1', 3, 1000000, 2),
(4, 'TS1', 1, 1000000, 1),
(4, 'TS1', 1, 1000000, 4),
(4, 'TS1', 1, 1000000, 5),
(4, 'TS1', 2, 1000000, 1),
(4, 'TS1', 2, 1000000, 2),

(5, 'SW1', 2, 1000000, 2),
(5, 'SW1', 3, 1000000, 2),
(6, 'SW1', 1, 1000000, 1),
(6, 'SW1', 1, 1000000, 4),
(7, 'SW1', 1, 1000000, 5),
(7, 'SW1', 2, 1000000, 1),
(8, 'SW1', 2, 1000000, 2),

(8, 'SW1', 2, 1000000, 2),
(9, 'SW1', 3, 1000000, 2),
(9, 'SW1', 1, 1000000, 1),
(10, 'SW1', 1, 1000000, 4),
(10, 'SW1', 1, 1000000, 5),
(11, 'SW1', 2, 1000000, 1),
(11, 'SW1', 2, 1000000, 2),

(12, 'SW1', 2, 1000000, 2),
(12, 'SW1', 3, 1000000, 2),
(13, 'SW1', 1, 1000000, 1),
(13, 'SW1', 1, 1000000, 4),
(14, 'SW1', 1, 1000000, 5),
(14, 'HO1', 2, 1000000, 1),
(15, 'HO1', 2, 1000000, 2),

(15, 'HO1', 2, 1000000, 2),
(16, 'HO1', 3, 1000000, 2),
(16, 'HO1', 1, 1000000, 1),
(17, 'HO1', 1, 1000000, 4),
(17, 'HO1', 1, 1000000, 5),
(18, 'HO1', 2, 1000000, 1),
(18, 'HO1', 2, 1000000, 2),

(19, 'HO1', 2, 1000000, 2),
(19, 'HO1', 3, 1000000, 2),
(24, 'HO1', 1, 1000000, 1),
(24, 'CA1', 1, 1000000, 4),
(25, 'CA1', 1, 1000000, 5),
(25, 'CA1', 2, 1000000, 1),
(26, 'CA1', 2, 1000000, 2),

(26, 'CA1', 2, 1000000, 2),
(27, 'CA1', 3, 1000000, 2),
(27, 'CA1', 1, 1000000, 1),
(34, 'CA1', 1, 1000000, 4),
(34, 'CA1', 1, 1000000, 5),
(44, 'CA1', 2, 1000000, 1),
(44, 'BA1', 2, 1000000, 2),

(45, 'BA1', 2, 1000000, 2),
(45, 'BA1', 3, 1000000, 2),
(46, 'BA1', 1, 1000000, 1),
(46, 'BA1', 1, 1000000, 4),
(47, 'BA1', 1, 1000000, 5),
(47, 'BA1', 2, 1000000, 1),
(48, 'BA1', 2, 1000000, 2),

(48, 'BA1', 2, 1000000, 2),
(54, 'BA1', 3, 1000000, 2),
(54, 'PA1', 1, 1000000, 1),
(55, 'PA1', 1, 1000000, 4),
(55, 'PA1', 1, 1000000, 5),
(56, 'JE1', 2, 1000000, 1),
(56, 'SO1', 2, 1000000, 2);
/*Tổng tiền theo tháng và theo năm*/

SELECT MONTH(`Ngày`) , SUM(`Tổng tiền`) 
FROM `hoadon` 
WHERE  MONTH(`Ngày`) = MONTH(CURRENT_DATE)
AND YEAR(`Ngày`) = YEAR(CURRENT_DATE);

/* Tổng tiền theo từ tháng đến tháng và theo năm */
SELECT MONTH(`Ngày`) , SUM(`Tổng tiền`) 
FROM `hoadon` 
WHERE  MONTH(`Ngày`) >= MONTH(CURRENT_DATE)
AND MONTH(`Ngày`) <= MONTH(CURRENT_DATE)
AND YEAR(`Ngày`) = YEAR(CURRENT_DATE);


CREATE TABLE `dangnhap` (
  `username` varchar(30) NOT NULL,
  `password` varchar(50) NOT NULL,
  `ID_Nhanvien` int(11) NOT NULL,
  `Type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`username`, `password`, `ID_Nhanvien`, `Type`) VALUES
('admin', 'admin', 1, 1),
('nam', 'nam', 3, 1),
('nghia', 'nghia', 5, 1),
('phat', 'phat', 4, 1),
('phu', 'phu', 2, 1);

-- --------------------------------------------------------







CREATE TABLE `khach` (
  `Idkhach` int(11) NOT NULL,
  `Họ` varchar(255) NOT NULL,
  `Tên` varchar(255) NOT NULL,
  `gioitinh` varchar(10) NOT NULL,
  `Gmail` varchar(30) NOT NULL,
  `SDT` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `khach`
--

INSERT INTO `khach` (`Idkhach`, `Họ`, `Tên`, `gioitinh`, `Gmail`, `SDT`) VALUES
(1, 'CUSTOMER', 'CUSTOMER', 'NaN', 'CUSTOMER@', 'CUSTOMER'),
(2, 'Phạm Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(3, 'Phạm Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(4, 'Phạm Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(5, 'Phạm Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(6, 'Phạm Thị', 'A', 'Nữ', 'pham1@gmail', '0123456789'),
(7, 'Phạm Thị', 'B', 'Nữ', 'pham2@gmail', '0123456789'),
(8, 'Phạm Thị', 'C', 'Nữ', 'pham1@gmail', '0123456789'),
(9, 'Phạm Thị', 'D', 'Nữ', 'pham1@gmail', '0123456789'),
(10, 'Phan Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(11, 'Phan Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(12, 'Phan Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(13, 'Phan Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(14, 'Phan Thị', 'A', 'Nữ', 'pham11@gmail', '0123456789'),
(15, 'Phan Thị', 'B', 'Nữ', 'pham21@gmail', '0123456789'),
(16, 'Phan Thị', 'C', 'Nữ', 'pham11@gmail', '0123456789'),
(17, 'Phan Thị', 'D', 'Nữ', 'pham11@gmail', '0123456789'),
(18, 'Nguyễn Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(19, 'Nguyễn Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(20, 'Nguyễn Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(21, 'Nguyễn Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(22, 'Nguyễn Thị', 'A', 'Nữ', 'pham1@gmail', '0123456789'),
(23, 'Nguyễn Thị', 'B', 'Nữ', 'pham2@gmail', '0123456789'),
(24, 'Nguyễn Thị', 'C', 'Nữ', 'pham1@gmail', '0123456789'),
(25, 'Nguyễn Thị', 'D', 'Nữ', 'pham1@gmail', '0123456789'),
(26, 'Lý Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(27, 'Lý Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(28, 'Lý Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(29, 'Lý Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(30, 'Lý Thị', 'A', 'Nữ', 'pham11@gmail', '0123456789'),
(31, 'Lý Thị', 'B', 'Nữ', 'pham21@gmail', '0123456789'),
(32, 'Lý Thị', 'C', 'Nữ', 'pham11@gmail', '0123456789'),
(33, 'Lý Thị', 'D', 'Nữ', 'pham11@gmail', '0123456789'),
(34, 'Lưu Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(35, 'Lưu Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(36, 'Lưu Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(37, 'Lưu Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(38, 'Lưu Thị', 'A', 'Nữ', 'pham11@gmail', '0123456789'),
(39, 'Lưu Thị', 'B', 'Nữ', 'pham21@gmail', '0123456789'),
(40, 'Lưu Thị', 'C', 'Nữ', 'pham11@gmail', '0123456789'),
(41, 'Lưu Thị', 'D', 'Nữ', 'pham11@gmail', '0123456789'),
(42, 'Đinh Văn', 'A', 'Nam', 'pham@gmail', '0123456789'),
(43, 'Đinh Văn', 'B', 'Nam', 'pham@gmail', '0123456789'),
(44, 'Đinh Văn', 'C', 'Nam', 'pham@gmail', '0123456789'),
(45, 'Đinh Văn', 'D', 'Nam', 'pham@gmail', '0123456789'),
(46, 'Đinh Thị', 'A', 'Nữ', 'pham11@gmail', '0123456789'),
(47, 'Đinh Thị', 'B', 'Nữ', 'pham21@gmail', '0123456789'),
(48, 'Đinh Thị', 'C', 'Nữ', 'pham11@gmail', '0123456789'),
(49, 'Đinh Thị', 'D', 'Nữ', 'pham11@gmail', '0123456789');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loai`
--

CREATE TABLE `loai` (
  `maloai` int(11) NOT NULL,
  `tenloai` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loai`
--

INSERT INTO `loai` (`maloai`, `tenloai`) VALUES
(1, 'T-Shirt'),
(2, 'SWEATSHIRT'),
(3, 'HODDIES'),
(4, 'JACKETS'),
(5, 'JEANS'),
(6, 'PANT'),
(7, 'SHORTS'),
(8, 'CAPS'),
(9, 'BAGS'),
(10, 'SOCKS');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `manhacungcap` int(11) NOT NULL,
  `tennhacungcap` varchar(255) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `sdt` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`manhacungcap`, `tennhacungcap`, `diachi`, `sdt`) VALUES
(1, 'PHAT FASHION COMPANY', 'Quận 12,TPHCM', '0707061515'),
(2, 'NAMNHI STORE', 'Quận Bình Tân,TPHCM', '0123456789'),
(3, 'PHUGAY SHOP', 'Quận Bình Tân,TPHCM', '0123456789'),
(4, 'NGHIANGUNGOC TAP DOAN 1 THANH VIEN', 'American Tho City', '0147852369'),
(5, 'FASHION COMPANY', 'Quận 12,TPHCM', '0707061515'),
(6, 'STORE', 'Quận Bình Tân,TPHCM', '0123456789'),
(7, 'SHOP', 'Quận Bình Tân,TPHCM', '0123456789'),
(8, 'TAP DOAN 1 THANH VIEN', 'American Tho City', '0147852369'),
(9, 'PHAT FASHION', 'Quận 12,TPHCM', '0707061515'),
(10, 'NAMNHI', 'Quận Bình Tân,TPHCM', '0123456789'),
(11, 'PHUGAY', 'Quận Bình Tân,TPHCM', '0123456789'),
(12, 'NGHIANGUNGOC TN', 'American Tho City', '0147852369'),
(13, 'PHAT  COMPANY', 'Quận 12,TPHCM', '0707061515'),
(14, 'NAM STORE', 'Quận Bình Tân,TPHCM', '0123456789'),
(15, 'PHU SHOP', 'Quận Bình Tân,TPHCM', '0123456789'),
(16, 'NGHIA TAP DOAN 1 THANH VIEN', 'American Tho City', '0147852369');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `Idnv` int(11) NOT NULL,
  `Họ` varchar(50) NOT NULL,
  `Tên` varchar(10) NOT NULL,
  `Ngày Sinh` date NOT NULL,
  `Giới tính` varchar(3) NOT NULL,
  `Địa chỉ` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `SDT` varchar(10) NOT NULL,
  `Lương tháng` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`Idnv`, `Họ`, `Tên`, `Ngày Sinh`, `Giới tính`, `Địa chỉ`, `Email`, `SDT`, `Lương tháng`) VALUES
(1, 'admin', 'admin', '2001-08-20', 'adm', 'admin', 'admin@gmail', '0', 0),
(2, 'Nguyễn Trọng', 'Phú', '2001-08-20', 'Nam', 'TPHCM', 'phu@gmail', '0123456789', 10000000),
(3, 'Nguyễn Hữu Hoài', 'Nam', '2001-08-20', 'Nam', 'TPHCM', 'nam@gmail', '0123456789', 10000000),
(4, 'Phan Minh', 'Phát', '2001-09-14', 'Nam', 'TPHCM', 'phat@gmail', '0123456789', 20000000),
(5, 'Lê Trọng', 'Nghĩa', '2001-08-20', 'Nam', 'TPHCM', 'nghia@gmail', '0123456789', 10000000),
(6, 'admin', 'admin', '2001-08-20', 'adm', 'admin', 'admin@gmail', '0', 0),
(7, 'Nguyễn Văn', 'A', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 3000000),
(8, 'Nguyễn Văn', 'B', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 4000000),
(9, 'Nguyễn Văn', 'C', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 5000000),
(10, 'Nguyễn Văn', 'D', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 6000000),
(11, 'Nguyễn Thị', 'A', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 3000000),
(12, 'Nguyễn Thị', 'B', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 4000000),
(13, 'Nguyễn Thị', 'C', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 5000000),
(14, 'Nguyễn Thị', 'D', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 6000000),
(15, 'Lê Văn', 'A', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 3000000),
(16, 'Lê Văn', 'B', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 4000000),
(17, 'Lê Văn', 'C', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 5000000),
(18, 'Lê Văn', 'D', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 6000000),
(19, 'Lê Thị', 'A', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 3000000),
(20, 'Lê Thị', 'B', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 4000000),
(21, 'Lê Thị', 'C', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 5000000),
(22, 'Lê Thị', 'D', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 6000000),
(23, 'Dương Văn', 'A', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 3000000),
(24, 'Dương Văn', 'B', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 4000000),
(25, 'Dương Văn', 'C', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 5000000),
(26, 'Dương Văn', 'D', '2001-08-20', 'Nam', 'TPHCM', 'nguyen@gmail', '0123456789', 6000000),
(27, 'Dương Thị', 'A', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 3000000),
(28, 'Dương Thị', 'B', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 4000000),
(29, 'Dương Thị', 'C', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 5000000),
(30, 'Dương Thị', 'D', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen1@gmail', '0123456789', 6000000),
(31, 'Đinh Văn', 'A', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 3000000),
(32, 'Đinh Văn', 'B', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 4000000),
(33, 'Đinh Văn', 'C', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 5000000),
(34, 'Đinh Văn', 'D', '2001-08-20', 'Nam', 'TPHCM', 'nguyen2@gmail', '0123456789', 6000000),
(35, 'Đinh Thị', 'A', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 3000000),
(36, 'Đinh Thị', 'B', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 4000000),
(37, 'Đinh Thị', 'C', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 5000000),
(38, 'Đinh Thị', 'D', '2001-08-20', 'Nữ', 'TPHCM', 'nguyen21@gmail', '0123456789', 6000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhaphang`
--

CREATE TABLE `phieunhaphang` (
  `maphieunhap` int(11) NOT NULL,
  `manhanvien` int(11) NOT NULL,
  `manhacungcap` int(11) NOT NULL,
  `ngaynhap` date NOT NULL,
  `tongtien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `phieunhaphang`
--

INSERT INTO `phieunhaphang` (`maphieunhap`, `manhanvien`, `manhacungcap`, `ngaynhap`, `tongtien`) VALUES
(1, 1, 1, '2020-01-20', 200000000),
(2, 2, 2, '2020-02-20', 200000000),
(3, 3, 3, '2020-03-20', 200000000),
(4, 4, 4, '2020-04-20', 200000000),
(5, 5, 5, '2020-05-20', 200000000),
(6, 6, 6, '2020-06-20', 200000000),
(7, 7, 7, '2020-07-20', 200000000),
(8, 8, 8, '2020-08-20', 200000000),
(9, 9, 9, '2020-09-20', 200000000),
(10, 10, 10, '2020-10-20', 200000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `masanpham` varchar(10) NOT NULL,
  `tensanpham` varchar(50) NOT NULL,
  `nsx` varchar(50) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  `soluong` int(11) NOT NULL,
  `maloai` int(11) NOT NULL,
  `dongia` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`masanpham`, `tensanpham`, `nsx`, `img`, `soluong`, `maloai`, `dongia`) VALUES
('BA1', 'TÚI ADIDAS', 'ADIDAS', 'blank.png', 50, 9, 1000000),
('BA12', 'TÚI NIKE', 'NIKE', 'blank.png', 50, 9, 1000000),
('BA13', 'TÚI PUMA', 'PUMA', 'blank.png', 50, 9, 1000000),
('BA14', 'TÚI GUCCI', 'GUCCI', 'blank.png', 50, 9, 1000000),
('BA15', 'TÚI LV', 'LV', 'blank.png', 50, 9, 1000000),
('CA1', 'NÓN ADIDAS', 'ADIDAS', 'blank.png', 50, 8, 1000000),
('CA12', 'NÓN NIKE', 'NIKE', 'blank.png', 50, 8, 1000000),
('CA13', 'NÓN PUMA', 'PUMA', 'blank.png', 50, 8, 1000000),
('CA14', 'NÓN GUCCI', 'GUCCI', 'blank.png', 50, 8, 1000000),
('CA15', 'NÓN LV', 'LV', 'blank.png', 50, 8, 1000000),
('HO1', 'ÁO HODDIES ADIDAS', 'ADIDAS', 'blank.png', 50, 3, 1000000),
('HO12', 'ÁO HODDIES NIKE', 'NIKE', 'blank.png', 50, 3, 1000000),
('HO13', 'ÁO HODDIES PUMA', 'PUMA', 'blank.png', 50, 3, 1000000),
('HO14', 'ÁO HODDIES GUCCI', 'GUCCI', 'blank.png', 50, 3, 1000000),
('HO15', 'ÁO HODDIES LV', 'LV', 'blank.png', 50, 3, 1000000),
('JA1', 'ÁO JACKET ADIDAS', 'ADIDAS', 'blank.png', 50, 4, 1000000),
('JA12', 'ÁO JACKET NIKE', 'NIKE', 'blank.png', 50, 4, 1000000),
('JA13', 'ÁO JACKET PUMA', 'PUMA', 'blank.png', 50, 4, 1000000),
('JA14', 'ÁO JACKET GUCCI', 'GUCCI', 'blank.png', 50, 4, 1000000),
('JA15', 'ÁO JACKET LV', 'LV', 'blank.png', 50, 4, 1000000),
('JE1', 'ÁO JEAN ADIDAS', 'ADIDAS', 'blank.png', 0, 5, 1000000),
('JE12', 'ÁO JEAN NIKE', 'NIKE', 'blank.png', 0, 5, 1000000),
('JE13', 'ÁO JEAN PUMA', 'PUMA', 'blank.png', 0, 5, 1000000),
('JE14', 'ÁO JEAN GUCCI', 'GUCCI', 'blank.png', 0, 5, 1000000),
('JE15', 'ÁO JEAN LV', 'LV', 'blank.png', 0, 5, 1000000),
('PA1', 'QUẦN DÀI ADIDAS', 'ADIDAS', 'blank.png', 50, 6, 1000000),
('PA12', 'QUẦN DÀI NIKE', 'NIKE', 'blank.png', 50, 6, 1000000),
('PA13', 'QUẦN DÀI PUMA', 'PUMA', 'blank.png', 50, 6, 1000000),
('PA14', 'QUẦN DÀI GUCCI', 'GUCCI', 'blank.png', 50, 6, 1000000),
('PA15', 'QUẦN DÀI LV', 'LV', 'blank.png', 50, 6, 1000000),
('SH1', 'QUẦN NGẮN ADIDAS', 'ADIDAS', 'blank.png', 50, 7, 1000000),
('SH12', 'QUẦN NGẮN NIKE', 'NIKE', 'blank.png', 50, 7, 1000000),
('SH13', 'QUẦN NGẮN PUMA', 'PUMA', 'blank.png', 50, 7, 1000000),
('SH14', 'QUẦN NGẮN GUCCI', 'GUCCI', 'blank.png', 50, 7, 1000000),
('SH15', 'QUẦN NGẮN LV', 'LV', 'blank.png', 50, 7, 1000000),
('SO1', 'VỚ ADIDAS', 'ADIDAS', 'blank.png', 50, 10, 1000000),
('SO12', 'VỚ NIKE', 'NIKE', 'blank.png', 50, 10, 1000000),
('SO13', 'VỚ PUMA', 'PUMA', 'blank.png', 50, 10, 1000000),
('SO14', 'VỚ GUCCI', 'GUCCI', 'blank.png', 50, 10, 1000000),
('SO15', 'VỚ LV', 'LV', 'blank.png', 50, 10, 1000000),
('SW1', 'ÁO SWEATSHIRT ADIDAS', 'ADIDAS', 'blank.png', 50, 2, 1000000),
('SW12', 'ÁO SWEATSHIRT NIKE', 'NIKE', 'blank.png', 50, 2, 1000000),
('SW13', 'ÁO SWEATSHIRT PUMA', 'PUMA', 'blank.png', 50, 2, 1000000),
('SW14', 'ÁO SWEATSHIRT GUCCI', 'GUCCI', 'blank.png', 50, 2, 1000000),
('SW15', 'ÁO SWEATSHIRT LV', 'LV', 'blank.png', 50, 2, 1000000),
('TS1', 'Áo T-SHIRT ADIDAS', 'ADIDAS', 'blank.png', 50, 1, 1000000),
('TS12', 'Áo T-SHIRT NIKE', 'NIKE', 'blank.png', 50, 1, 1000000),
('TS13', 'Áo T-SHIRT PUMA', 'PUMA', 'blank.png', 50, 1, 1000000),
('TS14', 'Áo T-SHIRT GUCCI', 'GUCCI', 'blank.png', 50, 1, 1000000),
('TS15', 'Áo T-SHIRT LV', 'LV', 'blank.png', 50, 1, 1000000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietgiamgia`
--
ALTER TABLE `chitietgiamgia`
  ADD KEY `IdGgiá` (`IdGgiá`),
  ADD KEY `Idsp` (`Idsp`);

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD KEY `Idhd` (`Idhd`),
  ADD KEY `mactsp` (`mactsp`),
  ADD KEY `Idsp` (`Idsp`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`masanpham`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD PRIMARY KEY (`machitietsanpham`,`masanpham`),
  ADD KEY `masanpham` (`masanpham`);

--
-- Chỉ mục cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD PRIMARY KEY (`username`,`ID_Nhanvien`),
  ADD KEY `ID_Nhanvien` (`ID_Nhanvien`);

--
-- Chỉ mục cho bảng `giamgia`
--
ALTER TABLE `giamgia`
  ADD PRIMARY KEY (`IdGgiá`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`Idhd`),
  ADD KEY `IdGgiá` (`IdGgiá`),
  ADD KEY `Idnv` (`Idnv`),
  ADD KEY `Idkhach` (`Idkhach`);

--
-- Chỉ mục cho bảng `khach`
--
ALTER TABLE `khach`
  ADD PRIMARY KEY (`Idkhach`);

--
-- Chỉ mục cho bảng `loai`
--
ALTER TABLE `loai`
  ADD PRIMARY KEY (`maloai`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`manhacungcap`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`Idnv`);

--
-- Chỉ mục cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `manhacungcap` (`manhacungcap`),
  ADD KEY `manhanvien` (`manhanvien`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masanpham`),
  ADD KEY `maloai` (`maloai`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  MODIFY `machitietsanpham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `Idhd` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=234;

--
-- AUTO_INCREMENT cho bảng `khach`
--
ALTER TABLE `khach`
  MODIFY `Idkhach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT cho bảng `loai`
--
ALTER TABLE `loai`
  MODIFY `maloai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `manhacungcap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `Idnv` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  MODIFY `maphieunhap` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietgiamgia`
--
ALTER TABLE `chitietgiamgia`
  ADD CONSTRAINT `chitietgiamgia_ibfk_1` FOREIGN KEY (`IdGgiá`) REFERENCES `giamgia` (`IdGgiá`),
  ADD CONSTRAINT `chitietgiamgia_ibfk_2` FOREIGN KEY (`Idsp`) REFERENCES `sanpham` (`masanpham`);

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `chitiethoadon_ibfk_1` FOREIGN KEY (`Idhd`) REFERENCES `hoadon` (`Idhd`),
  ADD CONSTRAINT `chitiethoadon_ibfk_2` FOREIGN KEY (`mactsp`) REFERENCES `chitietsanpham` (`machitietsanpham`),
  ADD CONSTRAINT `chitiethoadon_ibfk_3` FOREIGN KEY (`Idsp`) REFERENCES `sanpham` (`masanpham`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `chitietphieunhap_ibfk_1` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);

--
-- Các ràng buộc cho bảng `chitietsanpham`
--
ALTER TABLE `chitietsanpham`
  ADD CONSTRAINT `chitietsanpham_ibfk_1` FOREIGN KEY (`masanpham`) REFERENCES `sanpham` (`masanpham`);

--
-- Các ràng buộc cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD CONSTRAINT `dangnhap_ibfk_1` FOREIGN KEY (`ID_Nhanvien`) REFERENCES `nhanvien` (`Idnv`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `hoadon_ibfk_1` FOREIGN KEY (`IdGgiá`) REFERENCES `giamgia` (`IdGgiá`),
  ADD CONSTRAINT `hoadon_ibfk_2` FOREIGN KEY (`Idnv`) REFERENCES `nhanvien` (`Idnv`),
  ADD CONSTRAINT `hoadon_ibfk_3` FOREIGN KEY (`Idkhach`) REFERENCES `khach` (`Idkhach`);

--
-- Các ràng buộc cho bảng `phieunhaphang`
--
ALTER TABLE `phieunhaphang`
  ADD CONSTRAINT `phieunhaphang_ibfk_1` FOREIGN KEY (`manhacungcap`) REFERENCES `nhacungcap` (`manhacungcap`),
  ADD CONSTRAINT `phieunhaphang_ibfk_2` FOREIGN KEY (`manhanvien`) REFERENCES `nhanvien` (`Idnv`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`maloai`) REFERENCES `loai` (`maloai`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
SELECT MONTH(`Ngày`) , SUM(`Tổng tiền`) 
FROM `hoadon` 
WHERE  MONTH(`Ngày`) = MONTH(CURRENT_DATE)
AND YEAR(`Ngày`) = YEAR(CURRENT_DATE)

/*Tổng tiền theo từ tháng đến tháng và theo năm*/
SELECT MONTH(`Ngày`) , SUM(`Tổng tiền`) 
FROM `hoadon` 
WHERE  MONTH(`Ngày`) >= MONTH(CURRENT_DATE)
AND MONTH(`Ngày`) <= MONTH(CURRENT_DATE)
AND YEAR(`Ngày`) = YEAR(CURRENT_DATE)


/*Tổng hóa đơn khách trong năm*/
SELECT COUNT(Idhd) FROM hoadon WHERE YEAR(`Ngày`) = YEAR(CURRENT_DATE)

/*Top nhân viên bán hàng tốt nhất*/
SELECT Idnv , COUNT(Idnv) FROM hoadon GROUP BY Idnv LIMIT 1,5

/*Top sản phẩm bán chạy*/
SELECT Idsp , COUNT(Idsp) 
FROM chitiethoadon as cthd,hoadon as hd 
WHERE cthd.Idhd=hd.Idhd and hd.`Ngày` >= '' and hd.`Ngày` <= 
GROUP BY Idsp LIMIT 1,5

/*Top khách hàng mua nhiều tiền*/
SELECT Idkhach , SUM(`Tổng tiền`) FROM hoadon GROUP BY Idkhach 

SELECT DISTINCT COUNT(Idkhach) FROM hoadon WHERE YEAR(`Ngày`) = YEAR(CURRENT_DATE)
GROUP BY Idkhach
/*Top loại sản phảm bán chạy nhất*/
SELECT l.maloai , SUM(`Số lượng`)
FROM loai as l ,sanpham as sp ,chitiethoadon as cthd
WHERE l.maloai=sp.maloai and sp.masanpham = cthd.Idsp
GROUP BY l.maloai
