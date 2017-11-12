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
