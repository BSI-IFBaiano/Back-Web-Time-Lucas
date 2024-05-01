create table if not exists users(
	idUser serial,
	login varchar(25) not null,
	password varchar(25) not null,
	email varchar(250) unique not null,
	phone varchar(16) not null,
    createdAt timestamp not null,

    primary key (idUser)
);
create table if not exists admins(
    idAdmin serial,
    idUser serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idAdmin),
    foreign key(idUser) references users(idUser)
);

create table if not exists managers(
    idManager serial,
    idUser serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idManager),
    foreign key(idUser) references users(idUser)
);

create table if not exists departments(
    idDepartment serial,
    idManager serial not null,
    name varchar(250) not null,
    createdAt timestamp not null,

    primary key(idDepartment),
    foreign key(idManager) references managers(idManager)
);

create table if not exists teachers(
    idTeacher serial,
    idUser serial not null,
    idDepartment serial not null,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idTeacher),
    foreign key(idUser) references users(idUser),
    foreign key(idDepartment) references departments(idDepartment)
);

create table if not exists courses (
    idCourse serial,
    idDepartment serial not null,
    name varchar(250) not null,
    totWorkLoad int not null,
    createdAt timestamp not null,

    primary key(idCourse),
    foreign key(idDepartment) references departments(idDepartment)
);

create table if not exists students (
    idStudent serial,
    idUser serial,
    idCourse serial,
    name varchar(250) not null,
    lastName varchar(250) not null,

    primary key(idStudent),
    foreign key(idUser) references users(idUser)
);

create table if not exists subjects (
    idSubject serial,
    idDepartment serial not null,
    name varchar(250) not null,
    totNumberOfClasses int not null,

    primary key(idSubject),
    foreign key(idDepartment) references departments(idDepartment)
);

create table if not exists subjectsInCourse(
    idSubjectInCourse serial,
    idCourse serial not null,
    idSubject serial not null,

    primary key(idSubjectInCourse),
    foreign key(idCourse) references courses(idCourse),
    foreign key(idSubject) references subjects(idSubject)
);

create table if not exists subjectsTaught(
    idSubjectTaught serial,
    idSubject serial not null,
    idTeacher serial not null,

    primary key(idSubjectTaught),
    foreign key(idSubject) references subjects(idSubject),
    foreign key(idTeacher) references teachers(idTeacher)
);

create table if not exists allocations(
    idAllocation serial,
    idSubjectTaught serial not null,
    semester varchar(25),

    primary key(idAllocation),
    foreign key(idSubjectTaught) references subjectsTaught(idSubjectTaught)
);

create table if not exists studentRegistrations(
    idStudentRegistration serial,
    idStudent serial not null,

    primary key(idStudentRegistration),
    foreign key(idStudent) references students(idStudent)
);

