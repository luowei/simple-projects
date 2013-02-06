USE lzlt

GO

CREATE VIEW v_front_judge_price
AS
  SELECT
    pjd.id          AS id,
    pj.id           AS price_judge_id,
    pj.product_name AS product_name,
    pj.model_name   AS model_name,
    pj.area_name    AS area_name,
    pj.unit         AS unit,
    pjd.price_date  AS price_date,
    pjd.judge_price AS price,
    pjd.change_rate AS change_rate
  FROM t_price_judge pj, t_price_judge_detail pjd
  WHERE pj.id = pjd.price_judge_id
        AND pj.product_name IS NOT NULL
        AND pj.model_name IS NOT NULL
        AND pj.area_name IS NOT NULL
        AND pj.is_display =1
        AND pjd.price_date > (getdate() - 30)

GO


--创建视图
CREATE VIEW v_product_price
AS
  SELECT
    prod.lz_DomesticMarketProduct_id AS id,
    prods.ProductName                AS product_name,
    prod.area_Name                   AS area_name,
    prod.market_Name                 AS market_name,
    prod.manufacture_Name            AS manufacture_name,
    pric.Average                     AS price,
    pric.Change_Rate                 AS change_rate,
    pric.price_date                  AS price_date


  FROM lz_DomesticMarketProduct AS prod, lz_DomesticMarketPrice AS pric, LZ_products AS prods

  WHERE isJudge = 1
        AND prod.lz_DomesticMarketProduct_id = pric.lz_DomesticMarketProduct_Id
        AND prod.product_Id = prods.ProductID
        AND pric.price_date > (getdate() - 30)
        AND prods.ProductName IS NOT null
        AND prod.area_Name  IS NOT null
        AND prod.market_Name IS NOT null
        AND pric.Average IS NOT null
        AND pric.Change_Rate  IS NOT null
        AND pric.price_date IS NOT null

-------------------------------------------------------------------------
--石油
CREATE VIEW v_oil_price
AS
  SELECT
    prod.lz_DomesticMarketOilProduct_id AS id,
    prods.ProductName                   AS product_name,
    prod.AreaName                       AS area_name,
    prod.MarketName                     AS market_name,


    pric.Price_CNPC                     AS price,
    pric.Change_Rate                    AS change_rate,
    pric.PriceDate                      AS price_date


  FROM lz_DomesticMarketOilProduct AS prod, lz_DomesticMarketOilPrice AS pric, LZ_products AS prods

  WHERE isJudge = 1
        AND prod.lz_DomesticMarketOilProduct_id = pric.lz_DomesticMarketOilProduct_ID
        AND prod.product_Id = prods.ProductID
        AND pric.PriceDate > (getdate() - 30)
        AND product_name IS NOT null
        AND area_name IS NOT null
        AND market_name IS NOT null
        AND price IS NOT null
        AND change_rate IS NOT null
        AND price_date IS NOT null




--一般产品
--====================================================================================
--日期加一年
UPDATE lz_DomesticMarketPrice
SET price_date = DATEADD(YEAR, 1, price_date)
WHERE price_date < (getdate() - 30)
      AND price_date > DATEADD(MONTH, -1, DATEADD(YEAR, -1, getdate()))

-------------------------------------------------------------
--更新isJudge
UPDATE lz_DomesticMarketProduct
SET isJudge = 1

WHERE lz_DomesticMarketProduct_id

      IN (

  SELECT
    prod.lz_DomesticMarketProduct_id


  FROM lz_DomesticMarketProduct AS prod, lz_DomesticMarketPrice AS pric, LZ_products AS prods

  WHERE isJudge = 0
        AND prod.lz_DomesticMarketProduct_id = pric.lz_DomesticMarketProduct_Id
        AND prod.product_Id = prods.ProductID
        AND pric.price_date > (getdate() - 30)

)

--石油产品
--======================================================================================
--日期加一年
UPDATE lz_DomesticMarketOilPrice
SET PriceDate = DATEADD(YEAR, 1, PriceDate)
WHERE PriceDate < (getdate() - 30)
      AND PriceDate > DATEADD(MONTH, -1, DATEADD(YEAR, -1, getdate()))

-------------------------------------------------------------
--更新isJudge
UPDATE lz_DomesticMarketOilProduct
SET isJudge = 1

WHERE lz_DomesticMarketOilProduct_ID

      IN (

  SELECT
    prod.lz_DomesticMarketOilProduct_id


  FROM lz_DomesticMarketOilProduct AS prod, lz_DomesticMarketOilPrice AS pric, LZ_products AS prods

  WHERE isJudge = 0
        AND prod.lz_DomesticMarketOilProduct_id = pric.lz_DomesticMarketOilProduct_ID
        AND prod.product_Id = prods.ProductID
        AND pric.PriceDate > (getdate() - 30)
)



--======================================================================================
--======================================================================================
UPDATE lz_DomesticMarketProduct
SET isJudge = 1
WHERE lz_DomesticMarketProduct_id = 1824211
GO


--更新默认值
----------------------------------------------------------------------------------------
ALTER TABLE [dbo].[lz_DomesticMarketProduct] ADD DEFAULT ((0)) FOR [isJudge]
GO
ALTER TABLE [dbo].[lz_DomesticMarketOilProduct] ADD DEFAULT ((0)) FOR [isJudge]
GO

UPDATE lz_DomesticMarketProduct
SET isJudge = 0
GO
UPDATE lz_DomesticMarketOilProduct
SET isJudge = 0


--分页查询
--=========================================================================
--1.使用临时表
SELECT
  TOP 100 payId
INTO #tmpTable
FROM view_customerPayDetails
ORDER BY payId DESC
SELECT
  TOP 100 *
FROM view_customerPayDetails
WHERE payId NOT IN (SELECT
                      payId
                    FROM #tmpTable)
ORDER BY payId DESC
DROP TABLE #tmpTable

--2.使用row_number函数
SELECT
  *
FROM (
       SELECT
         *,
         ROW_NUMBER()
         OVER (
           ORDER BY price_date DESC) AS row_number
       FROM v_product_price vpp
     ) product_price
WHERE row_number > 1000
      AND row_number <= 1100
ORDER BY price_date DESC



--周涨幅
select top 10
  product_name,area_name,market_name,manufacture_name,price,change_rate,price_date
from v_product_price
where price_date > (getdate()-7)
      and change_rate > 0
order by change_rate desc ;

GO

--周跌幅
select top 10
  product_name,area_name,market_name,manufacture_name,price,change_rate,price_date
from v_product_price
where price_date > (getdate()-7)
      and change_rate < 0
order by change_rate asc ;
GO


