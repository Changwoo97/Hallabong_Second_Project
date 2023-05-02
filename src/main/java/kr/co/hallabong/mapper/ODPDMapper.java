package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.ODPDBean;

public interface ODPDMapper {
	@Select("WITH " + 
			"    odpd AS (SELECT d.reg_tm     AS reg_tm                          " + 
			"                  , d.dep_tm     AS dep_tm                          " +
			"                  , d.arr_tm     AS arr_tm                          " +
			"                  , d.no         AS dlvy_no                         " +
			"                  , d.ord_no     AS ord_no                          " +
			"                  , od.prod_no   AS prod_no                         " +
			"                  , p.name       AS prod_name                       " +
			"                  , od.prod_qnty AS prod_qnty                       " +
			"                  , d.send_name  AS send_name                       " +
			"                  , d.send_tel   AS send_tel                        " +
			"                  , d.send_addr  AS send_addr                       " +
			"                  , d.recv_name  AS recv_name                       " +
			"                  , d.recv_tel   AS recv_tel                        " +
			"                  , d.recv_addr  AS recv_addr                       " +
			"          FROM dlvy d INNER JOIN ord_dtl od ON d.ord_no = od.ord_no " +
			"                      INNER JOIN prod p     ON od.prod_no = p.no    " +
			"          WHERE sta = #{sta}                                        " +
			"          ORDER BY reg_tm DESC)                                     " + 
			"SELECT TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm                      " +
			"     , TO_CHAR(dep_tm, 'YYYY-MM-DD') AS dep_tm                      " + 
			"     , TO_CHAR(arr_tm, 'YYYY-MM-DD') AS arr_tm                      " +
			"     , dlvy_no                                                      " +
			"     , ord_no                                                       " +
			"     , prod_no                                                      " +
			"     , prod_name                                                    " +
			"     , prod_qnty                                                    " +
			"     , send_name                                                    " +
			"     , send_tel                                                     " + 
			"     , send_addr                                                    " +
			"     , recv_name                                                    " +
			"     , recv_tel                                                     " +
			"     , recv_addr                                                    " +
			"FROM odpd                                                           ")
	List<ODPDBean> selectODPDList(String sta);  
}
