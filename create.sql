CREATE DATABASE cratedigger;
\c cratedigger;
CREATE TABLE IF NOT EXISTS artists (
  id SERIAL PRIMARY KEY ,
  name VARCHAR,
  imageUrl VARCHAR
);

CREATE TABLE IF NOT EXISTS releases (
  id SERIAL PRIMARY KEY,
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
  id SERIAL PRIMARY KEY,
  title VARCHAR,
  releaseId int
);

CREATE TABLE IF NOT EXISTS notes (
  id SERIAL PRIMARY KEY,
  content VARCHAR,
  postedAt BIGINT,
  releaseId int
);

CREATE TABLE IF NOT EXISTS artists_releases (
  id SERIAL PRIMARY KEY,
  artistId int,
  releaseId int
);
CREATE DATABASE cratedigger_test WITH TEMPLATE cratedigger;