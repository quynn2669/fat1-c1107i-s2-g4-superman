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
FullName nvarchar(300) not null,
Address nvarchar(300),
Age int,
Gender varchar(6),
Department varchar(50),
Description nvarchar(300),
Sick nvarchar(300),
Doctor nvarchar(300) not null, 
DateIn smalldatetime,
DateOut varchar(125),
InHospital varchar(3) default 'No',
DrStt int
)
go 
insert into tblPatient values('Duong Tung','HaÒi Dýõng',12,'Male','Internal medicine','Break upaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa','Love Sick','Doctor A','1/10/2012','--/--/---','Yes',1)
insert into tblPatient values('Ngoc Thien','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','--/--/---','Yes',0)
insert into tblPatient values('Nguyen Hinh','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','--/--/---','Yes',1)
insert into tblPatient values('Duong A','HaÒi Dýõng',12,'Male','Internal medicine','Break up','Love Sick','Doctor A','12/12/2012','--/--/---','No',0)
insert into tblPatient values('Ngoc T','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','--/--/---','No',1)
insert into tblPatient values('Nguyen H','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','--/--/---','No',0)
go

Create proc selectAll
as 
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient
go	
Create proc selectAllEx
@DrSTT int
as 
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient where DrStt = @DrSTT
go	
-- Proc find Patient by ID
create proc findByID
@ID int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE ID = @ID
go
-- Proc find Patient by Name
create proc findByName
@Name nvarchar(100)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name
go
-- Proc find Patient by Name Ex
create proc findByNameEx
@Name nvarchar(100),
@DrStt int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and DrStt = @DrStt
go
-- Proc find Patient by Name and Department
create proc findBy1And2
@Name nvarchar(300),
@Department	nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and Department = @Department
go
-- Proc find Patient by Name and Department Ex
create proc findBy1And2Ex
@Name nvarchar(300),
@Department	nvarchar(50),
@DrSTT int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and Department = @Department and DrStt = @DrSTT
go
-- Proc find Patient by Name,Department and In Hospital
create proc findBy12And3
@Name nvarchar(300),
@Department nvarchar(50),
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
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
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and Department = @Department and InHospital = @InHospital and DateIn between @DateF and @DateT
go
 


-- Proc find Patient by Name,Department and Date In
create proc findBy12And4
@Name nvarchar(300),
@Department nvarchar(50),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and Department = @Department  and DateIn between @DateF and @DateT
go

-- Proc find Patient by Name and In Hospital
create proc findBy1And3
@Name nvarchar(300),
@InHospital	varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and InHospital = @InHospital
go

-- Proc find Patient by Name and In Hospital and Date In
create proc findBy13And4
@Name nvarchar(300),
@InHospital	varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE  FullName = @Name and InHospital = @InHospital  and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hospital
create proc findByIn
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE InHospital = @InHospital
go

-- Proc find Patient by Department
create proc findByDepartment
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE Department = @Department
go
-- Proc find Patient by Department Ex
create proc findByDepartmentEx
@Department nvarchar(50),
@DrSTT int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE Department = @Department and DrStt = @DrSTT
go
-- Proc find Patient by DateIn
create proc findByDateIn
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE DateIn between @DateF and @DateT
go

-- Proc find Patient by Name and DateIn
create proc findBy1And4
@Name nvarchar(300),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE FullName= @Name and DateIn between @DateF and @DateT
go


-- Proc find Patient by Department and DateIn
create proc findBy2And4
@Department varchar(50),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE Department = @Department and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and DateIn
create proc findBy3And4
@InHospital varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE InHospital =@InHospital and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and DateIn and Department
create proc findBy23And4
@Department varchar(50),
@InHospital varchar(3),
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE Department = @Department and InHospital =@InHospital and DateIn between @DateF and @DateT
go

-- Proc find Patient by In Hostpital and Deparment
create proc findBy2And3
@Department varchar(50),
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
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
INSERT INTO tblPatient VALUES (@FName,@Address,@Age,@Gender,@Dep,@Dec,'(empty)',@Dr,@DateIn,'(empty)','No','0')
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
@DateIn smalldatetime
as
UPDATE tblPatient
SET FullName = @FName,Address = @Address,Age = @Age,Gender = @Gender,Department = @Dep,Description =  @Dec,Doctor = @Dr,DateIn = @DateIn WHERE ID= @ID
go


--Create table to store doctor
create table tblDoctor(
ID int identity(1,1) primary key ,
FullName nvarchar(300) not null,
Age int,
Gender varchar(6),
)
go
insert into tblDoctor values('Dr Thanh','25','Male')
insert into tblDoctor values('Dr Vu Thuy','20','Female')
insert into tblDoctor values('Dr Le Hoang','33','Male')
insert into tblDoctor values('Dr Hai','25','Female')
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
PSick nvarchar(300),
PDes nvarchar(300),
PChange nvarchar(300),
DateIn smalldatetime,
DateOut nvarchar(50),
DateChange smalldatetime,
DName nvarchar(300)
)
go
--proc to get patient in hospital
create proc selectAllIH
as
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient where InHospital = 'yes'
go
-- Proc find Patient by Department In Hospital
create proc findByDepartmentIH
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE Department = @Department and InHospital ='Yes'
go
-- Proc find Patient by Name In Hospital
create proc findByNameIH
@Name nvarchar(300)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE FullName = @Name and InHospital ='Yes'
go
-- Proc find Patient by Name and Department In Hospital
create proc findBy1And2IH
@Name nvarchar(300),
@Department nvarchar(30)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],InHospital [In Hospital]
from tblPatient WHERE FullName = @Name and Department = @Department and InHospital ='Yes'
go
 
--Create proc insert data into tblInHospital
create proc ExamineYes
@ID_Patient int,
@PName nvarchar(300),
@PGender nvarchar(6),
@PSick nvarchar(300),
@PDes nvarchar(300),
@DateIn smalldatetime,
@DateOut nvarchar(50),
@DName nvarchar(300)
as
INSERT INTO tblInHospital VALUES (@ID_Patient,@PName,@PGender,@PSick,@PDes,'(empty)',@DateIn,@DateIn,'(empty)',@DName)
go
--Proc Examine in tblPatient
create proc ExamineP
@ID int,
@Sick nvarchar(300),
@Dr nvarchar(300),
@DateOut smalldatetime,
@InHospital varchar(3),
@DrSTT int
as
UPDATE tblPatient
SET Sick = @Sick,Doctor = @Dr,DateOut = @DateOut,InHospital = @InHospital,DrStt = @DrSTT WHERE ID= @ID
go