package com.kimmai.fgolog.web.rest.dto;

import com.kimmai.fgolog.service.dto.MysticCodeDTO;
import com.kimmai.fgolog.web.rest.dto.ServantResponseDTO.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartyResponseDTO {

    private Long id;
    private String name;

    private MysticCodeDTO mysticCode;
    private List<PartyMemberResponseDTO> servants;

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

    public MysticCodeDTO getMysticCode() {
        return this.mysticCode;
    }

    public void setMysticCode(MysticCodeDTO mysticCode) {
        this.mysticCode = mysticCode;
    }

    public List<ServantResponseDTO.PartyMemberResponseDTO> getServants() {
        return this.servants;
    }

    public void setServants(List<PartyMemberResponseDTO> servants) {
        this.servants = servants;
    }

}
