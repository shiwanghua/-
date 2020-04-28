create database 科研管理数据库;
use 科研管理数据库;

-- 强实体集

create table 研究室(名称 varchar(20) primary key,研究方向介绍 varchar(200));

create table 办公场地(所属研究室 varchar(20),
					  constraint CSTR1 foreign key (所属研究室) references 研究室(名称) 
					  ON DELETE CASCADE ON UPDATE CASCADE,
					  地址 varchar(30) primary key, 办公面积 int not null);
                       
create table 秘书(工号 varchar(8) primary key, 姓名 varchar(10) not null,
                  性别 char(1) not null, 年龄 decimal(2,0) not null,
                  聘用时间 varchar(10) not null, 职责 varchar(30));

create table 科研人员(工号 varchar(12) primary key, 姓名 varchar(10) not null,
                      性别 char(1) not null, 年龄 decimal(2,0) not null,
                      职称 varchar(10) not null, 研究方向 varchar(40));
                      
create table 科研项目(项目号 int primary key, 项目名 varchar(20)not null,
                      研究内容 varchar(50), 经费总额 int not null,
                      开工时间 varchar(10), 完成时间 varchar(10));
                      
create table 委托方联系人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null,
						  委托单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

create table 合作方联系人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null, 
						  合作单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

create table 质监方联系人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null, 
						  质监单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

create table 委托方负责人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null, 
						  委托单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

create table 合作方负责人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null, 
						  合作单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

create table 质监方负责人(姓名 varchar(10) primary key, 邮箱 varchar(30) not null, 
						  质监单位 varchar(20) not null, 单位地址 varchar(30) not null,
                          办公电话 int(11), 移动电话 int(11) not null);

-- 弱实体集

create table 子课题(项目号 int,
                    constraint CSTR2 foreign key(项目号) references 科研项目(项目号)
					ON DELETE CASCADE ON UPDATE CASCADE, 序号 tinyint, 
					ddl varchar(10) not null, 可支配经费 int not null, 技术指标 varchar(50), 
                    primary key(项目号,序号));

create table 研究成果(项目号 int, 成果名 varchar(20)not null,
					  constraint CSTR3 foreign key(项目号) references 科研项目(项目号)
					  ON DELETE CASCADE ON UPDATE CASCADE,
                      取得时间 varchar(10)not null, 排名 varchar(50),
                      primary key(项目号,成果名));

create table 有项目的科研人员(工号 varchar(8) primary key,
                              constraint CSTR4 foreign key(工号) references 科研人员(工号)
							  ON DELETE CASCADE ON UPDATE CASCADE, 参加时间 varchar(10),
                              工作量 varchar(30), 可支配经费 int not null);
                              
create table 专利(项目号 int, 成果名 varchar(20) not null, primary key(项目号, 成果名), 
                  constraint CSTR5 foreign key(项目号, 成果名) references 研究成果(项目号, 成果名)
				  ON DELETE CASCADE ON UPDATE CASCADE, 
                  专利名 varchar(20) not null, 说明 varchar(100));
                  
create table 论文(项目号 int, 成果名 varchar(20), primary key(项目号, 成果名),
                  constraint CSTR6 foreign key(项目号, 成果名) references 研究成果(项目号, 成果名)
				  ON DELETE CASCADE ON UPDATE CASCADE, 
                  论文名 varchar(20) not null, 字数 int not null, 说明 varchar(100));
                  
create table 软件著作权(项目号 int, 成果名 varchar(20), primary key(项目号, 成果名),
						constraint CSTR7 foreign key(项目号, 成果名) references 研究成果(项目号, 成果名)
				        ON DELETE CASCADE ON UPDATE CASCADE, 
                        著作名 varchar(20) not null, 出版社 varchar(15) not null, 说明 varchar(100));
                  
-- 联系集

create table 研究室_秘书服务(研究室名称 varchar(20), 秘书工号 varchar(8), primary key(研究室名称),
							 constraint CSTR8 foreign key(研究室名称) references 研究室(名称) 
							 ON DELETE CASCADE ON UPDATE CASCADE,
                             constraint CSTR9 foreign key(秘书工号) references 秘书(工号)
							 ON DELETE CASCADE ON UPDATE CASCADE);
                              
create table 研究室_位置(研究室名称 varchar(20), 地址 varchar(30),
                         constraint CSTR10 foreign key(研究室名称) references 研究室(名称) 
						 ON DELETE CASCADE ON UPDATE CASCADE,
                         constraint CSTR11 foreign key (地址) references 办公场地(地址) 
					     ON DELETE CASCADE ON UPDATE CASCADE,
                         primary key(研究室名称, 地址));
              
create table 研究室_科研人员(研究室名称 varchar(20), 科研人员工号 varchar(8),
							 constraint CSTR12 foreign key(研究室名称) references 研究室(名称) 
						     ON DELETE CASCADE ON UPDATE CASCADE,				
                             constraint CSTR13 foreign key(科研人员工号) references 科研人员(工号)
							 ON DELETE CASCADE ON UPDATE CASCADE,
                             primary key(研究室名称));
                
create table 研究室_主任(研究室名称 varchar(20), 科研人员工号 varchar(8),
                         constraint CSTR14 foreign key(研究室名称) references 研究室(名称) 
					     ON DELETE CASCADE ON UPDATE CASCADE,
                         constraint CSTR15 foreign key(科研人员工号) references 科研人员(工号)
					     ON DELETE CASCADE ON UPDATE CASCADE,
                         上任时间 varchar(10) not null, 任期 varchar(10), primary key(研究室名称));   
            
create table 项目_科研人员(项目号 int, 工号 varchar(12),
                           constraint CSTR16 foreign key(项目号) references 科研项目(项目号)
						   ON DELETE CASCADE ON UPDATE CASCADE,	
						   constraint CSTR17 foreign key(工号) references 科研人员(工号)
					       ON DELETE CASCADE ON UPDATE CASCADE,
                           primary key(项目号,工号));	
	
create table 贡献人_成果(工号 varchar(12),项目号 int,成果名 varchar(20), 
                         constraint CSTR18 foreign key(工号) references 科研人员(工号)
						 ON DELETE CASCADE ON UPDATE CASCADE,
                         constraint CSTR19 foreign key(项目号, 成果名) references 研究成果(项目号, 成果名)
						 ON DELETE CASCADE ON UPDATE CASCADE,
                         primary key(工号, 项目号, 成果名));

create table 科研人员_子课题(工号 varchar(8), 项目号 int, 序号 tinyint,
							 constraint CSTR20 foreign key(工号) references 科研人员(工号)
						     ON DELETE CASCADE ON UPDATE CASCADE,
                             constraint CSTR21 foreign key(项目号, 序号) references 子课题(项目号, 序号)
						     ON DELETE CASCADE ON UPDATE CASCADE,
							 primary key(工号, 项目号));

create table 负责_子课题(工号 varchar(8), 项目号 int, 序号 tinyint,
						 constraint CSTR22 foreign key(工号) references 科研人员(工号)
						 ON DELETE CASCADE ON UPDATE CASCADE,
                         constraint CSTR23 foreign key(项目号, 序号) references 子课题(项目号, 序号)
					     ON DELETE CASCADE ON UPDATE CASCADE,
                         primary key(项目号, 序号));

create table 负责_项目(工号 varchar(8), 项目号 int, 
					   constraint CSTR24 foreign key(工号) references 科研人员(工号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR25 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,	
                       primary key(项目号));

create table 委托_负责(项目号 int, 委托负责人 varchar(10),
					   constraint CSTR26 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR27 foreign key(委托负责人) references 委托方负责人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号));

create table 合作_负责(项目号 int, 合作负责人 varchar(10),
					   constraint CSTR28 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR29 foreign key(合作负责人) references 合作方负责人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号, 合作负责人));

create table 质监_负责(项目号 int, 质监负责人 varchar(10),
					   constraint CSTR30 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR31 foreign key(质监负责人) references 质监方负责人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号));

create table 委托_联系(项目号 int, 委托联系人 varchar(10),
					   constraint CSTR32 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR33 foreign key(委托联系人) references 委托方联系人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号, 委托联系人));

create table 合作_联系(项目号 int, 合作联系人 varchar(10),
					   constraint CSTR34 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
					   constraint CSTR35 foreign key(合作联系人) references 合作方联系人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号, 合作联系人));

create table 质监_联系(项目号 int, 质监联系人 varchar(10),
					   constraint CSTR36 foreign key(项目号) references 科研项目(项目号)
					   ON DELETE CASCADE ON UPDATE CASCADE,
                       constraint CSTR37 foreign key(质监联系人) references 质监方联系人(姓名)
                       ON DELETE CASCADE ON UPDATE CASCADE,
                       primary key(项目号,质监联系人));	

select count(*)
from information_schema.tables WHERE TABLE_SCHEMA = '科研管理数据库';
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      
                      