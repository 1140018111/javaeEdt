package com.generator.lottery.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * 库表设计  lotterySsq  表名
 * versionId  主键       redAreal  红区
 * blueAreal  蓝区    executeDate  开奖日期
 * salesAmount 销售金额   jackpot   奖池
 * inputDate   入库时间      flag   新旧标志（1最新0是历史）
 * reserve    备用字段1     spare   备用字段2
 * alternate  备用字段3    backup   备用字段4
 * @author dl
 */
@Component
@Scope("prototype")
public class LotterySsq {
    private String versionid;

    private String redareal;

    private String buleareal;

    private Date executedate;

    private String salesamount;

    private String jackpot;

    private Date inputdate;

    private String flag;

    private String reserve;

    private String spare;

    private String backup;

    public String getVersionid() {
        return versionid;
    }

    public void setVersionid(String versionid) {
        this.versionid = versionid == null ? null : versionid.trim();
    }

    public String getRedareal() {
        return redareal;
    }

    public void setRedareal(String redareal) {
        this.redareal = redareal == null ? null : redareal.trim();
    }

    public String getBuleareal() {
        return buleareal;
    }

    public void setBuleareal(String buleareal) {
        this.buleareal = buleareal == null ? null : buleareal.trim();
    }

    public Date getExecutedate() {
        return executedate;
    }

    public void setExecutedate(Date executedate) {
        this.executedate = executedate;
    }

    public String getSalesamount() {
        return salesamount;
    }

    public void setSalesamount(String salesamount) {
        this.salesamount = salesamount == null ? null : salesamount.trim();
    }

    public String getJackpot() {
        return jackpot;
    }

    public void setJackpot(String jackpot) {
        this.jackpot = jackpot == null ? null : jackpot.trim();
    }

    public Date getInputdate() {
        return inputdate;
    }

    public void setInputdate(Date inputdate) {
        this.inputdate = inputdate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    public String getSpare() {
        return spare;
    }

    public void setSpare(String spare) {
        this.spare = spare == null ? null : spare.trim();
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup == null ? null : backup.trim();
    }

	@Override
	public String toString() {
		return "LotterySsq{" +
					   "versionid='" + versionid + '\'' +
					   ", redareal='" + redareal + '\'' +
					   ", buleareal='" + buleareal + '\'' +
					   ", executedate=" + executedate +
					   ", salesamount='" + salesamount + '\'' +
					   ", jackpot='" + jackpot + '\'' +
					   ", inputdate=" + inputdate +
					   ", flag='" + flag + '\'' +
					   ", reserve='" + reserve + '\'' +
					   ", spare='" + spare + '\'' +
					   ", backup='" + backup + '\'' +
					   '}';
	}
}