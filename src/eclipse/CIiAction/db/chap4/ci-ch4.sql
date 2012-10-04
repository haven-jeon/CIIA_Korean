DROP TABLE IF EXISTS `search_terms`;
CREATE TABLE `search_terms` (                 
		      `search_term_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               search_term_text varchar(200)   NOT NULL,
               UNIQUE INDEX uidx_search_term_text (search_term_text)                
          ) ENGINE=InnoDB;


DROP TABLE IF EXISTS `user_search_term`;
CREATE TABLE `user_search_term` (     
			  `id` int  unsigned NOT NULL auto_increment,            
		      `user_id` int  unsigned not null,
		      `search_term_id` int unsigned not null,
		      `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id),
               FOREIGN KEY (search_term_id) REFERENCES search_terms (search_term_id)                       
          ) ENGINE=InnoDB;

delete from search_terms where search_term_id in (1,2,3,4,5,6);
insert into search_terms(`search_term_id`,`search_term_text`)
	values (1,"tagging"); 
insert into search_terms(`search_term_id`,`search_term_text`)
	values (2,"schema"); 
insert into search_terms(`search_term_id`,`search_term_text`)
	values (3,"denormalized"); 
insert into search_terms(`search_term_id`,`search_term_text`)
	values (4,"database"); 
insert into search_terms(`search_term_id`,`search_term_text`)
	values (5,"binary"); 
insert into search_terms(`search_term_id`,`search_term_text`)
	values (6,"normalized"); 

delete from user where user_id in (1,2);
insert into user(`user_id`,`name`)
	values (1,"John"); 
insert into user(`user_id`,`name`)
	values (2,"Jane"); 

delete from user_search_term where user_id in (1,2,3);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,1);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,2);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,3);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,4);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,5);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(1,2);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(2,6);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(2,4);
insert into user_search_term(`user_id`,`search_term_id`)		
	values(2,2);
	
select st.search_term_text, count(*) from search_terms st, user_search_term ust
	where st.search_term_id = ust.search_term_id 
	group by st.search_term_id
	order by count(*) desc