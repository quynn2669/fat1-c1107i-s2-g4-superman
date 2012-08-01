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
ID varchar (5),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5)
)
create table tblNhanVien(
ID varchar (5),
HoTen varchar(50),
DiaChi varchar(50),
Tuoi int,
GioiTinh varchar(5)
)
 