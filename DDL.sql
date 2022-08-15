create schema agenda;

use agenda;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on agenda.* to user@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(100) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table ant_trabalho (
  ant_id bigint unsigned not null auto_increment,
  ant_titulo varchar(200) not null,
  ant_texto varchar(100) not null,
  primary key (ant_id),
);

create table atv_atividade (
  atv_id bigint unsigned not null auto_increment,
  ant_id varchar(20) not null,
  usr_nome varchar(100) not null,
  atv_img varchar(300),
  atv_nomeimg varchar(300),
  atv_comentario varchar(300),
  primary key (atv_id),
);

create table tdu_termosdeuso (
  tdu_id bigint unsigned not null auto_increment,
  tdu_version varchar(20) not null,
  tdu_data varchar(100),
  tdu_empresadados varchar(1000),
  tdu_usuariodados varchar(1000),
  tdu_empresaparceiradados varchar(1000),
  primary key (tdu_id),
);

create table teu_termosusuario (
  teu_id bigint unsigned not null auto_increment,
  teu_usuario varchar(100) not null,
  teu_version varchar(20),
  teu_data varchar(100),
  teu_hora varchar(100),
  teu_empresadados varchar(10),
  teu_usuariodados varchar(10),
  teu_empresaparceiradados varchar(10),
  primary key (teu_id),
);

create table tef_termosfuncional (
  tef_id bigint unsigned not null auto_increment,
  tef_usuario varchar(100) not null,
  tef_version varchar(20),
  tef_data varchar(100),
  tef_hora varchar(100),
  tef_empresadados varchar(10),
  tef_usuariodados varchar(10),
  tef_empresaparceiradados varchar(10),
  primary key (tef_id),
);

create table fed_feedback (
  fed_id bigint unsigned not null auto_increment,
  ant_texto varchar(200) not null,
  usr_nome varchar(200) not null,
  atv_id varchar(100) not null,
  atv_img varchar(300),
  fed_comentario varchar(300),
  primary key (fed_id),
);

insert into usr_usuario (usr_nome, usr_senha)
    values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');

insert into usr_usuario (usr_nome, usr_senha)
    values ('professor', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
    
insert into aut_autorizacao (aut_nome)
    values ('ROLE_ADMIN');

insert into uau_usuario_autorizacao values (1, 1);

insert into uau_usuario_autorizacao values (2, 1);

insert into ant_trabalho (ant_titulo, ant_texto)
    values ('teste', 'teste');




insert into tdu_termosdeuso (tdu_version, tdu_data, tdu_empresadados, tdu_usuariodados, tdu_empresaparceiradados)
    values ('1.0', '10/08/22', 'teste 2.', 'teste2', 'teste2');


insert into tdu_termosdeuso (tdu_version, tdu_data, tdu_empresadados, tdu_usuariodados, tdu_empresaparceiradados)
    values ('2.0', '06/06/22','Para dar uma experiência customizada e melhorar nossos serviços para
          entender melhor como os usuários utilizam o aplicativo, para enviar
          comunicações e alertas importantes através de notificação push,
          emails, SMS, WhatsApp e demais meios fora do app, para apresentar
          anúncios publicitários, entre outras atividades que visam melhorar a
          experiência do usuário.', '1. Dados do perfil 2. Dados do pagamento 3. Dados de localização
          4.dados coletados durante a utilização do serviço', 'hands flowsense admob applovin criteo outlogic');

insert into teu_termosusuario (teu_usuario, teu_version, teu_data, teu_hora, teu_empresadados, teu_usuariodados, teu_empresaparceiradados)
    values ('victor','1.0', '2022/07/05', '3:40:16 PM', 'true', '', '');