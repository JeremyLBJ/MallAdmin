package com.lhd.sys.dao;

import com.lhd.sys.entity.Imginfo;
import com.lhd.sys.entity.ImginfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImginfoMapper {
	
	List<Imginfo> findImgPaths ( Integer id ) ;
	
	void removeByCid ( @Param("cId") Integer cId ) ;
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    long countByExample(ImginfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int deleteByExample(ImginfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int insert(Imginfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int insertSelective(Imginfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    List<Imginfo> selectByExample(ImginfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    Imginfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int updateByExampleSelective(@Param("record") Imginfo record, @Param("example") ImginfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int updateByExample(@Param("record") Imginfo record, @Param("example") ImginfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int updateByPrimaryKeySelective(Imginfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imginfo
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    int updateByPrimaryKey(Imginfo record);
}