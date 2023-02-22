package com.kimmai.fgolog.business;

import com.kimmai.fgolog.web.rest.dto.PartyRequestDTO;
import com.kimmai.fgolog.web.rest.dto.PartyResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PartyBusiness {

    List<PartyResponseDTO> getAllParties();

    PartyResponseDTO create(PartyRequestDTO req);

    PartyResponseDTO update(Long id, PartyRequestDTO req);

    Optional<PartyResponseDTO> findOne(Long id);
}
