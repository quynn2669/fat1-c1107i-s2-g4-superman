use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go

create table tblBenhNhan(
ID varchar (5),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
TrieuChung varchar(125),
Benh varchar(125) default '',
NgayNhap smalldatetime default getdate(),
NgayRa smalldatetime default getdate(),
NhapHoacKhong varchar(3) default 'Out'
)

select * from tblBenhNhan
go
create table tblBacSi(
ID varchar (12),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
TaiKhoan varchar (12),
MatKhau varchar(50)
)
go
insert into tblBacSi values('BS0001','Duong Van Tung','Ha Noi',19,'Nam','','')
go
select * from tblBacSi

go

create table tblNhanVien(
ID varchar (12),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
TaiKhoan varchar (12),
MatKhau varchar(50)
)
go
insert into tblNhanVien values('NV0001','Nguyen Van A','Ha Noi',19,'Nam','','')
 go
select * from tblNhanVien
go
 -- Tao proc tim kiem nhan vien trong bang bac si
 create proc timKiemBS
 @ID varchar(12),
 @HoTen Varchar(50)
 as
 SELECT ID,HoTen FROM tblBacSi WHERE ID = @ID AND HoTen = @HoTen
 go
 exec timKiemBS 'BS0001','Duong Van Tung'
 go
 -- Tao proc tim kiem nhan vien trong bang nhan vien
 create proc timKiemNV
 @ID varchar(12),
 @HoTen Varchar(50)
 as
 SELECT ID,HoTen FROM tblNhanVien WHERE ID = @ID AND HoTen = @HoTen
 go
 --Proc tao sua tai khoan cho bac si
 create proc taiKhoanBS
 @ID varchar(12),
 @HoTen Varchar(50),
 @TaiKhoan varchar(12),
 @MatKhau Varchar(50)
 as
 Update tblBacSi 
 set TaiKhoan = @TaiKhoan
 where ID = @ID and HoTen = @HoTen
 Update tblBacSi 
 set MatKhau = @MatKhau
 where ID = @ID and HoTen = @HoTen
 go
 --test
 --exec taiKhoanBS 'BS0001','Duong Van Tung','sa','123456'
 --Proc tao sua tai khoan cho nhan vien
 go
 create proc taiKhoanNV
 @ID varchar(12),
 @HoTen Varchar(50),
 @TaiKhoan varchar(12),
 @MatKhau Varchar(50)
 as
 Update tblNhanVien
 set TaiKhoan = @TaiKhoan
 where ID = @ID and HoTen = @HoTen
 Update tblNhanVien
 set MatKhau = @MatKhau
 where ID = @ID and HoTen = @HoTen
 go
 
 