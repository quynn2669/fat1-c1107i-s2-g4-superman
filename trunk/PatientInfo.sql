use master

if DB_ID('PI') is not null
drop database PI

go
Create database PI
go

use PI
go
-- Table to store room
create table tblRoom(
ID int identity(1,1) primary key ,
stt varchar(5)
)
go
--Table to store bed
create table tblBed(
id int identity(1,1)primary key,
room int foreign key References tblRoom(ID),
Stt varchar(5)
)
go
create table tblPatient(
ID int identity(1,1) primary key ,
FullName nvarchar(300) not null,
Address nvarchar(300),
Age int,
Gender varchar(6),
Department varchar(50),
Description nvarchar(300),
Sick nvarchar(300),
Doctor nvarchar(300) not null, 
DateIn smalldatetime,
DateOut smalldatetime,
InHospital varchar(3) default 'No',
room int foreign key references tblRoom(id),
bed int foreign key references tblbed(id),
DrStt int
)
go

Create proc selectAll
as 
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient
go	
Create proc selectAllEx
@DrSTT int
as 
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient where DrStt = @DrSTT
go	
-- Proc find Patient by ID
create proc findByID
@ID int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE ID = @ID
go
-- Proc find Patient by Name
create proc findByName
@Name nvarchar(100)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name
go
-- Proc find Patient by Name Ex
create proc findByNameEx
@Name nvarchar(100),
@DrStt int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and DrStt = @DrStt
go
-- Proc find Patient by Name and Department
create proc findBy1And2
@Name nvarchar(300),
@Department	nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and Department = @Department
go
-- Proc find Patient by Name and Department Ex
create proc findBy1And2Ex
@Name nvarchar(300),
@Department	nvarchar(50),
@DrSTT int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and Department = @Department and DrStt = @DrSTT
go
-- Proc find Patient by Name,Department and In Hospital
create proc findBy12And3
@Name nvarchar(300),
@Department nvarchar(50),
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and Department = @Department and InHospital = @InHospital
go

-- Proc find Patient by Name,Department,In Hospital and Date In
create proc findBy123And4
@Name nvarchar(300),
@Department nvarchar(50),
@InHospital varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and Department = @Department and InHospital = @InHospital and DateIn between @DateF and @DateT
go
 


-- Proc find Patient by Name,Department and Date In
create proc findBy12And4
@Name nvarchar(300),
@Department nvarchar(50),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and Department = @Department  and DateIn between @DateF and @DateT
go

-- Proc find Patient by Name and In Hospital
create proc findBy1And3
@Name nvarchar(300),
@InHospital	varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and InHospital = @InHospital
go

-- Proc find Patient by Name and In Hospital and Date In
create proc findBy13And4
@Name nvarchar(300),
@InHospital	varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE  FullName = @Name and InHospital = @InHospital  and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hospital
create proc findByIn
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE InHospital = @InHospital
go

-- Proc find Patient by Department
create proc findByDepartment
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE Department = @Department
go
-- Proc find Patient by Department Ex
create proc findByDepartmentEx
@Department nvarchar(50),
@DrSTT int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE Department = @Department and DrStt = @DrSTT
go
-- Proc find Patient by DateIn
create proc findByDateIn
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE DateIn between @DateF and @DateT
go

-- Proc find Patient by Name and DateIn
create proc findBy1And4
@Name nvarchar(300),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE FullName= @Name and DateIn between @DateF and @DateT
go


-- Proc find Patient by Department and DateIn
create proc findBy2And4
@Department varchar(50),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE Department = @Department and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and DateIn
create proc findBy3And4
@InHospital varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE InHospital =@InHospital and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and DateIn and Department
create proc findBy23And4
@Department varchar(50),
@InHospital varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE Department = @Department and InHospital =@InHospital and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and Deparment
create proc findBy2And3
@Department varchar(50),
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE InHospital =@InHospital and Department = @Department
go

--Proc Employee's addPatient
create proc empAddPatient
@FName nvarchar(300),
@Address nvarchar(300),
@Age int,
@Gender varchar(6),
@Dec nvarchar(300),
@Dep varchar(50),
@Dr nvarchar(300),
@DateIn smalldatetime
as
INSERT INTO tblPatient VALUES (@FName,@Address,@Age,@Gender,@Dep,@Dec,'null',@Dr,@DateIn,'null','No','null','null','0')
go
select * from tblPatient
--Proc Employee's updatePatient
go
create proc updatePatient
@ID int,
@FName nvarchar(300),
@Address nvarchar(50),
@Age int,
@Gender varchar(6),
@Dec nvarchar(300),
@Dep varchar(50),
@Dr nvarchar(300),
@DateIn smalldatetime,
@Room int,
@Bed int
as
UPDATE tblPatient
SET FullName = @FName,Address = @Address,Age = @Age,Gender = @Gender,Department = @Dep,Description =  @Dec,Doctor = @Dr,DateIn = @DateIn,room = @Room, bed = @Bed WHERE ID= @ID
go


--Create table to store doctor
create table tblDoctor(
ID int identity(1,1) primary key ,
FullName nvarchar(300) not null,
Age int,
Gender varchar(6),
)
go


--Create table to store Employee
create table tblEmployee(
ID int identity(1,1) primary key ,
FullName nvarchar(300) not null,
Age int,
Gender varchar(6),
)
go
-- Create table to store patient in hospital and patient's log examine
create table tblInHospital(
ID_Patient int foreign key References tblPatient(ID),
PName nvarchar(300),
PGender nvarchar(6),
PDepartment nvarchar(50),
PSick nvarchar(300),
PDes nvarchar(300),
PChange nvarchar(300),
DateIn smalldatetime,
DateOut nvarchar(50),
DateChange smalldatetime,
DName nvarchar(300),
Room int,
Bed int
)
go

--proc to get patient in hospital
create proc selectAllIH
as
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient where InHospital = 'yes'
go
-- Proc find Patient by Department In Hospital
create proc findByDepartmentIH
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE Department = @Department and InHospital ='Yes'
go
-- Proc find Patient by Name In Hospital
create proc findByNameIH
@Name nvarchar(300)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE FullName = @Name and InHospital ='Yes'
go
-- Proc find Patient by Name and Department In Hospital
create proc findBy1And2IH
@Name nvarchar(300),
@Department nvarchar(30)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital],Room [Room], bed [Bed]
from tblPatient WHERE FullName = @Name and Department = @Department and InHospital ='Yes'
go
 
--Create proc insert data into tblInHospital
create proc ExamineYes
@ID_Patient int,
@PName nvarchar(300),
@PGender nvarchar(6),
@PDepartment nvarchar(50),
@PSick nvarchar(300),
@PDes nvarchar(300),
@PChanges nvarchar(300),
@DateIn smalldatetime,
@DateOut nvarchar(50),
@DateChange smalldatetime,
@DName nvarchar(300),
@Room int,
@Bed int
as
INSERT INTO tblInHospital VALUES (@ID_Patient,@PName,@PGender,@PDepartment,@PSick,@PDes,@PChanges,@DateIn,@DateIn,@DateChange,@DName,@Room,@Bed)
go
--Proc Examine in tblPatient
create proc ExamineP
@ID int,
@PDepartment nvarchar(50),
@Sick nvarchar(300),
@Dr nvarchar(300),
@DateOut smalldatetime,
@InHospital varchar(3),
@DrSTT int
as
UPDATE tblPatient
SET Department = @PDepartment, Sick = @Sick,Doctor = @Dr,DateOut = @DateOut,InHospital = @InHospital,DrStt = @DrSTT WHERE ID= @ID
go
--Proc get data on tblInHospital for daily examine
create proc selectChanges
@ID int
as
select ID_Patient, PName, PGender,PDepartment,DateChange, DName,PChange 
from tblInHospital where ID_Patient = @ID
go
exec selectChanges 2
--Table to store account
create table tblAccount(
ID int,
Name nvarchar(300),
Role varchar(25),
Account varchar(12),
Password varchar(50)
)
select * from tblAccount
go

--Proc to add Room
create proc addRoom
as
INSERT INTO tblRoom values ('Empty')
go

--Proc to add Bed
create proc addBed
@Room int
as
INSERT INTO tblBed values (@Room,'Empty')
go
--Proc set STT bed
create proc setSTTBed
@bed int,
@STT varchar(6)
as
UPDATE tblBed SET Stt = @STT where id = @bed
go
-------------------------------------------------------------------------------------------
insert into tblRoom values('empty')
insert into tblRoom values('empty')
insert into tblRoom values('empty')
insert into tblRoom values('empty')
go
INSERT INTO tblBed VALUES (1,'Using')
INSERT INTO tblBed VALUES (1,'empty')
INSERT INTO tblBed VALUES (1,'empty')
INSERT INTO tblBed VALUES (1,'empty')
INSERT INTO tblBed VALUES (2,'empty')
INSERT INTO tblBed VALUES (2,'empty')
INSERT INTO tblBed VALUES (2,'empty')
INSERT INTO tblBed VALUES (2,'empty')
INSERT INTO tblBed VALUES (3,'empty')
INSERT INTO tblBed VALUES (3,'empty')
INSERT INTO tblBed VALUES (3,'empty')
INSERT INTO tblBed VALUES (3,'empty')
INSERT INTO tblBed VALUES (4,'empty')
INSERT INTO tblBed VALUES (4,'empty')
INSERT INTO tblBed VALUES (4,'empty')
INSERT INTO tblBed VALUES (4,'empty')
go
INSERT INTO tblDoctor VALUES ('Duong tung',20,'Male')
INSERT INTO tblDoctor VALUES ('Thanh Tung',20,'Male')
INSERT INTO tblDoctor VALUES ('Nguyen Hinh',20,'Male')
INSERT INTO tblDoctor VALUES ('Ngoc Thien',20,'Male')
go
INSERT INTO tblPatient VALUES ('Benh Nhan 1','Ha Noi','12','Nam','Dau Hong','Internal medicine','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 2','Ha Noi','12','Nam','Dau Hong','Surgical','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 3','Ha Noi','12','Nam','Dau Hong','Cardiovascular ','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 4','Ha Noi','12','Nam','Dau Hong','Cardiovascular','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 5','Ha Noi','12','Nam','Dau Hong','Internal medicine','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 6','Ha Noi','12','Nam','Dau Hong','Surgical','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
INSERT INTO tblPatient VALUES ('Benh Nhan 7','Ha Noi','12','Nam','Dau Hong','Internal medicine','(empty)','Duong Tung','01/01/2012','01/01/2012','No',1,1,'0')
go
