create database tradeAwayDB;
create user 'stark'@'localhost' identified by 'st@rk';
grant all on tradeAwayDB.* to 'stark'@'localhost';

-- Adding sample items

insert into item values(1,0,"https://cdn.pixabay.com/photo/2014/09/23/21/21/iphone-6-458151_1280.jpg","Iphone 8","Grey 16GB");
insert into item values(2,0,"https://cdn.pixabay.com/photo/2014/09/23/21/21/iphone-6-458150_1280.jpg","Iphone 8 plus","Grey 16GB");
insert into item values(3,1,"https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Clojure_logo.svg/2000px-Clojure_logo.svg.png","Living Clojure","Learn Clojure");
insert into item values(4,1,"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Python3-powered_hello-world.svg/2000px-Python3-powered_hello-world.svg.png","Intro to Python","Learn Python");
