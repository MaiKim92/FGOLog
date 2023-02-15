package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyResponseDTO {

    private Long id;
    private String name;
    private List<ServantResponseDTO> servants;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServantResponseDTO> getServants() {
        return this.servants;
    }

    public void setServants(List<ServantResponseDTO> servants) {
        this.servants = servants;
    }

}
