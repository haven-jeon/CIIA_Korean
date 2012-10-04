               
--
-- Populate the tables with some data
--         
delete from user where user_id in (1,2);
insert into user(`user_id`,`name`) values (1,"John"); 
insert into user(`user_id`,`name`) values (2,"Jane"); 
--
delete from item_type where item_type_id in (1,2,3);
insert into item_type (item_type_id,name,description) values (1, 'name_1', 'description_1');
insert into item_type (item_type_id,name,description) values (2, 'name_2', 'description_2');
insert into item_type (item_type_id,name,description) values (3, 'name_3', 'description_3');
--
delete from item where item_id in (1,2,3);
insert into item(item_id,item_type_id, name) values (1, 1, 'item1');
insert into item(item_id,item_type_id, name) values (2, 2, 'item2');
insert into item(item_id,item_type_id, name) values (3, 3, 'item3');
--
delete from tags where tag_id in (1,2,3,4,5,6);
insert into tags(`tag_id`,`tag_text`) values (1,"tagging"); 
insert into tags(`tag_id`,`tag_text`) values (2,"schema"); 
insert into tags(`tag_id`,`tag_text`) values (3,"denormalized"); 
insert into tags(`tag_id`,`tag_text`) values (4,"database"); 
insert into tags(`tag_id`,`tag_text`) values (5,"binary"); 
insert into tags(`tag_id`,`tag_text`) values (6,"normalized"); 	
--
delete from user_item_tag where user_id in (1,2,3);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,1,1);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,1,2);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,1,3);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,2,4);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,2,5);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(1,2,2);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(2,3,6);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(2,3,4);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`) values(2,3,2);
--
-- Search term related data
--    
delete from search_terms where search_term_id in (1,2,3,4,5,6);
insert into search_terms(`search_term_id`,`search_term_text`) values (1,"tagging"); 
insert into search_terms(`search_term_id`,`search_term_text`) values (2,"schema"); 
insert into search_terms(`search_term_id`,`search_term_text`) values (3,"denormalized"); 
insert into search_terms(`search_term_id`,`search_term_text`) values (4,"database"); 
insert into search_terms(`search_term_id`,`search_term_text`) values (5,"binary"); 
insert into search_terms(`search_term_id`,`search_term_text`) values (6,"normalized"); 
--
delete from user_search_term where user_id in (1,2,3);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,1);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,2);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,3);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,4);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,5);
insert into user_search_term(`user_id`,`search_term_id`)		values(1,2);
insert into user_search_term(`user_id`,`search_term_id`)		values(2,6);
insert into user_search_term(`user_id`,`search_term_id`)		values(2,4);
insert into user_search_term(`user_id`,`search_term_id`)		values(2,2);
--
	
