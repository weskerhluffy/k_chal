insert into User (login, password) values ('admin', '$2a$10$pxqM69qW70qZhmUYY15VK.RbT.gmd43oPms5EZ1sxesXHNT0CLf6W');
insert into User (login, password) values ('user1', '$2a$10$pxqM69qW70qZhmUYY15VK.RbT.gmd43oPms5EZ1sxesXHNT0CLf6W');
insert into User (login, password) values ('user2', '$2a$10$pxqM69qW70qZhmUYY15VK.RbT.gmd43oPms5EZ1sxesXHNT0CLf6W');
insert into User (login, password) values ('user3', '$2a$10$pxqM69qW70qZhmUYY15VK.RbT.gmd43oPms5EZ1sxesXHNT0CLf6W');
insert into User (login, password) values ('user4', '$2a$10$pxqM69qW70qZhmUYY15VK.RbT.gmd43oPms5EZ1sxesXHNT0CLf6W');

insert into Authority (name) values ('admin');
insert into Authority (name) values ('user');

insert into UserAuthority(idUser, idAuthority) values (1, 1);
insert into UserAuthority(idUser, idAuthority) values (2, 2);
insert into UserAuthority(idUser, idAuthority) values (3, 2);

insert into Game(state,idUserX,idUserO,idUserWin) values();
	