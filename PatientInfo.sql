use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go

create table tblPatient(
ID int identity(1,1) primary key ,
FirstName nvarchar(50) not null,
LastName nvarchar(50) not null,
Address nvarchar(50),
Age int,
Gender varchar(6),
Faculty varchar(50),
Description varchar(125),
Sick varchar(125) default '',
Doctor nvarchar(125) not null, 
DateIn smalldatetime,
DateOut varchar(125) default '--/--/---',
Stt varchar(3) default 'No'
)
go 
insert into tblPatient values('Duong','Tung','HaÒi Dýõng',12,'Male','Internal medicine','Break up','Love Sick','Doctor A','1/10/2012','12/12/2012','Yes')
insert into tblPatient values('Ngoc','Thien','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','12/12/2012','Yes')
insert into tblPatient values('Nguyen','Hinh','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','12/12/2012','Yes')
insert into tblPatient values('Duong','A','HaÒi Dýõng',12,'Male','Internal medicine','Break up','Love Sick','Doctor A','12/12/2012','12/12/2012','No')
insert into tblPatient values('Ngoc','T','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','12/12/2012','No')
insert into tblPatient values('Nguyen','H','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','12/12/2012','No')
go

Create view displayPatient
as 
select ID [ID],  FirstName + ' ' + LastName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Faculty [Faculty],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient
go	
select * from displayPatient
go
-- Proc find Patient by ID
create proc findByID
@ID int
as
SELECT [ID],[Full Name],[Address],[Age],[Gender],[Description],[Sick],[Faculty],[Dr.Manager],[DateIn],[DateOut],[In Hospital]
 FROM displayPatient WHERE ID = @ID
go
-- Proc find Patient by Name
create proc findByName
@Name nvarchar(100)
as
SELECT [ID],[Full Name],[Address],[Age],[Gender],[Description],[Sick],[Faculty],[Dr.Manager],[DateIn],[DateOut],[In Hospital]
 FROM displayPatient WHERE [Full Name] = @Name
go
-- Proc find Patient by In Hospital
create proc findByIn
@InHospital varchar(3)
as
SELECT [ID],[Full Name],[Address],[Age],[Gender],[Description],[Sick],[Faculty],[Dr.Manager],[DateIn],[DateOut],[In Hospital]
FROM displayPatient WHERE [In Hospital] = @InHospital
go
-- Proc find Patient by Faculty
create proc findByFaculty
@Faculty nvarchar(50)
as
SELECT [ID],[Full Name],[Address],[Age],[Gender],[Description],[Sick],[Faculty],[Dr.Manager],[DateIn],[DateOut],[In Hospital]
 FROM displayPatient WHERE [Faculty] = @Faculty
go
-- Proc find Patient by DateIn
create proc findByDateIn
@DateF smalldatetime,
@DateT smalldatetime
as
SELECT [ID],[Full Name],[Address],[Age],[Gender],[Description],[Sick],[Faculty],[Dr.Manager],[DateIn],[DateOut],[In Hospital]
 FROM displayPatient WHERE DateIn between @DateF and @DateT
go

