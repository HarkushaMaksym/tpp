
INSERT INTO planet (name, planet_id) VALUES ('The Earth', 1) ON CONFLICT DO NOTHING;

INSERT INTO satellite (name, satellite_id) VALUES ('The Moon', 1) ON CONFLICT DO NOTHING;
