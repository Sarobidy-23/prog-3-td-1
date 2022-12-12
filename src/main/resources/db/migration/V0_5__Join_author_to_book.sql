alter table book
    add column
        author_id integer references author(id);