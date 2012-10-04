--
-- Blog related tables
--   
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (                 
		      `blog_id` int  unsigned NOT NULL auto_increment,
               blog_name varchar(200)   NOT NULL,
               user_id int  unsigned NOT NULL, 
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`blog_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;
          
DROP TABLE IF EXISTS `blog_entry`;
CREATE TABLE `blog_entry` (                 
	      `blog_entry_id` int  unsigned NOT NULL auto_increment,
	      `blog_id` int  unsigned NOT NULL,
               title varchar(200)   NOT NULL,
               body BLOB   NOT NULL,
               permalink_url varchar(200)   NULL,
               version_id int  unsigned NOT NULL, 
               `create_date` timestamp NULL,
               `last_update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`blog_entry_id`),
               FOREIGN KEY (blog_id) REFERENCES blog (blog_id)              
          ) ENGINE=InnoDB;

--
-- History table
--   
DROP TABLE IF EXISTS `blog_entry_history`;
CREATE TABLE `blog_entry_history` (                 
	      `blog_entry_id` int  unsigned NOT NULL auto_increment,
	      version_id int  unsigned NOT NULL, 
	      `blog_id` int  unsigned NOT NULL,
               title varchar(200)   NOT NULL,
               body BLOB   NOT NULL,
               permalink_url varchar(200)   NULL,              
               `create_date` timestamp NULL,
               `last_update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`blog_entry_id`,`version_id`),
               FOREIGN KEY (blog_id) REFERENCES blog (blog_id)              
          ) ENGINE=InnoDB;

DROP TABLE IF EXISTS `blog_entry_comment`;
CREATE TABLE `blog_entry_comment` (                 
	      `blog_comment_entry_id` int  unsigned NOT NULL auto_increment,
	      `blog_entry_id` int  unsigned NOT NULL,
               comment varchar(2000)   NOT NULL,
               name varchar(100)    NULL,
               email varchar(100) null,
               url varchar(200) null,
               `visibility` int unsigned not null, 
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`blog_comment_entry_id`),
               FOREIGN KEY (blog_entry_id) REFERENCES blog_entry (blog_id)              
          ) ENGINE=InnoDB;
          
CREATE TABLE `reference_weblog` (                 
	      `ref_id` int  unsigned NOT NULL auto_increment,
	      `blog_entry_id` int  unsigned NOT NULL,
               title varchar(200)   NOT NULL,
               track_time timestamp NULL ,             
               url varchar(200) null,
               `visibility` int unsigned not null, 
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`ref_id`),
               FOREIGN KEY (blog_entry_id) REFERENCES blog_entry (blog_id)              
          ) ENGINE=InnoDB;          

--
-- User tagging
--           
DROP TABLE IF EXISTS `user_blog_tag`;
CREATE TABLE `user_blog_tag` (     
			  `blog_id` int  unsigned NOT NULL,            
		      `user_id` int  unsigned not null,
		      `tag_id` int  unsigned not null,
		      `visibility` int unsigned not null,
		      `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`blog_id`,`user_id`,`tag_id`),
               FOREIGN KEY (blog_id) REFERENCES blog (blog_id),
               FOREIGN KEY (user_id) REFERENCES user (user_id),
               FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                                     
          ) ENGINE=InnoDB;
          
DROP TABLE IF EXISTS `user_blog_entry_tag`;
CREATE TABLE `user_blog_entry_tag` (     
			  `blog_entry_id` int  unsigned NOT NULL,            
		      `user_id` int  unsigned not null,
		      `tag_id` int  unsigned not null,
		      `visibility` int unsigned not null,
		      `create_date` timestamp NULL default CURRENT_TIMESTAMP,  
               PRIMARY KEY  (`blog_entry_id`,`user_id`,`tag_id`),
               FOREIGN KEY (blog_entry_id) REFERENCES blog_entry (blog_entry_id),
               FOREIGN KEY (user_id) REFERENCES user (user_id),
               FOREIGN KEY (tag_id) REFERENCES tags (tag_id)                                     
          ) ENGINE=InnoDB;

--
-- Wiki related tables
--  
DROP TABLE IF EXISTS `wiki_page_history`;
DROP TABLE IF EXISTS `wiki_category_history`;
DROP TABLE IF EXISTS `wiki_page_category_history`;    
DROP TABLE IF EXISTS `wiki_page`;
DROP TABLE IF EXISTS `wiki_category`;
DROP TABLE IF EXISTS `wiki_page_category`;

CREATE TABLE `wiki_page` (                 
		      `wiki_page_id` int  unsigned NOT NULL auto_increment,
		       text BLOB   NOT NULL,
               title varchar(200)   NOT NULL,
               user_id int  unsigned NOT NULL, 
               permalink_url varchar(200)   NULL,
               `visibility` int unsigned not null,
               version_id int  unsigned NOT NULL, 
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_page_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;
          

CREATE TABLE `wiki_category` (                 
		      `wiki_category_id` int  unsigned NOT NULL auto_increment,
               name varchar(200)   NOT NULL,
               user_id int  unsigned NOT NULL, 
               permalink_url varchar(200)   NULL,
               `visibility` int unsigned not null,
               `version_id` int  unsigned NOT NULL, 
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_category_id`),
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB; 
--
-- Page category map table
--     
CREATE TABLE `wiki_page_category` (   
			  `wiki_page_id` int  unsigned NOT NULL,            
		      `wiki_category_id` int  unsigned NOT NULL,
               user_id int  unsigned NOT NULL, 
               `visibility` int unsigned not null,
               `version_id` int  unsigned NOT NULL, 
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_page_id`,`wiki_category_id`),
               FOREIGN KEY (wiki_page_id) REFERENCES wiki_page (wiki_page_id), 
               FOREIGN KEY (wiki_category_id) REFERENCES wiki_category (wiki_category_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;        
--
-- History tables
--     

CREATE TABLE `wiki_page_history` (                 
		      `wiki_page_id` int  unsigned NOT NULL,
		      `version_id` int  unsigned NOT NULL,
		       text BLOB   NOT NULL,
               title varchar(200)   NOT NULL,
               user_id int  unsigned NOT NULL, 
               permalink_url varchar(200)   NULL,
               `visibility` int unsigned not null,                
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_page_id`,`version_id`),
               FOREIGN KEY (wiki_page_id) REFERENCES wiki_page (wiki_page_id),
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;
          
DROP TABLE IF EXISTS `wiki_category_history`;
CREATE TABLE `wiki_category_history` (                 
		      `wiki_category_id` int  unsigned NOT NULL auto_increment,
		      `version_id` int  unsigned NOT NULL,
               name varchar(200)   NOT NULL,
               user_id int  unsigned NOT NULL, 
               permalink_url varchar(200)   NULL,
               `visibility` int unsigned not null,               
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_category_id`,`version_id`),
               FOREIGN KEY (wiki_category_id) REFERENCES wiki_category (wiki_category_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB; 
          
DROP TABLE IF EXISTS `wiki_page_category_history`;
CREATE TABLE `wiki_page_category_history` (   
			  `wiki_page_id` int  unsigned NOT NULL, 
			  `version_id` int  unsigned NOT NULL,           
		      `wiki_category_id` int  unsigned NOT NULL,
               user_id int  unsigned NOT NULL, 
               `visibility` int unsigned not null,               
               `update_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`wiki_page_id`,`wiki_category_id`,`version_id`),
               FOREIGN KEY (wiki_page_id) REFERENCES wiki_page (wiki_page_id), 
               FOREIGN KEY (wiki_category_id) REFERENCES wiki_category (wiki_category_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB; 
--
-- Msg boards related tables
--  
DROP TABLE IF EXISTS `user_question_rating`; 
DROP TABLE IF EXISTS `user_message_rating`;
DROP TABLE IF EXISTS `msg_board_message`;
DROP TABLE IF EXISTS `msg_board_question`;
DROP TABLE IF EXISTS `msg_board_topic`;
DROP TABLE IF EXISTS `msg_board_group`;
CREATE TABLE `msg_board_group` (                 
		      `msg_group_id` int  unsigned NOT NULL auto_increment,
               name varchar(200)   NOT NULL,
               owner_user_id int  unsigned NOT NULL, 
               description varchar(2000)   NULL,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_group_id`),
               FOREIGN KEY (owner_user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;


CREATE TABLE `msg_board_topic` (                 
		      `msg_topic_id` int  unsigned NOT NULL auto_increment,
               name varchar(200)   NOT NULL,
               owner_user_id int  unsigned NOT NULL, 
               description varchar(2000)   NULL,
               `msg_group_id` int  unsigned NOT NULL,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_topic_id`),
               FOREIGN KEY (msg_group_id) REFERENCES msg_board_group (msg_group_id), 
               FOREIGN KEY (owner_user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;         

CREATE TABLE `msg_board_question` (                 
		      `msg_question_id` int  unsigned NOT NULL auto_increment,
               user_id int  unsigned NOT NULL, 
               text varchar(2000)   NULL,
               `msg_topic_id` int  unsigned NOT NULL,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_question_id`),
               FOREIGN KEY (msg_topic_id) REFERENCES msg_board_topic (msg_topic_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;  
          
CREATE TABLE `msg_board_message` (                 
		      `msg_message_id` int  unsigned NOT NULL auto_increment,
               user_id int  unsigned NOT NULL, 
               text varchar(2000)   NULL,
               `msg_question_id` int  unsigned NOT NULL,
               `reply_to_post_id` int  unsigned NULL,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_message_id`),
               FOREIGN KEY (msg_question_id) REFERENCES msg_board_question (msg_question_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;              

--
-- User rating and Msg boards related tables
--    
    
 CREATE TABLE `user_question_rating` (                 
		      `msg_question_id` int  unsigned NOT NULL,
               user_id int  unsigned NOT NULL, 
               rating double,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_question_id`,user_id),
               FOREIGN KEY (msg_question_id) REFERENCES msg_board_question (msg_question_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;  
          
     
 CREATE TABLE `user_message_rating` (                 
		      `msg_message_id` int  unsigned NOT NULL,
               user_id int  unsigned NOT NULL, 
               rating double,
               `create_date` timestamp NULL default CURRENT_TIMESTAMP,
               PRIMARY KEY  (`msg_message_id`,user_id),
               FOREIGN KEY (msg_message_id) REFERENCES msg_board_message (msg_message_id), 
               FOREIGN KEY (user_id) REFERENCES user (user_id)               
          ) ENGINE=InnoDB;     