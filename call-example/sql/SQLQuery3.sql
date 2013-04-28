select t4.ProductName,SUM(t4.num) as num

from (

--国内出厂价
select COUNT(t1.lz_Domestic_exfactory_price_id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_Domestic_exfactory_price t1,
  lz_Domestic_exfactory_product t2,
  Lz_Products t3

where t1.lz_Domestic_exfactory_product_id in(t2.lz_Domestic_exfactory_product_id)
      and t2.product_Id in(t3.ProductID)
group by t3.ProductName,t2.product_Model_Id

union
--国内成品油价
select COUNT(t1.lz_DomesticMarketOilPrice_ID) as num,
       t3.ProductName as ProductName,
       t2.ProductModelID as model_id

from lz_DomesticMarketOilPrice t1,
  lz_DomesticMarketOilProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketOilProduct_id in(t2.lz_DomesticMarketOilProduct_ID)
      and t2.product_Id in(t3.ProductID)
group by t3.ProductName,t2.ProductModelID

union
----国内市场价
select COUNT(t1.lz_DomesticMarketPrice_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_DomesticMarketPrice t1,
  dbo.lz_DomesticMarketProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketPrice_Id in(t2.lz_DomesticMarketProduct_id)
      and t2.product_Id in(t3.ProductID)
group by t3.ProductName,t2.product_Model_Id

union
--国际市场价
select COUNT(t1.lz_International_market_Price_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_International_market_Price t1,
  lz_International_market_product t2,
  Lz_Products t3

where t1.lz_International_market_Price_Id in(t2.lz_International_market_product_id)
      and t2.product_Id in(t3.ProductID)
group by t3.ProductName,t2.product_Model_Id

union
--国际成品油价
select COUNT(t1.lz_BaseOil_Price_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_BaseOil_Price t1,
  lz_BaseOil_product t2,
  Lz_Products t3

where t1.lz_BaseOil_product_id in(t2.lz_BaseOil_product_id)
      and t2.product_Id in(t3.ProductID)
group by t3.ProductName,t2.product_Model_Id

     ) as t4
group by t4.ProductName
order by t4.ProductName

---------------------------------------------------------------------------------------------------
select t4.ProductName,SUM(t4.num) as num

from (

--国内出厂价
select COUNT(t1.lz_Domestic_exfactory_price_id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_Domestic_exfactory_price t1,
  lz_Domestic_exfactory_product t2,
  Lz_Products t3

where t1.lz_Domestic_exfactory_product_id = t2.lz_Domestic_exfactory_product_id
      and t2.product_Id = t3.ProductID
group by t3.ProductName,t2.product_Model_Id

union
--国内成品油价
select COUNT(t1.lz_DomesticMarketOilPrice_ID) as num,
       t3.ProductName as ProductName,
       t2.ProductModelID as model_id

from lz_DomesticMarketOilPrice t1,
  lz_DomesticMarketOilProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketOilProduct_id = t2.lz_DomesticMarketOilProduct_ID
      and t2.product_Id = t3.ProductID
group by t3.ProductName,t2.ProductModelID

union
----国内市场价
select COUNT(t1.lz_DomesticMarketPrice_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_DomesticMarketPrice t1,
  dbo.lz_DomesticMarketProduct t2,
  Lz_Products t3

where t1.lz_DomesticMarketPrice_Id = t2.lz_DomesticMarketProduct_id
      and t2.product_Id = t3.ProductID
group by t3.ProductName,t2.product_Model_Id

union
--国际市场价
select COUNT(t1.lz_International_market_Price_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_International_market_Price t1,
  lz_International_market_product t2,
  Lz_Products t3

where t1.lz_International_market_Price_Id = t2.lz_International_market_product_id
      and t2.product_Id = t3.ProductID
group by t3.ProductName,t2.product_Model_Id

union
--国际成品油价
select COUNT(t1.lz_BaseOil_Price_Id) as num,
       t3.ProductName as ProductName,
       t2.product_Model_Id as model_id

from lz_BaseOil_Price t1,
  lz_BaseOil_product t2,
  Lz_Products t3

where t1.lz_BaseOil_product_id = t2.lz_BaseOil_product_id
      and t2.product_Id = t3.ProductID
group by t3.ProductName,t2.product_Model_Id

     ) as t4
group by t4.ProductName
order by t4.ProductName



