

/*
Table structure for goods    商品信息表

type:
  1:上衣
  2：下衣
*/

DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
 `good_id` varchar(100) NOT NULL COMMENT '商品ID',
  `good_name` varchar(200) COMMENT '商品名称',
  `good_price` int(255) COMMENT '商品价格',
  `good_img` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `good_type` varchar(500) DEFAULT '1' COMMENT '商品类型',
  `good_num` int(255)   COMMENT '库存数量',
  `good_desc` varchar(500) default '商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述' 			COMMENT '商品描述',
  `good_com` int(255)   COMMENT '评论次数',
  PRIMARY KEY (`good_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;




INSERT INTO `goods` VALUES ('1', '纯色T恤', 38,'images/pi.png','1',255,'纯色T恤的商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('2', '红色毛衣', 45,'images/pi1.png','1',255,'红色毛衣--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('3', 'nike T恤', 4,'images/pi2.png','1',255,'nike T恤--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('4', '蓝色寸衣', 43,'images/pi3.png','1',222,'蓝色寸衣--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('5', '绿色短袖寸衣', 55,'images/pi4.png','1',222,'绿色短袖寸衣--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('6', '黄色短袖', 33,'images/pi5.png','1',255,'黄色短袖--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('7', '叼爆牛仔裤', 10,'images/pi6.png','2',222,'叼爆牛仔裤--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('8', 't-恤', 20,'images/pi.png','1',221,'t-恤--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);



INSERT INTO `goods` VALUES ('9', '蓝色寸衣2', 2,'images/pi3.png','1',222,'蓝色寸衣2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('10', '绿色短袖寸衣2', 23,'images/pi4.png','1',222,'绿色短袖寸衣2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('11', '黄色短袖2', 53,'images/pi5.png','1',255,'黄色短袖2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('12', '叼爆牛仔裤2', 56,'images/pi6.png','2',222,'叼爆牛仔裤2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('13', '纯色T恤2', 13,'images/pi.png','1',255,'纯色T恤2--的商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('14', '红色毛衣2', 24,'images/pi1.png','1',255,'红色毛衣2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('15', 'nike T恤2', 34,'images/pi2.png','1',255,'nike T恤2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
INSERT INTO `goods` VALUES ('16', 't-恤2', 18,'images/pi.png','1',221,'t-恤2--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);


INSERT INTO `goods` VALUES ('17', '叼爆牛仔裤3', 5,'images/pi6.png','2',222,'叼爆牛仔裤3--商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述商品描述',0);
/*
 Table structure for  收货信息表
*/

DROP TABLE IF EXISTS `accept`;
CREATE TABLE `accept` (
  `accept_id` varchar(100) NOT NULL COMMENT '收货信息ID',
  `accept_name` varchar(100) COMMENT '收货人',
  `user_id` varchar(200) not null COMMENT '用户id',
  `good_id` varchar(200) COMMENT '商品id',
  `address` varchar(500)  NOT NULL COMMENT '收货地址',
  `phone` varchar(500)  not null COMMENT '联系方式',
  PRIMARY KEY (`accept_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `accept` VALUES ('1', '刘亮','3', '1','湖北省武汉市q','138888888');
INSERT INTO `accept` VALUES ('2', '刘晶','1', '2','云南省昆明市q','159999999');
INSERT INTO `accept` VALUES ('3', '刘闪','2', '1','广州省深圳市q','136666666');





/*
 Table structure for shopcar 购物车

     购物车id与用户id一对一

     购物车状态：
        0---未购买
	1---已购买
*/

DROP TABLE IF EXISTS `shopcar`;
CREATE TABLE `shopcar` (
  `shopcar_id` varchar(100) NOT NULL COMMENT '购物车商品ID',
  `createTime` bigint(20) COMMENT '创建时间',
  `user_id` varchar(200) not null COMMENT '用户id',
  `good_id` varchar(200) not null COMMENT '商品id',

  PRIMARY KEY (`shopcar_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `shopcar` VALUES ('1', 2017,'3', '1');

INSERT INTO `shopcar` VALUES ('2', 2017,'3', '2');

INSERT INTO `shopcar` VALUES ('3', 2017,'3', '3');


/*
 Table structure for shopcar 评论表

*/
DROP TABLE IF EXISTS `writes`;
CREATE TABLE `writes` (
  `write_id` varchar(100) NOT NULL COMMENT '评论表ID',
  `user_id` varchar(100) not null COMMENT '用户ID',
  `good_id` varchar(200) not null COMMENT '商品id',
  `write_desc` varchar(200)  COMMENT '评论内容描述',
   `createTime` bigint(20)  COMMENT '评论时间',	  
  PRIMARY KEY (`write_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;
