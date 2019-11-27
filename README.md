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



## API

工具包提供的API较多，主要分为原生SQL与JPQL查询方法，并对其中主要的方法做说明

其中Spring data JPA提供的功能这里不再罗列



JPQL

| 方法                                                         | 说明 |
| ------------------------------------------------------------ | ---- |
| Page<T> findPageViewByJPQL(String jpql, int startPosition, int size, Map<String, Object> hits, Object... params) |      |
| <E> void executeUpdateByJPQL(String jpql, Map<String, Object> hits, Object... params) |      |
| <E> List<E> findAllByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params) |      |
| <E> E findSingleResultByJPQL(String jpql, Class<E> classType, Map<String, Object> hits, Object... params) |      |
| Long getCountByJPQL(String jqpl, Map<String, Object> hits, Object... params) |      |
| <E> List<E> findScrollResultByJPQL(String jpql, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params) |      |
| <E> Page<E> findPageViewByJPQL(String jpql, String totalJqpl, Class<E> classType, int startPosition, int size, Map<String, Object> hits, Object... params) |      |
|                                                              |      |

SQL

| 方法名                                                       | 说明 |
| ------------------------------------------------------------ | ---- |
| Page<T> findPageViewBySQL(String sql, int startPosition, int size, Object... params) |      |
| <E> void executeUpdateBySQL(String sql, Object... params)    |      |
| <E> List<E> findAllBySQL(String sql, Class<E> classType, Object... params) |      |
| <E> E findSingleResultBySQL(String sql, Class<E> classType, Object... params) |      |
| Long getCountBySQL(String sql, Object... params)             |      |
| <E> List<E> findScrollResultBySQL(String sql, Class<E> classType, int startPosition, int size, Map<String, Object> params) |      |
| <E> Page<E> findPageViewBySQL(String sql, Class<E> classType, int startPosition, int size, Object... params) |      |
| Page<?> findRawPageViewBySQL(String sql, String totalSql, int startPosition, int size, Object... params) |      |
|findAllBySQLSpefc||
|find(.+)BySQLSpefc||