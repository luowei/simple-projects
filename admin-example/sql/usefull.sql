

/*

数据库字段说明:

-------发票--------
用户名：guestbook[name]
合同编号：guestbook_ht[htbianhao]
开票时间：guestbook_ht_receipt[receiptdate]
收入项目：guestbook_ht_receipt[shouruxianmu]
发票金额：guestbook_ht_receipt[receiptsum]
发票号：guestbook_ht_receipt[sid]
到账日期： guestbook_ht_receipt[receiptdzdate]
款项类型：guestbook_ht[kuanxianglx]     1:应收
发票状态：guestbook_ht_receipt[receipttype]       1:有效 , -1:作废



-------发票明细--------
用户名：guestbook[name]
合同编号：guestbook_ht[htbianhao]  --> guestbook_ht[id] <==> guestbook[id]
开票时间：guestbook_ht_receipt[receiptdate]
收入项目：guestbook_ht_receipt[shouruxianmu]
发票金额：guestbook_ht_receipt[receiptsum]
发票号：guestbook_ht_receipt[sid]
款项类型：guestbook_ht[kuanxianglx]     1:应收

邮寄地址：guestbook_ht_receipt[send_addr]
            guestbook_ht_receipt[sid] <==>  guestbook_ht[sid]
            guestbook_ht_receipt[htbianhao] <==>  guestbook_ht[htbianhao]
            guestbook_ht_receipt[gid] <==>  guestbook[id]

邮寄状态:receipt_type
      0:未寄出
      1:已寄出
      2:已到达
      -1:退回

联系人：guestbook_ht_receipt[send_lxr]     guestbook_lxr[GC_Guestbookid]
电话：guestbook_ht_receipt[send_phone]
手机：guestbook_ht_receipt[send_mobile]
邮寄时间：guestbook_ht_receipt[send_time]
邮寄内容：guestbook_ht_receipt[send_content]

------搜索条件--------
--客户信息
  企业名称：guestbook[qymc]
  用户名:guestbook[name]

  --跟踪人   guestbook[adminid]
--会员类型  guestbook[trial, endtime]


--开票地   guestbook_ht[huikuandi]


--发票号  guestbook_ht_receipt[sid]
  合同编号：guestbook_ht_receipt[htbianhao]

--开票时间  guestbook_ht_receipt[receiptdate]
  起始时间：
  终止时间：

--发票金额  guestbook_ht_receipt[receiptsum]


--款项类型  guestbook_ht_receipt[receipttype] 0:应收,1:到账




--邮寄信息
  邮寄地址   guestbook_ht_receipt[send_addr]
  联系人   guestbook_ht_receipt[send_lxr]
  电话     guestbook_ht_receipt[send_phone]
  手机     guestbook_ht_receipt[send_mobile]
  邮寄时间   guestbook_ht_receipt[send_time]
  邮寄内容    guestbook_ht_receipt[send_content]



*/

create view v_ht_receipt
as
select
guestbook_ht_receipt.id,
guestbook_ht_receipt.gid,
guestbook_ht_receipt.receipt,
guestbook.name,
guestbook.qymc,
guestbook.adminid as trackid,
guestbook.trial,
guestbook.endtime,
guestbook_ht.kuanxianglx,
guestbook_ht.huikuandi,
guestbook_ht.daozhangtime,
guestbook_ht_receipt.htbianhao,
guestbook_ht_receipt.receiptdate,
guestbook_ht_receipt.shouruxianmu,
guestbook_ht_receipt.receiptsum,
guestbook_ht_receipt.sid,
guestbook_ht_receipt.receipttype,
guestbook_ht_receipt.send_type as sendtype,
guestbook_ht_receipt.send_addr as sendaddr,
guestbook_ht_receipt.send_zip as sendzip,
guestbook_ht_receipt.send_lxr as sendlxr,
guestbook_ht_receipt.send_phone as sendphone,
guestbook_ht_receipt.send_mobile as sendmobile,
guestbook_ht_receipt.send_time as sendtime,
guestbook_ht_receipt.send_content as sendcontent
from guestbook,guestbook_ht,guestbook_ht_receipt
where guestbook_ht_receipt.htbianhao = guestbook_ht.htbianhao
and  guestbook_ht_receipt.gid = guestbook.id

