package com.dcits.springmvc.kernel.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.dcits.springmvc.base.exception.DaoException;

public abstract interface IDao {
	/***
	 * ��ȡSqlSessionTemplate����
	 * @return
	 */
	public abstract SqlSessionTemplate getSqlSessionTemplate();
	/***
	 * ��ȡSpring��JdbcTemplate����
	 * @return
	 */
	public abstract JdbcTemplate getJdbcTemplate();
	/***
	 * ����
	 * @param sqlId mybatis��sqlId
	 * @param param ��������
	 * @return
	 * @throws DaoException
	 */
	public abstract int insertBySqlId(String sqlId, Object param)	throws DaoException;
	/***
	 * ����
	 * @param sqlId mybatis��sqlId
	 * @param param ��������
	 * @return
	 * @throws DaoException
	 */
	public abstract int updateBySqlId(String sqlId, Object param) throws DaoException;
	/***
	 * ɾ��
	 * @param sqlId mybatis��sqlId
	 * @param param ��������
	 * @return
	 * @throws DaoException
	 */
	public abstract int deleteBySqlId(String sqlId, Object param)	throws DaoException;
	/***
	 * ����ID��ѯ
	 * @param sqlId mybatis��sqlId
	 * @param param ��������
	 * @param paramClass  ���ض�������
	 * @return
	 * @throws DaoException
	 */
	public abstract <T> T findBySqlId(String sqlId, Object param,	Class<T> paramClass) throws DaoException;
	/***
	 * ��ѯ����List
	 * @param sqlId mybatis��sqlId
	 * @param param ��������
	 * @param paramClass  List�ж��������
	 * @return
	 * @throws DaoException
	 */
	public abstract <T> List<T> findListBySqlId(String sqlId,Object param, Class<T> paramClass) throws DaoException;
	/***
	 * ��ҳ��ѯ����List
	 * @param sqlId
	 * @param param  ��������
	 * @param pageNo ҳ��
	 * @param pageSize ÿҳ����
	 * @param paramClass List�ж��������
	 * @return list��װ�Ķ���
	 * @throws DaoException
	 */
	public abstract <T> List<T> findListBySqlId(String sqlId,Object param, int pageNo, int pageSize,Class<T> paramClass) throws DaoException;
	/***
	 * ִ��SQL
	 * @param sql
	 * @param param �����б�
	 * @return
	 * @throws DaoException
	 */
	public abstract int executeBySql(String sql, Object... paramVarArgs) throws DaoException;
	/***
	 * ����SQL��ѯ����Map��װ�Ķ���
	 * @param sql
	 * @param param ����
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findBySql(String sql,Object... paramVarArgs) throws DaoException;
	/***
	 * ����SQL��ѯ����List��װ�Ķ���
	 * @param sql
	 * @param param ����
	 * @return
	 * @throws DaoException
	 */
	public abstract List<Map<String, Object>> findListBySql(String sql,Object... param) throws DaoException;
	/***
	 * ��ҳ����SQL��ѯ����List��װ�Ķ���
	 * @param sql
	 * @param paramVarArgs
	 * @return
	 * @throws DaoException
	 */
	public abstract List<Map<String, Object>> findListBySql(String sql,int pageNo, int pageSize, Object... param) throws DaoException;

	public abstract <T> T findByProc(String sql,CallableStatementCallback<T> callback) throws DaoException;

	public abstract <T> T executeByProc(String sql,CallableStatementCallback<T> callback)throws DaoException;
	/***
	 * ����sqlId��ҳ��ѯ����Map��װ�Ķ��� ����������
	 * @param sqlId
	 * @param param ��������
	 * @param pageNo ҳ��
	 * @param pageSize ÿҳ����
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findRecordsWithTotalBySqlId(String sqlId, Object param, int pageNo, int pageSize)	throws DaoException;
	/***
	 * ����sql��ҳ��ѯ����Map��װ�Ķ��� ����������
	 * @param sqlId
	 * @param param �������� һ���� new Object[]{} ���飬 �����з�װ��������
	 * @param pageNo ҳ��
	 * @param pageSize ÿҳ����
	 * @return
	 * @throws DaoException
	 */
	public abstract Map<String, Object> findRecordsWithTotalBySql(String sql, int pageNo, int pageSize,Object... param) throws DaoException;
	/***
	 * ����ִ��SQL
	 * @param paramList
	 * @param sql
	 * @throws DaoException
	 */
	public abstract void batchInsert(List<Object> paramList, String sql) throws DaoException;
}
