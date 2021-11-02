package com.chekan;

import java.util.Objects;

public class Player {

    private String name;
    private char sign;
    private String status;

    public Player(String name, char sign) {
        this.name = name;
        this.sign = sign;
        this.status = "loser";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return sign == player.sign && name.equals(player.name) && status.equals(player.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sign, status);
    }

    @Override
    public String toString() {
        String res = name + "'s sign is " + sign + " and " + name + " is a " + status + " !";
        return res;
    }
}
