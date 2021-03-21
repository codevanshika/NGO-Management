package in.co.nog.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import in.co.nog.mgt.bean.DonorThingsBean;
import in.co.nog.mgt.exception.ApplicationException;
import in.co.nog.mgt.exception.DatabaseException;
import in.co.nog.mgt.exception.DuplicateRecordException;
import in.co.nog.mgt.util.JDBCDataSource;



/**
 * JDBC Implementation of DonorMoney Model
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 */
public class DonorThingsModel 
{
	private static Logger log = Logger.getLogger(DonorThingsModel.class);

    /**
     * Find next PK of DonorMoney
     * 
     * @throws DatabaseException
     */
    public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM N_DonorThings");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
       log.debug("Model nextPK End");
        return pk + 1;
    }

    /**
     * Add a DonorMoney
     * 
     * @param bean
     * @throws DatabaseException
     * @throws ApplicationException
     * 
     * 
     */
    public long add(DonorThingsBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;
		
		
		  
		  try { conn = JDBCDataSource.getConnection();
		 
            pk = nextPK();

            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO N_DonorThings VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setLong(2,bean.getUserId());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getDescription());
            pstmt.setString(6, bean.getCreatedBy());
            pstmt.setString(7, bean.getModifiedBy());
            pstmt.setTimestamp(8, bean.getCreatedDatetime());
            pstmt.setTimestamp(9, bean.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add DonorThings");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a DonorMoney
     * 
     * @param bean
     * @throws DatabaseException
     * @throws ApplicationException
     */
	    public void delete(DonorThingsBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM N_DonorThings WHERE ID=?");
	            pstmt.setLong(1, bean.getId());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	          //  log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in delete DonorThings");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model delete Started");
	    }

    /**
     * Find User by DonorMoney
     * 
     * @param name
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public DonorThingsBean findByName(String name) throws ApplicationException {
        log.debug("Model findBy EmailId Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM N_DonorThings WHERE NAME=?");
        DonorThingsBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new DonorThingsBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setDescription(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by emailId");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy EmailId End");
        return bean;
    }

    /**
     * Find DonorMoney by PK
     * 
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public DonorThingsBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM N_DonorThings WHERE ID=?");
        DonorThingsBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new DonorThingsBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setDescription(rs.getString(5));
               
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return bean;
    }

    /**
     * Update a DonorMoney
     * 
     * @param bean
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public void update(DonorThingsBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;
		
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE N_DonorThings SET UserId=?, NAME=?,Email=?,Description=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
            pstmt.setLong(1,bean.getUserId());
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getEmail());
            pstmt.setString(4, bean.getDescription());
            pstmt.setString(5, bean.getCreatedBy());
            pstmt.setString(6, bean.getModifiedBy());
            pstmt.setTimestamp(7, bean.getCreatedDatetime());
            pstmt.setTimestamp(8, bean.getModifiedDatetime());
            pstmt.setLong(9, bean.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating DonorThings ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    /**
     * Search DonorMoney
     * 
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List search(DonorThingsBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

    /**
     * Search DonorMoney with pagination
     * 
     * @return list : List of DonorMoneys
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List search(DonorThingsBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM N_DonorThings WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME LIKE '" + bean.getName() + "%'");
            }
          
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new DonorThingsBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setDescription(rs.getString(5));
                
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search DonorThings");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model search End");
        return list;
    }

    /**
     * Get List of DonorMoney
     * 
     * @return list : List of DonorMoney
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of DonorMoney with pagination
     * 
     * @return list : List of DonorMoney
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from N_DonorThings");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DonorThingsBean bean = new DonorThingsBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setDescription(rs.getString(5));
                bean.setCreatedBy(rs.getString(6));
                bean.setModifiedBy(rs.getString(7));
                bean.setCreatedDatetime(rs.getTimestamp(8));
                bean.setModifiedDatetime(rs.getTimestamp(9));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of DonorThings");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }
   
    
}
