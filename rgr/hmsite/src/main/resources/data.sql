
INSERT INTO planet (id, name) VALUES (1, 'The Earth') ON CONFLICT (id) DO NOTHING;


INSERT INTO satellite (id, name, planet_id) VALUES (1, 'The Moon', 1) ON CONFLICT (id) DO NOTHING;

INSERT INTO users (id, username, password, role) VALUES (1, 'George Washington', '$2a$10$aAgqkXCj9hJK.Uo0Y0L.zO1Z7zeJJwN8Jeon8Vrzh/MqC6LbimABC', 'ADMIN') ON CONFLICT (username) DO NOTHING;
INSERT INTO users (id, username, password, role) VALUES (2, 'John Adams', '$2a$10$aAgqkXCj9hJK.Uo0Y0L.zO1Z7zeJJwN8Jeon8Vrzh/MqC6LbimABC', 'USER') ON CONFLICT (username) DO NOTHING;
