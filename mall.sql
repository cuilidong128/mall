-- 表的结构 `sys_user`

CREATE TABLE `sys_user` (
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '编号',
  `username` varchar(20) NOT NULL COMMENT '帐号',
  `password` varchar(32) NOT NULL COMMENT '密码MD5(密码+盐)',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐',
  `realname` varchar(20) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `position` varchar(50) DEFAULT NULL COMMENT '职位',
  `dept_id` int(10) UNSIGNED NOT NULL COMMENT '部门',
  `deptName` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `createTime` bigint(20) DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

--3038D9CB63B3152A79B8153FB06C02F7
--66f1b370c660445a8657bf8bf1794486