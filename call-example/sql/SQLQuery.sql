

SELECT
  ((
--国内出厂价
SELECT
  COUNT(*)
FROM lz_Domestic_exfactory_price
WHERE lz_Domestic_exfactory_product_id IN (
  SELECT
    lz_Domestic_exfactory_product_id
  FROM lz_Domestic_exfactory_product
  WHERE product_Id IN (
    SELECT
      ProductID
    FROM Lz_Products
    WHERE ProductName = '苯胺'
  )
))
   +
   --国内成品油价
   (SELECT
  COUNT(*)
    FROM lz_DomesticMarketOilPrice
    WHERE lz_DomesticMarketOilProduct_ID IN (
      SELECT
        lz_DomesticMarketOilProduct_id
      FROM dbo.lz_DomesticMarketOilProduct
      WHERE product_Id IN (
        SELECT
          ProductID
        FROM Lz_Products
        WHERE ProductName = '苯胺'
      )
    ))
   +
   ----国内市场价
   (SELECT
  COUNT(*)
    FROM lz_DomesticMarketPrice
    WHERE lz_DomesticMarketProduct_Id IN (
      SELECT
        lz_DomesticMarketProduct_id
      FROM lz_DomesticMarketProduct
      WHERE product_Id IN (
        SELECT
          product_Id
        FROM LZ_products
        WHERE ProductName = '苯胺'
      )
    ))
   +
   --国际市场价
   (SELECT
  COUNT(*)
    FROM lz_International_market_Price
    WHERE lz_International_market_product_id IN (
      SELECT
        lz_International_market_product_id
      FROM lz_International_market_product
      WHERE product_Id IN (
        SELECT
          ProductID
        FROM Lz_Products
        WHERE ProductName = '苯胺'
      )
    ))
   +
   --国际成品油价
   (SELECT
  COUNT(*)
    FROM dbo.lz_BaseOil_Price
    WHERE lz_BaseOil_product_id IN (
      SELECT
        lz_BaseOil_product_id
      FROM dbo.lz_BaseOil_product
      WHERE product_Id IN (
        SELECT
          ProductID
        FROM Lz_Products
        WHERE ProductName = '苯胺'
      )
    ))
  )

----------------------------------------------------------------------------------------

select
  (SELECT
     COUNT(*)
   FROM lz_Domestic_exfactory_price)
  +
  (SELECT
     COUNT(*)
   FROM lz_DomesticMarketOilPrice)
  +
  (SELECT
     COUNT(*)
   FROM lz_DomesticMarketPrice)
  +
  (SELECT
     COUNT(*)
   FROM lz_International_market_Price)
  +
  (SELECT
     COUNT(*)
   FROM dbo.lz_BaseOil_Price)

--------------------------------------------------------------------------------------
select (
--国内出厂价
select COUNT(t1.lz_Domestic_exfactory_price_id) as num

from lz_Domestic_exfactory_price t1,
  lz_Domestic_exfactory_product t2,
  Lz_Products t3

where t1.lz_Domestic_exfactory_product_id = t2.lz_Domestic_exfactory_product_id
      and t2.product_Id = t3.ProductID and t3.ProductName='苯胺'
       )
       +
       (
--国内成品油价
select COUNT(t1.lz_DomesticMarketOilPrice_ID) as num

from lz_DomesticMarketOilPrice t1,
  lz_DomesticMarketOilProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketOilProduct_id = t2.lz_DomesticMarketOilProduct_ID
      and t2.product_Id = t3.ProductID and t3.ProductName='苯胺'
       )
       +
       (
----国内市场价
select COUNT(t1.lz_DomesticMarketPrice_Id) as num

from lz_DomesticMarketPrice t1,
  dbo.lz_DomesticMarketProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketPrice_Id = t2.lz_DomesticMarketProduct_id
      and t2.product_Id = t3.ProductID and t3.ProductName='苯胺'
       )
       +
       (
--国际市场价
select COUNT(t1.lz_International_market_Price_Id) as num

from lz_International_market_Price t1,
  lz_International_market_product t2,
  Lz_Products t3

where t1.lz_International_market_Price_Id = t2.lz_International_market_product_id
      and t2.product_Id = t3.ProductID and t3.ProductName='苯胺'
       )

       +
       (
--国际成品油价
select COUNT(t1.lz_BaseOil_Price_Id) as num

from lz_BaseOil_Price t1,
  lz_BaseOil_product t2,
  Lz_Products t3

where t1.lz_BaseOil_product_id = t2.lz_BaseOil_product_id
      and t2.product_Id = t3.ProductID and t3.ProductName='苯胺'
)


