/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.6.5-m8 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `blog`;

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(1);

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appreciation` varchar(1) COLLATE utf8_bin NOT NULL,
  `commentabled` tinyint(1) NOT NULL,
  `content` longtext COLLATE utf8_bin,
  `create_time` timestamp NULL DEFAULT NULL,
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `first_picture` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `flag` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `published` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `recommend` varchar(1) COLLATE utf8_bin NOT NULL,
  `share_statement` varchar(1) COLLATE utf8_bin NOT NULL,
  `title` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `tag_id` int(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK292449gwg5yf7ocdlmswv9w4j` (`type_id`),
  KEY `FK8ky5rrsxh01nkhctmo7d48p82` (`user_id`),
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`),
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`appreciation`,`commentabled`,`content`,`create_time`,`description`,`first_picture`,`flag`,`published`,`recommend`,`share_statement`,`title`,`update_time`,`views`,`type_id`,`user_id`,`tag_id`) values 
(1,'1',1,'# 1.配置\r\n\r\n## 1下面是pom依赖的导入和yml文件的配置情况\r\n\r\n```pom\r\n<!--分页插件-->\r\n<dependency>\r\n    <groupId>com.github.pagehelper</groupId>\r\n    <artifactId>pagehelper-spring-boot-starter</artifactId>\r\n    <version>1.2.12</version>\r\n</dependency>\r\n```\r\n\r\n```yml\r\npagehelper:\r\n  helperDialect: mysql\r\n  reasonable: true\r\n  supportMethodsArguments: true\r\n  params: count=countSql\r\n```\r\n\r\n# 2.后台的编写\r\n\r\n## 1.server的编写\r\n\r\n```java\r\n@Override\r\npublic List<Type> typeIFSelect() {\r\n\r\n    List<Type> type = typeDaoMapper.typeIFSelect();\r\n\r\n    return type;\r\n}\r\n```\r\n\r\n```java\r\n//检索所有标签\r\nList<Type> typeIFSelect();\r\n```\r\n\r\n## 2.dao层的编写\r\n\r\n不需要limit来修饰sql语句\r\n\r\n```java\r\n//检索所有标签\r\nList<Type> typeIFSelect();\r\n```\r\n\r\n```xml\r\n<!--检索所有标签-->\r\n<select id=\"typeIFSelect\" resultType=\"com.gaoqing.gaoqingblog.pojo.Type\">\r\n    SELECT * FROM t_type\r\n</select>\r\n```\r\n\r\n## 3.Controller的编写\r\n\r\n```java\r\n//查询所有标签\r\n@RequestMapping(\"/typeList\")\r\npublic String Type(Model model,\r\n                   @RequestParam(defaultValue = \"0\",value = \"pageNum\") Integer pageNum){\r\n    String orderBy = \"id desc\";\r\n    PageHelper.startPage(pageNum,5,orderBy);\r\n    List<Type> typelist = typeService.typeIFSelect();\r\n    PageInfo<Type> pageInfo = new PageInfo<Type>(typelist);\r\n    model.addAttribute(\"typelist\",pageInfo);\r\n    return \"admin/types\";\r\n}\r\n```\r\n\r\n这里有一个小的注意点PageHelper.startPage(pageNum,5,orderBy);这段代码必须在sql的检索语言的上面\r\n\r\n# 3.前端页面的处理\r\n\r\n```html\r\n<tbody>\r\n   <tr th:each=\"type,iterStat:${typelist.list}\">\r\n      <td th:text=\"${iterStat.index+1}\">1</td>\r\n      <td th:text=\"${type.name}\">刻意练习</td>\r\n      <td>\r\n         <a href=\"#\" class=\"ui mini teal button\" >编辑</a>\r\n         <a th:href=\"@{\'/admin/typeDelete/\'+${type.id}}\" class=\"ui mini red button\">删除</a>\r\n      </td>\r\n   </tr>\r\n</tbody>\r\n<tfoot>\r\n   <tr>\r\n      <th colspan=\"6\">\r\n         <div class=\"ui inverted divided stackable grid\">\r\n            <div class=\"three wide column\" align=\"center\">\r\n               <a class=\"item\" th:href=\"@{/admin/typeList(pageNum=${typelist.hasPreviousPage}?${typelist.prePage}:1)}\" th:unless=\"${typelist.isFirstPage}\">上一页</a>\r\n            </div>\r\n\r\n            <div class=\"ten wide column\" align=\"center\">\r\n               <p>第 <span th:text=\"${typelist.pageNum}\"></span> 页，共 <span th:text=\"${typelist.pages}\"></span> 页，有 <span th:text=\"${typelist.total}\"></span> 个分类</p>\r\n            </div>\r\n\r\n            <div class=\"three wide column\" align=\"center\">\r\n               <a class=\"item\" th:href=\"@{/admin/typeList(pageNum=${typelist.hasNextPage}?${typelist.nextPage}:${typelist.pages})}\" th:unless=\"${typelist.isLastPage}\">下一页</a>\r\n            </div>\r\n         </div>\r\n```','2023-08-05 01:00:01','\r\n```java\r\n@Override\r\npublic List<Type> typeIFSelect() {\r\n\r\n    List<Type> type = typeDaoMapper.typeIFSelect();\r\n\r\n    return type;\r\n}\r\n```\r\n\r\n```java\r\n//检索所有标签\r\nList<Type> typeIFSelect();','https://picsum.photos/1000/700','原创','1','1','1','mybatis分页','2023-10-22 16:57:39',4,1,1,1),
(11,'0',1,'## tyoe：类型\r\n解决方法：在mybatis-config.xml中声明你所创建的每一个Mapper 将每一个Mapper都注册进入Mybatis\r\n\r\n注：所有的路径 / 隔开\r\n```xml\r\n<mappers>\r\n    <mapper resource=\"com/gaoqing/dao/UserDao.xml\"></mapper>\r\n</mappers>\r\n```','2023-08-28 16:53:44','org.apache.ibatis.binding.BindingException: Type interface com.gaoqing.dao.UserDao is not known to the MapperRegistry','https://picsum.photos/seed/picsum/1000/700','原创','1','1','1','错误','2023-08-28 16:53:44',11,2,1,2),
(14,'0',0,'asd','2021-10-18 16:12:49','sda','https://picsum.photos/id/237/1000/700','原创','1','1','0','sda','2023-10-18 16:12:49',1,1,1,2),
(15,'0',1,'dsaas','2023-10-18 16:13:10','asd','https://picsum.photos/1000/700?blur','原创','1','1','0','asdxz','2023-10-18 16:13:10',6,2,1,1),
(17,'0',0,'asd','2021-10-18 16:13:40','ads','https://picsum.photos/1000/700?grayscale','原创','1','0','1','asda','2023-10-18 16:13:40',0,2,1,2),
(18,'0',0,'ss','2022-11-03 16:31:42','ss','https://picsum.photos/1000/700','原创','1','0','0','是是','2023-11-03 16:37:36',0,2,1,2),
(19,'1',1,'我使用的是idea2019.3.3\r\n\r\n下面是我的经历：\r\n\r\n一天我心血来潮想整个活。。。。给idea换背景。。。好死不死我去调大小和分辨率。。。。结果程序就损坏了，怎么点都打不开，这时候我就想到卸载软件重装（其实这一步就错了），然后破解，破解后还是打不开，就开始查博客，妈的五花八门的解决方法一个实用的没有一堆的废话，下面是我解决的历程：\r\n\r\n1.删除法：\r\n\r\n找到c盘下面安装idea后会生成的一个文件整个删除（当然这特么很傻帽，没错我就是相信了这个方法的傻帽）\r\n\r\nC:\\Users\\xxxx\\.IntelliJIdea2019.3\r\n\r\n2.换破解方式法\r\n\r\n这种方式就是推荐你去什么什么网站关注什什么公众号（当然我没试过）然后给你个序列号之类的破解，如果你原本就有破解的包之类的不要用这个，一句话不安全\r\n\r\n3.关键点来了\r\n\r\n后面我发现用网上的一个方法可以看idea到底报了什么玩意错，在idea安装目录下找到bin找到idea.bat右键使用文本打开在idea.bat最后一行添加 pause 打印报错信息\r\n\r\n后面我发现它老是报C盘下面idea64.exe.vmoptions这玩意在损坏还是啥的然后抽丝剥茧发现只有这个有点像什么东西的安装路径','2023-11-21 16:37:46','我使用的是idea2019.3.3\r\n\r\n下面是我的经历：\r\n\r\n一天我心血来潮想整个活。。。。给idea换背景。。。好死不死我去调大小和分辨率。。。。结果程序就损坏了，怎么点都打不开，这时候我就想到卸载软件重装（其实这一步就错了），然后破解，破解后还是打不开，就开始查博客，妈的五花八门的解决方法一个实用的没有一堆的废话，下面是我解决的历程：','https://picsum.photos/1000/700','转载','1','1','1','IDEA点不开、闪退、破解后无法打开、快来这','2024-01-15 16:02:20',125,2,1,2);

/*Table structure for table `t_blog_tags` */

DROP TABLE IF EXISTS `t_blog_tags`;

CREATE TABLE `t_blog_tags` (
  `blogs_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FK5feau0gb4lq47fdb03uboswm8` (`tags_id`),
  KEY `FKh4pacwjwofrugxa9hpwaxg6mr` (`blogs_id`),
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`),
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_blog_tags` */

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_comment` bit(1) NOT NULL,
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) DEFAULT NULL,
  `Ethical_judgment` int(1) DEFAULT '1' COMMENT '评论状态 1为正常 0为删除',
  PRIMARY KEY (`id`),
  KEY `FKke3uogd04j4jx316m1p51e05u` (`blog_id`),
  KEY `FK4jj284r3pb7japogvo6h72q95` (`parent_comment_id`),
  CONSTRAINT `FK4jj284r3pb7japogvo6h72q95` FOREIGN KEY (`parent_comment_id`) REFERENCES `t_comment` (`id`),
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`admin_comment`,`avatar`,`content`,`create_time`,`email`,`nickname`,`blog_id`,`parent_comment_id`,`Ethical_judgment`) values 
(1,'','../images/头像.jpg','真实太棒了','2024-03-01 00:00:01','1300610479@qq.com','喜茶',19,NULL,1),
(2,'','../images/头像.jpg','yes','2024-02-04 22:48:09','1300610479@qq.com','打包',19,1,1),
(3,'','../images/头像.jpg','我太喜欢你的文章了','2024-06-02 23:08:26','1300610479@qq.com','小意思',19,NULL,1),
(4,'','../images/头像.jpg','同意','2024-06-03 23:09:11','1300610479@qq.com','德国李先生',19,3,1);

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_tag` */

insert  into `t_tag`(`id`,`name`) values 
(1,'错误代码'),
(2,'入门文章'),
(4,'sada'),
(5,'asdas'),
(6,'sda'),
(7,'saddsad'),
(8,'从入门到入土5'),
(9,'532'),
(10,'324'),
(11,'234');

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_type` */

insert  into `t_type`(`id`,`name`) values 
(1,'python'),
(2,'java'),
(3,'c+'),
(4,'c++'),
(5,'c#'),
(6,'javaSpring');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL,
  `avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(19) COLLATE utf8_bin DEFAULT NULL COMMENT '盐值加密',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`avatar`,`create_time`,`email`,`nickname`,`password`,`type`,`update_time`,`username`,`salt`) values 
(1,'',NULL,NULL,'橘子不是唯一的水果','d87b3212f0faffff9f17cab658c3cfc8',NULL,NULL,'gaoqing','vip1'),
(2,NULL,NULL,NULL,'橘子不是唯一的水果','gaoqing18',NULL,NULL,'gaoqing445','vip1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
