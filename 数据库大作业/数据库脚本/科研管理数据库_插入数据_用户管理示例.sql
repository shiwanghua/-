use 科研管理数据库;

-- 强实体集

delete from 研究室;
insert into 研究室 values('软件学院研究室', '研究软件工程'),
						 ('SCUTResIns', '研究理工学'),
                         ('中国科研所', '研究真理'),
                         ('中软','研究中国软件行业发展方向和前景');

delete from 办公场地;
insert into 办公场地 values('软件学院研究室','B7-101','132'),
						  ('SCUTResIns','B7-102','100'),
                          ('中国科研所','B7-103','123');

delete from 秘书;
insert into 秘书 values('0001','秘书名1','男','25','2008-06-06','职责1'),
					   ('0002','秘书名2','女','26','2018-04-23','整理文案'),
                       ('0421','秘书名2','男','28','2015-05-12','写报告');

delete from 科研人员;
insert into 科研人员 values('3002145','石','男','30','职称1','BigData'),
						   ('00123','望','男','18','研究员','数据挖掘'),
                           ('2018','小不点','男','10','天才','量子力学'),
                           ('201630676843','卢研欣','女','18','石大本科生','地质研究');

delete from 科研项目;
insert into 科研项目 values('0001','SRP1','电子元件','20000','2018-06-30','2018-07-13'),
						   ('0002','SRP2','芯片','168000','2018-07-03','2018-07-13'),
                           ('0045','百年计划','社会主义','666666666','1949-10-01','2049-10-01'),
                           ('186681','留学计划','读研','201807','2019-10','2023-10');

delete from 委托方联系人;
insert into 委托方联系人 values();

delete from 委托方负责人;
insert into 委托方负责人 values();

delete from 合作方联系人;
insert into 合作方联系人 values();

delete from 合作方负责人;
insert into 合作方负责人 values();

delete from 质监方联系人;
insert into 质监方联系人 values();

delete from 质监方负责人;
insert into 质监方负责人 values();

-- 弱实体集

delete from 研究成果;
insert into 研究成果 values();

delete from 有项目的科研人员;
insert into 有项目的科研人员 values();

delete from 专利;
insert into 专利 values();

delete from 论文;
insert into 论文 values();

delete from 软件著作权;
insert into 软件著作权 values();

delete from 子课题;
insert into 子课题 values();

-- 联系集

delete from 研究室_秘书服务;
insert into 研究室_秘书服务 values('软件学院研究室','0001'),
								  ('SCUTResIns','0001'),
                                  ('中国科研所','0421'),
                                  ('中软','0002');

delete from 研究室_位置;
insert into 研究室_位置 values();

delete from 研究室_科研人员;
insert into 研究室_科研人员 values('软件学院研究室','3002145');

delete from 研究室_主任;
insert into 研究室_主任 values();

delete from 科研项目_科研人员;
insert into 科研项目_科研人员 values('0001','3002145'),
						            ('0002','3002145'),
                                    ('0045','3002145'),
                                    ('0045','2018'),
                                    ('186681','201630676843');

delete from 贡献人_成果;
insert into 贡献人_成果 values();

delete from 科研人员_子课题;
insert into 科研人员_子课题 values();

delete from 负责_子课题;
insert into 负责_子课题 values();

delete from 负责_项目;
insert into 负责_项目 values();

delete from 委托_负责;
insert into 委托_负责 values();

delete from 合作_负责;
insert into 合作_负责 values();

delete from 质监_负责;
insert into 质监_负责 values();

delete from 委托_联系;
insert into 委托_联系 values();

delete from 合作_联系;
insert into 合作_联系 values();

delete from 质监_联系;
insert into 质监_联系 values();

-- 表总数
select count(*)
from information_schema.tables WHERE TABLE_SCHEMA = '科研管理数据库';

-- 改管理员密码
alter user'root'@'localhost' IDENTIFIED BY '1'; 

-- 删除用户
drop user '科研人员1'@'localhost'; 

-- 创建用户（科研人员）
CREATE USER '科研人员1'@'localhost' IDENTIFIED BY '2';
CREATE USER '卢妍欣'@'localhost' IDENTIFIED BY '2018';
CREATE USER '唐素香'@'localhost' IDENTIFIED BY '2018';

-- 给用户(科研人员）授权
grant select on 科研管理数据库.研究室_秘书服务 to '科研人员1'@'localhost';
grant select,insert,update  on 科研管理数据库.科研人员 to '科研人员1'@'localhost';
grant select on 科研管理数据库.研究室 to '科研人员1'@'localhost';
grant select on 科研管理数据库.科研项目_科研人员 to '科研人员1'@'localhost'; 
grant select on 科研管理数据库.科研项目 to '科研人员1'@'localhost'; 
grant all privileges on * to '卢妍欣'@'localhost'; 
grant all privileges on * to '唐素香'@'localhost'; 

-- 收回用户权限
REVOKE all privileges ON 科研管理数据库.研究室_秘书服务 from '科研人员1'@'localhost';

-- 更新
FLUSH PRIVILEGES; 

                

























