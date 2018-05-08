## 预览
[预览地址（未完工）](http://193.112.43.236:8080/)
## 表结构

### 1. 酒店相关

```mysql
mysql> desc t_hotel_standard;
+---------------+--------------+------+-----+---------+----------------+
| Field         | Type         | Null | Key | Default | Extra          |
+---------------+--------------+------+-----+---------+----------------+
| id            | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| hotel_code    | varchar(32)  | YES  | MUL | NULL    |                |
| hotel_name    | varchar(255) | YES  |     | NULL    |                |
| phone         | varchar(32)  | YES  | MUL | NULL    |                |
| mobile        | varchar(32)  | YES  |     | NULL    |                |
| address       | varchar(255) | YES  |     | NULL    |                |
| coord_x       | double       | YES  |     | NULL    |                |
| coord_y       | double       | YES  |     | NULL    |                |
| province_code | varchar(16)  | YES  |     | NULL    |                |
| town_code     | varchar(16)  | YES  |     | NULL    |                |
| area_code     | varchar(16)  | YES  |     | NULL    |                |
| classify_code | varchar(32)  | YES  |     | NULL    |                |
| star_level    | smallint(6)  | YES  |     | NULL    |                |
| business_code | varchar(32)  | YES  |     | NULL    |                |
+---------------+--------------+------+-----+---------+----------------+

mysql> desc t_big_crawl_hotel_info;
+------------+--------------+------+-----+---------+----------------+
| Field      | Type         | Null | Key | Default | Extra          |
+------------+--------------+------+-----+---------+----------------+
| id         | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| hotel_id   | varchar(32)  | YES  |     | NULL    |                |
| hotel_name | varchar(128) | YES  |     | NULL    |                |
| phone      | varchar(32)  | YES  | MUL | NULL    |                |
| mobile     | varchar(32)  | YES  |     | NULL    |                |
| address    | varchar(256) | YES  |     | NULL    |                |
| introduce  | text         | YES  |     | NULL    |                |
| source     | varchar(16)  | YES  |     | NULL    |                |
| hotel_code | varchar(32)  | YES  | MUL | NULL    |                |
+------------+--------------+------+-----+---------+----------------+
```

### 2. 平台

```mysql
mysql> desc web_list;
+---------+--------------+------+-----+---------+-------+
| Field   | Type         | Null | Key | Default | Extra |
+---------+--------------+------+-----+---------+-------+
| id      | int(12)      | NO   | PRI | NULL    |       |
| webname | varchar(255) | NO   |     |         |       |
+---------+--------------+------+-----+---------+-------+

mysql> select * from web_list;
+----+--------------+
| id | webname      |
+----+--------------+
|  0 | 全部         |
|  1 | 携程         |
|  2 | 艺龙         |
|  3 | 美团         |
|  4 | 去哪儿       |
|  5 | 阿里旅行     |
|  6 | 大众点评     |
+----+--------------+
```

### 3. 评论相关

```mysql
mysql> desc hotel_comment;
+----------------+--------------+------+-----+------------+-------+
| Field          | Type         | Null | Key | Default    | Extra |
+----------------+--------------+------+-----+------------+-------+
| id             | bigint(20)   | NO   | PRI | NULL       |       |
| hotelname      | varchar(999) | NO   |     | 0          |       |
| phone          | varchar(200) | NO   | MUL | 000        |       |
| webid          | int(4)       | YES  | MUL | NULL       |       |
| hotelid        | varchar(32)  | YES  |     | NULL       |       |
| username       | varchar(255) | YES  |     | NULL       |       |
| grade          | float        | YES  |     | NULL       |       |
| comment_text   | text         | YES  |     | NULL       |       |
| comment_xml    | text         | YES  |     | NULL       |       |
| comment_time   | date         | NO   |     | 2001-01-01 |       |
| response       | mediumtext   | YES  |     | NULL       |       |
| hasresponse    | tinyint(4)   | NO   |     | 0          |       |
| is_in_main     | tinyint(4)   | NO   |     | 0          |       |
| good_or_bad    | int(1)       | YES  |     | 0          |       |
| is_in_main_bad | int(1)       | YES  |     | 0          |       |
| hotel_grade    | float        | YES  |     | NULL       |       |
+----------------+--------------+------+-----+------------+-------+

mysql> desc hotel_score;
+--------------+-------------+------+-----+---------+----------------+
| Field        | Type        | Null | Key | Default | Extra          |
+--------------+-------------+------+-----+---------+----------------+
| id           | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| comment_id   | bigint(20)  | NO   |     | NULL    |                |
| phone        | varchar(32) | NO   |     | 0000    |                |
| score_target | int(2)      | YES  |     | NULL    |                |
| score        | int(2)      | YES  |     | NULL    |                |
| comment_text | text        | YES  |     | NULL    |                |
+--------------+-------------+------+-----+---------+----------------+
```

### 4. 区域位置

```mysql
mysql> desc t_area;
+------------------+-------------+------+-----+---------+----------------+
| Field            | Type        | Null | Key | Default | Extra          |
+------------------+-------------+------+-----+---------+----------------+
| id               | bigint(20)  | NO   | PRI | NULL    | auto_increment |
| area_code        | varchar(16) | YES  |     | NULL    |                |
| area_parent_code | varchar(16) | YES  |     | NULL    |                |
| area_name        | varchar(32) | YES  |     | NULL    |                |
| area_level       | smallint(6) | YES  |     | NULL    |                |
| isLast           | varchar(1)  | YES  |     | NULL    |                |
| phone_code       | varchar(16) | YES  |     | NULL    |                |
+------------------+-------------+------+-----+---------+----------------+
```

### 4. 日评论分析

```mysql
mysql> desc analysis_day_comment;
+---------------------------+--------------+------+-----+---------+----------------+
| Field                     | Type         | Null | Key | Default | Extra          |
+---------------------------+--------------+------+-----+---------+----------------+
| id                        | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| phone                     | varchar(255) | NO   | MUL | 0000    |                |
| hotelid                   | varchar(32)  | YES  |     | NULL    |                |
| analysis_date             | date         | YES  | MUL | NULL    |                |
| webid                     | int(12)      | NO   | MUL | 0       |                |
| avg_grade                 | float        | YES  |     | 0       |                |
| comment_number            | int(12)      | YES  |     | 0       |                |
| response_number           | int(12)      | YES  |     | 0       |                |
| to_response_number        | int(12)      | YES  |     | 0       |                |
| opinion_number            | int(12)      | YES  |     | 0       |                |
| criticism_number          | int(12)      | YES  |     | 0       |                |
| praise_number             | int(12)      | YES  |     | 0       |                |
| neutral_number            | int(12)      | YES  |     | NULL    |                |
| nocare_number             | int(12)      | YES  |     | NULL    |                |
| praise_response_number    | int(12)      | YES  |     | NULL    |                |
| criticism_response_number | int(12)      | YES  |     | NULL    |                |
| neutral_response_number   | int(12)      | YES  |     | NULL    |                |
| nocare_response_number    | int(12)      | YES  |     | NULL    |                |
| is_in_main_bad            | int(12)      | YES  |     | NULL    |                |
+---------------------------+--------------+------+-----+---------+----------------+

mysql> show create table analysis_day_comment;
| Table                | Create Table                     
| analysis_day_comment | CREATE TABLE `analysis_day_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL DEFAULT '0000' COMMENT '酒店电话',
  `hotelid` varchar(32) DEFAULT NULL COMMENT '酒店id',
  `analysis_date` date DEFAULT NULL COMMENT '分析日期',
  `webid` int(12) NOT NULL DEFAULT '0' COMMENT '对应的网站id',
  `avg_grade` float DEFAULT '0' COMMENT '某天某酒店的用户评分平均值',
  `comment_number` int(12) DEFAULT '0' COMMENT '评论数',
  `response_number` int(12) DEFAULT '0' COMMENT '回复数',
  `to_response_number` int(12) DEFAULT '0' COMMENT '待回复数',
  `opinion_number` int(12) DEFAULT '0' COMMENT '观点数（criticism_number+praise_number+neutral_number）',
  `criticism_number` int(12) DEFAULT '0' COMMENT '批评观点数',
  `praise_number` int(12) DEFAULT '0' COMMENT '表扬观点数',
  `neutral_number` int(12) DEFAULT NULL,
  `nocare_number` int(12) DEFAULT NULL,
  `praise_response_number` int(12) DEFAULT NULL,
  `criticism_response_number` int(12) DEFAULT NULL,
  `neutral_response_number` int(12) DEFAULT NULL,
  `nocare_response_number` int(12) DEFAULT NULL,
  `is_in_main_bad` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `webid` (`webid`) USING BTREE,
  KEY `phone` (`phone`) USING BTREE,
  KEY `date` (`analysis_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1861168 DEFAULT CHARSET=utf8 |

mysql> desc analysis_day_target;
+----------------+--------------+------+-----+---------+----------------+
| Field          | Type         | Null | Key | Default | Extra          |
+----------------+--------------+------+-----+---------+----------------+
| id             | bigint(20)   | NO   | PRI | NULL    | auto_increment |
| phone          | varchar(255) | NO   | MUL | 000     |                |
| hotelid        | varchar(32)  | YES  |     | NULL    |                |
| analysis_date  | date         | YES  |     | NULL    |                |
| webid          | int(12)      | NO   | MUL | 0       |                |
| score          | char(2)      | YES  | MUL | NULL    |                |
| score_target   | int(2)       | YES  | MUL | NULL    |                |
| opinion_number | int(12)      | YES  |     | 0       |                |
+----------------+--------------+------+-----+---------+----------------+
8 rows in set (0.01 sec)

mysql> show create table analysis_day_target;
| Table               | Create Table
| analysis_day_target | CREATE TABLE `analysis_day_target` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) NOT NULL DEFAULT '000' COMMENT '暂时作为酒店的唯一标识',
  `hotelid` varchar(32) DEFAULT NULL COMMENT '预留，暂时不用',
  `analysis_date` date DEFAULT NULL COMMENT '分析对应的日期',
  `webid` int(12) NOT NULL DEFAULT '0' COMMENT '的网站id',
  `score` char(2) DEFAULT NULL COMMENT '1,0 or -1',
  `score_target` int(2) DEFAULT NULL COMMENT '评价目标，对应comment_target表的score_target',
  `opinion_number` int(12) DEFAULT '0' COMMENT '意见数',
  PRIMARY KEY (`id`),
  KEY `score_target` (`score_target`) USING BTREE,
  KEY `webid` (`webid`) USING BTREE,
  KEY `phone` (`phone`) USING BTREE,
  KEY `score` (`score`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6851779 DEFAULT CHARSET=utf8                                         |
```



### 5. 各表大小

```mysql
# hotel_comment                   1517216
# hotel_score                     7636235
# t_hotel_standard                2267218
# t_hotel_standard_new    	       865580
# t_big_crawl_hotel_info          2267218
# t_big_crawl_hotel_info_new      1415354
```

