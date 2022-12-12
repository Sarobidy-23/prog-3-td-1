alter table book
    add
        author_id int references author(id);