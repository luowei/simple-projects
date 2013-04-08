--创建估价视图
CREATE VIEW v_manage_price_judge
AS
  SELECT
    pjd.id          AS id,
    pj.id           AS price_judge_id,
    pj.product_name AS product_name,
    pj.model_name   AS model_name,
    pj.area_name    AS area_name,
    pj.unit         AS unit,
    pj.is_display   AS is_display,
    pjd.price_date  AS price_date,
    pjd.judge_price AS judge_price,
    pjd.change_rate AS change_rate,
    pjd.admin_id    AS admin_id,
    pjd.modify_date AS modify_date
  FROM t_price_judge pj, t_price_judge_detail pjd
  WHERE pj.id = pjd.price_judge_id
        AND pj.product_name IS NOT NULL
        AND pj.model_name IS NOT NULL
        AND pj.area_name IS NOT NULL

--创建成交价视图
CREATE VIEW v_manage_price_trade
AS
  SELECT
    ptd.id          AS id,
    pt.id           AS price_trade_id,
    pt.product_name AS product_name,
    pt.model_name   AS model_name,
    pt.area_name    AS area_name,
    pt.market_name  AS market_name,
    pt.trader_name  AS trader_name,
    pt.unit         AS unit,
    pt.is_display   AS is_display,
    ptd.price_date  AS price_date,
    ptd.trade_price AS trade_price,
    ptd.trade_num   AS trade_num,
    ptd.admin_id    AS admin_id,
    ptd.modify_date AS modify_date
  FROM t_price_trader pt, t_price_trader_detail ptd
  WHERE pt.id = ptd.price_trader_id
        AND pt.product_name IS NOT null
        AND pt.model_name IS NOT null
        AND pt.area_name IS NOT null
        AND pt.market_name IS NOT null
        AND pt.trader_name IS NOT null



--添加登录用户
INSERT INTO lz_Staff
  SELECT

    '168',
    'lzluowei',
    '3B6F4BF5DE5AA5234E8A7E4C155561F0',
    [lz_Staff_RealName],
    [lz_Staff_Sex],
    [lz_Staff_BirthDay],
    [lz_Staff_EMail],
    [lz_Staff_Phone],
    [lz_Staff_Mobile],
    [lz_Staff_RegDate],
    [lz_Staff_Separation],
    [lz_Staff_LastLoginDate],
    [lz_Staff_LastLoginIp],
    [lz_Staff_LoginCount],
    [lz_Staff_Memo],
    [lz_Staff_DeptId],
    [lz_Staff_PopeDomId],
    [lz_Staff_ManageLevel],
    [lz_Staff_MaxSMS],
    [lz_Staff_QXTAccount],
    [lz_Staff_QXTMobile],
    [lz_Staff_QXTSendToMobile],
    [lz_Staff_TmpPwd],
    [lz_Staff_LastMac],
    [lz_Staff_LastMachine],
    [lz_Staff_EnableLogin],
    [lz_Staff_IsBindHost],
    [lz_Staff_BindHostList],
    [lz_Staff_OtherIp],
    [lz_Staff_MachineCode],
    [lz_Staff_IsSupper]
  FROM lz_Staff
  WHERE [lz_Staff_LoginName] = 'lzliujun';

--插入t_price_judge估价字典数据
INSERT INTO t_price_judge (
  product_id,
  model_id,
  product_name,
  model_name,
  area_name,
  is_display,
  unit,
  modify_date,
  admin_id
)
  SELECT
    product_id + 5,
    model_id + 5,
    product_name + '5',
    model_name + '5',
    area_name + '5',
    is_display,
    unit,
    GETDATE(),
    admin_id
  FROM t_price_judge;

--插入t_price_judge_detail估价数据
INSERT INTO t_price_judge_detail (
  price_date, price_judge_id, judge_price, change_rate, memo
)
  SELECT
    GETDATE(),
    1,
    128.88,
    0.0,
    null

---------
 --插入数据
INSERT INTO t_price_judge_detail (
  price_date, price_judge_id, judge_price, change_rate, memo
)
  SELECT
    GETDATE(),
    price_judge_id + 1,
    judge_price - 15000,
    change_rate - 0.15,
    null
  FROM t_price_judge_detail

INSERT INTO t_price_trader_detail (
  price_date, price_trader_id, trade_price, trade_num, memo
)
  SELECT
    GETDATE(),
    price_trader_id + 1,
    trade_price - 15000,
    trade_num + 1,
    null
  FROM t_price_trader_detail

--更新字典表
update t_price_trader_detail set price_date=
  case datediff(day,price_date,'2013-10-25')
  when 0 then  price_date
  else price_date
  end

--删除估价字典唯一约束
ALTER TABLE t_price_judge
DROP CONSTRAINT uniqueindex_t_price_judge

--建立估价字典唯一约束
alter table t_price_judge add constraint uniqueindex_t_price_judge
unique(product_name,model_name,area_name,unit)
GO

--删除成交价字典唯一约束
ALTER TABLE t_price_trader
DROP CONSTRAINT uniqueindex_t_price_trader

--建立成交价字典唯一约束
alter table t_price_trader add constraint uniqueindex_t_price_trader
unique(product_name,model_name,area_name,market_name,trader_name,unit)
GO








