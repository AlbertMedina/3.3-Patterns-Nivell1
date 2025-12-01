USE escape_db;

INSERT INTO escape_db.escape_room (name) VALUES
('Pirates Hideout'),
('Cosmic Odyssey'),
('Nightmare Manor');

INSERT INTO escape_db.room (name, difficulty, price, escape_room_id) VALUES
('The Pirates Curse', 'MEDIUM', 30.00, 1),
('The Treasure Hunt', 'EASY', 22.50, 1),
('The Lost Starship', 'MEDIUM', 36.50, 2),
('The Haunting', 'HARD', 40.00, 3);

INSERT INTO escape_db.decoration (name, material, value, room_id) VALUES
('Skull', 'Plastic', 15.00, 1),
('Spyglass', 'Metal', 25.00, 1),
('Chest', 'Wood', 60.00, 2),
('Map', 'Paper', 10.00, 2),
('Button panel', 'Plastic', 50.00, 3),
('Astronaut helmet', 'Plastic', 70.00, 3),
('Chandelier', 'Metal', 100.00, 4),
('Book', 'Paper', 30.00, 4);

INSERT INTO escape_db.hint (text, theme, value, room_id) VALUES
('The map reveals hidden secrets if you look closely.', 'Exploration', 3.00, 1),
('What the pirates sought most became their doom.', 'Curse', 2.00, 2),
('The stars reveal paths no map can show.', 'Navigation', 3.50, 3),
('The family portraits in the manor hold the most horrifying secrets.', 'Horror', 4.50, 4);

INSERT INTO escape_db.user (name, surnames, email, subscribed) VALUES
('Jack', 'Sparrow Ironhook', 'sparrow@example.com', TRUE),
('John', 'Silver', 'johnsilver@example.com', FALSE),
('Neil', 'Alden Armstrong', 'n.armstrong@example.com', TRUE),
('Freddy', 'William Krueger', 'freddy_krueger@example.com', FALSE);

INSERT INTO escape_db.ticket (date, price, room_id, user_id) VALUES
('2025-01-01', 30.00, 1, 1),
('2025-02-02', 30.00, 1, 2),
('2025-03-03', 22.50, 2, 2),
('2025-04-04', 36.50, 3, 3),
('2025-05-05', 40.00, 4, 4);

INSERT INTO escape_db.reward (name, description, date, user_id) VALUES
('Treasure Hunter', 'Found the pirates treasure.', '2025-03-03', 2),
('Ghostbuster', 'Completed a horror room.', '2025-05-05', 4),
('Space Explorer', 'Discovered the secret of the stars', '2025-04-04', 3);

INSERT INTO escape_db.certification (name, date, room_id, user_id) VALUES
('Pirates Curse Certification', '2025-01-01', 1, 1),
('Treasure Hunt Certification', '2025-02-02', 1, 2),
('Treasure Hunt Certification', '2025-03-03', 2, 2),
('Lost Starship Certification', '2025-04-04', 3, 3),
('Haunting Certification', '2025-05-05', 4, 4);