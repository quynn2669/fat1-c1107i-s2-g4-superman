use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go

create table PTable(
FullName varchar(50),
Age int,
Sex char,
sick varchar(125) 
)





 