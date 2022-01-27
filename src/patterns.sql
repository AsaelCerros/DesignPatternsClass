# noinspection SqlNoDataSourceInspectionForFile

create table company(
                        company_id int auto_increment,
                        company_name varchar(255) not null,
                        primary key(company_id)
);

create table employees(
                        emp_id int auto_increment,
                        name varchar(255) not null,
                        primary key(emp_id)
);