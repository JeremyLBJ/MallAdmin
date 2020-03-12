package com.lhd.sys.dao;

import com.lhd.sys.entity.CartsTable;
import com.lhd.sys.entity.CartsTableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartsTableMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    long countByExample(CartsTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int deleteByExample(CartsTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int insert(CartsTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int insertSelective(CartsTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    List<CartsTable> selectByExample(CartsTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    CartsTable selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") CartsTable record, @Param("example") CartsTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int updateByExample(@Param("record") CartsTable record, @Param("example") CartsTableExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int updateByPrimaryKeySelective(CartsTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table carts_table
     *
     * @mbg.generated Sat Nov 16 18:45:00 CST 2019
     */
    int updateByPrimaryKey(CartsTable record);
}