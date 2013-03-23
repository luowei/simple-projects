

-- LzProducts 产品表
--
-- VlzDmprice  国内市场价
--
-- VLzIMPrice  国际市场价
--
-- VlzBOPrice 成品油国内市场价
--
-- VlzDeprice  国内出厂价


create table t_user_detail(
    id bigint  PRIMARY KEY IDENTITY(1,1) NOT NULL,
    uid bigint ,
    product_code nvarchar(20) UNIQUE,
    product_name nvarchar(20)
)

-- interface 下的 lz_Interface_User表添加两个字段:
-- start_yearmonth char(7)
-- end_yearmonth char(7)









