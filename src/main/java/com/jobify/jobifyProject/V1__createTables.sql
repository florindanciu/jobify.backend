CREATE TABLE companies
(id uuid PRIMARY KEY,
name varchar ,
description varchar ,
website_link varchar);

CREATE TABLE jobs
(id uuid PRIMARY KEY,
name varchar ,
description varchar,
apply_link varchar,
company_name varchar,
company_id uuid references companies(id),
published_date date);

CREATE TABLE users
(id uuid PRIMARY KEY,
isAdmin boolean,
first_name varchar,
last_name varchar,
email varchar,
password varchar,
jobId uuid references jobs(id),
companyId uuid references companies(id),
applied_job_id uuid);

CREATE TABLE applied_jobs
 (id uuid PRIMARY KEY,
 userId uuid references users(id),
 jobId uuid references jobs(id));





