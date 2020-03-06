package com.lhd.sys.dao;

import com.lhd.sys.entity.SysRoleUser;
import com.lhd.sys.entity.SysRoleUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    long countByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    int deleteByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    int insert(SysRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    int insertSelective(SysRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    List<SysRoleUser> selectByExample(SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    int updateByExampleSelective(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_ role_user
     *
     * @mbg.generated Fri Feb 14 21:10:03 CST 2020
     */
    int updateByExample(@Param("record") SysRoleUser record, @Param("example") SysRoleUserExample example);
}