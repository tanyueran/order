/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/9/9 10:07:41                            */
/*==============================================================*/


drop table if exists tab_file;

drop table if exists tab_food;

drop table if exists tab_food_img;

drop table if exists tab_order;

drop table if exists tab_user;

/*==============================================================*/
/* Table: tab_file                                              */
/*==============================================================*/
create table tab_file
(
   id                   varchar(255) not null comment '主键',
   file_name            varchar(255) comment '文件名称',
   file_type            varchar(255) comment '文件类型',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   url                  varchar(255) comment '保存的路劲',
   remark               varchar(255) comment '备注',
   primary key (id)
);

alter table tab_file comment '文件表';

/*==============================================================*/
/* Table: tab_food                                              */
/*==============================================================*/
create table tab_food
(
   id                   varchar(255) not null comment '主键',
   food_name            varchar(255) comment '菜品名称',
   food_type            int comment '菜品类型',
   food_detail          varchar(1000) comment '菜品介绍',
   price                double comment '价格',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   img_id               varchar(255) comment '菜品头像id',
   remark               varchar(255) comment '备注',
   primary key (id)
);

alter table tab_food comment '菜品表';

/*==============================================================*/
/* Table: tab_food_img                                          */
/*==============================================================*/
create table tab_food_img
(
   food_id              varchar(255) comment '菜品id',
   file_id              varchar(255) comment '图片id'
);

alter table tab_food_img comment '菜品图片中间表';

/*==============================================================*/
/* Table: tab_order                                             */
/*==============================================================*/
create table tab_order
(
   id                   varchar(255) not null comment '主键',
   user_id              varchar(255) comment '用户id',
   food_id              varchar(255) comment '菜品id',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '下单时间',
   primary key (id)
);

alter table tab_order comment '订单表';

/*==============================================================*/
/* Table: tab_user                                              */
/*==============================================================*/
create table tab_user
(
   id                   varchar(255) not null comment '主键',
   user_code            varchar(255) comment '账号',
   user_name            varchar(255) comment '用户姓名',
   user_pwd             varchar(255) comment '密码',
   file_id              varchar(255) comment '头像id',
   type                 int comment '用户类型(0：普通用户，1|：管理员用户)',
   remark               varchar(255) comment '备注',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table tab_user comment '账户表';

alter table tab_food_img add constraint FK_Reference_3 foreign key (food_id)
      references tab_food (id) on delete restrict on update restrict;

alter table tab_food_img add constraint FK_Reference_4 foreign key (file_id)
      references tab_file (id) on delete restrict on update restrict;

alter table tab_order add constraint FK_Reference_1 foreign key (user_id)
      references tab_user (id) on delete restrict on update restrict;

alter table tab_order add constraint FK_Reference_2 foreign key (food_id)
      references tab_food (id) on delete restrict on update restrict;

