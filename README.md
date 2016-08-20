# jfm-android
Just Fast Model For Android

-> 配置 ApiName

修改 pom.xml 中的 apiname;

-> 生成 *-api-version.jar

在 pom.xml 文件目录下 mvn install 即可!

-> 配置数据库

```bash
db.mysql.url = 192.168.1.250/zcq
db.mysql.user = root
db.mysql.password = lCzd9geWAuAuJtLhpaG/J+d28H8KiMFAWopxXkYpMNdUai6Xe/LsPqMQeg5MIrmvtMa+hzycdRhWs29ZUPU1IQ==
```

-> 配置 model 生成

```bash
ge.dict=false
ge.model.dao=false
ge.model.table=true
ge.mappingarpkit=false
ge.tablemapping=true
ge.mappingkit.classname=GalaxyTableMappingKit
ge.base.model.outdir=./src/test/java/com/ikaihuo/gp/subsystem/api/base/model
ge.base.model.package=com.ikaihuo.gp.subsystem.api.base.model
ge.model.outdir=./src/test/java/com/ikaihuo/gp/subsystem/api/model
ge.model.package=com.ikaihuo.gp.subsystem.api.model
```

- 将以上"subsystem"修改成对应的子系统 name, outdir中的"test"改成"main"即可;
- 生成 Model 方式, 使用 JF-ext2的 Ge或执行 com.ikaihuo.main.Main;
- 依赖 com.jfinal.ext2 : 2.0.8

-> 使用方式

```java
User user = new User();
//业务设置用户的 id 为123
user.setId(new BigInteger("123"));
System.out.println("业务设置用户的 id 为123=>"+user.efficientAttrs());
System.out.println("业务设置用户的 id 为123=>"+JsonKit.toJson(user.efficientAttrs()));
//DC查询 id=11的用户
user.setId(Match.EQ, new BigInteger("11"));
System.out.println("DC查询 id=11的用户=>"+user.dcExport());
System.out.println("DC查询 id=11的用户=>"+JsonKit.toJson(user.dcExport()));
//DC查询 id 在12到15范围的用户
user.setIdScope(new BigInteger("12"), new BigInteger("15"));
System.out.println("DC查询 id 在12到15范围的用户=>"+user.dcExport());
System.out.println("DC查询 id 在12到15范围的用户=>"+JsonKit.toJson(user.dcExport()));
//业务设置用户的name = zcq 
user.setName("zcq");
System.out.println("业务设置用户的name = zcq=>"+user.efficientAttrs());
System.out.println("业务设置用户的name = zcq=>"+JsonKit.toJson(user.efficientAttrs()));
//DC查询 name = zcq 的用户
user.setName(Match.EQ, "zcq");
System.out.println("DC查询 name = zcq 的用户=>"+user.dcExport());
System.out.println("DC查询 name = zcq 的用户=>"+JsonKit.toJson(user.dcExport()));
//DC模糊查询name为zcq 的用户 
user.setName(Match.LIKE, "zcq");
System.out.println("DC模糊查询name为zcq 的用户=>"+user.dcExport());
System.out.println("DC模糊查询name为zcq 的用户=>"+JsonKit.toJson(user.dcExport()));
user = new User();
user.setId(new BigInteger("11"));
user.setName(Match.NEQ, "zcq");
user.setOutput("id", "cccccid");
System.out.println("DC不相等查询name为zcq 的用户=>"+user.dcExport());
System.out.println("DC不相等查询name为zcq 的用户=>"+JsonKit.toJson(user.dcExport()));
System.out.println("DC不相等查询name为zcq 的用户EffectiveAttr=>"+JsonKit.toJson(user.efficientAttrs()));
```

输出

```java
业务设置用户的 id 为123=>com.ikaihuo.monkey.model.User@d60 {id:123}
业务设置用户的 id 为123=>{"id":123}
DC查询 id=11的用户=>{id=11, __dc_sql__={id== ?, __dc_sql_ops__={}, __output__=`id`, __output_col__=id, __as__=id, __dc_sql_params__={id=11}, __t__=user}}
DC查询 id=11的用户=>{"id":11,"__dc_sql__":{"id":"= ?","__dc_sql_ops__":{},"__output__":"`id`","__output_col__":"id","__as__":"id","__dc_sql_params__":{"id":"11"},"__t__":"user"}}
DC查询 id 在12到15范围的用户=>{id=11, __dc_sql__={id=BETWEEN ? AND ?, __dc_sql_ops__={}, __output__=`id`, __output_col__=id, __as__=id, __dc_sql_params__={id=12:15}, __t__=user}}
DC查询 id 在12到15范围的用户=>{"id":11,"__dc_sql__":{"id":"BETWEEN ? AND ?","__dc_sql_ops__":{},"__output__":"`id`","__output_col__":"id","__as__":"id","__dc_sql_params__":{"id":"12:15"},"__t__":"user"}}
业务设置用户的name = zcq=>com.ikaihuo.monkey.model.User@32b9f3 {id:11, name:zcq}
业务设置用户的name = zcq=>{"id":11,"name":"zcq"}
DC查询 name = zcq 的用户=>{id=11, name=zcq, __dc_sql__={id=BETWEEN ? AND ?, __dc_sql_ops__={}, __output__=`id`, __output_col__=id, name== ?, __as__=id, __dc_sql_params__={id=12:15, name=zcq}, __t__=user}}
DC查询 name = zcq 的用户=>{"id":11,"name":"zcq","__dc_sql__":{"id":"BETWEEN ? AND ?","__dc_sql_ops__":{},"__output__":"`id`","__output_col__":"id","name":"= ?","__as__":"id","__dc_sql_params__":{"id":"12:15","name":"zcq"},"__t__":"user"}}
DC模糊查询name为zcq 的用户=>{id=11, name=zcq, __dc_sql__={id=BETWEEN ? AND ?, __dc_sql_ops__={}, __output__=`id`, __output_col__=id, name=LIKE ?, __as__=id, __dc_sql_params__={id=12:15, name=zcq}, __t__=user}}
DC模糊查询name为zcq 的用户=>{"id":11,"name":"zcq","__dc_sql__":{"id":"BETWEEN ? AND ?","__dc_sql_ops__":{},"__output__":"`id`","__output_col__":"id","name":"LIKE ?","__as__":"id","__dc_sql_params__":{"id":"12:15","name":"zcq"},"__t__":"user"}}
DC不相等查询name为zcq 的用户=>{id=11, __output_col__=id, name=zcq, __as__=cccccid, __dc_sql__={__dc_sql_ops__={}, __output__=`id` AS cccccid, __output_col__=id, name=!= ?, __as__=cccccid, __dc_sql_params__={name=zcq}, __t__=user}, __t__=user}
DC不相等查询name为zcq 的用户=>{"id":11,"__output_col__":"id","name":"zcq","__as__":"cccccid","__dc_sql__":{"__dc_sql_ops__":{},"__output__":"`id` AS cccccid","__output_col__":"id","name":"!= ?","__as__":"cccccid","__dc_sql_params__":{"name":"zcq"},"__t__":"user"},"__t__":"user"}
DC不相等查询name为zcq 的用户EffectiveAttr=>{"id":11,"name":"zcq"}
```

-> Clear Field

See: com.ikaihuo.jfm.testing.ModelClearTest.java

-> 使用 DC 打印 Sql

```java
dc.showSql(true);
```

输出
```sql
SubSysName=>Galaxy-Test-SubSystem😄️
SQL Index=>0
SQL Type=>DELETE
SQL=>️ { DELETE FROM `user` WHERE `id` = ? OR `name` = ?; } params=[11, zcq]️
```

-> QueryOut

```java
System.out.println(queryOut);
```

输出

```java
QueryDataCount =>120
QueryOutData =>
[
com.ikaihuo.monkey.model.Profile@9ded17d6 {__output_col__:id, addr0:BeiJing0, __as__:id, name0:BruceZCQ0, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17c8 {addr1:BeiJing1, __output_col__:id, __as__:id, name1:BruceZCQ1, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17ca {addr2:BeiJing2, __output_col__:id, __as__:id, name2:BruceZCQ2, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17d0 {name3:BruceZCQ3, __output_col__:id, addr3:BeiJing3, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17ce {name4:BruceZCQ4, __output_col__:id, addr4:BeiJing4, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17d0 {name5:BruceZCQ5, addr5:BeiJing5, __output_col__:id, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17da {name6:BruceZCQ6, addr6:BeiJing6, __output_col__:id, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17d8 {addr7:BeiJing7, __output_col__:id, name7:BruceZCQ7, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17d6 {addr8:BeiJing8, __output_col__:id, name8:BruceZCQ8, __as__:id, __t__:profile}, 
com.ikaihuo.monkey.model.Profile@9ded17c8 {name9:BruceZCQ9, __output_col__:id, __as__:id, addr9:BeiJing9, __t__:profile}
]
```

-> DC: query sort

```java
String[] orders = model.get(Consts.ORDER_KEY);
```

-> DC: MyDC extends DC

```java
package com.ikaihuo.jfm.testing;

import org.springframework.stereotype.Component;

import com.ikaihuo.galaxy.dc.DC;

/**
 * @author BruceZCQ
 */
@Component
public class MyDC extends DC {
	
	public MyDC() {
		this.idc = new RemoteDCProxy();
	}

	@Override
	protected String subSysName() {
		return "Galaxy-Test-SubSystem😄";
	}

}
```

-> DC-SQL: Use DC

```java
MyDC dc = new MyDC();

User u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");
dc.delete(u);

Profile p = new Profile();
p.setId(new BigInteger("12"));
p.setInfo("information");
p.setUid(new BigInteger("1111"));
dc.delete(p);

p = new Profile();
p.setId(new BigInteger("12"));
p.setInfo("information");
p.setUid(new BigInteger("1111"));
dc.query(p);

u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");
dc.update(u);

p = new Profile();
p.setId(new BigInteger("12"));
p.setInfo("information");
dc.update(p);

u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");

p = new Profile();
p.setInfo("information");
dc.delete(u, p);

User uu = new User();
uu.setName("zcq");
boolean ret = dc.insert(uu);
System.out.println("😋Insert User =>"+uu +", result =>"+ret);

uu = new User();
uu.setName("zcq");
List<Object> ids = dc.save(uu);
//put id for yourself
uu.setId(new BigInteger(ids.get(0).toString()));
System.out.println("😋Saved User =>"+uu+", Ids =>"+ids);

p = new Profile();
p.setInfo("information");
dc.save(p);

uu = new User();
uu.setId(new BigInteger("11"));
uu.setName("zcq");
dc.query(uu);

uu = new User();
uu.setId(new BigInteger("11"));
uu.setName(Match.BW("a", "b"));
dc.query(uu);

uu = new User();
uu.setId(Match.BW(new BigInteger("11"), new BigInteger("144")));
dc.query(uu);

// return User
uu = new User();
uu.setName("zcq");
p = new Profile();
p.setInfo("information");
QueryOut queryOut = dc.query(uu, p);
List<User> users = queryOut.getQueryData();
for (int i = 0; i < users.size(); i++) {
	System.out.println("toJson=>" + JsonKit.toJson(users.get(i)));
	System.out.println("Model=>" + users.get(i));
	System.out.println("Class=>" + users.get(i).getClass());
}
System.out.println("QueryData Count=>"+queryOut.getQueryCount());

// return Profile
uu = new User();
uu.setName("zcq");
uu.setOutput("id", "cccccid");
Map<?, ?> map = (Map<?, ?>)uu.dcExport().get(Consts.ATTR_KEY);
System.out.println("SqlOutputColumn logic1=>"+map.get(Consts.OUTPUT_COLUMN_KEY)+"\nSqlOutputColumn logic2=>"+uu.getStr(Consts.OUTPUT_COLUMN_KEY));
System.out.println("SqlAS logic1=>"+map.get(Consts.AS_KEY)+"\nSqlAS logic2=>"+uu.getStr(Consts.AS_KEY));
p = new Profile();
p.setInfo("information");
queryOut = dc.query(p, uu);
List<Profile> profiles = queryOut.getQueryData();
for (int i = 0; i < profiles.size(); i++) {
	System.out.println("toJson=>" + JsonKit.toJson(profiles.get(i)));
	System.out.println("Model=>" + profiles.get(i));
	System.out.println("Class=>" + profiles.get(i).getClass());
}
System.out.println("QueryData Count=>"+queryOut.getQueryCount());
```

-> DC-SQL: Use Model

```java
MyDC idc = new MyDC();

User u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");
u.selectDC(idc);
u.delete();

Profile p = new Profile();
p.setId(new BigInteger("12"));
p.setInfo("information");
p.setUid( new BigInteger("1111"));
p.selectDC(idc);
p.delete();

p = new Profile();
p.setId( new BigInteger("12"));
p.setInfo("information");
p.setUid( new BigInteger("1111"));
p.selectDC(idc);
p.query();

u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");
u.selectDC(idc);
u.update();

p = new Profile();
p.setId(new BigInteger("12"));
p.setInfo("information");
p.selectDC(idc);
p.update();

u = new User();
u.setId(new BigInteger("11"));
u.setName("zcq");
u.selectDC(idc);
u.delete();

p = new Profile();
p.setInfo("information");
p.selectDC(idc);
p.delete();

User uu = new User();
uu.setName("zcq");
uu.selectDC(idc);
boolean ret = uu.insert();
System.out.println("😋Insert User =>" + uu + ", result =>" + ret);

uu = new User();
uu.setName("zcq");
uu.selectDC(idc);
Object id = uu.save();
// put id for yourself
uu.setId(new BigInteger(id.toString()));
System.out.println("😋Saved User =>" + uu + ", Id =>" + id);

p = new Profile();
p.setInfo("information");
p.selectDC(idc);
p.save();

uu = new User();
uu.setId(new BigInteger("11"));
uu.setName("zcq");
uu.selectDC(idc);
uu.query();

uu = new User();
uu.setId(new BigInteger("11"));
uu.setName("zcq");
uu.setName(Match.BW("a", "b"));
uu.selectDC(idc);
uu.query();

uu = new User();
uu.setId(Match.BW(new BigInteger("11"), new BigInteger("144")));
uu.selectDC(idc);
uu.query();
```

模拟查询找到10条匹配数据

```java
	@Override
	public List<Map<String, Object>> query(String subSysName, Object... datas) {
		System.out.println("invoke query SubSysName \""+subSysName+"\"");
		
		for (int i = datas.length-1; i >= 0 ; i--) {
			this.printSql(i, "query", SqlKit.query(datas[i]));
		}
		
		List<Map<String, Object>> retData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> testObj = new HashMap<String, Object>();
			testObj.put("name"+i, "BruceZCQ"+i);
			testObj.put("addr"+i, "BeiJing"+i);
			retData.add(testObj);
		}
		return retData;
	}
```

输出

```sql
invoke delete SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋delete😋 ➡️ ➡️ ➡️ ➡️ { DELETE FROM `user` WHERE `id` = ? AND `name` = ? } params=[11, zcq]⬅️ ⬅️ ⬅️ ️
invoke delete SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋delete😋 ➡️ ➡️ ➡️ ➡️ { DELETE FROM `profile` WHERE `id` = ? AND `uid` = ? AND `info` = ? } params=[12, 1111, information]⬅️ ⬅️ ⬅️ ️
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `profile` WHERE `id` = ? AND `uid` = ? AND `info` = ? } params=[12, 1111, information]⬅️ ⬅️ ⬅️ ️
invoke update SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋update😋 ➡️ ➡️ ➡️ ➡️ { UPDATE `user` SET `name` = ? WHERE `id` = ? } params=[zcq, [Ljava.lang.Object;@57dbe098]⬅️ ⬅️ ⬅️ ️
invoke update SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋update😋 ➡️ ➡️ ➡️ ➡️ { UPDATE `profile` SET `info` = ? WHERE `id` = ? } params=[information, [Ljava.lang.Object;@24764838]⬅️ ⬅️ ⬅️ ️
invoke delete SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋delete😋 ➡️ ➡️ ➡️ ➡️ { DELETE FROM `user` WHERE `id` = ? AND `name` = ? } params=[11, zcq]⬅️ ⬅️ ⬅️ ️
️sql index 1 Type 😋delete😋 ➡️ ➡️ ➡️ ➡️ { DELETE FROM `profile` WHERE `info` = ? } params=[information]⬅️ ⬅️ ⬅️ ️
invoke save SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋save😋 ➡️ ➡️ ➡️ ➡️ { INSERT INTO `user` (`name`) VALUES (?) } params=[zcq]⬅️ ⬅️ ⬅️ ️
😋Insert User =>com.ikaihuo.monkey.model.User@3338daf4 {id:100, __dc_as__:id, __dc_attr__:{id= = ?, __dc_as__=id, __dc_output__=`id`, name= = ?, __dc_attr_params__={id=[Ljava.lang.Object;@517f673b, name=[Ljava.lang.Object;@3527c4a}, __dc_attr_ops__={}, __t__=user, __dc_output_col__=id}, __dc_output__:`id`, name:zcq, __t__:user, __dc_output_col__:id}, result =>true
invoke save SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋save😋 ➡️ ➡️ ➡️ ➡️ { INSERT INTO `user` (`name`) VALUES (?) } params=[zcq]⬅️ ⬅️ ⬅️ ️
😋Saved User =>com.ikaihuo.monkey.model.User@93961abb {id:100, __dc_as__:id, __dc_attr__:{id= = ?, __dc_as__=id, __dc_output__=`id`, name= = ?, __dc_attr_params__={id=[Ljava.lang.Object;@1c5ddffa, name=[Ljava.lang.Object;@571ba4a2}, __dc_attr_ops__={}, __t__=user, __dc_output_col__=id}, __dc_output__:`id`, name:zcq, __t__:user, __dc_output_col__:id}, Ids =>[100]
invoke save SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋save😋 ➡️ ➡️ ➡️ ➡️ { INSERT INTO `profile` (`info`) VALUES (?) } params=[information]⬅️ ⬅️ ⬅️ ️
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `user` WHERE `id` = ? AND `name` = ? } params=[11, zcq]⬅️ ⬅️ ⬅️ ️
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `user` WHERE `id` = ? AND `name` BETWEEN ? AND ?  } params=[11, a, b]⬅️ ⬅️ ⬅️ ️
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `user` WHERE `id` BETWEEN ? AND ?  } params=[11, 144]⬅️ ⬅️ ⬅️ ️
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 1 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `profile` WHERE `info` = ? } params=[information]⬅️ ⬅️ ⬅️ ️
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `user` WHERE `name` = ? } params=[zcq]⬅️ ⬅️ ⬅️ ️
toJson=>{"__dc_as__":"id","__dc_output__":"`id`","addr0":"BeiJing0","name0":"BruceZCQ0","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b55447c {__dc_as__:id, __dc_output__:`id`, addr0:BeiJing0, name0:BruceZCQ0, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","addr1":"BeiJing1","__dc_output__":"`id`","name1":"BruceZCQ1","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b55446e {__dc_as__:id, addr1:BeiJing1, __dc_output__:`id`, name1:BruceZCQ1, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","__dc_output__":"`id`","addr2":"BeiJing2","name2":"BruceZCQ2","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b554470 {__dc_as__:id, __dc_output__:`id`, addr2:BeiJing2, name2:BruceZCQ2, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","name3":"BruceZCQ3","__dc_output__":"`id`","addr3":"BeiJing3","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b554476 {__dc_as__:id, name3:BruceZCQ3, __dc_output__:`id`, addr3:BeiJing3, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","name4":"BruceZCQ4","__dc_output__":"`id`","addr4":"BeiJing4","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b554474 {__dc_as__:id, name4:BruceZCQ4, __dc_output__:`id`, addr4:BeiJing4, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"name5":"BruceZCQ5","addr5":"BeiJing5","__dc_as__":"id","__dc_output__":"`id`","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b554476 {name5:BruceZCQ5, addr5:BeiJing5, __dc_as__:id, __dc_output__:`id`, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","name6":"BruceZCQ6","addr6":"BeiJing6","__dc_output__":"`id`","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b554480 {__dc_as__:id, name6:BruceZCQ6, addr6:BeiJing6, __dc_output__:`id`, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","addr7":"BeiJing7","__dc_output__":"`id`","name7":"BruceZCQ7","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b55447e {__dc_as__:id, addr7:BeiJing7, __dc_output__:`id`, name7:BruceZCQ7, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","addr8":"BeiJing8","__dc_output__":"`id`","name8":"BruceZCQ8","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b55447c {__dc_as__:id, addr8:BeiJing8, __dc_output__:`id`, name8:BruceZCQ8, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
toJson=>{"__dc_as__":"id","name9":"BruceZCQ9","__dc_output__":"`id`","addr9":"BeiJing9","__t__":"user","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.User@4b55446e {__dc_as__:id, name9:BruceZCQ9, __dc_output__:`id`, addr9:BeiJing9, __t__:user, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.User
QueryData Count=>120
SqlOutputColumn logic1=>id
SqlOutputColumn logic2=>id
SqlAS logic1=>cccccid
SqlAS logic2=>cccccid
invoke query SubSysName "Galaxy-Test-SubSystem😄"
️sql index 1 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` AS cccccid FROM `user` WHERE `name` = ? } params=[zcq]⬅️ ⬅️ ⬅️ ️
️sql index 0 Type 😋query😋 ➡️ ➡️ ➡️ ➡️ { SELECT `id` FROM `profile` WHERE `info` = ? } params=[information]⬅️ ⬅️ ⬅️ ️
toJson=>{"__dc_as__":"id","__dc_output__":"`id`","addr0":"BeiJing0","name0":"BruceZCQ0","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed269a {__dc_as__:id, __dc_output__:`id`, addr0:BeiJing0, name0:BruceZCQ0, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","addr1":"BeiJing1","__dc_output__":"`id`","name1":"BruceZCQ1","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed268c {__dc_as__:id, addr1:BeiJing1, __dc_output__:`id`, name1:BruceZCQ1, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","__dc_output__":"`id`","addr2":"BeiJing2","name2":"BruceZCQ2","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed268e {__dc_as__:id, __dc_output__:`id`, addr2:BeiJing2, name2:BruceZCQ2, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","name3":"BruceZCQ3","__dc_output__":"`id`","addr3":"BeiJing3","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed2694 {__dc_as__:id, name3:BruceZCQ3, __dc_output__:`id`, addr3:BeiJing3, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","name4":"BruceZCQ4","__dc_output__":"`id`","addr4":"BeiJing4","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed2692 {__dc_as__:id, name4:BruceZCQ4, __dc_output__:`id`, addr4:BeiJing4, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"name5":"BruceZCQ5","addr5":"BeiJing5","__dc_as__":"id","__dc_output__":"`id`","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed2694 {name5:BruceZCQ5, addr5:BeiJing5, __dc_as__:id, __dc_output__:`id`, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","name6":"BruceZCQ6","addr6":"BeiJing6","__dc_output__":"`id`","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed269e {__dc_as__:id, name6:BruceZCQ6, addr6:BeiJing6, __dc_output__:`id`, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","addr7":"BeiJing7","__dc_output__":"`id`","name7":"BruceZCQ7","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed269c {__dc_as__:id, addr7:BeiJing7, __dc_output__:`id`, name7:BruceZCQ7, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","addr8":"BeiJing8","__dc_output__":"`id`","name8":"BruceZCQ8","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed269a {__dc_as__:id, addr8:BeiJing8, __dc_output__:`id`, name8:BruceZCQ8, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
toJson=>{"__dc_as__":"id","name9":"BruceZCQ9","__dc_output__":"`id`","addr9":"BeiJing9","__t__":"profile","__dc_output_col__":"id"}
Model=>com.ikaihuo.monkey.model.Profile@2eed268c {__dc_as__:id, name9:BruceZCQ9, __dc_output__:`id`, addr9:BeiJing9, __t__:profile, __dc_output_col__:id}
Class=>class com.ikaihuo.monkey.model.Profile
QueryData Count=>120
```
