package com.dcits.springmvc.kernel.dao.impl;


import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dcits.springmvc.base.exception.DaoException;
import com.dcits.springmvc.kernel.dao.IDao;
import com.dcits.springmvc.kernel.dao.model.RecordCount;

public class BaseDao implements IDao {
	private Logger logger = Logger.getLogger(BaseDao.class);
	private SqlSessionTemplate sqlSessionTemplate;
	private JdbcTemplate jdbcTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public SqlSession getBatchSqlSession() {
		return this.sqlSessionTemplate.getSqlSessionFactory().openSession(
				ExecutorType.BATCH, false);
	}

	@Override
	public int insertBySqlId(String sqlId, Object param) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	      }
	      int res = this.sqlSessionTemplate.insert(sqlId, param);
	      return res;
	}

	@Override
	public int updateBySqlId(String sqlId, Object param) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	      }
	      int res = this.sqlSessionTemplate.update(sqlId, param);
	      return res;
	}

	@Override
	public int deleteBySqlId(String sqlId, Object param) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	      }
	      int res = this.sqlSessionTemplate.delete(sqlId, param);
	      return res;
	}

	@Override
	public <T> T findBySqlId(String sqlId, Object param, Class<T> paramClass) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	    }
	    return this.sqlSessionTemplate.selectOne(sqlId, param);
	}

	@Override
	public <T> List<T> findListBySqlId(String sqlId, Object param, Class<T> paramClass) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	    }
	    List<T> list = this.sqlSessionTemplate.selectList(sqlId, param);
	    return list;
	}

	@Override
	public <T> List<T> findListBySqlId(String sqlId, Object param,int pageNo, int pageSize, Class<T> paramClass) throws DaoException {
	    if (StringUtils.isBlank(sqlId)) {
	        throw new DaoException("参数sqlId不允许为空");
	    }
	    int offset = (pageNo - 1) * pageSize;
	    List<T> list = this.sqlSessionTemplate.selectList(sqlId, param, new RowBounds(offset, pageSize));
	    return list;
	}

	@Override
	public int executeBySql(String sql, Object... params)	throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		return this.jdbcTemplate.update(sql, params);
	}

	@Override
	public Map<String, Object> findBySql(String sql, Object... params) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,params);
		Map<String, Object> map = null;
		if (list.size() > 0) {
			map = (Map<String, Object>) list.get(0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> findListBySql(String sql,Object... params) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql,params);
		return list;
	}

	@Override
	public List<Map<String, Object>> findListBySql(String sql, int pageNo,int pageSize, Object... params) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		int offset = (pageNo - 1) * pageSize;
		String newSql = this.getLimitString(sql, offset, pageSize);
		Object[] newParams = setParameters(params, pageNo, pageSize);
		return this.jdbcTemplate.queryForList(newSql, newParams);
	}

	@Override
	public <T> T findByProc(String sql, CallableStatementCallback<T> callback) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		if (callback == null) {
			throw new DaoException("参数callback不允许为null");
		}
		return this.jdbcTemplate.execute(sql, callback);
	}

	@Override
	public <T> T executeByProc(String sql, CallableStatementCallback<T> callback) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		if (callback == null) {
			throw new DaoException("参数callback不允许为null");
		}
		return this.jdbcTemplate.execute(sql, callback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> findRecordsWithTotalBySqlId(String sqlId,Object param, int pageNo, int pageSize) throws DaoException {
		if (StringUtils.isBlank(sqlId)) {
			throw new DaoException("参数sqlId不允许为空");
		}
		int offset = (pageNo - 1) * pageSize;
		List<Object> list = this.sqlSessionTemplate.selectList(sqlId, param,new RowBounds(offset, pageSize));
		RecordCount rc = null;
		if ((param instanceof Map)) {
			rc = (RecordCount) ((Map<String, Object>) param).get("RECORD_COUNT_KEY");
		} else {
			Field[] fields = param.getClass().getDeclaredFields();
			Field countField = null;
			for (Field field : fields) {
				if (field.getType().isAssignableFrom(RecordCount.class)) {
					countField = field;
					break;
				}
			}
			if (countField != null) {
				countField.setAccessible(true);
				try {
					rc = (RecordCount) countField.get(param);
				} catch (IllegalArgumentException e) {
					this.logger.error("参数对象中没有该成员变量", e);
				} catch (IllegalAccessException e) {
					this.logger.error("无权访问该成员变量", e);
				}
			}
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("total",Integer.valueOf(rc != null ? rc.getTotal() : list.size()));
		resMap.put("rows", list);
		return resMap;
	}

	@Override
	public Map<String, Object> findRecordsWithTotalBySql(String sql,int pageNo, int pageSize, Object... params) throws DaoException {
		if (StringUtils.isBlank(sql)) {
			throw new DaoException("参数sql不允许为空");
		}
		String countSql = this.getCountString(sql);
		Map<String, Object> map = this.jdbcTemplate.queryForMap(countSql,params);
		Object num = map.get("n$$");
		int offset = (pageNo - 1) * pageSize;
		String limitSql = this.getLimitString(sql, offset, pageSize);
		Object[] newParams = setParameters(params, pageNo, pageSize);
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
				limitSql, newParams);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("total", num);
		resMap.put("rows", list);
		return resMap;
	}

	@Override
	public void batchInsert(List<Object> list, String sql) throws DaoException {
		SqlSession sqlSession = getBatchSqlSession();
		int batchCount = 1000;
		try {
			for (int i = 0; i < list.size(); i++) {
				if (list.size() < batchCount) {
					batchCount = list.size();
					sqlSession.insert(sql, list.subList(i, batchCount));
					sqlSession.commit();
					sqlSession.clearCache();
					break;
				}
				sqlSession.insert(sql, list.subList(i, batchCount));
				sqlSession.commit();
				sqlSession.clearCache();
				i = batchCount;
				batchCount = i + batchCount;
			}
		} catch (Exception e) {
			this.logger.error("业务发生异常", e);

			throw new DaoException(e);
		} finally {
			sqlSession.close();
		}
	}
	/***
	 * 获取分页SQL
	 * @param sql
	 * @param offset
	 * @param limit
	 * @return
	 */
	private String getLimitString(String sql, int offset, int limit) {
		StringBuilder str = new StringBuilder();
		str.append(sql).append(" limit ? , ?");
		return str.toString();
	}
	/***
	 * 设置参数
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	private Object[] setParameters(Object[] params, int pageNo, int pageSize) {
		if (params == null) {
			params = new Object[0];
		}
		int length = params.length;
		Object[] newParams = Arrays.copyOf(params, length + 2);
		int offset = (pageNo - 1) * pageSize;
		newParams[length] = Integer.valueOf(offset);
		newParams[(length + 1)] = Integer.valueOf(pageSize);
		return newParams;
	}
	/***
	 * 获取查询总行数SQL
	 * @param sql
	 * @return
	 */
	private String getCountString(String sql) {
		return "SELECT COUNT(*) n$$ FROM (" + sql + ") aliasForTable";
	}
}
