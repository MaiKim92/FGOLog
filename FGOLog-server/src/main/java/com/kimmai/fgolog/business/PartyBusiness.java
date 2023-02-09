package com.kimmai.fgolog.business;

import com.kimmai.fgolog.service.dto.PartyDTO;
import com.kimmai.fgolog.service.dto.PartyResponseDTO;

import java.util.List;

public interface PartyBusiness {

    List<PartyResponseDTO> getAllParties();

}
