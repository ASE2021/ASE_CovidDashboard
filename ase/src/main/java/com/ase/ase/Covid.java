package com.ase.ase;

public class Covid {
    private int AgeGroupID;
    private String AgeGroup;
    private String Province;
    private int ProvinceID;
    private int Population;
    private String Sex;
    private int Count;
    private int Cured;
    private int Death;

    public Covid(int ageGroupID, String ageGroup, String province, int provinceID, int population, String sex, int count, int cured, int death) {
        AgeGroupID = ageGroupID;
        AgeGroup = ageGroup;
        Province = province;
        ProvinceID = provinceID;
        Population = population;
        Sex = sex;
        Count = count;
        Cured = cured;
        Death = death;
    }

    public int getAgeGroupID() {
        return AgeGroupID;
    }

    public void setAgeGroupID(int ageGroupID) {
        AgeGroupID = ageGroupID;
    }

    public String getAgeGroup() {
        return AgeGroup;
    }

    public void setAgeGroup(String ageGroup) {
        AgeGroup = ageGroup;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getCured() {
        return Cured;
    }

    public void setCured(int cured) {
        Cured = cured;
    }

    public int getDeath() {
        return Death;
    }

    public void setDeath(int death) {
        Death = death;
    }
}
