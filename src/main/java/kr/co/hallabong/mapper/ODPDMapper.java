package kr.co.hallabong.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.co.hallabong.bean.join.ODPDBean;

public interface ODPDMapper {
	@Select("WITH\n" + 
			"    odpd AS (SELECT d.reg_tm\n" + 
			"                  , d.dep_tm\n" +
			"                  , d.arr_tm\n" +
			"                  , d.no         AS dlvy_no\n" +
			"                  , d.ord_no\n" +
			"                  , od.prod_no\n" +
			"                  , p.name       AS prod_name\n" +
			"                  , od.prod_qnty\n" +
			"                  , d.send_name\n" +
			"                  , d.send_tel\n" +
			"                  , d.send_addr\n" +
			"                  , d.recv_name\n" +
			"                  , d.recv_tel\n" +
			"                  , d.recv_addr\n" +
			"          FROM dlvy d INNER JOIN ord_dtl od ON d.ord_no = od.ord_no\n" +
			"                      INNER JOIN prod p     ON od.prod_no = p.no\n" +
			"          WHERE d.sta = #{sta}\n" +
			"          ORDER BY reg_tm DESC)\n" + 
			"SELECT TO_CHAR(reg_tm, 'YYYY-MM-DD') AS reg_tm\n" +
			"     , TO_CHAR(dep_tm, 'YYYY-MM-DD') AS dep_tm\n" + 
			"     , TO_CHAR(arr_tm, 'YYYY-MM-DD') AS arr_tm\n" +
			"     , dlvy_no\n" +
			"     , ord_no\n" +
			"     , prod_no\n" +
			"     , prod_name\n" +
			"     , prod_qnty\n" +
			"     , send_name\n" +
			"     , send_tel\n" + 
			"     , send_addr\n" +
			"     , recv_name\n" +
			"     , recv_tel\n" +
			"     , recv_addr\n" +
			"FROM odpd ")
	List<ODPDBean> selectODPDList(String sta);  
}
