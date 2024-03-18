create table conta (id number(19,0) generated as identity, icone varchar2(255 char), nome varchar2(255 char), saldo_inicial number(38,2) check (saldo_inicial>=0), primary key (id));
create table despesa (id number(19,0) generated as identity, data date not null, descricao varchar2(255 char), valor number(38,2) check (valor>=0), conta_id number(19,0) not null, primary key (id));
create table usuario (id number(19,0) generated as identity, email varchar2(255 char), nome varchar2(255 char), senha varchar2(255 char), primary key (id));
alter table despesa add constraint FKlngcgagha9t0c6n5kgqw4ut8g foreign key (conta_id) references conta;
