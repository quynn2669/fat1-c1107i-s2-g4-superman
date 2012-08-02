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
GioiTinh varchar(5)
)
go
insert into tblBacSi values('BS0001','Duong Van Tung','Ha Noi',19,'Nam')
go
select * from tblBacSi
go 
create table tblDefault( 
ID varchar (12),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5)
)
go

create table tblNhanVien(
ID varchar (12),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5)
)
go
insert into tblNhanVien values('NV0001','Nguyen Van A','Ha Noi',19,'Nam')
 go
 select * from tblNhanVien
 SELECT ID,HoTen FROM tblBacSi WHERE ID = 'BS0001' AND HoTen = 'Duong Van Tung' 
 go
 create proc timKiemBS
 @ID varchar(12),
 @HoTen Varchar(50)
 as
 SELECT ID,HoTen FROM tblBacSi WHERE ID = @ID AND HoTen = @HoTen
 go
 exec timKiemBS 'BS0001','Duong Van Tung'
 go
 create proc timKiemNV
 @ID varchar(12),
 @HoTen Varchar(50)
 as
 SELECT ID,HoTen FROM tblNhanVien WHERE ID = @ID AND HoTen = @HoTen
 go