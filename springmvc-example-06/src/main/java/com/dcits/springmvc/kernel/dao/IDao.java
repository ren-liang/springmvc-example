package com.dcits.springmvc.kernel.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dcits.springmvc.base.exception.DaoException;

public abstract interface IDao {
	/***
	 * 获取SqlSessionTemplate对象
	 * @return
	 */
	public abstract SqlSessionTemplate getSqlSessionTemplate();
	/***
	 * 获取Spring的JdbcTemplate对象
	 * @return
	 */
	public abstract JdbcTemplate getJdbcTemplate();
	/***
	 * 新增
	 * @param sqlId mybatis的sqlId
	 * @param param 参数对象
	 * @return
	 * @throws DaoException
	 */
	public abstract int insertBySqlId(String sqlId, Object param)	throws DaoException;
	/***
	 * 更新
	 * @param sqlId mybatis的sqlId
	 * @param param 参数对象
	 * @return
	 * @throws DaoException
	 */
	public abstract int updateBySqlId(String sqlId, Object param) throws DaoException;
	/***
	 * 删除
	 * @param sqlId mybatis的sqlId
	 * @param param 参数对象
	 * @return
	 * @throws DaoException
	 */
	public abstract int deleteBySqlId(String sqlId, Object param)	throws DaoException;
	/***
	 * 根据ID查询
	 * @param sqlId mybatis的sqlId
	 * @param param 参数对象
	 * @param paramClass  返回对象类型
	 * @return
	 * @throws DaoException
	 */
	public abstract <T> T findBySqlId(String sqlId, Object param,	Class<T> paramClass) throws DaoException;
	/***
	 * 查询返回List
	 * @param sqlId mybatis的sqlId
	 * @param param 参数对象
	 * @param paramClass  List中对象的类型
	 * @return
	 * @throws DaoException
	 */
	public abstract <T> List<T> findListBySqlId(String sqlId,Object param, Class<T> paramClass) throws DaoException;
	/***
	 * 分页查询返回List
	 * @param sqlId
	 * @param param  参数对象
	 * @param pageNo 页码
	 * @param pageSize 每页数量
	 * @param paramClass List中对象的类型
	 * @return list封装的对象
	 * @throws DaoException
	 */
	public abstract <T> List<T> findListBySqlId(String sqlId,Object param, int pageNo, int pageSize,Class<T> paramClass) throws DaoException;
	/***
	 * 执行SQL
	 * @param sql
	 * @param param 参数列表
	 * @return
	 * @throws DaoException
	 */
	public abstract int executeBySql(String sql, Object... paramVarArgs) throws DaoException;
	/***
	 * 根据SQL查询返回Map封装的对象
	 * @param sql
	 * @param param 参数
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findBySql(String sql,Object... paramVarArgs) throws DaoException;
	/***
	 * 根据SQL查询返回List封装的对象
	 * @param sql
	 * @param param 参数
	 * @return
	 * @throws DaoException
	 */
	public abstract List<Map<String, Object>> findListBySql(String sql,Object... param) throws DaoException;
	/***
	 * 分页根据SQL查询返回List封装的对象
	 * @param sql
	 * @param paramVarArgs
	 * @return
	 * @throws DaoException
	 */
	public abstract List<Map<String, Object>> findListBySql(String sql,int pageNo, int pageSize, Object... param) throws DaoException;

	public abstract <T> T findByProc(String sql,CallableStatementCallback<T> callback) throws DaoException;

	public abstract <T> T executeByProc(String sql,CallableStatementCallback<T> callback)throws DaoException;
	/***
	 * 根据sqlId分页查询返回Map封装的对象 包含总行数
	 * @param sqlId
	 * @param param 参数对象
	 * @param pageNo 页码
	 * @param pageSize 每页行数
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findRecordsWithTotalBySqlId(String sqlId, Object param, int pageNo, int pageSize)	throws DaoException;
	/***
	 * 根据sql分页查询返回Map封装的对象 包含总行数
	 * @param sqlId
	 * @param param 参数对象 一般是 new Object[]{} 数组， 数组中封装基本类型
	 * @param pageNo 页码
	 * @param pageSize 每页行数
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findRecordsWithTotalBySql(String sql, int pageNo, int pageSize,Object... param) throws DaoException;
	/***
	 * 批量执行SQL
	 * @param paramList
	 * @param sql
	 * @throws DaoException
	 */
	public abstract void batchInsert(List<Object> paramList, String sql) throws DaoException;
}
