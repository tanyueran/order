/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/9/2 14:23:35                            */
/*==============================================================*/


drop table if exists file_tab;

drop table if exists food_img_tab;

drop table if exists food_tab;

drop table if exists order_tab;

drop table if exists user_tab;

/*==============================================================*/
/* Table: file_tab                                              */
/*==============================================================*/
create table file_tab
(
   id                   varchar(255) not null comment '主键',
   file_name            varchar(255) comment '文件名称',
   file_type            varchar(255) comment '文件类型',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   url                  varchar(255) comment '保存的路劲',
   remark               varchar(255) comment '备注',
   primary key (id)
);

alter table file_tab comment '文件表';

/*==============================================================*/
/* Table: food_img_tab                                          */
/*==============================================================*/
create table food_img_tab
(
   food_id              varchar(255) comment '菜品id',
   file_id              varchar(255) comment '图片id'
);

alter table food_img_tab comment '菜品图片中间表';

/*==============================================================*/
/* Table: food_tab                                              */
/*==============================================================*/
create table food_tab
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

alter table food_tab comment '菜品表';

/*==============================================================*/
/* Table: order_tab                                             */
/*==============================================================*/
create table order_tab
(
   id                   varchar(255) not null comment '主键',
   user_id              varchar(255) comment '用户id',
   food_id              varchar(255) comment '菜品id',
   remark               varchar(255) comment '备注',
   create_time          datetime comment '下单时间',
   primary key (id)
);

alter table order_tab comment '订单表';

/*==============================================================*/
/* Table: user_tab                                              */
/*==============================================================*/
create table user_tab
(
   id                   varchar(255) not null comment '主键',
   user_codde           varchar(255) comment '账号',
   user_name            varchar(255) comment '用户姓名',
   password             varchar(255) comment '密码',
   file_id              varchar(255) comment '头像id',
   type                 int comment '用户类型',
   remark               varchar(255) comment '备注',
   create_time          datetime default CURRENT_TIMESTAMP comment '创建时间',
   update_time          datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
   primary key (id)
);

alter table user_tab comment '账户表';

alter table food_img_tab add constraint FK_Reference_3 foreign key (food_id)
      references food_tab (id) on delete restrict on update restrict;

alter table food_img_tab add constraint FK_Reference_4 foreign key (file_id)
      references file_tab (id) on delete restrict on update restrict;

alter table order_tab add constraint FK_Reference_1 foreign key (user_id)
      references user_tab (id) on delete restrict on update restrict;

alter table order_tab add constraint FK_Reference_2 foreign key (food_id)
      references food_tab (id) on delete restrict on update restrict;

