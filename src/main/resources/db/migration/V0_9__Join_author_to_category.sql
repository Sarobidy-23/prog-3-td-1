create table book_category
(
    id          serial,
    book_id     int references book(id),
    category_id int references category(id)
)