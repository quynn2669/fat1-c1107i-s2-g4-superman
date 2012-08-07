use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go

create table tblBenhNhan(
ID varchar (5) primary key,
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
Khoa varchar(12),
TrieuChung varchar(125),
Benh varchar(125) default '',
NgayNhap smalldatetime default getdate(),
NgayRa smalldatetime default getdate(),
NhapHoacKhong varchar(3) default 'Out'
)
go 
insert into tblBenhNhan values('BN1','Tung','HN',12,'Nam','Noi','Om','Yeu','12/12/2012','12/12/2012','Out')
insert into tblBenhNhan values('BN2','Thien','HN',11,'Nam','Noi','Cum','Yeu','10/12/2012','12/12/2012','Out')
insert into tblBenhNhan values('BN3','TungSuper','HN',12,'Nu','Noi','GOm','Yeu','11/12/2012','12/12/2012','Out')
select * from tblBenhNhan
go
create table tblBacSi(
ID varchar (12) primary key,
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
TaiKhoan varchar (12),
MatKhau varchar(50)
)
go


create table tblNhanVien(
ID varchar (12) primary key,
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5),
TaiKhoan varchar (12),
MatKhau varchar(50)
)
go


 -- Tao proc tim kiem ID bac si trong bang bac si
 create proc timKiemBS
 @ID varchar(12)
 as
 SELECT * FROM tblBacSi WHERE ID = @ID 
 go
  -- Tao proc tim kiem tai khoantrong bang bac si
 create proc timKiemTKBS
 @TaiKhoan varchar(12)
 as
 SELECT * FROM tblBacSi WHERE TaiKhoan =@TaiKhoan
 go
 -- Tao proc tim kiem ID nhan vien trong bang nhan vien
 create proc timKiemNV
 @ID varchar(12)
 as
 SELECT * FROM tblNhanVien WHERE ID = @ID 
 go
  -- Tao proc tim kiem tai khoan trong bang nhan vien
 create proc timKiemTKNV
 @TaiKhoan varchar(12)
 as
 SELECT * FROM tblNhanVien WHERE TaiKhoan =@TaiKhoan
 go
 
 --Proc them bac si
 create proc ghiBS
 @ID varchar(12),
 @HoTen Varchar(50),
 @DiaChi varchar(50),
 @Tuoi int,
 @GioiTinh varchar(5),
 @TaiKhoan varchar(12),
 @MatKhau Varchar(50)
 as
 INSERT INTO tblBacSi VALUES(@ID,@HoTen,@DiaChi,@Tuoi,@GioiTinh,@TaiKhoan,@MatKhau)
 go
 --Proc update benh nhan
 create proc updatePatient
 @ID varchar(12),
 @Benh nvarchar(30),
 @NgayNhap smalldatetime,
 @NgayRa smalldatetime,
 @NhapHoacKhong nvarchar(10)
 as
 update tblBenhNhan set ID = @ID,Benh=@Benh,NgayNhap=@NgayNhap,NgayRa=@NgayRa,NhapHoacKhong=@NhapHoacKhong
 where ID = @ID
 go
--Proc them nhan vien
 create proc ghiNV
 @ID varchar(12),
 @HoTen Varchar(50),
 @DiaChi varchar(50),
 @Tuoi int,
 @GioiTinh varchar(5),
 @TaiKhoan varchar(12),
 @MatKhau Varchar(50)
 as
 INSERT INTO tblNhanVien VALUES(@ID,@HoTen,@DiaChi,@Tuoi,@GioiTinh,@TaiKhoan,@MatKhau)
 go
 -- Tao proc kiem tra tai khoan va mat khau trong tblBacSi
 create proc DNBS
 @TaiKhoan varchar(12),
 @MatKhau varchar(50)
 as
 SELECT * FROM tblBacSi where TaiKhoan =@TaiKhoan AND MatKhau =@MatKhau
 go
 -- Tao proc kiem tra tai khoan va mat khau trong tblNhanVien
 create proc DNNV
 @TaiKhoan varchar(12),
 @MatKhau varchar(50)
 as
 SELECT * FROM tblNhanVien where TaiKhoan =@TaiKhoan AND MatKhau =@MatKhau
 go
 select * from tblBacSi
go
select * from tblNhanVien
go