package ru.makhach.vesselmanager.model.entity;

import ru.makhach.vesselmanager.model.base.abstr.CoordEntity;
import ru.makhach.vesselmanager.model.base.types.Status;
import ru.makhach.vesselmanager.model.base.types.Type;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vessel")
@SequenceGenerator(name = "default_seq", sequenceName = "vessel_id_seq")
public class Vessel extends CoordEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "year_built")
    private Integer yearBuilt;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "current_port_id")
    private Port currentPort;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "home_port_id")
    private Port homePort;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "dock_id")
    private Dock dock;

    public Vessel() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(Integer yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public Port getHomePort() {
        return homePort;
    }

    public void setHomePort(Port homePort) {
        this.homePort = homePort;
    }

    public Dock getDock() {
        return dock;
    }

    public void setDock(Dock dock) {
        this.dock = dock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vessel that = (Vessel) o;
        return id.equals(that.id) && name.equals(that.name) && status == that.status && type == that.type && Objects.equals(yearBuilt, that.yearBuilt) && Objects.equals(latitude, that.latitude) && Objects.equals(longitude, that.longitude) && Objects.equals(country, that.country) && Objects.equals(currentPort, that.currentPort) && Objects.equals(homePort, that.homePort) && Objects.equals(dock, that.dock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, type, yearBuilt, latitude, longitude, country, currentPort, homePort, dock);
    }

    @Override
    public String toString() {
        return "VesselEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", type=" + type +
                ", yearBuilt=" + yearBuilt +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country=" + country +
                ", currentPort=" + currentPort +
                ", homePort=" + homePort +
                ", dock=" + dock +
                '}';
    }
}
