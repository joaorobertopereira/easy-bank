package br.com.helpcsistemas.easybank.model.enums;

public enum Authority {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private final Integer cod;
    private final String name;

    private Authority(Integer cod, String name) {
        this.cod = cod;
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public String getName() {
        return name;
    }

    public static Authority toEnum(Integer cod) {
        if (cod == null) { return null; }

        for (Authority x : Authority.values()) {
            if (cod.equals(x.getCod())) { return x; }
        }

        throw new IllegalArgumentException("Id invalid: " + cod);
    }

}
