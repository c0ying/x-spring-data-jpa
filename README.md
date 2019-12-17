# Spring data jpa 功能拓展工具包



## 概述

JPA 是 java 官方的持久化层统计API。其中Hibernate, Eclipse Link都已实现支持。
Spring data Jpa提供对JPA功能的封装简化调用的处理。但是在部分使用场景调用还是较为繁琐，

工具包特性

- 常用方法的封装
- 对JPA动态查询调用进行简化
- 原生SQL动态查询简化，无需手动拼接字符串
- 数据库原生SQL生成工具（基于Druid 语法解析器。）
- 完全兼容Spring data JPA原有特性
- 无需额外编写实现类，只需创建实体对应的实体查询接口，加快开发速度

## Quick Started

* Spring Data Jpa 特性使用

  ```java
  interface PersonRepository extends Repository<Person, Long> {
    List<Person> findByLastname(String lastname);
  }
  ```

* 动态JPQL

  ```java
  SpecificationBuilder<T> spcfiBuilder = SpecificationBuilder.getInstance(T.class);
  spcfiBuilder.like("name", "name");
  spcfiBuilder.eq("no", "no");
  dao.findAll(spcfiBuilder.build());
  ```

* 动态SQL

  ```java
  //普通查询
  SQLSpecificationBuilder spcfiBuilder =SQLBaseSearchSpecification.getInstance();
  spcfiBuilder.eq("no", "no");
  dao.findAllBySQLSpefc(spcfiBuilder.build());
  //分页查询
  Sort sort = new Sort(new Order(Direction.DESC, "creatTime");
  dao.findAllBySQLSpefc(spcfiBuilder.build(), sort);
  ```

* 直接调用对应查询语句

  ```java
  //JPQL查询语句
  List<T> datas = dao.findAllByJPQL("FROM XX WHERE id = ?1 AND (state = -1 OR state = -98) ",T.class, 2); //参数:JPQL,返回类型，JPQL参数
  //SQL查询语句
  dao.findAllBySQL("select * from XX where id = ?", 1);
  ```

### Specification指令

## API

工具包提供的API较多，主要分为原生SQL与JPQL查询方法，并对其中主要的方法做说明

其中Spring data JPA提供的功能这里不再罗列



JPQL

| 方法                                                         | 说明                                         |
| ------------------------------------------------------------ | -------------------------------------------- |
| Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Map<String, Object> hits, Object... params) | 根据JPQL分页查询                             |
| <E> void executeUpdateByJPQL(String jpql, Map<String, Object> hits, Object... params) | 执行更新操作的JPQL                           |
| <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params) | 根据JPQL查询列表，并指定返回类型             |
| <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params) | 根据JPQL查询单个结果，并指定返回类型         |
| Long getCountByJPQL(String jqpl, Map<String, Object> hits, Object... params) | 根据JPQL计算总数                             |
| <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params) | 根据JPQL查询列表，滚动指定页数并指定返回类型 |
| <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params) | 根据JPQL分页查询，可指定计算总数JPQL         |
|                                                              |                                              |

SQL

| 方法名                                                       | 说明 |
| ------------------------------------------------------------ | ---- |
| Page<T> findPageViewBySQL(String sql, int startPosition, int size, Object... params) | 根据SQL分页查询 |
| <E> void executeUpdateBySQL(String sql, Object... params)    | 执行更新操作的SQL |
| <E> List<E> findAllBySQL(String sql, Class<E> classType, Object... params) | 根据SQL查询列表，并指定返回类型 |
| <E> E findSingleResultBySQL(String sql, Class<E> classType, Object... params) | 根据SQL查询单个结果，并指定返回类型 |
| Long getCountBySQL(String sql, Object... params)             | 根据SQL计算总数 |
| <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params) | 根据SQL查询列表，滚动指定页数并指定返回类型 |
| <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params) | 根据SQL分页查询,并指定返回类型 |
| Page<?> findRawPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params) | 根据SQL分页查询,指定返回类型为Object |
|findAllBySQLSpefc|根据动态语句API自动生成查询|
|find(.+)BySQLSpefc|其他根据动态语句API自动生成查询列表，单个结果或分页查询|