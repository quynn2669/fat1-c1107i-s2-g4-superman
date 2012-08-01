use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go

create table tblPatient(
PFullName varchar(50),
PAddress varchar(50),
PAge int,
PSex varchar(5),
PDescription varchar(125),
PSick varchar(125),
DateIn smalldatetime,
DateOut smalldatetime,
)

select * from tblPatient

 