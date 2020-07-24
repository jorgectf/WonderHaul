create table hibernate_sequence (next_val bigint) engine=MyISAM
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table WH_BlockMeta (location varchar(50) not null, drops bit, type varchar(255), internal_item_id bigint, primary key (location)) engine=MyISAM
create table WH_BlockMeta_Props (block_location varchar(50) not null, property_value varchar(255), property_name varchar(50) not null, primary key (block_location, property_name)) engine=MyISAM
create table WH_BlockMeta_WH_PersistedInventory (BlockMetadata_location varchar(50) not null, customInventories_id bigint not null, primary key (BlockMetadata_location, customInventories_id)) engine=MyISAM
create table WH_PersistedInv_Item (inventory_id bigint not null, item_id bigint not null) engine=MyISAM
create table WH_PersistedInventory (id bigint not null, reference varchar(255), primary key (id)) engine=MyISAM
create table WH_PersistedItem (id bigint not null, item Text, primary key (id)) engine=MyISAM
alter table WH_BlockMeta_WH_PersistedInventory add constraint UK_3rg0n25tuynt1hgcxlthvtpqb unique (customInventories_id)
alter table WH_PersistedInv_Item add constraint UK_28dld9ybks3waqmxdlc340sd3 unique (item_id)
alter table WH_BlockMeta add constraint FK1djsxhf9hotn018hyb2a6ctc1 foreign key (internal_item_id) references WH_PersistedItem (id)
alter table WH_BlockMeta_Props add constraint FKrrj6j4weuuavldk7hv06q1onr foreign key (block_location) references WH_BlockMeta (location)
alter table WH_BlockMeta_WH_PersistedInventory add constraint FK228hhkbss9tni6wed3jgk0ev4 foreign key (customInventories_id) references WH_PersistedInventory (id)
alter table WH_BlockMeta_WH_PersistedInventory add constraint FKppo997vlgghqap5md2gvoy97h foreign key (BlockMetadata_location) references WH_BlockMeta (location)
alter table WH_PersistedInv_Item add constraint FKk73pul90qxbgvkqxykjotraku foreign key (item_id) references WH_PersistedItem (id)
alter table WH_PersistedInv_Item add constraint FKo9v00dvtasbpcfudatiit2pfd foreign key (inventory_id) references WH_PersistedInventory (id)
