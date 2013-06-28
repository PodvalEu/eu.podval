# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  street                    varchar(255),
  constraint pk_address primary key (id))
;

create sequence address_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

