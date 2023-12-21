create table if not exists users(
	username varchar not null primary key,
	password varchar not null,
	enabled boolean not null default true
);

create table if not exists authorities (
	username varchar not null,
	authority varchar not null,
	constraint fk_authorities_users foreign key(username) references users(username)
);
