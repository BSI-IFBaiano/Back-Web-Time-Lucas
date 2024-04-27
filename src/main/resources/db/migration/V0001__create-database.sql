create table if not exists userr(
	idUserr serial,
	userrr varchar(25) not null,
	password varchar(25) not null,
	email varchar(250) unique not null,
	phone varchar(16) not null,
    createdAt timestamp not null,

    primary key (idUserr)
);
create table if not exists admin(
    idAdmin serial,
    idUserr serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idAdmin),
    foreign key(idUserr) references userr(idUserr)
);

create table if not exists manager(
    idManager serial,
    idUserr serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idManager),
    foreign key(idUserr) references userr(idUserr)
);

create table if not exists department(
    idDepartment serial,
    idManager serial not null,
    name varchar(250) not null,
    createdAt timestamp not null,

    primary key(idDepartment),
    foreign key(idManager) references manager(idManager)
);

create table if not exists teacher(
    idTeacher serial,
    idUserr serial not null,
    idDepartment serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idTeacher),
    foreign key(idUserr) references userr(idUserr),
    foreign key(idDepartment) references department(idDepartment)
);

create table if not exists course (
    idCourse serial,
    idDepartment serial not null,
    name varchar(250) not null,
    totWorkLoad int not null,
    createdAt timestamp not null,

    primary key(idCourse),
    foreign key(idDepartment) references department(idDepartment)
);

create table if not exists student (
    idStudent serial,
    idUserr serial,
    idCourse serial,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idStudent),
    foreign key(idUserr) references userr(idUserr)
);

create table if not exists subject (
    idSubject serial,
    idDepartment serial not null,
    name varchar(250) not null,
    totNumberOfClasses int not null,

    primary key(idSubject),
    foreign key(idDepartment) references department(idDepartment)
);

create table if not exists subjectsInCourse(
    idSubjectsInCourse serial,
    idCourse serial not null,
    idSubject serial not null,

    primary key(idSubjectsInCourse),
    foreign key(idCourse) references course(idCourse),
    foreign key(idSubject) references subject(idSubject)
);

create table if not exists subjectsTaught(
    idSubjectTaught serial,
    idSubject serial not null,
    idTeacher serial not null,

    primary key(idSubjectTaught),
    foreign key(idSubject) references subject(idSubject),
    foreign key(idTeacher) references teacher(idTeacher)
);

create table if not exists allocations(
    idAllocations serial,
    idSubjectTaught serial not null,
    semester varchar(25),

    primary key(idAllocations),
    foreign key(idSubjectTaught) references subjectsTaught(idSubjectTaught)
);

create table if not exists enrolledStudent(
    idEnrolledStudent serial,
    idStudent serial not null,

    primary key(idEnrolledStudent),
    foreign key(idStudent) references student(idStudent)
);

