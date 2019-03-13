show databases;

select * from SKV where 1>0 limit 10 offset 0;
select distinct SKEY,count(*) from SKV group by SKEY limit 10;

begin,commit,rollback,savepoint id,rollback to id

--创建索引
CREATE INDEX SKEY_INDEX ON SKV(SKEY(16)); 

--备份
BEGIN;
CREATE TABLE SKV_BACKUP LIKE SKV;
INSERT INTO SKV_BACKUP SELECT * FROM SKV;
COMMIT;

--查询重复
select count(*) as repetitions ,SKEY,SVALUE
from SKV
group by SVALUE
having repetitions >1;

--保存数据
select * from SKV 
into OUTFILE  'C:\Users\Tsual\Desktop\text.txt';

--导入和导出数据库
mysqldump -uroot -p db0 > C:\Users\Tsual\Desktop\text.txt
mysql -uroot -p123456 < C:\Users\Tsual\Desktop\text.txt