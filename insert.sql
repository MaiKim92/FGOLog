USE fgologserver;

INSERT INTO wish_item (servant_id) VALUES (106);
INSERT INTO wish_item (servant_id) VALUES (107);
INSERT INTO wish_item (servant_id) VALUES (108);
INSERT INTO wish_item (servant_id) VALUES (109);
INSERT INTO wish_item (servant_id) VALUES (110);
INSERT INTO wish_item (servant_id) VALUES (111);
INSERT INTO wish_item (servant_id) VALUES (114);

INSERT INTO skill (`name`, `level`, `image_url`, `seq`, `servant_id`) VALUES ('Eye of the Mind (False) B', 10, 'view/assets/img/skill/Evade.png', 1, 1);
INSERT INTO skill (`name`, `level`, `image_url`, `seq`, `servant_id`) VALUES ('Projection Magecraft B', 10, 'view/assets/img/skill/Quick-Arts-Buster-Up.png', 2, 1);
INSERT INTO skill (`name`, `level`, `image_url`, `seq`, `servant_id`) VALUES ('Kiss Demon B', 10, 'view/assets/img/skill/NP-Gain.png', 3, 1);

INSERT INTO party (`id`,`name`) VALUES (1, 'Shell farm (Babylonia - Observatory)');
INSERT INTO party (`id`,`name`) VALUES (2, 'Bullet farm (Russia - City of Beasts)');
INSERT INTO party (`id`,`name`) VALUES (3, 'Silk farm (Heian-Kyo - Gojou Bridge)');
INSERT INTO party (`id`,`name`) VALUES (4, 'Egg farm (Heian-Kyo - Inari Shrine)');

INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (1, 1, 13);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (2, 1, 35);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (3, 1, 112);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (4, 1, 7);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (1, 2, 1);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (2, 2, 35);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (3, 2, 112);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (4, 2, 84);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (5, 2, 79);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (1, 3, 30);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (2, 3, 62);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (3, 3, 113);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (4, 3, 9);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (1, 4, 29);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (2, 4, 79);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (3, 4, 113);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (4, 4, 78);
INSERT INTO `party_member` (`seq`, `party_id`, `servant_id`) VALUES (5, 4, 9);

INSERT INTO fgologserver.command_code(id, name) VALUES (1, 'Love Hunter');