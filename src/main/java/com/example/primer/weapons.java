package com.example.primer;

public class weapons {

    int id_weapon,id_pavilion,year_weapon,kalibr_weapon ;
    String name_weapon;

    public void setId_weapon(int id_weapon) {
        this.id_weapon = id_weapon;
    }

    public void setName_weapon(String nameWeapon) {
        this.name_weapon = nameWeapon;
    }

    public void setId_pavilion(int id_pavilion) {
        this.id_pavilion = id_pavilion;
    }

    public void setYear_weapon(int year_weapon) {
        this.year_weapon = year_weapon;
    }

    public void setKalibr_weapon(int kalibr_weapon) {
        this.kalibr_weapon = kalibr_weapon;
    }

    public int getId_weapon() {
        return id_weapon;
    }

    public int getId_pavilion() {
        return id_pavilion;
    }

    public String getName_weapon() {
        return name_weapon;
    }

    public int getYear_weapon() {
        return year_weapon;
    }

    public int getKalibr_weapon() {
        return kalibr_weapon;
    }

    public weapons(int id_weapon, int id_pavilion, String name_weapon, int year_weapon, int kalibr_weapon) {
        this.id_weapon = id_weapon;
        this.id_pavilion = id_pavilion;
        this.name_weapon = name_weapon;
        this.year_weapon = year_weapon;
        this.kalibr_weapon = kalibr_weapon;
    }
}
