package com.mytests.spring.springDataArraysTest;

import com.mytests.spring.springDataArraysTest.model.MyArrays;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * *
 * <p>Created by irina on 1/24/2024.</p>
 * <p>Project: spring-data-arrays</p>
 * *
 */
public interface MyArraysRepository extends CrudRepository<MyArrays, Integer> {

    List<MyArrays> findAll();

    @Query("select e from MyArrays e where e.strArraysOne = array() or e.strArraysTwo = array_list()")
    List<MyArrays> arrayTest();

    // would be nice to check: parameters type consistency (all have same type as 1st one) and column type matching
   // @Query("select e from MyArrays e where e.strArraysOne = array(1,'bbb','ccc') or e.strArraysTwo = array_list(1 ,'', 0) or e.datesArray = array(timestamp, current date, local date )")
    @Query("select e from MyArrays e where e.strArraysOne = array('aaa','bbb','ccc') or e.strArraysTwo = array_list('foo', 'bar') or e.datesArray = array(current date, cast(local date as date))")
    List<MyArrays> arrayWithArgTest();

    // https://youtrack.jetbrains.com/issue/IDEA-344367/JPA-QL-within-group-clause-is-not-parsed-after-arrayagg
    // 'within group' after array_agg() is not parsed:
    @Query("select array_agg(e.str) within group (order by e.str asc nulls last) from MyArrays e")
    List<String> testArrayAggWithinGroup();

    // with listagg - no errors:
    @Query("select listagg (e.str, ', ') within group (order by e.str asc nulls last) from MyArrays e")
    List<String> testListAggWithinGroup();

    // https://youtrack.jetbrains.com/issue/IDEA-344383/JPA-QL-distinct-inside-arrayagg-is-not-parsed
    // distinct inside array_agg() is not parsed
    @Query("select array_agg(distinct e.str) within group (order by e.str) from MyArrays e ")
    List<String> testArrayAggDistinct();

    // but this one is parsed properly:
    @Query("select listagg(distinct e.str, ', ') within group (order by e.str) from MyArrays e ")
    List<String> testListAggDistinct();

    @Query("select 'success!' where array('aaa','bbb','ccc') = any (select e.strArraysOne from MyArrays e)")
    List<String> testWhereInSelectClause();

    // https://youtrack.jetbrains.com/issue/IDEA-344385/JPA-QL-where-in-select-clause-is-not-parsed-when-from-is-missing
    // 'where' in 'select' clause is not parsed when 'from' is missing: compare this
    @Query("SELECT  'match!'  WHERE  ?1 = ANY (SELECT e.str FROM MyArrays e)")
    List<String> testWhereInSelectClause0(String arg);

    // and this (redundant `from` -> no errors)
    @Query("SELECT 'match!' from MyArrays ee WHERE  ?1 = ANY (SELECT e.str FROM MyArrays e)")
    List<String> testWhereInSelectClause1(String arg);

    // complex query: 2 errors described above
    @Query("select 1 where array('element one','element two', 'element three') is not distinct from (select array_agg(e.str) within group (order by e.str asc nulls last) from MyArrays e)")
    List<String> testArrayAggComplex();

    // 'null' is not parsed here
    @Query("from MyArrays e where array_position(e.strArraysOne, null) = :arg")
    // check the number of arguments in array_position(): should be 2 or 3
    // @Query("from MyArrays e where array_position(e.strArraysOne) = :arg")
    List<MyArrays> testArrayPosition(@Param("arg") int arg);

    @Query("select array_concat(array_positions(e.strArraysOne, 'aaa'), array_positions(e.strArraysOne, 'bbb')) from MyArrays e ")
    List<Integer> testArrayPositions();

    // check number of args for array_concat(): at least 2 are required
    // and the number of parameters for array_contains(): 2 params are required
    //@Query("select array_concat(array_positions(e.strArraysOne, 'aaa')) from MyArrays e where array_contains(array_concat(e.strArraysOne,e.strArraysTwo))")
    @Query("select array_concat(array_positions(e.strArraysOne, 'aaa'), array_positions(e.strArraysOne, 'bbb')) from MyArrays e where array_contains(array_concat(e.strArraysOne,e.strArraysTwo),'')")
    List<Integer> testArrayConcat();

    // for some reason the following queries don't work: to be investigated later
    // @Query("select e.id from MyArrays e where array_contains_nullable(:s, e.str)")
    // @Query("select e.id from MyArrays e where array_contains_nullable(e.strArraysOne, null)")
    // @Query("select e.id from MyArrays e where array_contains_nullable(new String[]{'aaa','bbb'}(), e.str)")

    @Query("select e.id from MyArrays e where array_contains_nullable(e.strArraysTwo, '')")
    List<Integer> testArrayContainsNullable(@Param("s") String[] s);

  @Query("select e from MyArrays e where array_contains(array_concat(e.strArraysOne, array('qqq','ttt', 'rrr'), e.strArraysTwo || null) || cast(array_get(e.datesArray,1) as string ), 'foo') ")
  List<MyArrays> testArrayConcat2();

  // https://youtrack.jetbrains.com/issue/IDEA-344361
  @Query("select array_prepend(null, e.strArraysOne) from MyArrays e where array_length(e.strArraysTwo)>2")
  List<String> testArrayPrepend();

  @Query("select array_fill(cast(null as String), 3)")
  List<MyArrays> testArrayFill0();

    @Query("select e from MyArrays e where array_overlaps_nullable(e.strArraysOne, array_fill(cast(null as String), 3))")
    List<MyArrays> testArrayFill1();

    @Query("select e from MyArrays e where array_contains(e.intArrays, array_fill(11,1))")
    List<MyArrays> testArrayContainsArray();

    // https://youtrack.jetbrains.com/issue/IDEA-344361
    @Query("select array_concat(e.strArraysOne, array(null,null,null)) from MyArrays e")
    List<String> testNulls();
}
