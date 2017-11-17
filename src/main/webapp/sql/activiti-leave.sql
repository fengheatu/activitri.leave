CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `leave_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `days` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `leave_date` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `user_id` bigint(20) DEFAULT NULL,
  `state` int(4) DEFAULT NULL COMMENT ' 0初始录入,1.开始审批,2为审批完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `employee` VALUES (1, '张三', '123', 'zhangsan@163.com', '1', 2);
INSERT INTO `employee` VALUES (2, '李四', '123', 'lisi@163.com', '1', 3);
INSERT INTO `employee` VALUES (3, '王五', '123', 'wangwu@163.com', '1', 4);
INSERT INTO `employee` VALUES (4, '赵六', '123', 'zhaoliu@163.com', '1', 5);
INSERT INTO `employee` VALUES (5, '李七', '123', 'liqi@163.com', '1', 6);

