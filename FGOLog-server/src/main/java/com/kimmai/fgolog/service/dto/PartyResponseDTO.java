package com.kimmai.fgolog.service.dto;

import java.util.List;

public class PartyResponseDTO {

    private String name;
    private List<ServantDTO> servants;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServantDTO> getServants() {
        return this.servants;
    }

    public void setServants(List<ServantDTO> servants) {
        this.servants = servants;
    }

}
