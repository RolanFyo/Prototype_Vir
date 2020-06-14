package com.baka.Main.data;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "product")
@Component
public class ProductEntity {

    @Id
    @Column
    long id;

    @Column
    String name;

    @Column (name = "license_cost")
    BigDecimal licenseCost;

    @Column (name = "data_amountGB")
    int dataAmount;

    @Column (name = "vm_ram_amountGB")
    int VMram;

    @Column (name = "session_count")
    int seessionCount;

    public ProductEntity(int id, String name, BigDecimal licenseCost, int dataAmount, int VMram, int seessionCount) {
        this.id = id;
        this.name = name;

        this.licenseCost = licenseCost;
        this.dataAmount = dataAmount;
        this.VMram = VMram;
        this.seessionCount = seessionCount;
    }

    public ProductEntity() {};

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", licenseCost=" + licenseCost +
                ", dataAmount=" + dataAmount +
                ", VMram=" + VMram +
                ", seessionCount=" + seessionCount +
                '}';
    }

    public String print() {
        return String.format("Virtuala masina '%s' : Licenses cena - %s, datu apjoms - %d GB, atminas apjoms - %d GB, sessiju skaits %d.", name, licenseCost, dataAmount, VMram, seessionCount);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLicenseCost(BigDecimal licenseCost) {
        this.licenseCost = licenseCost;
    }

    public void setDataAmount(int dataAmount) {
        this.dataAmount = dataAmount;
    }

    public void setVMram(int VMram) {
        this.VMram = VMram;
    }

    public void setSeessionCount(int seessionCount) {
        this.seessionCount = seessionCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLicenseCost() {
        return licenseCost;
    }

    public int getDataAmount() {
        return dataAmount;
    }

    public int getVMram() {
        return VMram;
    }

    public int getSeessionCount() {
        return seessionCount;
    }
}
