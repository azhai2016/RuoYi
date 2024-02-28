DROP TABLE IF EXISTS `minids_products`;
CREATE TABLE `minids_products` (
    `saas_id` varchar(255) NOT NULL DEFAULT '88888',
    `store_id` varchar(255) NOT NULL,
    `spu_id` varchar(255) NOT NULL,
    `title` varchar(255) DEFAULT NULL,
    `primary_image` varchar(255) DEFAULT NULL,
    `available` int(11) DEFAULT NULL,
    `min_sale_price` decimal(18, 2) DEFAULT NULL,
    `min_line_price` decimal(18, 2) DEFAULT NULL,
    `max_sale_price` decimal(18, 2) DEFAULT NULL,
    `max_line_price` decimal(18, 2) DEFAULT NULL,
    `spu_stock_quantity` int(11) DEFAULT NULL,
    `sold_num` int(11) DEFAULT NULL,
    `is_put_on_sale` int(11) DEFAULT NULL,
    `_video` varchar(255) DEFAULT '255',
    `_etitle` varchar(255) DEFAULT '255',
    `title_prefix_tags` varchar(255) DEFAULT NULL,
    `thumb` varchar(255) DEFAULT NULL,
    `category_id` varchar(255) DEFAULT NULL,
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    PRIMARY KEY (`saas_id`, `store_id`, `spu_id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
SET FOREIGN_KEY_CHECKS = 1;
-- 分类表
DROP TABLE IF EXISTS `minids_products_category`;
CREATE TABLE `minids_products_category` (
    `category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
    `parent_id` bigint(20) DEFAULT '0' COMMENT '父部门id',
    `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
    `category_name` varchar(30) DEFAULT '' COMMENT '分类名称',
    `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
    `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
    `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 DEFAULT CHARSET = utf8 COMMENT = '商品分类表';
SET FOREIGN_KEY_CHECKS = 1;