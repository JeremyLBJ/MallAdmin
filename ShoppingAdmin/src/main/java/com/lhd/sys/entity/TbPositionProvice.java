package com.lhd.sys.entity;

public class TbPositionProvice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_position_provice.id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_position_provice.provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    private Integer proviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_position_provice.provice_name
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    private String proviceName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_position_provice.short_provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    private Long shortProviceId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_position_provice.id
     *
     * @return the value of tb_position_provice.id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_position_provice.id
     *
     * @param id the value for tb_position_provice.id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_position_provice.provice_id
     *
     * @return the value of tb_position_provice.provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public Integer getProviceId() {
        return proviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_position_provice.provice_id
     *
     * @param proviceId the value for tb_position_provice.provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public void setProviceId(Integer proviceId) {
        this.proviceId = proviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_position_provice.provice_name
     *
     * @return the value of tb_position_provice.provice_name
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public String getProviceName() {
        return proviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_position_provice.provice_name
     *
     * @param proviceName the value for tb_position_provice.provice_name
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public void setProviceName(String proviceName) {
        this.proviceName = proviceName == null ? null : proviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_position_provice.short_provice_id
     *
     * @return the value of tb_position_provice.short_provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public Long getShortProviceId() {
        return shortProviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_position_provice.short_provice_id
     *
     * @param shortProviceId the value for tb_position_provice.short_provice_id
     *
     * @mbg.generated Wed Jan 29 15:02:17 CST 2020
     */
    public void setShortProviceId(Long shortProviceId) {
        this.shortProviceId = shortProviceId;
    }
}