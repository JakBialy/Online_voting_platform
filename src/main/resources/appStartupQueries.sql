-- ADD COMPANIES
INSERT INTO companies (id, company_name) values (1, 'TEST_1');
INSERT INTO companies (id, company_name) values (2, 'TEST_2');

-- ADD EMPLOYERS IDS
INSERT INTO employers_ids_companies (company_id, employers_ids) values (1, 'ABC123');
INSERT INTO employers_ids_companies (company_id, employers_ids) values (1, 'ABC1234');
INSERT INTO employers_ids_companies (company_id, employers_ids) values (1, 'DEMO');
INSERT INTO employers_ids_companies (company_id, employers_ids) values (2, 'ABC321');
INSERT INTO employers_ids_companies (company_id, employers_ids) values (2, 'ABC4321');

-- ADD VOTES
INSERT INTO votes (id, is_finished, is_one_winner, name, vote_start, vote_end, vote_results, companies_id)
values (1, 'f', 'f', 'Company''s drinking party', now() + interval '1 minute',
        now() + interval '2 minutes' , now() + interval '2 minutes', 1);

INSERT INTO votes (id, is_finished, is_one_winner, name, vote_start, vote_end, vote_results, companies_id)
values (2, 'f', 'f', 'Some another event', now(),
        now() + interval '20 minutes' , now() + interval '30 minutes', 1);

INSERT INTO votes (id, is_finished, is_one_winner, name, vote_start, vote_end, vote_results, companies_id)
values (3, 't', 't', 'Some event in the past', now() - interval '15 days',
        now() - interval '10 days' , now() - interval '9 days', 1);

-- ADD VOTE OPTIONS
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (1, 'f', 'Thursday', 0, 0, 1);
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (2, 'f', 'Friday', 0, 0, 1);
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (3, 'f', '10.05.2019', 0, 0, 2);
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (4, 'f', '10.06.2019', 0, 0, 2);
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (5, 'f', 'Saturday', 0, 10, 3);
INSERT INTO vote_options (id, is_winner, name, percentage, votes_number, vote_id) values (6, 't', 'Friday', 0, 15, 3);

-- ROLES
INSERT INTO roles (role_id, role) values (1, 'ADMIN');
INSERT INTO roles (role_id, role) values (2, 'USER');

-- USERS
INSERT INTO users (id, document_id, email, enabled, first_name, last_name, password, username, companies_id)
values (1, 'ABC123', 'bialyj1@gmail.com', 't', 'Jakub', 'B', '$2a$10$NqfI4SdgfumDjj5iZf.JXOodbpMoll1/mafIkbF5xDEDTHc5ov5I6', 'bialyj1@gmail.com', 1);

-- ADD USERS TO ROLES
INSERT INTO users_roles (user_id, role_id) values (1, 2);

