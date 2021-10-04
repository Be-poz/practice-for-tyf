create table donation (
                          id bigint not null auto_increment,
                          amount bigint,
                          creator_id bigint,
                          donator_id bigint,
                          primary key (id)
) engine=InnoDB;

create table master (
                        id bigint not null auto_increment,
                        name varchar(255),
                        slave_id bigint,
                        primary key (id)
) engine=InnoDB;

create table member (
                        id bigint not null auto_increment,
                        name varchar(255),
                        primary key (id)
) engine=InnoDB;

create table slave (
                       id bigint not null auto_increment,
                       name varchar(255),
                       primary key (id)
) engine=InnoDB;

alter table donation
    add constraint UKp9dor9gow8qpyfjjrq80xnd35 unique (donator_id, creator_id);

alter table donation
    add constraint FK1p1lwqxs91f1xrbv9b8x9uu60
        foreign key (creator_id)
            references member (id);

alter table donation
    add constraint FKkyw46epq4p3xtgqyweniyxwss
        foreign key (donator_id)
            references member (id);

alter table master
    add constraint FKh8soe8x737ijp7txf5yw1u9nn
        foreign key (slave_id)
            references slave (id);