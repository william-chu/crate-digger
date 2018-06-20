SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS artists (
  id int PRIMARY KEY auto_increment,
  name VARCHAR,
  imageUrl VARCHAR
);

CREATE TABLE IF NOT EXISTS releases (
  id int PRIMARY KEY auto_increment,
  title VARCHAR,
  label VARCHAR,
  labelNumber VARCHAR,
  mediaCondition int,
  sleeveType VARCHAR,
  sleeveCondition int,
  seller VARCHAR,
  mediaType VARCHAR,
  price int,
  datePurchased VARCHAR,
  isInCollection BOOL,
  imageUrl VARCHAR
);

CREATE TABLE IF NOT EXISTS tracks (
  id int PRIMARY KEY auto_increment,
  title VARCHAR,
  releaseId int
);

CREATE TABLE IF NOT EXISTS notes (
  id int PRIMARY KEY auto_increment,
  content VARCHAR,
  postedAt BIGINT,
  releaseId int
);

CREATE TABLE IF NOT EXISTS artists_releases (
  id int PRIMARY KEY auto_increment,
  artistId int,
  releaseId int
);