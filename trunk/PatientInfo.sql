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
FullName nvarchar(125) not null,
Address nvarchar(50),
Age int,
Gender varchar(6),
Department varchar(50),
Description nvarchar(125),
Sick nvarchar(125),
Doctor nvarchar(125) not null, 
DateIn smalldatetime,
DateOut varchar(125),
Stt varchar(3) default 'No',
DrStt int
)
go 
insert into tblPatient values('Duong Tung','HaÒi Dýõng',12,'Male','Internal medicine','Break up','Love Sick','Doctor A','1/10/2012','--/--/---','Yes',1)
insert into tblPatient values('Ngoc Thien','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','--/--/---','Yes',0)
insert into tblPatient values('Nguyen Hinh','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','--/--/---','Yes',1)
insert into tblPatient values('Duong A','HaÒi Dýõng',12,'Male','Internal medicine','Break up','Love Sick','Doctor A','12/12/2012','--/--/---','No',0)
insert into tblPatient values('Ngoc T','HaÌ Nôòi',11,'Male','Surgical','Headache',' Head injuries','Doctor A','10/12/2012','--/--/---','No',1)
insert into tblPatient values('Nguyen H','Hýng Yên',12,'Male','Cardiovascular','tired','flu','Doctor A','11/12/2012','--/--/---','No',0)
go

Create view displayPatient
as 
select ID [ID],  FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient
go	
-- Proc find Patient by ID
create proc findByID
@ID int
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE ID = @ID
go
-- Proc find Patient by Name
create proc findByName
@Name nvarchar(100)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE  FullName = @Name
go
-- Proc find Patient by In Hospital
create proc findByIn
@InHospital varchar(3)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE Stt = @InHospital
go
-- Proc find Patient by Department
create proc findByDepartment
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE Department = @Department
go
-- Proc find Patient by DateIn
create proc findByDateIn
@DateF smalldatetime,
@DateT smalldatetime
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE DateIn between @DateF and @DateT
go
--Proc Employee's addPatient
create proc empAddPatient
@FName nvarchar(125),
@Address nvarchar(50),
@Age int,
@Gender varchar(6),
@Dec nvarchar(125),
@Dep varchar(50),
@Dr nvarchar(125),
@DateIn smalldatetime
as
INSERT INTO tblPatient VALUES (@FName,@Address,@Age,@Gender,@Dep,@Dec,'---',@Dr,@DateIn,'--/--/----','No','0')
go
select * from tblPatient
--Proc Employee's updatePatient
go
create proc empUpdatePatient
@ID int,
@FName nvarchar(125),
@Address nvarchar(50),
@Age int,
@Gender varchar(6),
@Dec nvarchar(125),
@Dep varchar(50),
@Dr nvarchar(125),
@DateIn smalldatetime
as
UPDATE tblPatient
SET FullName = @FName,Address = @Address,Age = @Age,Gender = @Gender,Department = @Dep,Description =  @Dec,Doctor = @Dr,DateIn = @DateIn WHERE ID= @ID
go
-- Proc find Patient by Department
create proc findByDepartmentCU
@Department nvarchar(50)
as
select ID [ID],   FullName [Full Name],Address [Address],Age [Age],Gender [Gender],Description [Description],Sick [Sick],Department [Department],Doctor [Dr.Manager],DateIn [DateIn],DateOut [DateOut],STT [In Hospital]
from tblPatient WHERE Department = @Department AND DrStt = 0
go
--Proc Check up Patient
go
create proc checkUpPatient
@ID int,
@Sick nvarchar(300),
@Dep varchar(50),
@Dr nvarchar(125),
@DateOut smalldatetime,
@STT varchar(3),
@DrSTT int
as
UPDATE tblPatient
SET Sick = @Sick, Department = @Dep,Doctor = @Dr,DateOut = @DateOut,Stt = @STT,DrStt = @DrSTT WHERE ID= @ID
go

select * from tblPatient