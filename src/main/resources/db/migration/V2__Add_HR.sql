
INSERT INTO usr (active, reg_date, email, password, phone, username)
VALUES (true, current_timestamp, 'roman1985al@ukr.net', '123', '123456789', 'HR')
RETURNING id;

INSERT INTO user_role (user_id, user_roles)
VALUES ((SELECT id FROM usr WHERE email = 'roman1985al@ukr.net'), 'HR_MANAGER');

INSERT INTO employee (active, reg_date, email, first_name, second_name, last_name, phone)
VALUES (true, current_timestamp, 'roman1985al@ukr.net', 'HR', 'HR', 'HR', '123456789')
RETURNING id;

INSERT INTO employee_role (employee_id, employee_roles)
VALUES ((SELECT id FROM employee WHERE email = 'roman1985al@ukr.net'), 'HR_MANAGER');
