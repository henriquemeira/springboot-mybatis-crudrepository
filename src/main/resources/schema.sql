create table contact (
  id int not null auto_increment, 
  fullname varchar(50), 
  email varchar(100),
  phone varchar(20), 
  birthday date, 
  primary key (id)
);
