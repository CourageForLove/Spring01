package com.ChIoe.Repository;

import com.ChIoe.JPAEntity.News;
import org.springframework.data.jpa.repository.JpaRepository;
/*
*
参数位置	类型	说明
第一个泛型参数	News	实体类：要操作的数据库表对应的实体类
第二个泛型参数	Integer	主键类型：实体类主键字段的数据类型
* */
public interface NewsRepository extends JpaRepository<News,Integer> {
  /*
  *  提供对 News 实体的 CRUD 操作
    Spring Data JPA 会自动生成实现类*/

}
