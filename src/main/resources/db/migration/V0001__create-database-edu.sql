create table if not exists tb_users(
	id_user serial,
	username varchar(25) unique not null,
	password varchar(150) not null,
    name varchar(100) not null,
    last_name varchar(100) not null,
	email varchar(100) unique not null,
    recovery_email varchar(100) unique not null,
	phone varchar(25) not null,
    created_at timestamp not null,
    role varchar(50) not null,

    primary key (id_user)
);

create table if not exists tb_admins(
    id_admin serial,
    id_user int not null,

    primary key(id_admin),
    foreign key(id_user) references tb_users(id_user)
);

create table if not exists tb_managers(
    id_manager serial,
    id_user int not null,

    primary key(id_manager),
    foreign key(id_user) references tb_users(id_user)
);

create table if not exists tb_departments(
    id_department serial,
    id_manager int not null,
    name varchar(150) not null,
    description varchar(250) not null,
    created_at timestamp not null,

    primary key(id_department),
    foreign key(id_manager) references tb_managers(id_manager)
);

create table if not exists tb_teachers(
    id_teacher serial,
    id_user int not null,
    id_department int not null,

    primary key(id_teacher),
    foreign key(id_user) references tb_users(id_user),
    foreign key(id_department) references tb_departments(id_department)
);

create table if not exists tb_courses (
    id_course serial,
    id_department int not null,
    name varchar(150) not null,
    tot_work_Load int not null,
    created_at timestamp not null,

    primary key(id_course),
    foreign key(id_department) references tb_departments(id_department)
);

create table if not exists tb_students (
    id_student serial,
    id_user int not null,
    id_course int not null,

    primary key(id_student),
    foreign key(id_user) references tb_users(id_user),
    foreign key(id_course) references tb_courses(id_course)
);

create table if not exists tb_subjects (
    id_subject serial,
    id_department int not null,
    name varchar(150) not null,
    tot_number_of_classes int not null,

    primary key(id_subject),
    foreign key (id_department) references tb_departments(id_department)
);

create table if not exists tb_subjects_in_course(
    id_subject_in_course serial,
    id_course serial not null,
    id_subject serial not null,

    primary key(id_subject_in_course),
    foreign key(id_course) references tb_courses(id_course),
    foreign key(id_subject) references tb_subjects(id_subject)
);

create table if not exists tb_subjects_taught(
    id_subject_taught serial,
    id_subject int not null,
    id_teacher int not null,

    primary key(id_subject_taught),
    foreign key(id_subject) references tb_subjects(id_subject),
    foreign key(id_teacher) references tb_teachers(id_teacher)
);

create table if not exists tb_allocations(
    id_allocation serial,
    id_subject_taught int not null,
    semester varchar(25),

    primary key(id_allocation),
    foreign key(id_subject_taught) references tb_subjects_taught(id_subject_taught)
);

create table if not exists tb_student_registrations(
    id_student_registration serial,
    id_student int not null,

    primary key(id_student_registration),
    foreign key(id_student) references tb_students(id_student)
);
