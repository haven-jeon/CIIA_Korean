--
-- General tables for user and items. For your application they will be a lot more
-- richer, in columns
--        
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (                 
		      `user_id` int unsigned PRIMARY KEY NOT NULL auto_increment,
               name varchar(50)                   
          ) ENGINE=InnoDB;
drop table if exists item_type;
create table item_type (                 
		      `item_type_id` int unsigned PRIMARY KEY NOT NULL auto_increment,
           name varchar(50),
					description varchar(200),
					UNIQUE INDEX uidx_item_type (item_type_id) 
          ) ENGINE=InnoDB;
         
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (                 
		      `item_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
					item_type_id int unsigned not null,
          name varchar(50),
					 FOREIGN KEY (item_type_id) REFERENCES item_type (item_type_id)
          ) ENGINE=InnoDB;        

 
--
-- Tagging related. 
--  
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (                 
		      `tag_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               tag_text varchar(50)   NOT NULL,
							 stemmed_text varchar(50) default null,
               UNIQUE INDEX uidx_tag_text (tag_text)                
          ) ENGINE=InnoDB;

DROP TABLE IF EXISTS `tagging_source`;
CREATE TABLE `tagging_source` (                 
		      `source_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               source_name varchar(50)   NOT NULL,
               UNIQUE INDEX uidx_tagging_source (source_name)                
          ) ENGINE=InnoDB;

DROP TABLE IF EXISTS `tag_summary`;
DROP TABLE IF EXISTS `days`;
CREATE TABLE `days` (                 
		      `day_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               day  timestamp NOT NULL,
               UNIQUE INDEX uidx_days (day)                
          ) ENGINE=InnoDB;
          
CREATE TABLE `tag_summary` (       
			  `tag_id` int  unsigned NOT NULL ,          
		      `day_id` int  unsigned NOT NULL ,
               number int  unsigned NOT NULL,
               PRIMARY KEY  (`tag_id`,`day_id`),  
               FOREIGN KEY (tag_id) REFERENCES tags (tag_id),
               FOREIGN KEY (day_id) REFERENCES days (day_id)             
          ) ENGINE=InnoDB;
--
-- MySQLicious, 
--                      
DROP TABLE IF EXISTS `MySQLicious`;
CREATE TABLE `MySQLicious` (                 
		      `id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               url varchar(200)   NOT NULL,
               name varchar(50)   NOT NULL,
               description varchar(2000)   NOT NULL,
               tags varchar(500)   NOT NULL,
               create_date  timestamp NOT NULL               
          ) ENGINE=InnoDB;    
--
-- Scuttle 
--                      
DROP TABLE IF EXISTS `Scuttle_bookmark`;
CREATE TABLE `Scuttle_bookmark` (                 
		      `bookmark_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               url varchar(200)   NOT NULL,
               name varchar(50)   NOT NULL,
               description varchar(2000)   NOT NULL,
               create_date  timestamp NOT NULL               
          ) ENGINE=InnoDB; 
          
DROP TABLE IF EXISTS `Scuttle_tags`;
CREATE TABLE `Scuttle_tags` (                 
		      `id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               bookmark_id int  unsigned NOT NULL,
               tags varchar(100)   NOT NULL,
               FOREIGN KEY (bookmark_id) REFERENCES Scuttle_bookmark (bookmark_id)             
          ) ENGINE=InnoDB; 
--
-- Toxi 
--                      
DROP TABLE IF EXISTS `Toxi_bookmark_tag`;   
DROP TABLE IF EXISTS `Toxi_bookmark`;       
DROP TABLE IF EXISTS `Toxi_tags`;
CREATE TABLE `Toxi_bookmark` (                 
		      `bookmark_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               url varchar(200)   NOT NULL,
               name varchar(50)   NOT NULL,
               description varchar(2000)   NOT NULL,
               create_date  timestamp NOT NULL               
          ) ENGINE=InnoDB; 

CREATE TABLE `Toxi_tags` (                 
		      `tag_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
           tag varchar(100)   NOT NULL           
          ) ENGINE=InnoDB;   


CREATE TABLE `Toxi_bookmark_tag` (                 
		      `id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
		      `bookmark_id` int  unsigned NOT NULL,
              `tag_id` int  unsigned NOT NULL,   
              FOREIGN KEY (bookmark_id) REFERENCES Toxi_bookmark (bookmark_id),
               FOREIGN KEY (tag_id) REFERENCES Toxi_tags (tag_id)      
          ) ENGINE=InnoDB;                
--
-- Search terms related. 
--  
DROP TABLE IF EXISTS `search_terms`;
CREATE TABLE `search_terms` (                 
		      `search_term_id` int  unsigned PRIMARY KEY NOT NULL auto_increment,
               search_term_text varchar(200)   NOT NULL,
               UNIQUE INDEX uidx_search_term_text (search_term_text)                
          ) ENGINE=InnoDB;
--
-- Mapping tables
--  
DROP TABLE IF EXISTS `user_item_rating_statistic`;
DROP TABLE IF EXISTS `user_item_rating`;
CREATE TABLE `user_item_rating` (                 
		      `user_id` int  unsigned not null,
		      `item_id` int unsigned not null,
		       rating double,
		       `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`user_id`,`item_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id),
               FOREIGN KEY (item_id) REFERENCES item (item_id)                       
          ) ENGINE=InnoDB;
--
-- Statistic table
--           
CREATE TABLE `user_item_rating_statistic` (                 
		      `item_id` int unsigned not null,
		      `day_id` int  unsigned not null,
		       average_rating double,
		       sum_rating double,
		       number int unsigned,		       
               PRIMARY KEY  (`item_id`,`day_id`),
               FOREIGN KEY (day_id) REFERENCES days (day_id),
               FOREIGN KEY (item_id) REFERENCES item (item_id)                       
          ) ENGINE=InnoDB;
--
-- tag table
--                      
DROP TABLE IF EXISTS `user_item_tag`;
CREATE TABLE `user_item_tag` (                 
		      `user_id` int  unsigned not null,
		      `item_id` int unsigned not null,
		      `tag_id` int unsigned not null,
		       `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`user_id`,`item_id`,`tag_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id),
               FOREIGN KEY (item_id) REFERENCES item (item_id),
               FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                        
          ) ENGINE=InnoDB;
 
DROP TABLE IF EXISTS `item_tag`;
CREATE TABLE `item_tag` (                 
		      `source_id` int  unsigned not null,
		      `item_id` int unsigned not null,
		      `tag_id` int unsigned not null,
					weight double not null,
		       `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`source_id`,`item_id`,`tag_id`),
               FOREIGN KEY (source_id) REFERENCES tagging_source (source_id),
               FOREIGN KEY (item_id) REFERENCES item (item_id),
               FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                        
          ) ENGINE=InnoDB;
--
-- Search term tables
--  
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
          
--
-- Folder tables
--        
DROP TABLE IF EXISTS `folder`;
CREATE TABLE `folder` (                 
		      `folder_id` int  unsigned NOT NULL auto_increment,
		      `user_id` int  unsigned NOT NULL,
               name varchar(50),
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
                PRIMARY KEY  (`folder_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id)                
          ) ENGINE=InnoDB; 
          
                   
DROP TABLE IF EXISTS `folder_item`;
CREATE TABLE `folder_item` (                 
		      `folder_id` int  unsigned NOT NULL,
		      `item_id` int  unsigned not null,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
                PRIMARY KEY  (`folder_id`,`item_id`),
               FOREIGN KEY (item_id) REFERENCES item (item_id), 
                FOREIGN KEY (folder_id) REFERENCES folder (folder_id)                 
          ) ENGINE=InnoDB;  
 
DROP TABLE IF EXISTS `item_review`;
CREATE TABLE `item_review` (                 
		      `user_id` int  unsigned NOT NULL,
		      `item_id` int  unsigned not null,
		      	`review` blob not null,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
                PRIMARY KEY  (`user_id`,`item_id`),
               FOREIGN KEY (item_id) REFERENCES item (item_id), 
                FOREIGN KEY (user_id) REFERENCES user (user_id)                 
          ) ENGINE=InnoDB;    
--
-- Metadata related tables
--         
DROP TABLE IF EXISTS `item_metadata`;
CREATE TABLE `item_metadata` (                 
		      `tag_id` int  unsigned NOT NULL,
		      `item_id` int  unsigned not null,
		      	`magnitude` double not null,  
                PRIMARY KEY  (`tag_id`,`item_id`),
               FOREIGN KEY (item_id) REFERENCES item (item_id), 
                FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                 
          ) ENGINE=InnoDB;     
          
DROP TABLE IF EXISTS `user_metadata`;
CREATE TABLE `user_metadata` (                 
		      `tag_id` int  unsigned NOT NULL,
		      `user_id` int  unsigned not null,
		      	`magnitude` double not null,  
                PRIMARY KEY  (`tag_id`,`user_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id), 
                FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                 
          ) ENGINE=InnoDB;                      
--
-- Populate the tables with some data
--         
delete from user where user_id in (1,2);
insert into user(`user_id`,`name`)
	values (1,"John"); 
insert into user(`user_id`,`name`)
	values (2,"Jane"); 
	
delete from item where item_id in (1,2,3);
insert into item(`item_id`,`name`)
	values (1,"item1"); 
insert into item(`item_id`,`name`)
	values (2,"item2"); 
insert into item(`item_id`,`name`)
	values (3,"item3"); 

delete from tags where tag_id in (1,2,3,4,5,6);
insert into tags(`tag_id`,`tag_text`)
	values (1,"tagging"); 
insert into tags(`tag_id`,`tag_text`)
	values (2,"schema"); 
insert into tags(`tag_id`,`tag_text`)
	values (3,"denormalized"); 
insert into tags(`tag_id`,`tag_text`)
	values (4,"database"); 
insert into tags(`tag_id`,`tag_text`)
	values (5,"binary"); 
insert into tags(`tag_id`,`tag_text`)
	values (6,"normalized"); 	
	
delete from user_item_tag where user_id in (1,2,3);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,1,1);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,1,2);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,1,3);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,2,4);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,2,5);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(1,2,2);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(2,3,6);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(2,3,4);
insert into user_item_tag(`user_id`,`item_id`,`tag_id`)		
	values(2,3,2);
	
select t.tag_text, count(*) from user_Item_Tag uit, tags t
	where uit.tag_id = t.tag_id 
	group by t.tag_text
	
select uit2.item_id, count(*) from user_item_tag uit2 where
	uit2.tag_id in (select uit.tag_id from user_item_tag uit, tags t where
						uit.tag_id = t.tag_id and
						uit.item_id = 1)
			group by uit2.item_id

select distinct t.tag_id from tags t, user_item_tag uit where 
	t.tag_id = uit.tag_id and uit.user_id = 1;

select distinct uit2.user_id from user_item_tag uit2, tags t2 where
	uit2.tag_id = t2.tag_id and
	uit2.tag_id in (select distinct t.tag_id from tags t, user_item_tag uit where
						t.tag_id = uit.tag_id and uit.user_id =1)
						
select uit3.tag_id, count(*) from user_item_tag uit3, tags t3 where
	uit3.tag_id = t3.tag_id and 
	uit3.user_id in 
	(select distinct uit2.user_id from user_item_tag uit2, tags t2 where
	uit2.tag_id = t2.tag_id and
	uit2.tag_id in (select distinct t.tag_id from tags t, user_item_tag uit where
						t.tag_id = uit.tag_id and uit.user_id =1))
	group by uit3.tag_id
	
	select t.tag_id, t.tag_text, count(*) from user_item_tag uit, tags t where
	uit.tag_id = t.tag_id 
	group by t.tag_id order by t.tag_id
	
--
-- Search term related data
--    
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
	
	