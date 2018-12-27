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

ALTER TABLE `sys_user`
  ADD PRIMARY KEY (`user_id`);

ALTER TABLE `sys_user`
  MODIFY `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;

CREATE TABLE `sys_config` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '',
  `key` varchar(20) NOT NULL COMMENT '',
  `value` varchar(100) DEFAULT NULL COMMENT '',
  `status`tinyint(4) DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) DEFAULT NULL COMMENT '简介'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置';

ALTER TABLE `sys_config`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sys_config`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_dept` (
  `dept_id` int(10) UNSIGNED NOT NULL COMMENT '',
  `parent_id` int(10) UNSIGNED NOT NULL COMMENT '',
  `name` varchar(80) NOT NULL COMMENT '',
  `order_num` int(10) DEFAULT NULL COMMENT '',
  `manager` int(10) DEFAULT NULL COMMENT '',
  `del_flag`tinyint(4) DEFAULT NULL COMMENT '状态'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';

ALTER TABLE `sys_dept`
  ADD PRIMARY KEY (`dept_id`);

ALTER TABLE `sys_dept`
  MODIFY `dept_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_dic` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '',
  `name` varchar(80) NOT NULL COMMENT '字典名称',
  `code` varchar(100) DEFAULT NULL COMMENT '字典编码',
  `value` varchar(100) DEFAULT NULL COMMENT '项-值',
  `text` varchar(100) DEFAULT NULL COMMENT '项',
  `type`tinyint(4) DEFAULT NULL COMMENT '类型 0：目录，1：字典项',
  `parent_id` int(10) UNSIGNED NOT NULL COMMENT '上级目录Id',
  `sort_no` int(10) UNSIGNED NOT NULL COMMENT '排序',
  `editable`tinyint(4) DEFAULT NULL COMMENT '是否可编辑。1：是。0：否',
  `status`tinyint(4) DEFAULT NULL COMMENT '状态。1启用。0禁用',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

ALTER TABLE `sys_dic`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sys_dic`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_menu` (
  `menu_id` int(10) UNSIGNED NOT NULL COMMENT '',
  `parent_id` int(10) UNSIGNED NOT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(80) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(100) DEFAULT NULL COMMENT '授权',
  `params` varchar(200) DEFAULT NULL COMMENT '扩展参数'
  `type`  tinyint(4) DEFAULT NULL COMMENT '是否可编辑',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(10) UNSIGNED NOT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='功能菜单';

ALTER TABLE `sys_menu`
  ADD PRIMARY KEY (`menu_id`);

ALTER TABLE `sys_menu`
  MODIFY `menu_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_role` (
  `role_id` int(10) UNSIGNED NOT NULL COMMENT '',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
   `dept_id` int(10) UNSIGNED NOT NULL COMMENT '部门ID',
   `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统角色';

ALTER TABLE `sys_role`
  ADD PRIMARY KEY (`role_id`);

ALTER TABLE `sys_role`
  MODIFY `role_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_role_dept` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '',
   `role_id` int(10) UNSIGNED NOT NULL COMMENT '',
   `dept_id` int(10) UNSIGNED NOT NULL COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与部门';

ALTER TABLE `sys_role_dept`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sys_role_dept`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_role_menu` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '',
   `role_id` int(10) UNSIGNED NOT NULL COMMENT '',
   `menu_id` int(10) UNSIGNED NOT NULL COMMENT '',
    `params` varchar(100) NOT NULL COMMENT '扩展参数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单';

ALTER TABLE `sys_role_menu`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sys_role_menu`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


CREATE TABLE `sys_user_role` (
  `id` int(10) UNSIGNED NOT NULL COMMENT '',
   `role_id` int(10) UNSIGNED NOT NULL COMMENT '',
   `user_id` int(10) UNSIGNED NOT NULL COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色与菜单';

ALTER TABLE `sys_user_role`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `sys_user_role`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '', AUTO_INCREMENT=3;


--3038D9CB63B3152A79B8153FB06C02F7
--66f1b370c660445a8657bf8bf1794486