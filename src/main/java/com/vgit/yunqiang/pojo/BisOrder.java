package com.vgit.yunqiang.pojo;

import com.vgit.yunqiang.pojo.base.BasePojo;

import java.util.List;

/**
 * 订单
 */
public class BisOrder extends BasePojo {

    private Long id;

    private Long userId;

    private String orderSn;

    private Long stockId;

    private Integer status;

    private Double totalMoney;

    private Double totalVolume;

    private Long confirmTime;

    private Byte commentStatus;

    private Long commentTime;

    private Long shipTime;

    private Long finishedTime;

    private String digest;

    private Long lastCancelTime;

    /**
     * 订单明细
     */
    private List<BisOrderDetail> detailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(Double totalVolume) {
        this.totalVolume = totalVolume;
    }

    public Long getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Long confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public Long getShipTime() {
        return shipTime;
    }

    public void setShipTime(Long shipTime) {
        this.shipTime = shipTime;
    }

    public Long getFinishedTime() {
        return finishedTime;
    }

    public void setFinishedTime(Long finishedTime) {
        this.finishedTime = finishedTime;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public Long getLastCancelTime() {
        return lastCancelTime;
    }

    public void setLastCancelTime(Long lastCancelTime) {
        this.lastCancelTime = lastCancelTime;
    }

    public List<BisOrderDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<BisOrderDetail> detailList) {
        this.detailList = detailList;
    }
}
