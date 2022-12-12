create table author
(
    id              serial,
    name            varchar,
    birth_date      date,
    particularity   varchar,
    primary key (id)
);

alter sequence author_id_seq restart 3;