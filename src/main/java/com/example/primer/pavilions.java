package com.example.primer;

public class pavilions {

        int id_pavilion;
        String name_pavilion;

        public void setId_pavilion(int id_pavilion) {
            this.id_pavilion = id_pavilion;
        }

        public void setName_pavilion(String name_pavilion) {
            this.name_pavilion = name_pavilion;
        }

        public int getId_pavilion() {
            return id_pavilion;
        }

        public String getName_pavilion() {
            return name_pavilion;
        }

        public pavilions(int id_pavilion, String name_pavilion) {
            this.id_pavilion = id_pavilion;
            this.name_pavilion = name_pavilion;
        }
    }
