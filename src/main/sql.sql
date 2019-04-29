DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `visiable` tinyint(1) DEFAULT '1' COMMENT '是否可见(注销)，0代表不可见，1代表可见',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(25) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;



INSERT INTO `user` VALUES ('8', '1', 'string', 'string', 'string');
INSERT INTO `user` VALUES ('11', '1', 'djm', '12334', '156236');
INSERT INTO `user` VALUES ('12', '0', '邓建明', 'd923231006..', '15623666936');
INSERT INTO `user` VALUES ('13', '1', '邓建明1', '123456', '1234567890');
INSERT INTO `user` VALUES ('15', '1', '邓建明', 'string', '15623666936');
