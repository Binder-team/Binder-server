
CREATE SCHEMA IF NOT EXISTS binderdb;

CREATE TABLE IF NOT EXISTS binderdb.user_table(
   id INTEGER,
   username string,
   city string,
   postal_code string,
   phone_number string,
   reputation string,
   isbanned Boolean
);

CREATE TABLE IF NOT EXISTS binderdb.user_books (
 	id integer,
	user_id integer,
	book_id integer,
	is_available boolean
);

CREATE TABLE IF NOT EXISTS binderdb.books (
 	id integer,
	isbn integer,
	condition integer(255),
);

CREATE TABLE IF NOT EXISTS binderdb.reputation (
    id integer,
	review_target integer,
	recipient integer,
	reviewer integer,
	score integer
);


CREATE TABLE IF NOT EXISTS binderdb.trade_table (
 	id integer,
	sender integer,
	receiver integer,
	book_id integer,
	is_matched boolean,
	is_accepted boolean,
	is_exchanged boolean
);