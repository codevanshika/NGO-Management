package in.co.nog.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.nog.mgt.bean.DonorMoneyBean;
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
public class DonorMoneyModel 
{
	private static Logger log = Logger.getLogger(DonorMoneyModel.class);

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
                    .prepareStatement("SELECT MAX(ID) FROM N_DonorMoney");
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
    public long add(DonorMoneyBean bean) throws ApplicationException,
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
                    .prepareStatement("INSERT INTO N_DonorMoney VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setLong(2,bean.getUserId());
            pstmt.setString(3, bean.getName());
            pstmt.setString(4, bean.getEmail());
            pstmt.setString(5, bean.getAccountNo());
            pstmt.setString(6, bean.getBankname()); 
            pstmt.setString(7, bean.getIFSCCode());
            pstmt.setString(8, bean.getAmount());
            pstmt.setString(9, bean.getCreatedBy());
            pstmt.setString(10, bean.getModifiedBy());
            pstmt.setTimestamp(11, bean.getCreatedDatetime());
            pstmt.setTimestamp(12, bean.getModifiedDatetime());
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
            throw new ApplicationException("Exception : Exception in add DonorMoney");
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
	    public void delete(DonorMoneyBean bean) throws ApplicationException {
	        log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM N_DonorMoney WHERE ID=?");
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
	                    "Exception : Exception in delete DonorMoney");
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
    public DonorMoneyBean findByName(String name) throws ApplicationException {
        log.debug("Model findBy EmailId Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM N_DonorMoney WHERE NAME=?");
        DonorMoneyBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new DonorMoneyBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setAccountNo(rs.getString(5));
                bean.setBankname(rs.getString(6));
                bean.setIFSCCode(rs.getString(7));
                bean.setAmount(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
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
    public DonorMoneyBean findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM N_DonorMoney WHERE ID=?");
        DonorMoneyBean bean = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new DonorMoneyBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setAccountNo(rs.getString(5));
                bean.setBankname(rs.getString(6));
                bean.setIFSCCode(rs.getString(7));
                bean.setAmount(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                
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
    public void update(DonorMoneyBean bean) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;
		
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE N_DonorMoney SET UserId=?, NAME=?,Email=?,AccountNo=?,BankName=?,IFSCCOde=?,amount=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
            pstmt.setLong(1,bean.getUserId());
            pstmt.setString(2, bean.getName());
            pstmt.setString(3, bean.getEmail());
            pstmt.setString(4, bean.getAccountNo());
            pstmt.setString(5, bean.getBankname()); 
            pstmt.setString(6, bean.getIFSCCode());
            pstmt.setString(7, bean.getAmount());
            pstmt.setString(8, bean.getCreatedBy());
            pstmt.setString(9, bean.getModifiedBy());
            pstmt.setTimestamp(10, bean.getCreatedDatetime());
            pstmt.setTimestamp(11, bean.getModifiedDatetime());
            pstmt.setLong(12, bean.getId());
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
            throw new ApplicationException("Exception in updating DonorMoney ");
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
    public List search(DonorMoneyBean bean) throws ApplicationException {
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
    public List search(DonorMoneyBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM N_DonorMoney WHERE 1=1");
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
                bean = new DonorMoneyBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setAccountNo(rs.getString(5));
                bean.setBankname(rs.getString(6));
                bean.setIFSCCode(rs.getString(7));
                bean.setAmount(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search DonorMoney");
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
        StringBuffer sql = new StringBuffer("select * from N_DonorMoney");
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
                DonorMoneyBean bean = new DonorMoneyBean();
                bean.setId(rs.getLong(1));
                bean.setUserId(rs.getLong(2));   
                bean.setName(rs.getString(3));
                bean.setEmail(rs.getString(4));
                bean.setAccountNo(rs.getString(5));
                bean.setBankname(rs.getString(6));
                bean.setIFSCCode(rs.getString(7));
                bean.setAmount(rs.getString(8));
                bean.setCreatedBy(rs.getString(9));
                bean.setModifiedBy(rs.getString(10));
                bean.setCreatedDatetime(rs.getTimestamp(11));
                bean.setModifiedDatetime(rs.getTimestamp(12));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of DonorMoney");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }
   
    
}
