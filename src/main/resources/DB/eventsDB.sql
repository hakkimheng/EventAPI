
-- CREATE DATABASE
CREATE DATABASE events;

-- CREATE VENUE TABLE
CREATE TABLE venue(
    venue_id SERIAL PRIMARY KEY ,
    venue_name VARCHAR NOT NULL ,
    location VARCHAR(50) NOT NULL
);

-- CREATE EVENTS TABLE
CREATE TABLE events(
    events_id SERIAL PRIMARY KEY ,
    events_name VARCHAR(50) NOT NULL ,
    events_date DATE NOT NULL ,
    venue_id INT REFERENCES venue(venue_id) ON DELETE CASCADE
);

--CREATE TABLE ATTENDEE
CREATE TABLE attendee(
    attendee_id SERIAL PRIMARY KEY ,
    attendee_name VARCHAR NOT NULL ,
    email VARCHAR(50) NOT NULL
);

-- CREATE TABLE EVENTS-ATTENDEE
CREATE TABLE events_attendee(
    id SERIAL PRIMARY KEY ,
    events_id INT REFERENCES events(events_id) ON DELETE CASCADE ,
    attendee_id INT REFERENCES attendee(attendee_id) ON DELETE CASCADE

);

DROP TABLE events_attendee;
DROP TABLE events;
DROP TABLE attendee;
DROP TABLE venue;


